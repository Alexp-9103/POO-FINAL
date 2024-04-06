package logico;

public class JefeProyecto extends Trabajador {
    private int cantidadTrabajadores;


    public JefeProyecto(String id, String nombre, String apellidos, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int cantidadTrabajadores, int aniosExperiencia) {
        super(id, nombre, apellidos, direccion, sexo, edad, salarioHora, evaluacion, aniosExperiencia);
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