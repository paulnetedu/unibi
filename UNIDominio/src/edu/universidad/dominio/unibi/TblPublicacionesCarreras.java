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
@Table(name = "tbl_publicaciones_carreras", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_publicaciones_carreras", name = "seq_publicaciones_carreras", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblPublicacionesCarreras.consultarPorPublicacion", query = "SELECT t FROM TblPublicacionesCarreras t WHERE t.publicacionId = :publicacionId")})
public class TblPublicacionesCarreras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_publicaciones_carreras" )
    private Integer id;
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblPublicaciones publicacionId;
    @JoinColumn(name = "carrera_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblCatalogosItems carreraId;

    public TblPublicacionesCarreras() {
    }

    public TblPublicacionesCarreras(Integer id) {
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

    public TblCatalogosItems getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(TblCatalogosItems carreraId) {
        this.carreraId = carreraId;
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
        if (!(object instanceof TblPublicacionesCarreras)) {
            return false;
        }
        TblPublicacionesCarreras other = (TblPublicacionesCarreras) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblPublicacionesCarreras[ id=" + id + " ]";
    }

}
