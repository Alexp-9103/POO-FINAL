package logico;

public class Planificador extends Trabajador {
    private int frecuenciaPlanificacion;

    public Planificador(String id, String nombre, String apellidos, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int frecuenciaPlanificacion, int aniosExperiencia) {
        super(id, nombre, apellidos, direccion, sexo, edad, salarioHora, evaluacion, aniosExperiencia);
        this.frecuenciaPlanificacion = frecuenciaPlanificacion;
    }
    public int getFrecuenciaPlanificacion() {
        return frecuenciaPlanificacion;
    }

    public void setFrecuenciaPlanificacion(int frecuenciaPlanificacion) {
        this.frecuenciaPlanificacion = frecuenciaPlanificacion;
    }
}