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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Paul Mendoza del Carpio
 */
@Entity
@Table(name = "tbl_catalogos", catalog = "unidb", schema = "sch_unibi")
@NamedQueries({
    @NamedQuery(name = "TblCatalogos.consultarPorDescripcion", query = "SELECT t FROM TblCatalogos t WHERE t.descripcion = :descripcion")})
public class TblCatalogos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catalogoId")
    private List<TblCatalogosItems> tblCatalogosItemsList;

    public TblCatalogos() {
    }

    public TblCatalogos(Integer id) {
        this.id = id;
    }

    public TblCatalogos(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = (descripcion == null) ? null : descripcion.toUpperCase();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = (descripcion == null) ? null : descripcion.toUpperCase();
    }

    public List<TblCatalogosItems> getTblCatalogosItemsList() {
        return tblCatalogosItemsList;
    }

    public void setTblCatalogosItemsList(List<TblCatalogosItems> tblCatalogosItemsList) {
        this.tblCatalogosItemsList = tblCatalogosItemsList;
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
        if (!(object instanceof TblCatalogos)) {
            return false;
        }
        TblCatalogos other = (TblCatalogos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblCatalogos[ id=" + id + " ]";
    }

}
