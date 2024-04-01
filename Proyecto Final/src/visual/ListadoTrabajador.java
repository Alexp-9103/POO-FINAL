package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import logico.Disenador;
import logico.JefeProyecto;
import logico.Planificador;
import logico.Programador;
import logico.Trabajador;

import java.awt.*;
import java.util.ArrayList;

public class ListadoTrabajador extends JDialog {

    private DefaultTableModel model;
    private JComboBox<String> comboBox;
    private ArrayList<Trabajador> trabajadores = new ArrayList<>(); 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoTrabajador dialog = new ListadoTrabajador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

    public ListadoTrabajador() {
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

        JLabel lblTipoDeTrabajador = new JLabel("Tipo de Trabajador:");
        lblTipoDeTrabajador.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(lblTipoDeTrabajador);

        comboBox = new JComboBox<>();
        comboBox.addActionListener(e -> loadTrabajadores(comboBox.getSelectedIndex(), trabajadores));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"<Todos>", "Jefe de Proyecto", "Diseñador", "Programador", "Planificador"}));
        panel.add(comboBox);

        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        tablePanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        String[] headers = {"ID", "Nombre", "Apellido", "Dirección", "Sexo", "Edad", "Salario por Hora", "Evaluación", "Años de Experiencia"};

        JTable table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);
        table.setModel(model);

        loadTrabajadores(0, trabajadores); // Cambia trabajadores por la lista de trabajadores que tienes
    }

    private void loadTrabajadores(int index, ArrayList<Trabajador> trabajadores) {
        model.setRowCount(0);
        for (Trabajador trabajador : trabajadores) {
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
                model.addRow(new Object[]{
                        trabajador.getId(),
                        trabajador.getNombre(),
                        trabajador.getApellido(),
                        trabajador.getDireccion(),
                        trabajador.getSexo(),
                        trabajador.getEdad(),
                        trabajador.getSalarioHora(),
                        trabajador.getEvaluacion(),
                        trabajador.getAniosExperiencia()
                });
            }
        }
    }
}
