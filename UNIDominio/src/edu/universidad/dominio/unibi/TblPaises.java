/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.universidad.dominio.unibi;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Paul Mendoza del Carpio
 */
@Entity
@Table(name = "tbl_paises", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_libros", name = "seq_libros", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblPaises.consultarPaises", query = "SELECT t FROM TblPaises t"),
    @NamedQuery(name = "TblPaises.consultarPorNombre", query = "SELECT t FROM TblPaises t WHERE t.nombre = :nombre")})
public class TblPaises implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_paises" )
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paisId")
    private List<TblCiudades> tblCiudadesList;
    @OneToMany(mappedBy = "paisId")
    private List<TblPublicaciones> tblPublicacionesList;

    public TblPaises() {
    }

    public TblPaises(Integer id) {
        this.id = id;
    }

    public TblPaises(Integer id, String nombre) {
        this.id = id;
        this.nombre = (nombre == null) ? null : nombre.toUpperCase();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = (nombre == null) ? null : nombre.toUpperCase();
    }

    public List<TblCiudades> getTblCiudadesList() {
        return tblCiudadesList;
    }

    public void setTblCiudadesList(List<TblCiudades> tblCiudadesList) {
        this.tblCiudadesList = tblCiudadesList;
    }

    public List<TblPublicaciones> getTblPublicacionesList() {
        return tblPublicacionesList;
    }

    public void setTblPublicacionesList(List<TblPublicaciones> tblPublicacionesList) {
        this.tblPublicacionesList = tblPublicacionesList;
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
        if (!(object instanceof TblPaises)) {
            return false;
        }
        TblPaises other = (TblPaises) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblPaises[ id=" + id + " ]";
    }

}
