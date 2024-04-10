package visual;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logico.*;

public class ListadoTrabajador extends JDialog {

    private DefaultTableModel model;
    private JComboBox<String> comboBox;
    private JJDCommunications jjdCommunications;

    public static void main(String[] args) {
        try {
            ListadoTrabajador dialog = new ListadoTrabajador();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListadoTrabajador() {
    	setTitle("Listado De Trabajador");
        setSize(900, 500); // Tamaño ajustado para que quepa todo el contenido
        setLocationRelativeTo(null);

        jjdCommunications = JJDCommunications.getInstance();

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));
        contentPanel.add(panel, BorderLayout.NORTH);

        JLabel lblTipoDeTrabajador = new JLabel("Tipo de Trabajador:");
        panel.add(lblTipoDeTrabajador);

        comboBox = new JComboBox<>();
        comboBox.addActionListener(e -> loadTrabajadores(comboBox.getSelectedIndex()));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"<Todos>", "Jefe de Proyecto", "Diseñador", "Programador", "Planificador"}));
        panel.add(comboBox);
        

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout(0, 0));
        contentPanel.add(tablePanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane();
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        String[] headers = {"ID", "Nombre", "Apellido", "Dirección", "Sexo", "Edad", "Salario por Hora", "Tipo de Trabajador", "Evaluación"};

        JTable table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);
        table.setModel(model);

     // Ajuste del ancho de las columnas y permitir redimensionarlas
        int[] columnWidths = {70, 100, 100, 150, 30, 30, 90, 120, 80}; // Ancho de las columnas
        for (int i = 0; i < headers.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        // Alinear texto en las celdas a la izquierda
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        for (int i = 0; i < headers.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Usar FlowLayout con alineación centrada
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el botón de detalles al panel de botones
        JButton btnDetalles = new JButton("Detalle de Trabajador");
        buttonPanel.add(btnDetalles);

        // Agregar el botón de eliminar al panel de botones
        JButton btnEliminar = new JButton("Eliminar");
        buttonPanel.add(btnEliminar);



        // Acción para eliminar un trabajador
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int option = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este trabajador?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        String id = (String) model.getValueAt(selectedRow, 0);
                        jjdCommunications.eliminarTrabajador(id);
                        loadTrabajadores(comboBox.getSelectedIndex());
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una opcion");
                }
            }
        });

        
        // Acción para ver detalles de un trabajador
        btnDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String idTrabajador = (String) model.getValueAt(selectedRow, 0);
                    DetallesTrabajador detallesTrabajador = new DetallesTrabajador(idTrabajador);
                    detallesTrabajador.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una opcion");
                }
            }
        });

        // Cargar trabajadores al iniciar la ventana
        loadTrabajadores(0);
    }

    private void loadTrabajadores(int index) {
        // Limpiar modelo de tabla antes de cargar nuevos datos
        model.setRowCount(0);

        // Cargar datos de trabajadores según el tipo seleccionado
        for (Trabajador trabajador : jjdCommunications.getListaTrabajadores()) {
            boolean agregar = false;
            switch (index) {
                case 0: // Todos
                    agregar = true;
                    break;
                case 1: // Jefe de Proyecto
                    agregar = trabajador instanceof JefeProyecto;
                    break;
                case 2: // Diseñador
                    agregar = trabajador instanceof Disenador;
                    break;
                case 3: // Programador
                    agregar = trabajador instanceof Programador;
                    break;
                case 4: // Planificador
                    agregar = trabajador instanceof Planificador;
                    break;
            }
            if (agregar) {
                // Obtener el tipo de trabajador
                String tipoTrabajador = "";
                if (trabajador instanceof JefeProyecto) {
                    tipoTrabajador = "Jefe de Proyecto";
                } else if (trabajador instanceof Disenador) {
                    tipoTrabajador = "Diseñador";
                } else if (trabajador instanceof Programador) {
                    tipoTrabajador = "Programador";
                } else if (trabajador instanceof Planificador) {
                    tipoTrabajador = "Planificador";
                }

                // Agregar fila con los datos del trabajador, incluyendo el apellido
                model.addRow(new Object[]{
                    trabajador.getId(),
                    trabajador.getNombre(),
                    trabajador.getApellido(), // Aquí se agrega el apellido
                    trabajador.getDireccion(),
                    trabajador.getSexo(),
                    trabajador.getEdad(),
                    trabajador.getSalarioHora(),
                    tipoTrabajador,
                    trabajador.getEvaluacion()
                });
            }
        }
    }

}