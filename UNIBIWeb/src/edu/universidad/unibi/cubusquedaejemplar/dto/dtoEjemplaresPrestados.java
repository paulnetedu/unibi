package edu.universidad.unibi.cubusquedaejemplar.dto;

import java.util.Date;

public class dtoEjemplaresPrestados {
    
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

    public void setEstadoEjemplar(String estadoEjemplar) {
        this.estadoEjemplar = estadoEjemplar;
    }

    public String getEstadoEjemplar() {
        return estadoEjemplar;
    }
    public void setFechaDevolucionMax(String fechaDevolucionMax) {
        this.fechaDevolucionMax = fechaDevolucionMax;
    }

    public String getFechaDevolucionMax() {
        return fechaDevolucionMax;
    }
      
    protected String codigo;
    protected String titulo;
    protected String autor;
    protected String estadoEjemplar;
    protected String fechaDevolucionMax;



    public dtoEjemplaresPrestados(String codigo,String titulo,String autor,String estadoEjemplar,String fechaDevolucionMax) {
        this.codigo=codigo;
        this.titulo=titulo;
        this.autor=autor;
        this.estadoEjemplar=estadoEjemplar;
        this.fechaDevolucionMax=fechaDevolucionMax;
    }
}
