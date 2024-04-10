package logico;
import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
	
	String id;
	String nombre;
	String direccion;
	ArrayList <Proyecto> misProyectos;

	
	public Cliente(String id, String nombre, String direccion, ArrayList<Proyecto> misProyectos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.misProyectos = misProyectos;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public ArrayList<Proyecto> getMisProyectos() {
		return misProyectos;
	}


	public void setMisProyectos(ArrayList<Proyecto> misProyectos) {
		this.misProyectos = misProyectos;
	}
	
    public ArrayList<Proyecto> buscarProyectosAsociados() {
        return misProyectos;
    }
	
}
