package edu.universidad.unibi.curegistrocatalogo.dto;

import java.io.Serializable;

public class DtoEjemplar implements Serializable {
    
    private String id;
    
    private String titulo;
    
    private String autor;
    
    private int estadoFisico;
    
    private int estadoDisponibilidad;
    
    
    ///nuevos atributos
    private double valor;
    private String observaciones;
    private int forma;
    private String publicacion_id;
    private int procedencia;
    
    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setForma(int forma) {
        this.forma = forma;
    }

    public int getForma() {
        return forma;
    }

    public void setPublicacion_id(String publicacion_id) {
        this.publicacion_id = publicacion_id;
    }

    public String getPublicacion_id() {
        return publicacion_id;
    }

    public void setProcedencia(int procedencia) {
        this.procedencia = procedencia;
    }

    public int getProcedencia() {
        return procedencia;
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

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setEstadoFisico(int estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public int getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoDisponibilidad(int estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    public int getEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }
}
