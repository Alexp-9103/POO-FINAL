package logico;

class Dise�ador extends Trabajador {
    private int a�osExperiencia;
    
    public Dise�ador(String id, String nombre, String apellidos, String direccion, char sexo, int edad, double salarioHora, String evaluacion, int a�osExperiencia) {
        super(id, nombre, apellidos, direccion, sexo, edad, salarioHora, evaluacion, salarioHora);
        this.a�osExperiencia = a�osExperiencia;
    }
    public int getA�osExperiencia() {
        return a�osExperiencia;
    }

    public void setA�osExperiencia(int a�osExperiencia) {
        this.a�osExperiencia = a�osExperiencia;
    }
}
