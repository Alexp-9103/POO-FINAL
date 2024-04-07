
package logico;

public class Disenador extends Trabajador{
	    private int aniosExperiencia;
	    
	    public Disenador(String id, String nombre, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int aniosExperiencia) {
	        super(id, nombre, direccion, sexo, edad, salarioHora, evaluacion);
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
