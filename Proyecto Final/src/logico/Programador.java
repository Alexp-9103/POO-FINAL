package logico;

public class Programador extends Trabajador {
    private String lenguajeEspecializado;

    public Programador(String id, String nombre, String apellidos, String direccion, char sexo, int edad, double salarioHora, String evaluacion, String lenguajeEspecializado, int aniosExperiencia) {
        super(id, nombre, apellidos, direccion, sexo, edad, salarioHora, evaluacion, aniosExperiencia);
        this.lenguajeEspecializado = lenguajeEspecializado;
    }

    public String getLenguajeEspecializado() {
        return lenguajeEspecializado;
    }

    public void setLenguajeEspecializado(String lenguajeEspecializado) {
        this.lenguajeEspecializado = lenguajeEspecializado;
    }
}