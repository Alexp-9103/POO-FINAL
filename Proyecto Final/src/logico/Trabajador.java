package logico;

import java.util.Date;

public abstract class Trabajador {
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


    public Trabajador(String id, String Nombre, String Apellido, String Direccion, char Sexo, int Edad, double salarioHora, String Evaluacion, int aniosExperiencia) {
        this.id = id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Sexo = Sexo;
        this.Edad = Edad;
        this.salarioHora = salarioHora;
        this.Evaluacion = Evaluacion;
        this.setAniosExperiencia(aniosExperiencia);
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
    public abstract boolean estaDisponible();

	public static double calcularSalarioTrabajador(Trabajador trabajador, Date fechaInicio, Date fechaFin) {
        // Calculamos el n�mero de milisegundos entre las fechas de inicio y fin
        long milisegundosTrabajados = fechaFin.getTime() - fechaInicio.getTime();
        
        // Convertimos milisegundos a d�as
        int diasTrabajados = (int) (milisegundosTrabajados / (1000 * 60 * 60 * 24));

        // Suponiendo que el trabajador trabaja exactamente 6 horas por d�a
        int horasTrabajadasPorDia = 6;

        // Calculamos el salario total multiplicando el salario por hora por el n�mero de d�as trabajados y horas trabajadas por d�a
        double salarioTotal = trabajador.getSalarioHora() * diasTrabajados * horasTrabajadasPorDia;

        return salarioTotal;
    }
    
    

}