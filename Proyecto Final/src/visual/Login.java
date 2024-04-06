package visual;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import logico.Control;
import logico.User;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FileInputStream usuariosFile = new FileInputStream("usuarios.dat");
                    ObjectInputStream usuariosRead = new ObjectInputStream(usuariosFile);
                    Object obj = usuariosRead.readObject();
                    if (obj instanceof Control) {
                        Control temp = (Control) obj;
                        Control.instancia = temp;
                        usuariosFile.close();
                        usuariosRead.close();
                    } else {
                        throw new IOException("El archivo no contiene un objeto de tipo Control");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    Login frame = new Login();
                    frame.setLocationRelativeTo(null); // Colocar la ventana en el centro
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setTitle("Inicio de Sesion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 281);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblLogin = new JLabel("LOGIN");
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblLogin.setBounds(108, 20, 60, 30);
        panel.add(lblLogin);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(39, 52, 191, 14);
        panel.add(lblUsuario);

        JLabel lblContrasea = new JLabel("Contraseña:");
        lblContrasea.setBounds(39, 111, 191, 14);
        panel.add(lblContrasea);

        textField = new JTextField();
        textField.setBounds(39, 77, 191, 20);
        panel.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(39, 141, 191, 20);
        panel.add(textField_1);
        textField_1.setColumns(10);

        JButton btnLogin = new JButton("OK");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = textField.getText();
                String contrasena = textField_1.getText();
                if (usuario.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Optional<User> usuarioValido = Control.getInstance().confirmarLogin(usuario, contrasena);
                    if (usuarioValido.isPresent()) {
                        Principal frame = new Principal();
                        Control.setUsuarioLogueado(usuarioValido.get());
                        dispose();
                        frame.setVisible(true);
                    } else {
                        // Mostrar mensaje de advertencia y sugerir registro
                        int opcion = JOptionPane.showConfirmDialog(null, "Usuario no encontrado. ¿Desea registrarse?", "Advertencia", JOptionPane.YES_NO_OPTION);
                        if (opcion == JOptionPane.YES_OPTION) {
                            // Abre la ventana de registro
                            RegUsuario registroUsuario = new RegUsuario(); // Pasar la referencia de la ventana de inicio de sesión al registro
                            registroUsuario.setVisible(true);
                            dispose(); // Cerrar la ventana de inicio de sesión
                        }
                    }
                }
            }
        });
        btnLogin.setBounds(39, 187, 89, 23);
        panel.add(btnLogin);

        JButton btnRegistro = new JButton("Registro");
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de registro
                RegUsuario registroUsuario = new RegUsuario(); // Pasar la referencia de la ventana de inicio de sesión al registro
                registroUsuario.setVisible(true);
                dispose(); // Cerrar la ventana de inicio de sesión
            }
        });
        btnRegistro.setBounds(141, 187, 89, 23);
        panel.add(btnRegistro);

        setLocationRelativeTo(null);
    }
}
