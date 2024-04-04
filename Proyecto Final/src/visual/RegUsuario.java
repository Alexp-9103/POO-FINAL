package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logico.Control;
import logico.User;

import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class RegUsuario extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField nombreUsuarioField;
    private JTextField contraseñaField;
    private JTextField confirmarContraseñaField;
    private JComboBox<String> tipoUsuarioComboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegUsuario dialog = new RegUsuario();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegUsuario() {
        setTitle("Registro de Usuario");
        setBounds(100, 100, 400, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel tituloLabel = new JLabel("Registro de Usuario");
        tituloLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        tituloLabel.setBounds(105, 11, 176, 30);
        contentPanel.add(tituloLabel);

        JLabel nombreUsuarioLabel = new JLabel("Nombre de Usuario:");
        nombreUsuarioLabel.setBounds(30, 60, 120, 14);
        contentPanel.add(nombreUsuarioLabel);

        nombreUsuarioField = new JTextField();
        nombreUsuarioField.setBounds(170, 57, 160, 20);
        contentPanel.add(nombreUsuarioField);
        nombreUsuarioField.setColumns(10);

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setBounds(30, 100, 80, 14);
        contentPanel.add(contraseñaLabel);

        contraseñaField = new JTextField();
        contraseñaField.setBounds(170, 97, 160, 20);
        contentPanel.add(contraseñaField);
        contraseñaField.setColumns(10);

        JLabel confirmarContraseñaLabel = new JLabel("Confirmar Contraseña:");
        confirmarContraseñaLabel.setBounds(30, 140, 140, 14);
        contentPanel.add(confirmarContraseñaLabel);

        confirmarContraseñaField = new JTextField();
        confirmarContraseñaField.setBounds(170, 137, 160, 20);
        contentPanel.add(confirmarContraseñaField);
        confirmarContraseñaField.setColumns(10);

        JLabel tipoUsuarioLabel = new JLabel("Tipo de Usuario:");
        tipoUsuarioLabel.setBounds(30, 180, 100, 14);
        contentPanel.add(tipoUsuarioLabel);

        tipoUsuarioComboBox = new JComboBox<>();
        tipoUsuarioComboBox.setBounds(169, 177, 160, 20);
        tipoUsuarioComboBox.addItem("<Seleccione>");
        tipoUsuarioComboBox.addItem("Administrador");
        tipoUsuarioComboBox.addItem("Usuario Regular");
        contentPanel.add(tipoUsuarioComboBox);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(botonesPanel, BorderLayout.SOUTH);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
        botonesPanel.add(registrarButton);
        getRootPane().setDefaultButton(registrarButton);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login frame = new Login();
                frame.setLocationRelativeTo(null); // Colocar la ventana en el centro
                frame.setVisible(true);
            }
        });
        botonesPanel.add(cancelarButton);
    }

    
    private void registrarUsuario() {
        String nombreUsuario = nombreUsuarioField.getText();
        String contraseña = contraseñaField.getText();
        String confirmarContraseña = confirmarContraseñaField.getText();
        String tipoUsuario = tipoUsuarioComboBox.getSelectedItem().toString();

        if (nombreUsuario.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty() || tipoUsuario.equals("<Seleccione>")) {
            // Mostrar mensaje de advertencia si algún campo está vacío o el tipo de usuario no se ha seleccionado
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            // Mostrar mensaje de advertencia si las contraseñas no coinciden
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Si los campos están completos y las contraseñas coinciden, entonces registrar al usuario
        User user = new User(tipoUsuario, nombreUsuario, contraseña);
        Control.getInstance().registrarUsuario(user);

        // Guardar el usuario en un fichero
        try (FileOutputStream fileOut = new FileOutputStream("usuarios.dat", true);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(user);
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Cerrar la ventana de registro
        dispose();

        // Mostrar la ventana de inicio de sesión
        Login loginWindow = new Login();
        loginWindow.setLocationRelativeTo(null); // Centrar la ventana de inicio de sesión
        loginWindow.setVisible(true);
    }





}
