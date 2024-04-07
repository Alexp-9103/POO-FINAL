package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import logico.Cliente;
import logico.JJDCommunications;

public class RegCliente extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDireccion;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegCliente dialog = new RegCliente();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegCliente() {
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Informaci\u00F3n del Cliente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 11, 414, 169);
        contentPanel.add(panel);
        panel.setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(10, 30, 46, 14);
        panel.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(66, 27, 154, 20);
        panel.add(txtId);
        txtId.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 70, 64, 14);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);
        txtNombre.setBounds(66, 67, 154, 20);
        panel.add(txtNombre);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(10, 110, 64, 14);
        panel.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setColumns(10);
        txtDireccion.setBounds(66, 107, 154, 20);
        panel.add(txtDireccion);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Registrar");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText();
                String nombre = txtNombre.getText();
                String direccion = txtDireccion.getText();
                Cliente cliente = new Cliente(id, nombre, direccion, new ArrayList<>());
                JJDCommunications.getInstance().insertarCliente(cliente);
                JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    private void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
    }
}
