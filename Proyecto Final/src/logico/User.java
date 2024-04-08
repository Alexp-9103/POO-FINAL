package logico;

import java.io.Serializable;

public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String tipo;
    private String userName;
    private String pass;
    private boolean isAdmin; // Nuevo campo para indicar si el usuario es administrador

    public User(String tipo, String userName, String pass, boolean isAdmin) {
        this.tipo = tipo;
        this.userName = userName;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
