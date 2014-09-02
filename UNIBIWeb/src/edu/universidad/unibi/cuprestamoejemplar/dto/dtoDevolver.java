package edu.universidad.unibi.cuprestamoejemplar.dto;

import java.io.Serializable;

import java.util.Date;
public class dtoDevolver implements Serializable  {
    private String fecha="22/05/2014";
    private Boolean estado = true;
    private String cprestamo="25";
    private String cejemplar="ING-34";
    private String titulo="materiales para ingenieria";

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setCprestamo(String cprestamo) {
        this.cprestamo = cprestamo;
    }

    public String getCprestamo() {
        return cprestamo;
    }

    public void setCejemplar(String cejemplar) {
        this.cejemplar = cejemplar;
    }

    public String getCejemplar() {
        return cejemplar;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
