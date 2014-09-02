package edu.universidad.unibi.cuprestamoejemplar.dto;

import java.util.Date;

public class DtoDevolucion {
    private int id;
    private int iddetalle;
    private Date fechaDevolucion;
    
    //id del bibliotecario
    private int idBibliotecario=100000;
    
    private int estadoFisicoDev;
    private int diasAtraso;
    
    private int estadoFisicoEjemplar;
    private int estadoDisponibilidadEjemplar;
    private int estadoDetalle;
    private int estadoPrestamo;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setIdBibliotecario(int idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public int getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setEstadoFisicoDev(int estadoFisicoDev) {
        this.estadoFisicoDev = estadoFisicoDev;
    }

    public int getEstadoFisicoDev() {
        return estadoFisicoDev;
    }

    public void setDiasAtraso(int diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public int getDiasAtraso() {
        return diasAtraso;
    }

    public void setEstadoFisicoEjemplar(int estadoFisicoEjemplar) {
        this.estadoFisicoEjemplar = estadoFisicoEjemplar;
    }

    public int getEstadoFisicoEjemplar() {
        return estadoFisicoEjemplar;
    }

    public void setEstadoDisponibilidadEjemplar(int estadoDisponibilidadEjemplar) {
        this.estadoDisponibilidadEjemplar = estadoDisponibilidadEjemplar;
    }

    public int getEstadoDisponibilidadEjemplar() {
        return estadoDisponibilidadEjemplar;
    }

    public void setEstadoDetalle(int estadoDetalle) {
        this.estadoDetalle = estadoDetalle;
    }

    public int getEstadoDetalle() {
        return estadoDetalle;
    }

    public void setEstadoPrestamo(int estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public int getEstadoPrestamo() {
        return estadoPrestamo;
    }

}
