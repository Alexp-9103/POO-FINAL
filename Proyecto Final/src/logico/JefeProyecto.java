package logico;

public class JefeProyecto extends Trabajador {
    private int cantidadTrabajadores;


    public JefeProyecto(String id, String nombre, String apellidos, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int cantidadTrabajadores) {
        super(id, nombre, apellidos, direccion, sexo, edad, salarioHora, evaluacion);
        this.cantidadTrabajadores = cantidadTrabajadores;
    }

    public int getCantidadTrabajadores() {
        return cantidadTrabajadores;
    }

    public void setCantidadTrabajadores(int cantidadTrabajadores) {
        this.cantidadTrabajadores = cantidadTrabajadores;
    }
}