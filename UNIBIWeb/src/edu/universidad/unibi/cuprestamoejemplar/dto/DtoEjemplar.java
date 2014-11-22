package edu.universidad.unibi.cuprestamoejemplar.dto;

import java.io.Serializable;

import java.util.Date;

public class DtoEjemplar implements Serializable {
    
    private String id;
    private String volumenLibro;

 
    private String titulo;
    
    private String autores;
    
    private Date fechaPublicacion;
    
    private int estadoDisponibilidad;
    
    private Date fechaDevolucion;

private String autor;
    private int estadoFisico;    

public void setEstadoFisico(int estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public int getEstadoFisico() {
        return estadoFisico;
    }
    public void setAutor(String autores) {
        this.autor = autores;
    }

    public String getAutor() {
        return autor;
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

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getAutores() {
        return autores;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setEstadoDisponibilidad(int estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    public int getEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
    public void setVolumenLibro(String volumenLibro) {
        this.volumenLibro = volumenLibro;
    }

    public String getVolumenLibro() {
        return volumenLibro;
    }
}
