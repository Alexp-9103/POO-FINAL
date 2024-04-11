package logico;

import java.io.Serializable;
import java.util.Date;

public abstract class Trabajador implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String Nombre;
    private String Apellido;
    private String Direccion;
    private char Sexo;
    private int Edad;
    private double salarioHora;
    private String Evaluacion;
    private int aniosExperiencia;
    private int cantidadProyectos;


    public Trabajador(String id, String Nombre, String Apellido, String Direccion, char Sexo, int Edad, double salarioHora, String Evaluacion) {
        this.id = id;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Sexo = Sexo;
        this.Edad = Edad;
        this.salarioHora = salarioHora;
        this.Evaluacion = Evaluacion;
        
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public char getSexo() {
		return Sexo;
	}

	public void setSexo(char sexo) {
		Sexo = sexo;
	}

	public int getEdad() {
		return Edad;
	}

	public void setEdad(int edad) {
		Edad = edad;
	}

	public double getSalarioHora() {
		return salarioHora;
	}

	public void setSalarioHora(double salarioHora) {
		this.salarioHora = salarioHora;
	}

	public String getEvaluacion() {
		return Evaluacion;
	}

	public void setEvaluacion(String evaluacion) {
		Evaluacion = evaluacion;
	}
	
	
    public int getAniosExperiencia() {
		return aniosExperiencia;
	}

	public void setAniosExperiencia(int aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
	}
	
	
    public int getCantidadProyectos() {
		return cantidadProyectos;
	}

	public void setCantidadProyectos(int cantidadProyectos) {
		this.cantidadProyectos = cantidadProyectos;
	}
	
    public void aumentarProyectos() {
        cantidadProyectos++;
    }
    
    public void desasociarProyecto() {
        cantidadProyectos--;
    }

	
    public abstract boolean estaDisponible();
    
	public static double calcularSalarioTrabajador(Trabajador trabajador, Date fechaInicio, Date fechaFin) {
        long milisegundosTrabajados = fechaFin.getTime() - fechaInicio.getTime();
        int diasTrabajados = (int) (milisegundosTrabajados / (1000 * 60 * 60 * 24));
        int horasTrabajadasPorDia = 6;
        double salarioTotal = trabajador.getSalarioHora() * diasTrabajados * horasTrabajadasPorDia;
        return salarioTotal;
    }   
	
	public void actualizarEvaluacion(boolean proyectoEntregadoATiempo) {
        if (proyectoEntregadoATiempo) {
            if (Evaluacion.equals("Cumplidor")) {
                Evaluacion = "Destacado";
            } else if (Evaluacion.equals("Incumplidor")) {
                Evaluacion = "Cumplidor";
            }
        } else {
            if (Evaluacion.equals("Cumplidor")) {
                Evaluacion = "Incumplidor";
            } else if (Evaluacion.equals("Destacado")) {
                Evaluacion = "Cumplidor";
            }
        }
    }

}