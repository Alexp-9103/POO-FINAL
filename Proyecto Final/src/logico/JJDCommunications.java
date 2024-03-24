package logico;

import java.util.Date;
import java.util.ArrayList;

public class JJDCommunications {
    private ArrayList<Trabajador> ListaTrabajadores;
    private ArrayList<Cliente> ListaClientes;
    private ArrayList<Contrato> ListaContratos;
    private ArrayList<Proyecto> ListaProyectos;
	
    public void JJDcommunications() {
        this.ListaTrabajadores = new ArrayList<>();
        this.ListaClientes = new ArrayList<>();
        this.ListaContratos = new ArrayList<>();
        this.ListaProyectos = new ArrayList<>();
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

	public ArrayList<Contrato> getListaContratos() {
		return ListaContratos;
	}

	public void setListaContratos(ArrayList<Contrato> listaContratos) {
		ListaContratos = listaContratos;
	}

	public ArrayList<Proyecto> getListaProyectos() {
		return ListaProyectos;
	}

	public void setListaProyectos(ArrayList<Proyecto> listaProyectos) {
		ListaProyectos =listaProyectos;
	}
	
	
    // Método para calcular el salario total pagado a los trabajadores en un periodo de tiempo determinado
    public double calcularSalarioTotal(Date fechaInicio, Date fechaFin) {
        double salarioTotal = 0.0;
        
        for (Trabajador trabajador : ListaTrabajadores) {
            // Calculamos el salario del trabajador en el período y lo sumamos al total        	
            salarioTotal += Trabajador.calcularSalarioTrabajador(trabajador, fechaInicio, fechaFin);
        }
        
        return salarioTotal;
    }
	
    
    
}
