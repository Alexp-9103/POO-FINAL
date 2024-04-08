package visual;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import java.util.ArrayList;
import java.util.Optional;

import logico.Control;
import logico.User;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField textField_user;
    private JPasswordField textField_pass;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Cargar usuarios desde el archivo usuarios.dat
                    cargarUsuariosDesdeArchivo();

                    Login frame = new Login();
                    frame.setLocationRelativeTo(null); // Colocar la ventana en el centro
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    // Método para cargar usuarios desde el archivo usuarios.dat
    private static void cargarUsuariosDesdeArchivo() {
        try (FileInputStream fileIn = new FileInputStream("usuarios.dat");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            ArrayList<User> usuarios = (ArrayList<User>) objectIn.readObject();
            Control.getInstance().setUsuarios(usuarios); // Establecer los usuarios en la instancia de Control
        } catch (FileNotFoundException e) {
            // Manejar la excepción si el archivo no existe
            System.out.println("El archivo 'usuarios.dat' no se encontró. Se creará uno nuevo.");
            // No es necesario establecer la instancia de Control, ya que getInstance() crea la instancia si es null
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

        JLabel lblContrasea = new JLabel("Contrasena:");
        lblContrasea.setBounds(39, 111, 191, 14);
        panel.add(lblContrasea);

        textField_user = new JTextField();
        textField_user.setBounds(39, 77, 191, 20);
        panel.add(textField_user);
        textField_user.setColumns(10);

        textField_pass = new JPasswordField();
        textField_pass.setBounds(39, 141, 191, 20);
        panel.add(textField_pass);
        textField_pass.setColumns(10);

        JButton btnLogin = new JButton("OK");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = textField_user.getText();
                String contrasena = textField_pass.getText();
                if (usuario.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Optional<User> usuarioValido = Control.getInstance().confirmarLogin(usuario, contrasena);
                    if (usuarioValido.isPresent()) {
                        Principal frame;
                        if (usuarioValido.get().isAdmin()) {
                            // Usuario es administrador, activar el menú administrativo
                            frame = new Principal(true);
                        } else {
                            // Usuario no es administrador, desactivar el menú administrativo
                            frame = new Principal(false);
                        }
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
                RegUsuario registroUsuario = new RegUsuario(); // Pasar la referencia de la ventana de inicio de sesiï¿½n al registro
                registroUsuario.setVisible(true);
                dispose(); // Cerrar la ventana de inicio de sesiï¿½n
            }
        });
        btnRegistro.setBounds(141, 187, 89, 23);
        panel.add(btnRegistro);
        
        setLocationRelativeTo(null);
    }
}