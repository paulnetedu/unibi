/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.universidad.dominio.unibi;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Paul Mendoza del Carpio
 */
@Entity
@Table(name = "tbl_ciudades", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_ciudades", name = "seq_ciudades", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblCiudades.consultarPorIdpais", query = "SELECT t FROM TblCiudades t WHERE t.paisId = :paisid"),
    @NamedQuery(name = "TblCiudades.consultarPorNombre", query = "SELECT t FROM TblCiudades t WHERE t.nombre = :nombre")})
public class TblCiudades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_ciudades" )
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "pais_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblPaises paisId;
    @OneToMany(mappedBy = "ciudadId")
    private List<TblPublicaciones> tblPublicacionesList;

    public TblCiudades() {
    }

    public TblCiudades(Integer id) {
        this.id = id;
    }

    public TblCiudades(Integer id, String nombre) {
        this.id = id;
        this.nombre = (nombre == null) ? null : nombre.toUpperCase();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = (nombre == null) ? null : nombre.toUpperCase();
    }

    public TblPaises getPaisId() {
        return paisId;
    }

    public void setPaisId(TblPaises paisId) {
        this.paisId = paisId;
    }

    public List<TblPublicaciones> getTblPublicacionesList() {
        return tblPublicacionesList;
    }

    public void setTblPublicacionesList(List<TblPublicaciones> tblPublicacionesList) {
        this.tblPublicacionesList = tblPublicacionesList;
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
        if (!(object instanceof TblCiudades)) {
            return false;
        }
        TblCiudades other = (TblCiudades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblCiudades[ id=" + id + " ]";
    }

}
