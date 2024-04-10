package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.JJDCommunications;

public class ListadoCliente extends JDialog {

    private JTable table;
    private DefaultTableModel model;
    private Object rows[];
    private JJDCommunications jjdCommunications;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListadoCliente dialog = new ListadoCliente();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListadoCliente() {
        jjdCommunications = JJDCommunications.getInstance(); // Inicializar jjdCommunications
    	setTitle("Listado De Cliente");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JLabel lblClientesActuales = new JLabel("Listado de Clientes");
        lblClientesActuales.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblClientesActuales.setForeground(Color.BLACK);
        lblClientesActuales.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblClientesActuales, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);

        String[] headers = {"Cédula", "Nombre", "Dirección", "Proyectos"};

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);
        table.setModel(model);

        cargarClientes();

        JPanel buttonPanel = new JPanel();
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnVerDetalles = new JButton("Ver Detalles");
        btnVerDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String idCliente = (String) model.getValueAt(selectedRow, 0);
                    DetallesCliente detallesCliente = new DetallesCliente(idCliente);
                    detallesCliente.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un Cliente para ver los detalles.");
                }
            }
        });
        buttonPanel.add(btnVerDetalles);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int option = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este cliente?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        String id = (String) model.getValueAt(selectedRow, 0);
                        jjdCommunications.eliminarCliente(id);
                        cargarClientes(); // Recargar la tabla después de eliminar
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonPanel.add(btnEliminar);
    }

    private void cargarClientes() {
        model.setRowCount(0);
        rows = new Object[model.getColumnCount()];
        ArrayList<Cliente> listaClientes = JJDCommunications.getInstance().getListaClientes();
        if (listaClientes != null) {
            for (Cliente cliente : listaClientes) {
                int cantidadProyectos = cliente.getMisProyectos().size(); 
                rows[0] = cliente.getId();
                rows[1] = cliente.getNombre();
                rows[2] = cliente.getDireccion();
                rows[3] = cantidadProyectos; 
                model.addRow(rows);
            }
        }
    }

    
}
