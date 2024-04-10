package visual;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Contrato;
import logico.JJDCommunications;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListadoContrato extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private Object rows[];

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListadoContrato dialog = new ListadoContrato();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    /**
     * Create the dialog.
     */
    public ListadoContrato() {
    	setTitle("Listado De Contrato");
        setBounds(100, 100, 670, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JLabel lblContratosActuales = new JLabel("Contratos");
                lblContratosActuales.setFont(new Font("Tahoma", Font.BOLD, 14));
                lblContratosActuales.setForeground(Color.BLACK);
                lblContratosActuales.setHorizontalAlignment(JLabel.CENTER);
                panel.add(lblContratosActuales, BorderLayout.NORTH);
            }
            {
                JScrollPane scrollPane = new JScrollPane();
                panel.add(scrollPane, BorderLayout.CENTER);
                {
                    String[] headers = {"ID Contrato", "ID Cliente", "Nombre Proyecto", "Fecha Inicio", "Fecha Entrega", "Prorroga"};

                    table = new JTable();
                    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    scrollPane.setViewportView(table);

                    model = new DefaultTableModel();
                    model.setColumnIdentifiers(headers);
                    table.setModel(model);

                    cargarContratos();
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnVerDetalles = new JButton("Ver Detalles");
                btnVerDetalles.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String idContrato = (String) model.getValueAt(selectedRow, 0);
                            DetallesContrato detallesContrato = new DetallesContrato(idContrato);
                            detallesContrato.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor, seleccione un Contrato para ver los detalles.");
                        }
                    }
                });
                buttonPane.add(btnVerDetalles);
            }

            {
                JButton btnEliminar = new JButton("Eliminar");
                btnEliminar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int option = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este contrato?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                String idContrato = (String) model.getValueAt(selectedRow, 0);
                                // Implementa la lógica para eliminar el contrato con el idContrato
                                cargarContratos(); // Recargar la tabla después de eliminar
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor, seleccione un contrato para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                buttonPane.add(btnEliminar);
            }
        }
    }

    private void cargarContratos() {
        model.setRowCount(0);
        rows = new Object[model.getColumnCount()];
        ArrayList<Contrato> listaContratos = JJDCommunications.getInstance().getListaContratos();
        if (listaContratos != null) {
            for (Contrato contrato : listaContratos) {
                rows[0] = contrato.getIdContrato();
                rows[1] = contrato.getIdCliente();
                rows[2] = contrato.getNombreProyecto();
                rows[3] = contrato.getFechaInicio();
                rows[4] = contrato.getFechaEntrega();
                rows[5] = contrato.isProrroga() ? "Si" : "No";
                model.addRow(rows);
            }
        }
    }
}
