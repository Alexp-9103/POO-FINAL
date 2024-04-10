package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import logico.JJDCommunications;
import logico.Proyecto;

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


    public ListadoProyecto() {
        setTitle("Listado De Proyecto");
        setSize(841, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

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
        btnVerDetalles.addActionListener(e -> verDetallesProyecto());
        buttonPanel.add(btnVerDetalles);

        JButton btnEliminarProyecto = new JButton("Eliminar Proyecto");
        btnEliminarProyecto.addActionListener(e -> eliminarProyecto());
        buttonPanel.add(btnEliminarProyecto);

        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        tablePanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        String[] headers = {"ID Proyecto", " Nombre", "Cantidad de Trabajadores", "Contrato Activo"};

        table = new JTable();
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
                        proyecto.getIdProyecto(),
                        proyecto.getNombre(),
                        proyecto.getCantTrabajadores(),
                        proyecto.isContratoActivo() ? "Sí" : "No"
                });
            }
        }
    }

    private void verDetallesProyecto() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proyecto de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String nombreProyecto = (String) table.getValueAt(selectedRow, 1);
            DetallesProyecto detallesProyecto = new DetallesProyecto(nombreProyecto);
            detallesProyecto.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            detallesProyecto.setVisible(true);
        }
    }

    private void eliminarProyecto() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proyecto de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar el proyecto?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String idProyecto = (String) model.getValueAt(selectedRow, 0);
                JJDCommunications.getInstance().eliminarProyecto(idProyecto);
                loadProyectos(comboBox.getSelectedIndex());
            }
        }
    }
}