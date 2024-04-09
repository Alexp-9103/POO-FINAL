package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Proyecto;
import logico.JJDCommunications;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListadoProyecto extends JDialog {

    private DefaultTableModel model;
    private JComboBox<String> comboBox;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListadoProyecto dialog = new ListadoProyecto();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Create the dialog.
     */
    public ListadoProyecto() {
    	setTitle("Listado De Proyecto");
        setSize(841, 400);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));
        
        
        JLabel lblEstadoDelProyecto = new JLabel("Estado del Proyecto:");
        lblEstadoDelProyecto.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(lblEstadoDelProyecto);

        comboBox = new JComboBox<>();
        comboBox.addActionListener(e -> loadProyectos(comboBox.getSelectedIndex()));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"<Todos>", "En Progreso", "Finalizado", "Pendiente"}));
        panel.add(comboBox);

        JPanel buttonPanel = new JPanel();
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton btnVerDetalles = new JButton("Ver Detalles");
        btnVerDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ListadoProyecto.this, "Debe seleccionar un proyecto de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Obtener el nombre del proyecto seleccionado
                    String nombreProyecto = (String) table.getValueAt(selectedRow, 0);
                    // Llamar al método para abrir la interfaz de detalles del proyecto
                    abrirDetallesProyecto(nombreProyecto);
                }
            }
        });
        buttonPanel.add(btnVerDetalles);




        JButton btnEliminarProyecto = new JButton("Eliminar Proyecto");
        btnEliminarProyecto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ListadoProyecto.this, "Debe seleccionar un proyecto de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(ListadoProyecto.this, "¿Está seguro que desea eliminar el proyecto?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Obtener el ID del proyecto seleccionado en la tabla
                        String idProyecto = (String) model.getValueAt(selectedRow, 0);
                        // Eliminar el proyecto utilizando el método eliminarProyecto de JJDCommunications
                        JJDCommunications.getInstance().eliminarProyecto(idProyecto);
                        // Actualizar la tabla
                        loadProyectos(comboBox.getSelectedIndex());
                    }
                }
            }
        });
        buttonPanel.add(btnEliminarProyecto);


        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        tablePanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        String[] headers = {"Nombre", "ID Cliente", "Cantidad de Trabajadores", "Contrato Activo"};

        JTable table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);
        table.setModel(model);

        loadProyectos(0); // Cargar todos los proyectos al inicio
    }

    private void loadProyectos(int index) {
        model.setRowCount(0);
        ArrayList<Proyecto> proyectos = JJDCommunications.getInstance().getListaProyectos();
        
        for (Proyecto proyecto : proyectos) {
            boolean agregar = false;
            switch (index) {
                case 0: // Todos
                    agregar = true;
                    break;
                case 1: // En Progreso
                    agregar = proyecto.isContratoActivo();
                    break;
                case 2: // Finalizado
                    agregar = !proyecto.isContratoActivo();
                    break;

            }
            if (agregar) {
                model.addRow(new Object[]{
                        proyecto.getNombre(),
                        proyecto.getIdCliente(),
                        proyecto.getCantTrabajadores(),
                        proyecto.isContratoActivo() ? "Si" : "No"
                });
            }
        }
    }
    
    // Método para abrir la interfaz de detalles del proyecto
    private void abrirDetallesProyecto(String nombreProyecto) {
        // Crear una instancia de la clase DetallesProyecto y pasar el nombre del proyecto como parámetro
        DetallesProyecto detallesProyecto = new DetallesProyecto();
        // Configurar la ventana de detalles del proyecto
        detallesProyecto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detallesProyecto.setVisible(true);
        detallesProyecto.pack();
        detallesProyecto.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }
}
