package logico;

import java.util.Date;


public class Contrato {
    private int idContrato;
    private int idCliente;
    private String nombreProyecto;
    private Date fechaInicio;
    private Date fechaEntrega;
    private boolean prorroga;

    public Contrato(int identificador, int idCliente, String nombreProyecto, Date fechaInicio, Date fechaEntrega, boolean prorroga, int idContrato) {
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

