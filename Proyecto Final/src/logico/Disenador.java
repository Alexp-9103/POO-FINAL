package logico;

class Disenador extends Trabajador {
    private int anosExperiencia;
    
    public Disenador(String id, String nombre, String apellidos, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int anosExperiencia) {
        super(id, nombre, apellidos, direccion, sexo, edad, salarioHora, evaluacion);
        this.anosExperiencia = anosExperiencia;
    }
    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }
}
