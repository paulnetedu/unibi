package edu.universidad.unibi.cuprestamoejemplar.dto;

import java.io.Serializable;

import java.util.Date;

public class DtoPrestamo implements Serializable{

        private int id;
        private String idejemplar;
        private String titulo;
       private Date fechaDevolucion;
       private int estadoFisico;
       private int estadofisico_devolucion;
       private int estado_detalle_prestamo;
        private int estadoPrestamo;
        private int idDetalle;
        private int estadoDevolucionTa;

    public void setEstadoDevolucionTa(int estadoDevolucionTa) {
        this.estadoDevolucionTa = estadoDevolucionTa;
    }

    public int getEstadoDevolucionTa() {
        return estadoDevolucionTa;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setEstadoFisico(int estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public int getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadofisico_devolucion(int estadofisico_devolucion) {
        this.estadofisico_devolucion = estadofisico_devolucion;
    }

    public int getEstadofisico_devolucion() {
        return estadofisico_devolucion;
    }

    public void setEstado_detalle_prestamo(int estado_detalle_prestamo) {
        this.estado_detalle_prestamo = estado_detalle_prestamo;
    }

    public int getEstado_detalle_prestamo() {
        return estado_detalle_prestamo;
    }

    public void setEstadoPrestamo(Integer estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public Integer getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setIdejemplar(String idejemplar) {
        this.idejemplar = idejemplar;
    }

    public String getIdejemplar() {
        return idejemplar;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    

 
}
