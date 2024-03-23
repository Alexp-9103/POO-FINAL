package logico;

public class Trabajador {
	private String id;
	private String Nombre;
	private String Apellido;
	private String Direccion;
	private char Sexo;
	private int Edad;
	private double salarioHora;
	private String Evaluacion;

	public Trabajador(String id, String Nombre, String Apellidos, String Direccion, char Sexo, int Edad, double salarioHora, String Evaluacion) {
	    this.id = id;
	    this.Nombre = Nombre;
	    this.Apellido = Apellidos;
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

}