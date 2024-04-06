package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class Control implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private ArrayList<User> usuarios;
    public static Control instancia;
    private static User usuarioLogueado;
    
    private Control() {
        usuarios = new ArrayList<>();
    }
    
    public static Control getInstance() {
        if (instancia == null) {
            instancia = new Control();
        }
        return instancia;
    }

    public ArrayList<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<User> usuarios) {
        this.usuarios = usuarios;
    }

    public static User getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(User usuarioLogueado) {
        Control.usuarioLogueado = usuarioLogueado;
    }

    public void registrarUsuario(User user) {
        usuarios.add(user);
    }

    public Optional<User> confirmarLogin(String nombreUsuario, String contrasena) {
        Optional<User> usuarioEncontrado = usuarios.stream()
                                                .filter(user -> user.getUserName().equals(nombreUsuario) && user.getPass().equals(contrasena))
                                                .findFirst();
        usuarioEncontrado.ifPresent(user -> usuarioLogueado = user);
        return usuarioEncontrado;
    }
    
}