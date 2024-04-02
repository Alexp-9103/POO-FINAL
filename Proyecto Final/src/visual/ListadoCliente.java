package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Proyecto;
import logico.JJDCommunications;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ListadoCliente extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private Object rows[];

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
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JLabel lblClientesActuales = new JLabel("Clientes Actuales");
                lblClientesActuales.setFont(new Font("Tahoma", Font.BOLD, 14));
                lblClientesActuales.setForeground(Color.BLACK);
                lblClientesActuales.setHorizontalAlignment(JLabel.CENTER);
                panel.add(lblClientesActuales, BorderLayout.NORTH);
            }
            {
                JScrollPane scrollPane = new JScrollPane();
                panel.add(scrollPane, BorderLayout.CENTER);
                {
                    String[] headers = {"Cedula", "Nombre", "Direccion", "Proyectos"};

                    table = new JTable();
                    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    scrollPane.setViewportView(table);

                    model = new DefaultTableModel();
                    model.setColumnIdentifiers(headers);
                    table.setModel(model);

                    cargarClientes();
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnSalir = new JButton("Salir");
                btnSalir.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                buttonPane.add(btnSalir);
            }
        }
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


