package edu.universidad.unibi.curegistrousuario.bean;

import edu.universidad.unibi.curegistrousuario.BoRegistroUsuario;

import edu.universidad.unibi.curegistrousuario.BoRegistroUsuarioImpl;

import edu.universidad.unibi.curegistrousuario.dto.DtoUsuarios;

import edu.universidad.unibi.util.JsfUtil;
import edu.universidad.unibi.util.bean.BeanNotificacionData;

import java.io.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "beanActualizacionUsuario")
@SessionScoped
public class BeanActualizacionUsuario implements Serializable {
    private BoRegistroUsuario bo;
    private Integer id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nroDocumento;
    private String retorno;
    private int tipoDocumento;
    private String email;

    public BeanActualizacionUsuario() {
        bo = new BoRegistroUsuarioImpl();
    }

    public void cargarUsuario(Integer idusu) {
        // System.out.println("Almacenando---");
        DtoUsuarios dto = bo.consultarUsuarioPorId(idusu);
        id = dto.getId();
        nombres = dto.getNombres();
        apellidoPaterno = dto.getApellido_paterno();
        //System.out.println(apellidoPaterno + "dto.getApellido_paterno()" + dto.getApellido_paterno());
        apellidoMaterno = dto.getApellido_materno();
        this.nroDocumento = dto.getNumero_documento();
        this.tipoDocumento = dto.getTipo_documento();
        email = dto.getEmail();
    }

    public void Correo(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        //System.out.println("aaa " + nombres);
        if (value.toString().length() > 0) {
            String correo = value.toString();
            if (!isEmail(correo)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en Correo",
                                                              "Correo no Valido"));
            }
        }
    }

    public void verificarDocumento(FacesContext context, UIComponent component,
                                   Object value) throws ValidatorException {
        if (!value.toString().matches("[0-9]*")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje", "No valido!!"));
        } else if (value.toString().length() < 8) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje",
                                                          "Nro Documento demaciado Corto"));
        }
    }

    public String cancelar() {
        String pagina = retorno;
        //BeanBusquedaUsuario been = new BeanBusquedaUsuario();
        //JsfUtil.establecerObjetoSesion("beanBusquedaUsuario", been);
        return pagina;
    }


    public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat =
            Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        } else {
            return false;
        }
    }


    public void actualizarUsuario() {
        bo.actualizarUsuario(id, nombres, apellidoPaterno, apellidoMaterno, nroDocumento, tipoDocumento, email);
        BeanNotificacionData.show(1, "La actualización fue un exito!");
    }


    public BoRegistroUsuario getBo() {
        return bo;
    }

    public void setBo(BoRegistroUsuario bo) {
        this.bo = bo;
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
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
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

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }
}
