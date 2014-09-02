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
@Table(name = "tbl_prestamos", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_prestamos", name = "seq_prestamos", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblPrestamos.consultarPrestamos", query = "SELECT t FROM TblPrestamos t"),
    @NamedQuery(name = "TblPrestamos.consultarPorEstado", query = "SELECT t FROM TblPrestamos t WHERE t.estado = :estado"),
    @NamedQuery(name = "TblPrestamos.consultarPorFechaPrestamo", query = "SELECT t FROM TblPrestamos t WHERE t.fechaPrestamo = :fechaPrestamo"),
    @NamedQuery(name = "TblPrestamos.consultarPorFechaDevolucionMax", query = "SELECT t FROM TblPrestamos t WHERE t.fechaDevolucionMax = :fechaDevolucionMax")})
public class TblPrestamos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_prestamos" )
    private Integer id;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fecha_prestamo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrestamo;
    @Basic(optional = false)
    @Column(name = "fecha_devolucion_max")
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucionMax;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prestamoId")
    private List<TblPrestamosDetalle> tblPrestamosDetalleList;
    @JoinColumn(name = "bibliotecario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblUsuarios bibliotecarioId;
    @JoinColumn(name = "lector_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblUsuarios lectorId;

    public TblPrestamos() {
    }

    public TblPrestamos(Integer id) {
        this.id = id;
    }

    public TblPrestamos(Integer id, int estado, Date fechaPrestamo, Date fechaDevolucionMax) {
        this.id = id;
        this.estado = estado;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionMax = fechaDevolucionMax;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucionMax() {
        return fechaDevolucionMax;
    }

    public void setFechaDevolucionMax(Date fechaDevolucionMax) {
        this.fechaDevolucionMax = fechaDevolucionMax;
    }

    public List<TblPrestamosDetalle> getTblPrestamosDetalleList() {
        return tblPrestamosDetalleList;
    }

    public void setTblPrestamosDetalleList(List<TblPrestamosDetalle> tblPrestamosDetalleList) {
        this.tblPrestamosDetalleList = tblPrestamosDetalleList;
    }

    public TblUsuarios getBibliotecarioId() {
        return bibliotecarioId;
    }

    public void setBibliotecarioId(TblUsuarios bibliotecarioId) {
        this.bibliotecarioId = bibliotecarioId;
    }

    public TblUsuarios getLectorId() {
        return lectorId;
    }

    public void setLectorId(TblUsuarios lectorId) {
        this.lectorId = lectorId;
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
        if (!(object instanceof TblPrestamos)) {
            return false;
        }
        TblPrestamos other = (TblPrestamos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblPrestamos[ id=" + id + " ]";
    }

}
