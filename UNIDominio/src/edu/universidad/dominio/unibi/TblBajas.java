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
@Table(name = "tbl_bajas", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_bajas", name = "seq_bajas", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblBajas.consultarPorMotivo", query = "SELECT t FROM TblBajas t WHERE t.motivo = :motivo"),
    @NamedQuery(name = "TblBajas.consultarPorFechaBaja", query = "SELECT t FROM TblBajas t WHERE t.fechaBaja = :fechaBaja")})
public class TblBajas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_bajas" )
    private Integer id;
    @Basic(optional = false)
    @Column(name = "motivo")
    private int motivo;
    @Basic(optional = false)
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @JoinColumn(name = "bibliotecario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblUsuarios bibliotecarioId;
    @JoinColumn(name = "prestamo_detalle_id", referencedColumnName = "id")
    @ManyToOne
    private TblPrestamosDetalle prestamoDetalleId;
    @JoinColumn(name = "ejemplar_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblEjemplares ejemplarId;

    public TblBajas() {
    }

    public TblBajas(Integer id) {
        this.id = id;
    }

    public TblBajas(Integer id, int motivo, Date fechaBaja) {
        this.id = id;
        this.motivo = motivo;
        this.fechaBaja = fechaBaja;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public TblUsuarios getBibliotecarioId() {
        return bibliotecarioId;
    }

    public void setBibliotecarioId(TblUsuarios bibliotecarioId) {
        this.bibliotecarioId = bibliotecarioId;
    }

    public TblPrestamosDetalle getPrestamoDetalleId() {
        return prestamoDetalleId;
    }

    public void setPrestamoDetalleId(TblPrestamosDetalle prestamoDetalleId) {
        this.prestamoDetalleId = prestamoDetalleId;
    }

    public TblEjemplares getEjemplarId() {
        return ejemplarId;
    }

    public void setEjemplarId(TblEjemplares ejemplarId) {
        this.ejemplarId = ejemplarId;
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
        if (!(object instanceof TblBajas)) {
            return false;
        }
        TblBajas other = (TblBajas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblPerdidas[ id=" + id + " ]";
    }

}
