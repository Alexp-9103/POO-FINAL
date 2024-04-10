package logico;

import java.io.Serializable;

public class JefeProyecto extends Trabajador implements Serializable {
    private static final long serialVersionUID = 1L;
    private int cantidadTrabajadores;


    public JefeProyecto(String id, String nombre, String apellido, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int cantidadTrabajadores) {
        super(id, nombre, apellido, direccion, sexo, edad, salarioHora, evaluacion);
        this.cantidadTrabajadores = cantidadTrabajadores;
    }

    public int getCantidadTrabajadores() {
        return cantidadTrabajadores;
    }

    public void setCantidadTrabajadores(int cantidadTrabajadores) {
        this.cantidadTrabajadores = cantidadTrabajadores;
    }
    @Override
    public boolean estaDisponible() {
        return getCantidadProyectos() < 2;
    }
}