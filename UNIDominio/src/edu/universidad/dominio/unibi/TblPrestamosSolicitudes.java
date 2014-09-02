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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Paul Mendoza del Carpio
 */
@Entity
@Table(name = "tbl_prestamos_solicitudes", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_prestamos_solicitudes", name = "seq_prestamos_solicitudes", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblPrestamosSolicitudes.consultarPorFechaSolicitud", query = "SELECT t FROM TblPrestamosSolicitudes t WHERE t.fechaSolicitud = :fechaSolicitud")})
public class TblPrestamosSolicitudes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_prestamos_solicitudes" )
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prestamosSolicitudesId")
    private List<TblPrestamosSolicitudesDetalle> tblPrestamosSolicitudesDetalleList;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblUsuarios usuarioId;

    public TblPrestamosSolicitudes() {
    }

    public TblPrestamosSolicitudes(Integer id) {
        this.id = id;
    }

    public TblPrestamosSolicitudes(Integer id, Date fechaSolicitud) {
        this.id = id;
        this.fechaSolicitud = fechaSolicitud;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public List<TblPrestamosSolicitudesDetalle> getTblPrestamosSolicitudesDetalleList() {
        return tblPrestamosSolicitudesDetalleList;
    }

    public void setTblPrestamosSolicitudesDetalleList(List<TblPrestamosSolicitudesDetalle> tblPrestamosSolicitudesDetalleList) {
        this.tblPrestamosSolicitudesDetalleList = tblPrestamosSolicitudesDetalleList;
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
        if (!(object instanceof TblPrestamosSolicitudes)) {
            return false;
        }
        TblPrestamosSolicitudes other = (TblPrestamosSolicitudes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblPrestamosSolicitudes[ id=" + id + " ]";
    }

}
