package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Programador extends Trabajador implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private ArrayList<String> lenguajesEspecializados;

    public Programador(String id, String nombre, String direccion, char sexo, int edad, double salarioHora, String evaluacion, ArrayList<String> lenguajesEspecializados) {
        super(id, nombre, direccion, sexo, edad, salarioHora, evaluacion);
        this.lenguajesEspecializados = lenguajesEspecializados;
    }

    public ArrayList<String> getLenguajesEspecializados() {
        return lenguajesEspecializados;
    }

    public void setLenguajesEspecializados(ArrayList<String> lenguajesEspecializados) {
        this.lenguajesEspecializados = lenguajesEspecializados;
    }
    
 // Metodo para agregar un lenguaje especializado
    public void agregarLenguajeEspecializado(String lenguaje) {
        if (lenguajesEspecializados == null) {
            lenguajesEspecializados = new ArrayList<>();
        }
        lenguajesEspecializados.add(lenguaje);
    }
    @Override
    public boolean estaDisponible() {
        return getCantidadProyectos() < 1;
    }
}
