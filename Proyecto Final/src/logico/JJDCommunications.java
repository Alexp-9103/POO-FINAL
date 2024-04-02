package logico;

import java.util.Date;
import java.util.ArrayList;
public class JJDCommunications {
    private ArrayList<Trabajador> ListaTrabajadores;
    private ArrayList<Cliente> ListaClientes;
    private ArrayList<Proyecto> ListaProyectos;
    private ArrayList<Contrato> ListaContratos;
	public static JJDCommunications JJD = null;
    
    public JJDCommunications() {
        ListaTrabajadores = new ArrayList<>();
        ListaClientes = new ArrayList<>();
        ListaProyectos = new ArrayList<>();
        ListaContratos = new ArrayList<>();
    }
	    
    public static JJDCommunications getInstance(){
		if(JJD==null){
			JJD = new JJDCommunications();
		}
		return JJD;
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
		ListaProyectos =listaProyectos;
	}
	public ArrayList<Contrato> getListaContratos() {
		return ListaContratos;
	}
	public void setListaContratos(ArrayList<Contrato> listaContratos) {
		ListaContratos = listaContratos;
	}
	
    public double calcularSalarioTotal(Date fechaInicio, Date fechaFin) {
    	double salarioTotal = 0.0;
        
        for (Trabajador trabajador : ListaTrabajadores) {
            // Calculamos el salario del trabajador en el perï¿½odo y lo sumamos al total        	
            salarioTotal += Trabajador.calcularSalarioTrabajador(trabajador, fechaInicio, fechaFin);
        }
        
        return salarioTotal;
    }    
    
    public void insertarCliente(Cliente cliente){
		ListaClientes.add(cliente);
	}
    
    public void insertarTrabajador(Trabajador trabajador){
		ListaTrabajadores.add(trabajador);
	}
	
    public void insertarProyecto(Proyecto proyecto){
        ListaProyectos.add(proyecto);
    }
    
    public void insertarContrato(Contrato contrato){
        ListaContratos.add(contrato);
    }
    
	public Cliente BuscarCliente(String id) {
			for(Cliente cliente : ListaClientes) {
				if((cliente.getId().equalsIgnoreCase(id))) {
					
					return cliente;
				}
			}
			
			return null;
		}
	
    public Trabajador BuscarTrabajador(String id) {
        for (Trabajador trabajador : ListaTrabajadores) {
            if (trabajador.getId().equalsIgnoreCase(id)) {
                return trabajador;
            }
        }
        
        return null;
    }
    
    public Proyecto BuscarProyecto(String id) {
        for (Proyecto proyecto : ListaProyectos) {
            if (proyecto.getIdCliente().equalsIgnoreCase(id)) {
                return proyecto;
            }
        }
        
        return null;
    }  
    //Por si se necesita
    
/*    public Contrato BuscarContrato(String id) {
        for (Contrato contrato : ListaContratos) {
            if (contrato.getId().equalsIgnoreCase(id)) {
                return contrato;
            }
        }
        
        return null;
    } */ 
	  	
}