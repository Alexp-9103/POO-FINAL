
package logico;

import java.io.Serializable;

public class Disenador extends Trabajador implements Serializable {
    private static final long serialVersionUID = 1L;
	    private int aniosExperiencia;
	    
	    public Disenador(String id, String nombre, String apellido, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int aniosExperiencia) {
	        super(id, nombre, apellido, direccion, sexo, edad, salarioHora, evaluacion);
	        this.aniosExperiencia = aniosExperiencia;
	    }
	    public int getAniosExperiencia() {
	        return aniosExperiencia;
	    }
		public void setAniosExperiencia(int aniosExperiencia) {
			this.aniosExperiencia = aniosExperiencia;
	}
	    @Override
	    public boolean estaDisponible() {
	        return getCantidadProyectos() < 2;
	    }
}
