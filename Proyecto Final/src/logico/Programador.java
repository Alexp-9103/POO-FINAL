package logico;

import java.util.ArrayList;

public class Programador extends Trabajador {
    private ArrayList<String> lenguajesEspecializados;

    public Programador(String id, String nombre, String apellidos, String direccion, char sexo, int edad, double salarioHora, String evaluacion, ArrayList<String> lenguajesEspecializados, int aniosExperiencia) {
        super(id, nombre, apellidos, direccion, sexo, edad, salarioHora, evaluacion, aniosExperiencia);
        this.lenguajesEspecializados = lenguajesEspecializados;
    }

    public ArrayList<String> getLenguajesEspecializados() {
        return lenguajesEspecializados;
    }

    public void setLenguajesEspecializados(ArrayList<String> lenguajesEspecializados) {
        this.lenguajesEspecializados = lenguajesEspecializados;
    }
    @Override
    public boolean estaDisponible() {
        return getCantidadProyectos() < 1;
    }
}