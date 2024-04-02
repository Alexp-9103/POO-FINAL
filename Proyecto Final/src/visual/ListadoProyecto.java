package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Proyecto;
import logico.JJDCommunications;

import java.awt.*;
import java.util.ArrayList;

public class ListadoProyecto extends JDialog {

    private DefaultTableModel model;
    private JComboBox<String> comboBox;

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
                case 3: // Pendiente
                    // Agregar lógica para los proyectos pendientes si es necesario
                    break;
            }
            if (agregar) {
                model.addRow(new Object[]{
                        proyecto.getNombre(),
                        proyecto.getIdCliente(),
                        proyecto.getCantTrabajadores(),
                        proyecto.isContratoActivo() ? "Sí" : "No"
                });
            }
        }
    }
}
