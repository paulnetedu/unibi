/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.universidad.dominio.unibi;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Paul Mendoza del Carpio
 */
@Entity
@Table(name = "tbl_revistas", catalog = "unidb", schema = "sch_unibi")
@DiscriminatorValue("1")
@NamedQueries({
    @NamedQuery(name = "TblRevistas.consultarPorTitulo", query = "SELECT t FROM TblRevistas t WHERE t.titulo LIKE :titulo"),
    @NamedQuery(name = "TblRevistas.consultarPorVolumen", query = "SELECT t FROM TblRevistas t WHERE t.volumen = :volumen"),
    @NamedQuery(name = "TblRevistas.consultarPorNumero", query = "SELECT t FROM TblRevistas t WHERE t.numero = :numero")})
public class TblRevistas extends TblPublicaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "volumen")
    private int volumen;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    //@JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    //@OneToOne(optional = false)
    //private TblPublicaciones publicacion;

    public TblRevistas() {
    }

    public TblRevistas(String id) {
        this.id = id;
    }

    public TblRevistas(String id, int volumen, int numero) {
        this.id = id;
        this.volumen = volumen;
        this.numero = numero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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
        if (!(object instanceof TblRevistas)) {
            return false;
        }
        TblRevistas other = (TblRevistas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblRevistas[ id=" + id + " ]";
    }

}
