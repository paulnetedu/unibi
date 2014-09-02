/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.universidad.dominio.unibi;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Paul Mendoza del Carpio
 */
@Entity
@Table(name = "tbl_sanciones", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_sanciones", name = "seq_sanciones", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblSanciones.consultarPorFechaInicio", query = "SELECT t FROM TblSanciones t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "TblSanciones.consultarPorFechaFin", query = "SELECT t FROM TblSanciones t WHERE t.fechaFin = :fechaFin"),
    @NamedQuery(name = "TblSanciones.consultarPorLector", query = "SELECT t FROM TblSanciones t WHERE t.usuarioId = :usuarioId"),
    @NamedQuery(name = "TblSanciones.consultarPorMotivo", query = "SELECT t FROM TblSanciones t WHERE t.motivo = :motivo")})
public class TblSanciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_sanciones" )
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(name = "motivo")
    private int motivo;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblUsuarios usuarioId;

    public TblSanciones() {
    }

    public TblSanciones(Integer id) {
        this.id = id;
    }

    public TblSanciones(Integer id, Date fechaInicio, Date fechaFin, int motivo) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.motivo = motivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public TblUsuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(TblUsuarios usuarioId) {
        this.usuarioId = usuarioId;
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
        if (!(object instanceof TblSanciones)) {
            return false;
        }
        TblSanciones other = (TblSanciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblSanciones[ id=" + id + " ]";
    }

}
