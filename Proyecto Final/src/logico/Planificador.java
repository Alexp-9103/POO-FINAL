package logico;

import java.io.Serializable;

public class Planificador extends Trabajador implements Serializable {
    private static final long serialVersionUID = 1L;
    private int frecuenciaPlanificacion;

    public Planificador(String id, String nombre, String apellido, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int frecuenciaPlanificacion) {
        super(id, nombre, apellido, direccion, sexo, edad, salarioHora, evaluacion);
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