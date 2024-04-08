package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Cliente;
import logico.JJDCommunications;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegCliente extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtCedula;

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
        setBounds(100, 100, 450, 256);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Información del Cliente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 11, 414, 169);
        contentPanel.add(panel);
        panel.setLayout(null);

        JLabel lblId = new JLabel("Cédula:");
        lblId.setBounds(10, 31, 64, 14);
        panel.add(lblId);

        txtCedula = new JTextField();
        txtCedula.setColumns(10);
        txtCedula.setBounds(76, 28, 150, 20);
        panel.add(txtCedula);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 70, 64, 14);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);
        txtNombre.setBounds(76, 67, 230, 20);
        panel.add(txtNombre);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(10, 110, 64, 14);
        panel.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setColumns(10);
        txtDireccion.setBounds(76, 107, 230, 20);
        panel.add(txtDireccion);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Registrar");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
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

    private void registrarCliente() {
        String cedula = txtCedula.getText().trim();
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();

        if (cedula.isEmpty() || nombre.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear el cliente
        Cliente cliente = new Cliente(cedula, nombre, direccion, new ArrayList<>());

        // Insertar cliente en la lista
        JJDCommunications.getInstance().insertarCliente(cliente);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);

        // Limpiar los campos de texto
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
    }
}
