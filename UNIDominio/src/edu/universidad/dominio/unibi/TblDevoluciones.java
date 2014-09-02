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
@Table(name = "tbl_devoluciones", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_devoluciones", name = "seq_devoluciones", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblDevoluciones.consultarPorFechaDevolucion", query = "SELECT t FROM TblDevoluciones t WHERE t.fechaDevolucion = :fechaDevolucion"),
    @NamedQuery(name = "TblDevoluciones.consultarPorEstadoFisico", query = "SELECT t FROM TblDevoluciones t WHERE t.estadoFisico = :estadoFisico"),
    @NamedQuery(name = "TblDevoluciones.consultarPorDiasAtraso", query = "SELECT t FROM TblDevoluciones t WHERE t.diasAtraso = :diasAtraso")})
public class TblDevoluciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_devoluciones" )
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha_devolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucion;
    @Basic(optional = false)
    @Column(name = "estado_fisico")
    private int estadoFisico;
    @Basic(optional = false)
    @Column(name = "dias_atraso")
    private int diasAtraso;
    @JoinColumn(name = "bibliotecario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblUsuarios bibliotecarioId;
    @JoinColumn(name = "prestamo_detalle_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblPrestamosDetalle prestamoDetalleId;

    public TblDevoluciones() {
    }

    public TblDevoluciones(Integer id) {
        this.id = id;
    }

    public TblDevoluciones(Integer id, Date fechaDevolucion, int estadoFisico, int diasAtraso) {
        this.id = id;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoFisico = estadoFisico;
        this.diasAtraso = diasAtraso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(int estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public int getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(int diasAtraso) {
        this.diasAtraso = diasAtraso;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDevoluciones)) {
            return false;
        }
        TblDevoluciones other = (TblDevoluciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblDevoluciones[ id=" + id + " ]";
    }

}
