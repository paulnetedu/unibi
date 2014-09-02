package edu.universidad.unibi.cuprestamoejemplar.dto;

import java.io.Serializable;

import java.util.Date;
import java.util.Observer;

public class DtoSancion implements Serializable {
    
    private int id;
    //private int usuarioId;
    private Date fechaInicio;
    private Date fechaFin;
    private int motivo;
    private int prestamoDetalleId;
    
    public DtoSancion() {
        
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

   

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setPrestamoDetalleId(int prestamoDetalleId) {
        this.prestamoDetalleId = prestamoDetalleId;
    }

    public int getPrestamoDetalleId() {
        return prestamoDetalleId;
    }
}
