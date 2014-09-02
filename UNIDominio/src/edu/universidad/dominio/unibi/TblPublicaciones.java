/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.universidad.dominio.unibi;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Paul Mendoza del Carpio
 */
@Entity
@Table(name = "tbl_publicaciones", catalog = "unidb", schema = "sch_unibi")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo", discriminatorType=DiscriminatorType.INTEGER)
@NamedQueries({
    @NamedQuery(name = "TblPublicaciones.consultarPorFechaPublicacion", query = "SELECT t FROM TblPublicaciones t WHERE t.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "TblPublicaciones.consultarPorNumeroPaginas", query = "SELECT t FROM TblPublicaciones t WHERE t.numeroPaginas = :numeroPaginas"),
    @NamedQuery(name = "TblPublicaciones.consultarPorValorFisico", query = "SELECT t FROM TblPublicaciones t WHERE t.valorFisico = :valorFisico"),
    @NamedQuery(name = "TblPublicaciones.consultarPorValorDigital", query = "SELECT t FROM TblPublicaciones t WHERE t.valorDigital = :valorDigital"),
    @NamedQuery(name = "TblPublicaciones.consultarPorTipo", query = "SELECT t FROM TblPublicaciones t WHERE t.tipo = :tipo")})
public class TblPublicaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "fecha_publicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @Column(name = "numero_paginas")
    private Integer numeroPaginas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_fisico")
    private Double valorFisico;
    @Column(name = "valor_digital")
    private Double valorDigital;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicacionId")
    private List<TblEjemplares> tblEjemplaresList;
    //@OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    //private TblRevistas tblRevista;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicacionId")
    private List<TblPublicacionesCarreras> tblPublicacionesCarrerasList;
    //@OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    //private TblLibros tblLibro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicacionId")
    private List<TblPublicacionesAreas> tblPublicacionesAreasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicacionId")
    private List<TblPublicacionesIdiomas> tblPublicacionesIdiomasList;
    @JoinColumn(name = "pais_id", referencedColumnName = "id")
    @ManyToOne
    private TblPaises paisId;
    @JoinColumn(name = "ciudad_id", referencedColumnName = "id")
    @ManyToOne
    private TblCiudades ciudadId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicacionId")
    private List<TblPublicacionesAutores> tblPublicacionesAutoresList;
    //@OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    //private TblTesis tblTesis;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicacionId")
    private List<TblContenidos> tblContenidosList;

    public TblPublicaciones() {
    }

    public TblPublicaciones(String id) {
        this.id = id;
    }

    public TblPublicaciones(String id, String titulo, Date fechaPublicacion, int tipo) {
        this.id = id;
        this.titulo = (titulo == null) ? null : titulo.toUpperCase();
        this.fechaPublicacion = fechaPublicacion;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = (titulo == null) ? null : titulo.toUpperCase();
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Double getValorFisico() {
        return valorFisico;
    }

    public void setValorFisico(Double valorFisico) {
        this.valorFisico = valorFisico;
    }

    public Double getValorDigital() {
        return valorDigital;
    }

    public void setValorDigital(Double valorDigital) {
        this.valorDigital = valorDigital;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<TblEjemplares> getTblEjemplaresList() {
        return tblEjemplaresList;
    }

    public void setTblEjemplaresList(List<TblEjemplares> tblEjemplaresList) {
        this.tblEjemplaresList = tblEjemplaresList;
    }

    public List<TblPublicacionesCarreras> getTblPublicacionesCarrerasList() {
        return tblPublicacionesCarrerasList;
    }

    public void setTblPublicacionesCarrerasList(List<TblPublicacionesCarreras> tblPublicacionesCarrerasList) {
        this.tblPublicacionesCarrerasList = tblPublicacionesCarrerasList;
    }

    public List<TblPublicacionesAreas> getTblPublicacionesAreasList() {
        return tblPublicacionesAreasList;
    }

    public void setTblPublicacionesAreasList(List<TblPublicacionesAreas> tblPublicacionesAreasList) {
        this.tblPublicacionesAreasList = tblPublicacionesAreasList;
    }

    public List<TblPublicacionesIdiomas> getTblPublicacionesIdiomasList() {
        return tblPublicacionesIdiomasList;
    }

    public void setTblPublicacionesIdiomasList(List<TblPublicacionesIdiomas> tblPublicacionesIdiomasList) {
        this.tblPublicacionesIdiomasList = tblPublicacionesIdiomasList;
    }

    public TblCiudades getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(TblCiudades ciudadId) {
        this.ciudadId = ciudadId;
    }

    public List<TblPublicacionesAutores> getTblPublicacionesAutoresList() {
        return tblPublicacionesAutoresList;
    }

    public void setTblPublicacionesAutoresList(List<TblPublicacionesAutores> tblPublicacionesAutoresList) {
        this.tblPublicacionesAutoresList = tblPublicacionesAutoresList;
    }

    /*public void setTblRevista(TblRevistas tblRevista) {
        this.tblRevista = tblRevista;
    }

    public TblRevistas getTblRevista() {
        return tblRevista;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public void setPaisId(TblPaises paisId) {
        this.paisId = paisId;
    }

    public TblPaises getPaisId() {
        return paisId;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPublicaciones)) {
            return false;
        }
        TblPublicaciones other = (TblPublicaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public void setTblContenidosList(List<TblContenidos> tblContenidosList) {
        this.tblContenidosList = tblContenidosList;
    }

    public List<TblContenidos> getTblContenidosList() {
        return tblContenidosList;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblPublicaciones[ id=" + id + ", pubAutores=" 
               + ((tblPublicacionesAutoresList == null) ? 0 : tblPublicacionesAutoresList.size()) + " ]";
    }

}
