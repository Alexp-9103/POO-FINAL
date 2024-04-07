package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.JJDCommunications;
import logico.Trabajador;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoTrabajador extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JButton btnUpdate;
    private JButton btnCancel;
    private JTable table;
    private JButton btnDelete;
    private static DefaultTableModel model;
    private static Object[] rows;
    private Trabajador selected = null;

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
        setResizable(false);
        setTitle("Listado de Trabajadores");
        setModal(true);
        setBounds(100, 100, 600, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Trabajadores Registrados:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane, BorderLayout.CENTER);

        String[] headers = {"ID", "Nombre", "Dirección", "Sexo", "Edad", "Salario por Hora", "Evaluación", "Experiencia (años)", "Proyectos"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);
        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index > -1) {
                    btnDelete.setEnabled(true);
                    btnUpdate.setEnabled(true);
                    selected = JJDCommunications.getInstance().BuscarTrabajador(table.getValueAt(index, 0).toString());
                }
            }
        });
        table.setModel(model);
        scrollPane.setViewportView(table);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnDelete = new JButton("Eliminar");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selected != null) {
                    int option = JOptionPane.showConfirmDialog(null, "¿Seguro desea eliminar al trabajador con ID: " + selected.getId() + "?", "Eliminar", JOptionPane.WARNING_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                    	JJDCommunications.getInstance().eliminarTrabajador(selected.getId());
                        loadTrabajadores();
                    }
                }
            }
        });
        btnDelete.setEnabled(false);
        buttonPane.add(btnDelete);

        btnUpdate = new JButton("Modificar");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Depending on the subclass, you may want to open a different dialog for updating
                // For simplicity, assuming a generic dialog for all subclasses.
                RegTrabajador update = new RegTrabajador();
                update.setVisible(true);
            }
        });
        btnUpdate.setEnabled(false);
        btnUpdate.setActionCommand("OK");
        buttonPane.add(btnUpdate);
        getRootPane().setDefaultButton(btnUpdate);

        btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancel.setActionCommand("Cancel");
        buttonPane.add(btnCancel);

        loadTrabajadores();
    }

	public static void loadTrabajadores() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		int cant = JJDCommunications.getInstance().getCantTrabajador();
		for (Trabajador trabajador : JJDCommunications.getInstance().getListaTrabajadores()) {
            rows[0] = trabajador.getId();
            rows[1] = trabajador.getNombre();
            rows[2] = trabajador.getDireccion();
            rows[3] = trabajador.getSexo();
            rows[4] = trabajador.getEdad();
            rows[5] = trabajador.getSalarioHora();
            rows[6] = trabajador.getEvaluacion();
			model.addRow(rows);
		}
    }
}
