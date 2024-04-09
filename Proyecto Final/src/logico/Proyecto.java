package logico;

import java.util.ArrayList;

public class Proyecto {
	
	String idCliente;
	String idProyecto;
	String nombre;
	int cantTrabajadores;
	boolean contratoActivo;
	ArrayList <Trabajador> losTrabajadores;
	
	public Proyecto(String idCliente, String nombre, int cantTrabajadores, boolean contratoActivo,
			ArrayList<Trabajador> losTrabajadores) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.cantTrabajadores = cantTrabajadores;
		this.contratoActivo = contratoActivo;
		this.losTrabajadores = losTrabajadores;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantTrabajadores() {
		return cantTrabajadores;
	}

	public void setCantTrabajadores(int cantTrabajadores) {
		this.cantTrabajadores = cantTrabajadores;
	}

	public boolean isContratoActivo() {
		return contratoActivo;
	}

	public void setContratoActivo(boolean contratoActivo) {
		this.contratoActivo = contratoActivo;
	}

	public ArrayList<Trabajador> getLosTrabajadores() {
		return losTrabajadores;
	}

	public void setLosTrabajadores(ArrayList<Trabajador> losTrabajadores) {
		this.losTrabajadores = losTrabajadores;
	}
		
	

}
