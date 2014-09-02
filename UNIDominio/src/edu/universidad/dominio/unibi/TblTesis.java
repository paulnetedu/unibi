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
@Table(name = "tbl_tesis", catalog = "unidb", schema = "sch_unibi")
@DiscriminatorValue("2")
@NamedQueries({
    @NamedQuery(name = "TblTesis.consultarPorTitulo", query = "SELECT t FROM TblTesis t WHERE t.titulo LIKE :titulo"),
    @NamedQuery(name = "TblTesis.consultarPorTituloOptado", query = "SELECT t FROM TblTesis t WHERE t.tituloOptado = :tituloOptado")})
public class TblTesis extends TblPublicaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    //@JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    //@OneToOne(optional = false)
    //private TblPublicaciones publicacion;
    
    @Column(name = "titulo_optado")
    private int tituloOptado;
    
    @JoinColumn(name = "institucion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblCatalogosItems institucionId;

    public TblTesis() {
    }

    public TblTesis(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TblCatalogosItems getInstitucionId() {
        return institucionId;
    }

    public void setInstitucionId(TblCatalogosItems institucionId) {
        this.institucionId = institucionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public void setTituloOptado(int tituloOptado) {
        this.tituloOptado = tituloOptado;
    }

    public int getTituloOptado() {
        return tituloOptado;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTesis)) {
            return false;
        }
        TblTesis other = (TblTesis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblTesis[ id=" + id + " ]";
    }

}
