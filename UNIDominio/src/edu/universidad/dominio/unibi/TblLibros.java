/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.universidad.dominio.unibi;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Paul Mendoza del Carpio
 */
@Entity
@Table(name = "tbl_libros", catalog = "unidb", schema = "sch_unibi")
@DiscriminatorValue("0")
@NamedQueries({
    @NamedQuery(name = "TblLibros.consultarPorTitulo", query = "SELECT t FROM TblLibros t WHERE t.titulo LIKE :titulo"),
    @NamedQuery(name = "TblLibros.consultarPorAutor", query ="SELECT t FROM TblLibros t WHERE t.id in(SELECT t.publicacionId FROM TblPublicacionesAutores t WHERE  t.autorId in (SELECT t.id FROM TblAutores t WHERE t.nombre LIKE :autor))"),
    @NamedQuery(name = "TblLibros.consultarPorIsbn", query = "SELECT t FROM TblLibros t WHERE t.isbn = :isbn"),
    @NamedQuery(name = "TblLibros.consultarPorVolumen", query = "SELECT t FROM TblLibros t WHERE t.volumen = :volumen"),
    @NamedQuery(name = "TblLibros.consultarPorTomo", query = "SELECT t FROM TblLibros t WHERE t.tomo = :tomo"),
    @NamedQuery(name = "TblLibros.consultarPorEdicion", query = "SELECT t FROM TblLibros t WHERE t.edicion = :edicion")})
public class TblLibros extends TblPublicaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "volumen")
    private Integer volumen;
    @Column(name = "tomo")
    private Integer tomo;
    @Basic(optional = false)
    @Column(name = "edicion")
    private int edicion;
    //@JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    //@OneToOne(optional = false)
    //private TblPublicaciones publicacion;
    @JoinColumn(name = "editorial_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblCatalogosItems editorialId;

    public TblLibros() {
    }

    public TblLibros(String id) {
        this.id = id;
    }

    public TblLibros(String id, String isbn, int edicion) {
        this.id = id;
        this.isbn = (isbn == null) ? null : isbn.toUpperCase();
        this.edicion = edicion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = (isbn == null) ? null : isbn.toUpperCase();
    }

    public Integer getVolumen() {
        return volumen;
    }

    public void setVolumen(Integer volumen) {
        this.volumen = volumen;
    }

    public Integer getTomo() {
        return tomo;
    }

    public void setTomo(Integer tomo) {
        this.tomo = tomo;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public TblCatalogosItems getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(TblCatalogosItems editorialId) {
        this.editorialId = editorialId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblLibros)) {
            return false;
        }
        TblLibros other = (TblLibros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblLibros[ id=" + id + " ]";
    }

}
