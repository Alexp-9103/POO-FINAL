package logico;

import java.util.ArrayList;

public class JJDCommunications {
    private ArrayList<Trabajador> ListaTrabajadores;
    private ArrayList<Cliente> ListaClientes;
    private ArrayList<Proyecto> ListaProyectos;
    private ArrayList<Contrato> ListaContratos;
	
    public void JJDcommunications() {
        this.ListaTrabajadores = new ArrayList<>();
        this.ListaClientes = new ArrayList<>();
        this.ListaProyectos = new ArrayList<>();
        this.ListaContratos = new ArrayList<>();
    }

	public ArrayList<Trabajador> getListaTrabajadores() {
		return ListaTrabajadores;
	}

	public void setListaTrabajadores(ArrayList<Trabajador> listaTrabajadores) {
		ListaTrabajadores = listaTrabajadores;
	}

	public ArrayList<Cliente> getListaClientes() {
		return ListaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		ListaClientes = listaClientes;
	}

	public ArrayList<Proyecto> getListaProyectos() {
		return ListaProyectos;
	}

	public void setListaProyectos(ArrayList<Proyecto> listaProyectos) {
		ListaProyectos = listaProyectos;
	}

	public ArrayList<Contrato> getListaContratos() {
		return ListaContratos;
	}

	public void setListaContratos(ArrayList<Contrato> listaContratos) {
		ListaContratos = listaContratos;
	}
    
    
}
