package logico;

import java.io.Serializable;
import java.util.Date;

public class Contrato implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String idContrato;
    private String idCliente;
    private String idProyecto;
    private String nombreProyecto;
    private Date fechaInicio;
    private Date fechaEntrega;
    private boolean prorroga;

    public Contrato(String idContrato, String idCliente, String idProyecto, String nombreProyecto, Date fechaInicio, Date fechaEntrega, boolean prorroga) {
        this.idContrato = idContrato;
        this.idCliente = idCliente;
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.prorroga = prorroga;
       
    }

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdProyecto() {
		return idProyecto;
	}
	
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public boolean isProrroga() {
		return prorroga;
	}

	public void setProrroga(boolean prorroga) {
		this.prorroga =prorroga;

	}
	
    public double calcularPenalizacion(Date fechaActual) {
        long diasRetraso = (fechaActual.getTime() - fechaEntrega.getTime()) / (1000 * 60 * 60 * 24);
        double penalizacion = 0.01 * diasRetraso;
        return penalizacion;
    }
	
	public  double calcularCostoProyecto(Date fechaInicio, Date fechaEntrega) {
		double costoProyecto = 0;
		
		JJDCommunications jjd = new JJDCommunications();
		
		costoProyecto = ((jjd.calcularSalarioTotal(fechaInicio,fechaEntrega)*6)*0.25);

		return costoProyecto;
  }		
	
}