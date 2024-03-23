package logico;

import java.time.LocalDate;

public class Contrato {
    private int idContrato;
    private int idCliente;
    private String nombreProyecto;
    private LocalDate fechaInicio;
    private LocalDate fechaEntrega;
    private boolean prorroga;

    public Contrato(int identificador, int idCliente, String nombreProyecto, LocalDate fechaInicio, LocalDate fechaEntrega, boolean prorroga, int idContrato) {
        this.idContrato = idContrato;
        this.idCliente = idCliente;
        this.nombreProyecto = nombreProyecto;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.prorroga = prorroga;
       
    }

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public boolean isProrroga() {
		return prorroga;
	}

	public void setProrroga(boolean prorroga) {
		this.prorroga = prorroga;
	}   
}

