package edu.universidad.unibi.cubusquedaejemplar.dto;

import java.io.Serializable;

public class dtoBusquedaEjemplar implements Serializable{
    public dtoBusquedaEjemplar(String volumenLibro, String codigo, String titulo, String autor, String area, String estadoFisico, String disponibilidad, String fechaDevolucion) {
        super();
        
        this.codigo=codigo;
        this.titulo=titulo;
        this.autor=autor;
        this.area=area;
        this.estadoFisico=estadoFisico;
        this.disponibilidad=disponibilidad;
        this.fechaDevolucion=fechaDevolucion;
        this.volumenLibro=volumenLibro;
    }
    public dtoBusquedaEjemplar()
     {}

    protected String Id;
    protected String codigo;
    protected String titulo;
    protected String autor;
    protected String area;
      protected String  volumenLibro;

    protected String estadoFisico;
    protected String disponibilidad;
    protected String fechaDevolucion;
    protected Boolean disponible = true;
    protected Boolean seleccionado = false;
       

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Boolean getDisponible() {
        return disponible;
    }
   

    public void setId(String Id) {
           this.Id = Id;
    }

    public void setVolumenLibro(String volumenLibro) {
        this.volumenLibro = volumenLibro;
    }

    public String getVolumenLibro() {
        return volumenLibro;
    }
     public String getId() {
           return Id;
    }
     
    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Boolean getSeleccionado() {
        return seleccionado;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
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

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setEstadoFisico(String estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public String getEstadoFisico() {
        return estadoFisico;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

}