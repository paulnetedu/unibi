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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Paul Mendoza del Carpio
 */
@Entity
@Table(name = "tbl_ejemplares", catalog = "unidb", schema = "sch_unibi")
@NamedQueries({
    @NamedQuery(name = "TblEjemplares.consultarPorId", query = "SELECT t FROM TblEjemplares t WHERE t.id = :id"),
    @NamedQuery(name = "TblEjemplares.consultarPorTitulo", query = "SELECT t FROM TblEjemplares t WHERE t.publicacionId.titulo LIKE :titulo"),
    @NamedQuery(name = "TblEjemplares.consultarPorPublicacion", query = "SELECT t FROM TblEjemplares t WHERE t.publicacionId = :publicacion"),
    @NamedQuery(name = "TblEjemplares.consultarPorEstadoFisico", query = "SELECT t FROM TblEjemplares t WHERE t.estadoFisico = :estadoFisico"),
    @NamedQuery(name = "TblEjemplares.consultarPorEstadoDisponibilidad", query = "SELECT t FROM TblEjemplares t WHERE t.estadoDisponibilidad = :estadoDisponibilidad"),
    @NamedQuery(name = "TblEjemplares.consultarPorValor", query = "SELECT t FROM TblEjemplares t WHERE t.valor = :valor"),
    @NamedQuery(name = "TblEjemplares.consultarPorObservaciones", query = "SELECT t FROM TblEjemplares t WHERE t.observaciones = :observaciones"),
    @NamedQuery(name = "TblEjemplares.consultarPorForma", query = "SELECT t FROM TblEjemplares t WHERE t.forma = :forma")})
public class TblEjemplares implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "estado_fisico")
    private int estadoFisico;
    @Basic(optional = false)
    @Column(name = "estado_disponibilidad")
    private int estadoDisponibilidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "forma")
    private int forma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejemplarId")
    private List<TblPrestamosSolicitudesDetalle> tblPrestamosSolicitudesDetalleList;
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblPublicaciones publicacionId;
    @JoinColumn(name = "procedencia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblCatalogosItems procedencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejemplarId")
    private List<TblBajas> tblBajasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejemplarId")
    private List<TblPrestamosDetalle> tblPrestamosDetalleList;

    public TblEjemplares() {
    }

    public TblEjemplares(String id) {
        this.id = id;
    }

    public TblEjemplares(String id, int estadoFisico, int estadoDisponibilidad, int forma) {
        this.id = id;
        this.estadoFisico = estadoFisico;
        this.estadoDisponibilidad = estadoDisponibilidad;
        this.forma = forma;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(int estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public int getEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }

    public void setEstadoDisponibilidad(int estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getForma() {
        return forma;
    }

    public void setForma(int forma) {
        this.forma = forma;
    }

    public List<TblPrestamosSolicitudesDetalle> getTblPrestamosSolicitudesDetalleList() {
        return tblPrestamosSolicitudesDetalleList;
    }

    public void setTblPrestamoSolicitudDetalleList(List<TblPrestamosSolicitudesDetalle> tblPrestamosSolicitudesDetalleList) {
        this.tblPrestamosSolicitudesDetalleList = tblPrestamosSolicitudesDetalleList;
    }

    public TblPublicaciones getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(TblPublicaciones publicacionId) {
        this.publicacionId = publicacionId;
    }

    public TblCatalogosItems getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(TblCatalogosItems procedencia) {
        this.procedencia = procedencia;
    }

    public List<TblBajas> getTblBajasList() {
        return tblBajasList;
    }

    public void setTblBajasList(List<TblBajas> tblBajasList) {
        this.tblBajasList = tblBajasList;
    }

    public List<TblPrestamosDetalle> getTblPrestamosDetalleList() {
        return tblPrestamosDetalleList;
    }

    public void setTblPrestamosDetalleList(List<TblPrestamosDetalle> tblPrestamosDetalleList) {
        this.tblPrestamosDetalleList = tblPrestamosDetalleList;
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
        if (!(object instanceof TblEjemplares)) {
            return false;
        }
        TblEjemplares other = (TblEjemplares) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblEjemplares[ id=" + id + " ]";
    }

}
