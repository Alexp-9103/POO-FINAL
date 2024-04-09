package logico;

import java.util.Date;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class JJDCommunications implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Trabajador> ListaTrabajadores;
    private ArrayList<Cliente> ListaClientes;
    private ArrayList<Proyecto> ListaProyectos;
    private ArrayList<Contrato> ListaContratos;
    private static final String FILE_NAME = "data.dat";
	public static JJDCommunications JJD = null;
    private String[] ListaSecundaria;
    private String[] auxiliarListTrabajadores;
    
    public JJDCommunications() {
        ListaTrabajadores = new ArrayList<>();
        ListaClientes = new ArrayList<>();
        ListaProyectos = new ArrayList<>();
        ListaContratos = new ArrayList<>();
    }
	    

    public void guardarDatos() {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static JJDCommunications cargarDatos() {
        JJDCommunications jjd = null;
        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            jjd = (JJDCommunications) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return jjd;
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
            // Calculamos el salario del trabajador en el periodo y lo sumamos al total        	
            salarioTotal += Trabajador.calcularSalarioTrabajador(trabajador, fechaInicio, fechaFin);
        }
        
        return salarioTotal;
    }    
    
    public void insertarCliente(Cliente cliente){
		ListaClientes.add(cliente);
	}
    
    public void eliminarCliente(String idCliente) {
        Cliente clienteAEliminar = buscarCliente(idCliente);
        if (clienteAEliminar != null) {
            ListaClientes.remove(clienteAEliminar);
        }
    }

    public Cliente buscarCliente(String idCliente) {
        for (Cliente cliente : ListaClientes) {
            if (cliente.getId().equalsIgnoreCase(idCliente)) {
                return cliente;
            }
        }
        return null;
    }

    public void insertarTrabajador(Trabajador trabajador){
        // Agregar el trabajador a la lista
        ListaTrabajadores.add(trabajador);

        // Establecer la evaluacion del trabajador como "Cumplidor"
        trabajador.setEvaluacion("Cumplidor");
    }
    
    public void eliminarTrabajador(String id) {
        Trabajador trabajadorAEliminar = BuscarTrabajador(id);
        if (trabajadorAEliminar != null) {
            ListaTrabajadores.remove(trabajadorAEliminar);
        }
    }


	
    public void insertarProyecto(Proyecto proyecto){
        ListaProyectos.add(proyecto);
    }
    
    public void insertarContrato(Contrato contrato){
        ListaContratos.add(contrato);
    }
    public String[] getListaSecundaria() {
  		return ListaSecundaria;
  	}

  	public void setListaSecundaria(String[] listaSecundaria) {
  		ListaSecundaria = listaSecundaria;
  	}

  	public String[] getAuxiliarListTrabajadores() {
  		return auxiliarListTrabajadores;
  	}

  	public void setAuxiliarListTrabajadores(String[] auxiliarListTrabajadores) {
  		this.auxiliarListTrabajadores = auxiliarListTrabajadores;
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
    
    public void eliminarProyecto(String idProyecto) {
        Proyecto proyectoAEliminar = buscarProyecto(idProyecto);
        if (proyectoAEliminar != null) {
            ListaProyectos.remove(proyectoAEliminar);
        }
    }

    public Proyecto buscarProyecto(String idProyecto) {
        for (Proyecto proyecto : ListaProyectos) {
            if (proyecto.getIdCliente().equalsIgnoreCase(idProyecto)) {
                return proyecto;
            }
        }
        return null;
    }


    public Contrato obtenerContratoPorProyecto(String idProyecto) {
        for (Contrato contrato : ListaContratos) {
            if (contrato.getIdProyecto().equals(idProyecto)) {
                return contrato;
            }
        }
        return null; // Si no se encuentra ningún contrato con el ID de proyecto proporcionado
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
