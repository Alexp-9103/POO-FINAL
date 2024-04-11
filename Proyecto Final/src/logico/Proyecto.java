package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    String idProyecto;
    String nombre;
    int cantTrabajadores;
    boolean contratoActivo;
    boolean entregado;
    ArrayList<Trabajador> losTrabajadores;
    Contrato contrato; // Variable para almacenar el contrato asociado al proyecto

    public Proyecto(String idProyecto, String nombre, int cantTrabajadores, boolean contratoActivo, boolean entregado,
            ArrayList<Trabajador> losTrabajadores) {
        super();
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.cantTrabajadores = cantTrabajadores;
        this.contratoActivo = contratoActivo;
        this.entregado = entregado;
        this.losTrabajadores = losTrabajadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantTrabajadores() {
        return cantTrabajadores;
    }

    public void setCantTrabajadores(int cantTrabajadores) {
        this.cantTrabajadores = cantTrabajadores;
    }

    public boolean isContratoActivo() {
        return contratoActivo;
    }

    public void setContratoActivo(boolean contratoActivo) {
        this.contratoActivo = contratoActivo;
    }
    
    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public ArrayList<Trabajador> getLosTrabajadores() {
        return losTrabajadores;
    }

    public void setLosTrabajadores(ArrayList<Trabajador> losTrabajadores) {
        this.losTrabajadores = losTrabajadores;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    public boolean estaEntregadoATiempo() {
        if (contrato != null) {
            Date fechaActual = new Date(); // Obtener la fecha actual
            Date fechaInicioContrato = contrato.getFechaInicio(); // Obtener la fecha de inicio del contrato
            Date fechaEntregaContrato = contrato.getFechaEntrega(); // Obtener la fecha de entrega del contrato
            
            // Impresiones para depuración
            System.out.println("Fecha actual: " + fechaActual);
            System.out.println("Fecha inicio contrato: " + fechaInicioContrato);
            System.out.println("Fecha entrega contrato: " + fechaEntregaContrato);
            
            // Verificar si la fecha actual está dentro del período del contrato
            return (fechaActual.after(fechaInicioContrato) && fechaActual.before(fechaEntregaContrato));
        }
        return false; // Si no hay contrato asociado, asumimos que no se entregó a tiempo
    }



    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
