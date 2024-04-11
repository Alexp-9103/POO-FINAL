package logico;

import java.util.Date;
import java.io.File;
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
	public static JJDCommunications JJD = null;
	private static final String FILE_NAME = "data.dat";
	
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
    
    public static JJDCommunications getInstance() {
        if (JJD == null) {
            synchronized (JJDCommunications.class) { // Sincronizar el bloque para garantizar la concurrencia segura
                if (JJD == null) {
                	JJD = new JJDCommunications();
                }
            }
        }
        return JJD;
    }
    
    public void guardarDatos() {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cargarDatos() {
        File archivoDatos = new File(FILE_NAME);
        if (!archivoDatos.exists()) {
            System.out.println("El archivo 'data.dat' no existe.");
            return; // Terminar el m�todo si el archivo no existe
        }

        try (FileInputStream fileIn = new FileInputStream(archivoDatos);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            JJDCommunications instancia = (JJDCommunications) objectIn.readObject();
            // Copiar los datos de la instancia le�da al objeto actual
            this.ListaTrabajadores = instancia.ListaTrabajadores;
            this.ListaClientes = instancia.ListaClientes;
            this.ListaProyectos = instancia.ListaProyectos;
            this.ListaContratos = instancia.ListaContratos;
            // Tambi�n puedes copiar otras variables de instancia si es necesario
        } catch (FileNotFoundException e) {
            // Manejar la excepci�n si el archivo no existe
            System.out.println("El archivo 'data.dat' no se encontr�.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer los datos desde el archivo 'data.dat'");
            e.printStackTrace();
        }
    }


    public void cargarUsuariosDesdeArchivo() {
        File archivoUsuarios = new File("usuarios.dat");
        if (!archivoUsuarios.exists()) {
            try {
                archivoUsuarios.createNewFile();
                System.out.println("Se ha creado un nuevo archivo 'usuarios.dat'");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo 'usuarios.dat'");
                e.printStackTrace();
                return; // Terminar el m�todo si ocurre un error al crear el archivo
            }
        }

        if (archivoUsuarios.length() == 0) {
            System.out.println("El archivo 'usuarios.dat' est� vac�o.");
            return; // Terminar el m�todo si el archivo est� vac�o
        }

        try (FileInputStream fileIn = new FileInputStream(archivoUsuarios);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            ArrayList<User> usuarios = (ArrayList<User>) objectIn.readObject();
            Control.getInstance().setUsuarios(usuarios);
        } catch (FileNotFoundException e) {
            // Manejar la excepci�n si el archivo no existe
            System.out.println("El archivo 'usuarios.dat' no se encontr�.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer los usuarios desde el archivo 'usuarios.dat'");
            e.printStackTrace();
        }
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
	
	public void desasociarTrabajadoresProyecto(String idProyecto) {
	    for (Proyecto proyecto : ListaProyectos) {
	        if (proyecto.getIdProyecto().equals(idProyecto)) {
	            // Obtener la lista de trabajadores asociados al proyecto
	            ArrayList<Trabajador> trabajadoresProyecto = proyecto.getLosTrabajadores();
	            // Establecer el estado de disponibilidad de los trabajadores asociados a true
	            for (Trabajador trabajador : trabajadoresProyecto) {
	                trabajador.estaDisponible();
	            }
	            // Eliminar el proyecto de la lista de proyectos
	            ListaProyectos.remove(proyecto);
	            break;
	        }
	    }
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

    public Proyecto buscarProyecto(String nombreProyecto) {
        for (Proyecto proyecto : ListaProyectos) {
            if (proyecto.getNombre().equalsIgnoreCase(nombreProyecto)) {
                return proyecto;
            }
        }
        return null;
    }


    
    public ArrayList<Proyecto> obtenerProyectosPorCliente(String idCliente) {
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        // Recorrer todos los contratos para buscar los proyectos asociados al cliente
        for (Contrato contrato : ListaContratos) {
            // Verificar si el contrato pertenece al cliente con el ID proporcionado
            if (contrato.getIdCliente().equalsIgnoreCase(idCliente)) {
                // Obtener el proyecto asociado al contrato
                Proyecto proyecto = buscarProyecto(contrato.getIdProyecto());
                if (proyecto != null && !proyectos.contains(proyecto)) {
                    // Si el proyecto no est� en la lista de proyectos asociados al cliente, agregarlo
                    proyectos.add(proyecto);
                }
            }
        }
        return proyectos;
    }




    public Contrato obtenerContratoPorProyecto(String idProyecto) {
        for (Contrato contrato : ListaContratos) {
            if (contrato.getIdProyecto().equals(idProyecto)) {
                return contrato;
            }
        }
        return null;
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

    public void eliminarContrato(String idContrato) {
        Contrato contratoAEliminar = buscarContrato(idContrato);
        if (contratoAEliminar != null) {
            ListaContratos.remove(contratoAEliminar);
        }
    }

    public Contrato buscarContrato(String idContrato) {
        for (Contrato contrato : ListaContratos) {
            if (contrato.getIdContrato().equalsIgnoreCase(idContrato)) {
                return contrato;
            }
        }
        return null;
    }
    
  
    public void prorrogarContrato(Contrato contrato, int diasProrroga) {
        // Obtener la fecha de entrega actual del contrato
        Date fechaEntregaActual = contrato.getFechaEntrega();
        // Calcular la nueva fecha de entrega sumando los días de prórroga
        long tiempoEnMilisegundos = fechaEntregaActual.getTime() + (diasProrroga * 24L * 60 * 60 * 1000);
        Date nuevaFechaEntrega = new Date(tiempoEnMilisegundos);
        // Actualizar la fecha de entrega del contrato
        contrato.setFechaEntrega(nuevaFechaEntrega);
    }

    public double calcularCostoProyecto(Proyecto proyecto) {
        Contrato contrato = obtenerContratoPorProyecto(proyecto.getIdProyecto());
        if (contrato != null) {
            Date fechaInicio = contrato.getFechaInicio();
            Date fechaEntrega = contrato.getFechaEntrega();
            int diasDiferencia = diferenciaDias(fechaInicio, fechaEntrega);
            double costoTotal = 0;
            for (Trabajador trabajador : proyecto.getLosTrabajadores()) {
                costoTotal += diasDiferencia * trabajador.getSalarioHora() * 0.25;
            }
            return costoTotal;
        }
        return 0; 
    }

    public double calcularPenalizacion(Proyecto proyecto) {
        Contrato contrato = obtenerContratoPorProyecto(proyecto.getIdProyecto());
        if (contrato != null && !contrato.isProrroga()) { 
            Date fechaEntrega = contrato.getFechaInicio();
            Date fechaLimite = contrato.getFechaEntrega();
            int diasRetraso = diferenciaDias(fechaEntrega, fechaLimite);
            if (diasRetraso > 0) {
                double penalizacionTotal = 0;
                for (Trabajador trabajador : proyecto.getLosTrabajadores()) {
                    penalizacionTotal += diasRetraso * trabajador.getSalarioHora() * 0.01;
                }
                return penalizacionTotal;
            }
        }
        return 0;
    }


 
    private int diferenciaDias(Date fechaInicio, Date fechaFin) {
        long diferenciaMillis = fechaFin.getTime() - fechaInicio.getTime();
        return (int) (diferenciaMillis / (1000 * 60 * 60 * 24));
    } 
    
    public Proyecto obtenerProyectoPorId(String idProyecto) {
        for (Proyecto proyecto : ListaProyectos) {
            if (proyecto.getIdProyecto().equals(idProyecto)) {
                return proyecto;
            }
        }
        return null;
    }

    
 }
    
    	
