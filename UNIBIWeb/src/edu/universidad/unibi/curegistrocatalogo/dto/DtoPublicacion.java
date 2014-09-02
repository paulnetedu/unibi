package edu.universidad.unibi.curegistrocatalogo.dto;

import java.io.Serializable;

import java.util.Date;

public class DtoPublicacion implements Serializable {
    
    private String idPublicacion;
    
    private String titulo;
    
    private Date fechaPublicacion;
    
    private int numeroPaginas;
    
    private Double valorFisico;
    
    private Double valorDigital;
    
    private int tipo;
    
    private Integer paisId;
    
    private Integer ciudadId;

    // Libro
    private String isbn;
    
    private Integer volumen;
    
    private Integer tomo;
    
    private Integer edicion;
    
    private Integer editorial;
    
    // Revista
    private Integer numero;
    
    // Tesis
    private Integer tituloOptado;
    
    private Integer institucion;
    
    private int[]listArea;
    private int[]listAutor;
    private int[]listCarreras;
    private int[]listIdioma;
    
    private int area;
    private int autor;
    private int carrera;
    private int idioma;


    public void setArea(int area) {
        this.area = area;
    }

    public int getArea() {
        return area;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public int getAutor() {
        return autor;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }

    public int getCarrera() {
        return carrera;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }

    public int getIdioma() {
        return idioma;
    }

    public void setListArea(int[] listArea) {
        this.listArea = listArea;
    }

    public int[] getListArea() {
        return listArea;
    }

    public void setListAutor(int[] listAutor) {
        this.listAutor = listAutor;
    }

    public int[] getListAutor() {
        return listAutor;
    }

    public void setListCarreras(int[] listCarreras) {
        this.listCarreras = listCarreras;
    }

    public int[] getListCarreras() {
        return listCarreras;
    }

    public void setListIdioma(int[] listIdioma) {
        this.listIdioma = listIdioma;
    }

    public int[] getListIdioma() {
        return listIdioma;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getIdPublicacion() {
        return idPublicacion;
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

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setValorFisico(Double valorFisico) {
        this.valorFisico = valorFisico;
    }

    public Double getValorFisico() {
        return valorFisico;
    }

    public void setValorDigital(Double valorDigital) {
        this.valorDigital = valorDigital;
    }

    public Double getValorDigital() {
        return valorDigital;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }

    public Integer getPaisId() {
        return paisId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setVolumen(Integer volumen) {
        this.volumen = volumen;
    }

    public Integer getVolumen() {
        return volumen;
    }

    public void setTomo(Integer tomo) {
        this.tomo = tomo;
    }

    public Integer getTomo() {
        return tomo;
    }

    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    public Integer getEdicion() {
        return edicion;
    }

    public void setEditorial(Integer editorial) {
        this.editorial = editorial;
    }

    public Integer getEditorial() {
        return editorial;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setTituloOptado(Integer tituloOptado) {
        this.tituloOptado = tituloOptado;
    }

    public Integer getTituloOptado() {
        return tituloOptado;
    }

    public void setInstitucion(Integer institucion) {
        this.institucion = institucion;
    }

    public Integer getInstitucion() {
        return institucion;
    }
}
