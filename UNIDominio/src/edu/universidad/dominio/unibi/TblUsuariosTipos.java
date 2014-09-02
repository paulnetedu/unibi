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
@Table(name = "tbl_usuarios_tipos", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_usuarios_tipos", name = "seq_usuarios_tipos", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblUsuariosTipos.consultarPorUsuario", query = "SELECT t FROM TblUsuariosTipos t WHERE t.usuarioId = :usuarioId")})
public class TblUsuariosTipos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_usuarios_tipos" )
    private Integer id;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblUsuarios usuarioId;
    @JoinColumn(name = "tipo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblCatalogosItems tipoId;

    public TblUsuariosTipos() {
    }

    public TblUsuariosTipos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblUsuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(TblUsuarios usuarioId) {
        this.usuarioId = usuarioId;
    }

    public TblCatalogosItems getTipoId() {
        return tipoId;
    }

    public void setTipoId(TblCatalogosItems tipoId) {
        this.tipoId = tipoId;
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
        if (!(object instanceof TblUsuariosTipos)) {
            return false;
        }
        TblUsuariosTipos other = (TblUsuariosTipos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblUsuariosTipos[ id=" + id + " ]";
    }

}
