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
@Table(name = "tbl_usuarios", catalog = "unidb", schema = "sch_unibi")
@SequenceGenerator(sequenceName="seq_usuarios", name = "seq_usuarios", catalog ="unidb", schema = "sch_unibi",allocationSize=1)
@NamedQueries({
      @NamedQuery(name = "TblUsuarios.consultarPorNombres", query = "SELECT t FROM TblUsuarios t WHERE t.nombres = :nombres"),
    @NamedQuery(name = "TblUsuarios.consultarPorApellidoPaterno", query = "SELECT t FROM TblUsuarios t WHERE t.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "TblUsuarios.consultarPorApellidoMaterno", query = "SELECT t FROM TblUsuarios t WHERE t.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "TblUsuarios.consultarPorTipoDocumento", query = "SELECT t FROM TblUsuarios t WHERE t.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "TblUsuarios.consultarPorEmail", query = "SELECT t FROM TblUsuarios t WHERE t.email = :email"),
    @NamedQuery(name = "TblUsuarios.consultarNroDocumento", query = "SELECT t FROM TblUsuarios t WHERE t.numeroDocumento = :numeroDocumento"),
@NamedQuery(name = "TblUsuarios.consultarHomonimia", query = "SELECT t FROM TblUsuarios t WHERE t.nombres LIKE :nombres and t.apellidoPaterno LIKE :apellidoPaterno and t.apellidoMaterno LIKE :apellidoMaterno and t.numeroDocumento != :documento "),
@NamedQuery(name = "TblUsuarios.consultarPorNombreCompleto", query = "SELECT t FROM TblUsuarios t WHERE CONCAT(t.nombres,' ', t.apellidoPaterno,' ',t.apellidoMaterno) LIKE :nomcompleto"),
@NamedQuery(name = "TblUsuarios.consultarPorNumeroDocumento", query = "SELECT t FROM TblUsuarios t WHERE t.numeroDocumento like :numeroDocumento")})
public class TblUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_usuarios" )
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Basic(optional = false)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @Column(name = "tipo_documento")
    private int tipoDocumento;
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private List<TblSanciones> tblSancionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotecarioId")
    private List<TblBajas> tblBajasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private List<TblUsuariosTipos> tblUsuariosTiposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotecarioId")
    private List<TblDevoluciones> tblDevolucionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private List<TblPrestamosSolicitudes> tblPrestamosSolicitudesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotecarioId")
    private List<TblPrestamos> tblPrestamosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lectorId")
    private List<TblPrestamos> tblPrestamosList1;

    public TblUsuarios() {
    }

    public TblUsuarios(Integer id) {
        this.id = id;
    }

    public TblUsuarios(Integer id, String nombres, String apellidoPaterno, String apellidoMaterno, String numeroDocumento, int tipoDocumento) {
        this.id = id;
        this.nombres = (nombres == null) ? null : nombres.toUpperCase();
        this.apellidoPaterno = (apellidoPaterno == null) ? null : apellidoPaterno.toUpperCase();
        this.apellidoMaterno = (apellidoMaterno == null) ? null : apellidoMaterno.toUpperCase();
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = (nombres == null) ? null : nombres.toUpperCase();
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = (apellidoPaterno == null) ? null : apellidoPaterno.toUpperCase();
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = (apellidoMaterno == null) ? null : apellidoMaterno.toUpperCase();
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TblSanciones> getTblSancionesList() {
        return tblSancionesList;
    }

    public void setTblSancionesList(List<TblSanciones> tblSancionesList) {
        this.tblSancionesList = tblSancionesList;
    }

    public List<TblBajas> getTblBajasList() {
        return tblBajasList;
    }

    public void setTblBajasList(List<TblBajas> tblBajasList) {
        this.tblBajasList = tblBajasList;
    }

    public List<TblUsuariosTipos> getTblUsuariosTiposList() {
        return tblUsuariosTiposList;
    }

    public void setTblUsuariosTiposList(List<TblUsuariosTipos> tblUsuariosTiposList) {
        this.tblUsuariosTiposList = tblUsuariosTiposList;
    }

    public List<TblDevoluciones> getTblDevolucionesList() {
        return tblDevolucionesList;
    }

    public void setTblDevolucionesList(List<TblDevoluciones> tblDevolucionesList) {
        this.tblDevolucionesList = tblDevolucionesList;
    }

    public List<TblPrestamosSolicitudes> getTblPrestamosSolicitudesList() {
        return tblPrestamosSolicitudesList;
    }

    public void setTblPrestamosSolicitudesList(List<TblPrestamosSolicitudes> tblPrestamosSolicitudesList) {
        this.tblPrestamosSolicitudesList = tblPrestamosSolicitudesList;
    }

    public List<TblPrestamos> getTblPrestamosList() {
        return tblPrestamosList;
    }

    public void setTblPrestamosList(List<TblPrestamos> tblPrestamosList) {
        this.tblPrestamosList = tblPrestamosList;
    }

    public List<TblPrestamos> getTblPrestamosList1() {
        return tblPrestamosList1;
    }

    public void setTblPrestamosList1(List<TblPrestamos> tblPrestamosList1) {
        this.tblPrestamosList1 = tblPrestamosList1;
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
        if (!(object instanceof TblUsuarios)) {
            return false;
        }
        TblUsuarios other = (TblUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.universidad.dominio.unibi.TblUsuarios[ id=" + id + " ]";
    }

}
