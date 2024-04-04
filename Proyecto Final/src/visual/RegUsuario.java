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
import javax.swing.JPasswordField;
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
    private JPasswordField  contrasenaField;
    private JPasswordField confirmarContrasenaField;
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

        JLabel contrasenaLabel = new JLabel("Contrasena:");
        contrasenaLabel.setBounds(30, 100, 80, 14);
        contentPanel.add(contrasenaLabel);

        contrasenaField = new JPasswordField();
        contrasenaField.setBounds(170, 97, 160, 20);
        contentPanel.add(contrasenaField);
        contrasenaField.setColumns(10);

        JLabel confirmarContrasenaLabel = new JLabel("Confirmar Contrasena:");
        confirmarContrasenaLabel.setBounds(30, 140, 140, 14);
        contentPanel.add(confirmarContrasenaLabel);

        confirmarContrasenaField = new JPasswordField();
        confirmarContrasenaField.setBounds(170, 137, 160, 20);
        contentPanel.add(confirmarContrasenaField);
        confirmarContrasenaField.setColumns(10);

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
        String contrasena = contrasenaField.getText();
        String confirmarContrasena = confirmarContrasenaField.getText();
        String tipoUsuario = tipoUsuarioComboBox.getSelectedItem().toString();

        if (nombreUsuario.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty() || tipoUsuario.equals("<Seleccione>")) {
            // Mostrar mensaje de advertencia si algún campo está vacío o el tipo de usuario no se ha seleccionado
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!contrasena.equals(confirmarContrasena)) {
            // Mostrar mensaje de advertencia si las contrasenas no coinciden
            JOptionPane.showMessageDialog(this, "Las contrasenas no coinciden.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Si los campos están completos y las contrasenas coinciden, entonces registrar al usuario
        User user = new User(tipoUsuario, nombreUsuario, contrasena);
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
