/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.universidad.dominio.unibi;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Paul Mendoza del Carpio
 */
@Entity
@Table(name = "tbl_publicaciones_areas", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_publicaciones_areas", name = "seq_publicaciones_areas", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblPublicacionesAreas.consultarPorPublicacion", query = "SELECT t FROM TblPublicacionesAreas t WHERE t.publicacionId = :publicacionId")})
public class TblPublicacionesAreas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_publicaciones_areas" )
    private Integer id;
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblPublicaciones publicacionId;
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblCatalogosItems areaId;

    public TblPublicacionesAreas() {
    }

    public TblPublicacionesAreas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblPublicaciones getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(TblPublicaciones publicacionId) {
        this.publicacionId = publicacionId;
    }

    public TblCatalogosItems getAreaId() {
        return areaId;
    }

    public void setAreaId(TblCatalogosItems areaId) {
        this.areaId = areaId;
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
        if (!(object instanceof TblPublicacionesAreas)) {
            return false;
        }
        TblPublicacionesAreas other = (TblPublicacionesAreas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblPublicacionesAreas[ id=" + id + " ]";
    }

}
