package edu.universidad.unibi.curegistrocatalogo.dto;

import java.io.Serializable;

import java.util.Date;

public class DtoResultado implements Serializable {
    
    private String id;
    
    private String titulo;
    
    private Date fechaPublicacion;
    private int tipo;

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public DtoResultado() {
        
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }
}
