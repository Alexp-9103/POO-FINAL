package logico;

public class Disenador extends Trabajador{
	    private int aniosExperiencia;
	    
	    public Disenador(String id, String nombre, String apellidos, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int aniosExperiencia) {
	        super(id, nombre, apellidos, direccion, sexo, edad, salarioHora, evaluacion, aniosExperiencia);
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