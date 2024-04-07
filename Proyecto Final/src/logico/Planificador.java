package logico;

public class Planificador extends Trabajador {
    private int frecuenciaPlanificacion;

    public Planificador(String id, String nombre, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int frecuenciaPlanificacion) {
        super(id, nombre, direccion, sexo, edad, salarioHora, evaluacion);
        this.frecuenciaPlanificacion = frecuenciaPlanificacion;
    }
    public int getFrecuenciaPlanificacion() {
        return frecuenciaPlanificacion;
    }

    public void setFrecuenciaPlanificacion(int frecuenciaPlanificacion) {
        this.frecuenciaPlanificacion = frecuenciaPlanificacion;
    }
    
    @Override
    public boolean estaDisponible() {
        return true; 
    }
    
}