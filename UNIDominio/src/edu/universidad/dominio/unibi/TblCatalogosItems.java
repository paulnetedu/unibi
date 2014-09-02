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
@Table(name = "tbl_catalogos_items", catalog = "unidb", schema = "sch_unibi")
@NamedQueries({
    @NamedQuery(name = "TblCatalogosItems.consultarPorCatalogo", query = "SELECT t FROM TblCatalogosItems t WHERE t.catalogoId = :catalogoId"),
    @NamedQuery(name = "TblCatalogosItems.consultarPorCatalogonombre", query = "SELECT t FROM TblCatalogosItems t WHERE t.catalogoId = :catalogoId AND t.descripcion LIKE :nombre"),
    @NamedQuery(name = "TblCatalogosItems.consultarPorDescripcion", query = "SELECT t FROM TblCatalogosItems t " 
            + "WHERE t.descripcion = :descripcion ")})
public class TblCatalogosItems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "catalogo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblCatalogos catalogoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedencia")
    private List<TblEjemplares> tblEjemplaresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoId")
    private List<TblUsuariosTipos> tblUsuariosTiposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carreraId")
    private List<TblPublicacionesCarreras> tblPublicacionesCarrerasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "editorialId")
    private List<TblLibros> tblLibrosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaId")
    private List<TblPublicacionesAreas> tblPublicacionesAreasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiomaId")
    private List<TblPublicacionesIdiomas> tblPublicacionesIdiomasList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionId")
    private List<TblTesis> tblTesisList1;

    public TblCatalogosItems() {
    }

    public TblCatalogosItems(Integer id) {
        this.id = id;
    }

    public TblCatalogosItems(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = (descripcion == null) ? null : descripcion.toUpperCase();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = (descripcion == null) ? null : descripcion.toUpperCase();
    }

    public TblCatalogos getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(TblCatalogos catalogoId) {
        this.catalogoId = catalogoId;
    }

    public List<TblEjemplares> getTblEjemplaresList() {
        return tblEjemplaresList;
    }

    public void setTblEjemplaresList(List<TblEjemplares> tblEjemplaresList) {
        this.tblEjemplaresList = tblEjemplaresList;
    }

    public List<TblUsuariosTipos> getTblUsuariosTiposList() {
        return tblUsuariosTiposList;
    }

    public void setTblUsuariosTiposList(List<TblUsuariosTipos> tblUsuariosTiposList) {
        this.tblUsuariosTiposList = tblUsuariosTiposList;
    }

    public List<TblPublicacionesCarreras> getTblPublicacionesCarrerasList() {
        return tblPublicacionesCarrerasList;
    }

    public void setTblPublicacionesCarrerasList(List<TblPublicacionesCarreras> tblPublicacionesCarrerasList) {
        this.tblPublicacionesCarrerasList = tblPublicacionesCarrerasList;
    }

    public List<TblLibros> getTblLibrosList() {
        return tblLibrosList;
    }

    public void setTblLibrosList(List<TblLibros> tblLibrosList) {
        this.tblLibrosList = tblLibrosList;
    }

    public List<TblPublicacionesAreas> getTblPublicacionesAreasList() {
        return tblPublicacionesAreasList;
    }

    public void setTblPublicacionesAreasList(List<TblPublicacionesAreas> tblPublicacionesAreasList) {
        this.tblPublicacionesAreasList = tblPublicacionesAreasList;
    }

    public List<TblPublicacionesIdiomas> getTblPublicacionesIdiomasList() {
        return tblPublicacionesIdiomasList;
    }

    public void setTblPublicacionesIdiomasList(List<TblPublicacionesIdiomas> tblPublicacionesIdiomasList) {
        this.tblPublicacionesIdiomasList = tblPublicacionesIdiomasList;
    }

    public List<TblTesis> getTblTesisList1() {
        return tblTesisList1;
    }

    public void setTblTesisList1(List<TblTesis> tblTesisList1) {
        this.tblTesisList1 = tblTesisList1;
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
        if (!(object instanceof TblCatalogosItems)) {
            return false;
        }
        TblCatalogosItems other = (TblCatalogosItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblCatalogosItems[ id=" + id + " ]";
    }

}
