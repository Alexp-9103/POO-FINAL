package logico;

import java.util.Date;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	
    private static final int MAX_JEFE_PROYECTO = 1;
    private static final int MAX_PROGRAMADOR = 3;
    private static final int MAX_DISENADOR = 2;
    private static final int MAX_PLANIFICADOR = 1;

    private static int cantidadJefeProyecto = 0;
    private static int cantidadProgramador = 0;
    private static int cantidadDisenador = 0;
    private static int cantidadPlanificador = 0;
    
    public static void StartAgain() {
    	cantidadJefeProyecto = 0;
    	cantidadProgramador = 0;
    	cantidadDisenador = 0;
    	cantidadPlanificador= 0;
    }
    
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
        } catch (FileNotFoundException e) {
            // El archivo no existe, puede ser la primera ejecución
            System.out.println("No se encontró el archivo de datos. Se creará uno nuevo.");
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
            if (proyecto.getIdProyecto().equalsIgnoreCase(idProyecto)) {
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
        return null; // Si no se encuentra ningï¿½n contrato con el ID de proyecto proporcionado
    }

    public static boolean puedeAgregarTrabajador(String tipoTrabajador) {
        switch (tipoTrabajador) {
            case "Jefe de Proyecto":
                return cantidadJefeProyecto < MAX_JEFE_PROYECTO;
            case "Programador":
                return cantidadProgramador < MAX_PROGRAMADOR;
            case "Disenador":
                return cantidadDisenador < MAX_DISENADOR;
            case "Planificador":
                return cantidadPlanificador < MAX_PLANIFICADOR;
            default:
                return false;
        }
    }

    public static void agregarTrabajador(String workerDetails) {
        String[] details = workerDetails.split("\\|");
        String tipoTrabajador = details[3].trim();
        switch (tipoTrabajador) {
            case "Jefe de Proyecto":
                cantidadJefeProyecto++;
                break;
            case "Programador":
                cantidadProgramador++;
                break;
            case "Disenador":
                cantidadDisenador++;
                break;
            case "Planificador":
                cantidadPlanificador++;
                break;
        }
    }

    public static void removeTrabajador(String selectedWorker) {
        String[] details = selectedWorker.split("\\|");
        String tipoTrabajador = details[3].trim();
        switch (tipoTrabajador) {
            case "Jefe de Proyecto":
                cantidadJefeProyecto--;
                break;
            case "Programador":
                cantidadProgramador--;
                break;
            case "Disenador":
                cantidadDisenador--;
                break;
            case "Planificador":
                cantidadPlanificador--;
                break;
        }
    }

    
    

    
    public Contrato BuscarContrato(String idContrato) {
        for (Contrato contrato : ListaContratos) {
            if (contrato.getIdContrato().equalsIgnoreCase(idContrato)) {
                return contrato;
            }
        }
        
        return null;
    }


    public void insertarContrato(Contrato contrato) {
        ListaContratos.add(contrato);
    }


    public boolean existeTrabajadorConIdentificacion(String id) {
        // Iterar sobre la lista de trabajadores para verificar si hay alguno con la misma identificación
        for (Trabajador trabajador : ListaTrabajadores) { // Supongamos que listaTrabajadores es tu lista de trabajadores
            if (trabajador.getId().equals(id)) { // Comparar la identificación del trabajador con la identificación proporcionada
                return true; // Si se encuentra un trabajador con la misma identificación, retornar true
            }
        }
        return false; // Si no se encuentra ningún trabajador con la misma identificación, retornar false
    }

    public Trabajador buscarTrabajadorPorId(String workerId) {
        for (Trabajador trabajador : ListaTrabajadores) {
            if (trabajador.getId().equals(workerId)) {
                return trabajador; 
            }
        }
        return null;
    }
    
    	

}