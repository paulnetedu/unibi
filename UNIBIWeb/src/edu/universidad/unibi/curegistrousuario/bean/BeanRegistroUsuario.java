package edu.universidad.unibi.curegistrousuario.bean;

import edu.universidad.unibi.curegistrousuario.BoRegistroUsuario;
import edu.universidad.unibi.curegistrousuario.BoRegistroUsuarioImpl;

import edu.universidad.unibi.curegistrousuario.dto.DtoUsuarios;
import edu.universidad.unibi.util.Bo;
import edu.universidad.unibi.util.JsfUtil;
import edu.universidad.unibi.util.bean.BeanNotificacionData;

import java.io.Serializable;

import java.util.Date;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PreDestroy;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "beanRegistroUsuario")
@SessionScoped
public class BeanRegistroUsuario implements Serializable {

    private BoRegistroUsuario bo;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nroDocumento;
    private String tituloDocumento;
    private String tituloHomonimo;
    private int tipoDocumento;
    private String email;
    private List<DtoUsuarios> lstDtoHomonimos;
    private List<DtoUsuarios> lstDtoDocumento;

    public BeanRegistroUsuario() {
        bo = new BoRegistroUsuarioImpl();
        limpiarOtrosDatos();
    }

    @PreDestroy
    public void destroy() {
        ((Bo) bo).closeEmf();
    }

    public void limpiarOtrosDatos() {
        nombres = null;
        apellidoPaterno = null;
        apellidoMaterno = null;
        nroDocumento = null;
        tipoDocumento = 0;
        email = null;

    }


    public void guardarUsuario() {
        tituloDocumento = "";
        tituloHomonimo = "";
        lstDtoHomonimos = null;
        lstDtoDocumento = null;
        int homonimos;
        String Mensaje;
        if (bo.verificarDocumento(nroDocumento.toString()) > 0) {
            //System.out.println("tamaño de documento existente :" + lstDtoDocumento.size());
            tituloDocumento = "Documento existente";
            lstDtoDocumento = bo.consultarNroDocumento(nroDocumento.toString());
            Mensaje = "No se creo el usuario por que existe un documeto resitrado con el mismo numero!";
        } else {
            bo.registroUsuario(nombres, apellidoPaterno, apellidoMaterno, nroDocumento, tipoDocumento, email);
            homonimos = bo.consultarHomonimo(nombres, apellidoPaterno, apellidoMaterno, nroDocumento).size();
            if (homonimos > 0) {
                tituloHomonimo = "Lista Homonimos";
                //System.out.println("hay homonimos:" + lstDtoHomonimos.size());
                lstDtoHomonimos = bo.consultarHomonimo(nombres, apellidoPaterno, apellidoMaterno, nroDocumento);
                Mensaje = "Se ha creado el nuevo usuario de manera exitosa, pero hay homonimos!";
            } else {
                System.out.println("No hay homonimos y guardo :");
                Mensaje = "Se ha creado el nuevo usuario de manera exitosa!";
                //limpiarOtrosDatos();

            }
            //Mensaje = "Provando"+homonimos;
        }
        limpiarOtrosDatos();
        BeanNotificacionData.show(1, Mensaje);

    }


    public String irActualizacion(Integer id) {
        String pagina = "actualizacionUsuario";
        // System.out.println("Int  : " + id);
        BeanActualizacionUsuario been = new BeanActualizacionUsuario();
        been.setRetorno("nuevoUsuario");
        been.cargarUsuario(id);
        JsfUtil.establecerObjetoSesion("beanActualizacionUsuario", been);
        limpiarOtrosDatos();
        return pagina;
    }

    public void Correo(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // System.out.println("Verificar correo obteniendo nombre :" + nombres);
        if (value.toString().length() > 0) {
            String correo = value.toString();
            if (isEmail(correo)) {
                System.out.println("Mail correcto");
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en Correo",
                                                              "Correo no Valido"));
            }
        }
    }

    public void verificarDocumento(FacesContext context, UIComponent component,
                                   Object value) throws ValidatorException {

        if (value.toString().length() == 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje", "Valor requerido"));
        } else if (!value.toString().matches("[0-9]*")) {

            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje", "Solo Numeros!!"));

        } else if (value.toString().length() < 8) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje",
                                                          "Nro Documento demaciado Corto"));

        }
    }


    public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat =
            Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            //System.out.println("[" + mat.group() + "]");
            return true;
        } else {
            return false;
        }
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


    public List<DtoUsuarios> getLstDtoHomonimos() {
        return lstDtoHomonimos;
    }

    public void setLstDtoHomonimos(List<DtoUsuarios> lstDtoHomonimos) {
        this.lstDtoHomonimos = lstDtoHomonimos;
    }


    public List<DtoUsuarios> getLstDtoDocumento() {
        return lstDtoDocumento;
    }

    public void setLstDtoDocumento(List<DtoUsuarios> lstDtoDocumento) {
        this.lstDtoDocumento = lstDtoDocumento;
    }

    public String getTituloDocumento() {
        return tituloDocumento;
    }

    public void setTituloDocumento(String tituloDocumento) {
        this.tituloDocumento = tituloDocumento;
    }

    public String getTituloHomonimo() {
        return tituloHomonimo;
    }

    public void setTituloHomonimo(String tituloHomonimo) {
        this.tituloHomonimo = tituloHomonimo;
    }
}
