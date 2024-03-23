package logico;

public class Disenador {
	    private int aniosExperiencia;
	    
	    public Disenador(String id, String nombre, String apellidos, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int aniosExperiencia) {
	        super();
	        this.aniosExperiencia = aniosExperiencia;
	    }
	    public int getAñosExperiencia() {
	        return aniosExperiencia;
	    }

	    public void setAñosExperiencia(int añosExperiencia) {
	        this.aniosExperiencia = añosExperiencia;
	    }
}


