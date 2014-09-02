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

/**
 *
 * @author Paul Mendoza del Carpio
 */
@Entity
@Table(name = "tbl_prestamos_detalle", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_prestamos_detalle", name = "seq_prestamos_detalle", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblEjemplares.consultarPorCodigo", query ="SELECT t FROM TblEjemplares t WHERE t.id LIKE :codigo"),
    @NamedQuery(name = "TblPrestamosDetalle.consultarPorEstado", query = "SELECT t FROM TblPrestamosDetalle t WHERE t.estado = :estado"),
    @NamedQuery(name = "TblPrestamosDetalle.consultarUltimoPorEjemplarEstado", query = "SELECT t FROM TblPrestamosDetalle t WHERE t.ejemplarId.id = :ejemplarId AND t.estado = :estado ORDER BY t.prestamoId.fechaPrestamo DESC"),
    @NamedQuery(name = "TblPrestamosDetalle.consultarPorEjemplarPrestado", query = "SELECT t FROM TblPrestamosDetalle t WHERE t.ejemplarId.id LIKE :ejemplarId AND t.estado = :estado ORDER BY t.prestamoId.fechaPrestamo DESC"),
    @NamedQuery(name = "TblPrestamosDetalle.consultarPorPrestamo", query = "SELECT t FROM TblPrestamosDetalle t WHERE t.prestamoId.id = :idPrestamo"),
    @NamedQuery(name = "TblPrestamosDetalle.consultarPorEstadoFisico", query = "SELECT t FROM TblPrestamosDetalle t WHERE t.estadoFisico = :estadoFisico")})
public class TblPrestamosDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_prestamos_detalle" )
    private Integer id;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "estado_fisico")
    private int estadoFisico;
    @OneToMany(mappedBy = "prestamoDetalleId")
    private List<TblBajas> tblBajasList;
    @JoinColumn(name = "prestamo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblPrestamos prestamoId;
    @JoinColumn(name = "ejemplar_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblEjemplares ejemplarId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prestamoDetalleId")
    private List<TblDevoluciones> tblDevolucionesList;

    public TblPrestamosDetalle() {
    }

    public TblPrestamosDetalle(Integer id) {
        this.id = id;
    }

    public TblPrestamosDetalle(Integer id, int estado, int estadoFisico) {
        this.id = id;
        this.estado = estado;
        this.estadoFisico = estadoFisico;
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

    public int getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(int estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public List<TblBajas> getTblBajasList() {
        return tblBajasList;
    }

    public void setTblBajasList(List<TblBajas> tblPerdidasList) {
        this.tblBajasList = tblBajasList;
    }

    public TblPrestamos getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(TblPrestamos prestamoId) {
        this.prestamoId = prestamoId;
    }

    public TblEjemplares getEjemplarId() {
        return ejemplarId;
    }

    public void setEjemplarId(TblEjemplares ejemplarId) {
        this.ejemplarId = ejemplarId;
    }

    public List<TblDevoluciones> getTblDevolucionesList() {
        return tblDevolucionesList;
    }

    public void setTblDevolucionesList(List<TblDevoluciones> tblDevolucionesList) {
        this.tblDevolucionesList = tblDevolucionesList;
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
        if (!(object instanceof TblPrestamosDetalle)) {
            return false;
        }
        TblPrestamosDetalle other = (TblPrestamosDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblPrestamosDetalle[ id=" + id + " ]";
    }

}
