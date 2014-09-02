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
@Table(name = "tbl_autores", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_autores", name = "seq_autores", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "TblAutores.consultarAutor", query = "SELECT t FROM TblAutores t"),
    @NamedQuery(name = "TblAutores.consultarPorNombreautor", query = "SELECT t FROM TblAutores t WHERE t.nombre LIKE :nombre"),
    @NamedQuery(name = "TblAutores.consultarPorNombre", query = "SELECT t FROM TblAutores t " 
            + "WHERE t.nombre = :nombre")})
public class TblAutores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_autores" )
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autorId")
    private List<TblPublicacionesAutores> tblPublicacionesAutoresList;

    public TblAutores() {
    }

    public TblAutores(Integer id) {
        this.id = id;
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

    public List<TblPublicacionesAutores> getTblPublicacionesAutoresList() {
        return tblPublicacionesAutoresList;
    }

    public void setTblPublicacionesAutoresList(List<TblPublicacionesAutores> tblPublicacionesAutoresList) {
        this.tblPublicacionesAutoresList = tblPublicacionesAutoresList;
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
        if (!(object instanceof TblAutores)) {
            return false;
        }
        TblAutores other = (TblAutores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblAutores[ id=" + id + " ]";
    }

}
