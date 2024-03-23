package logico;

class Diseñador extends Trabajador {
    private int añosExperiencia;
    
    public Diseñador(String id, String nombre, String apellidos, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int añosExperiencia) {
        super(id, nombre, apellidos, direccion, sexo, edad, salarioHora, evaluacion, salarioHora);
        this.añosExperiencia = añosExperiencia;
    }
    public int getAñosExperiencia() {
        return añosExperiencia;
    }

    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }
}
