package edu.universidad.unibi.curegistrousuario.bean;

import edu.universidad.unibi.curegistrousuario.BoRegistroUsuario;

import edu.universidad.unibi.curegistrousuario.BoRegistroUsuarioImpl;
import edu.universidad.unibi.curegistrousuario.dto.DtoUsuarios;
import edu.universidad.unibi.util.Bo;

import edu.universidad.unibi.util.JsfUtil;

import edu.universidad.unibi.util.bean.BeanNotificacionData;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PreDestroy;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "beanBusquedaUsuario")
@SessionScoped
public class BeanBusquedaUsuario implements Serializable {
    private BoRegistroUsuario bo;
    private String textoBusqueda;
    private String Nombres;
    private String numero_documento;
    private List<DtoUsuarios> lstDtoUsuarios;

    /**
     * Tipo de busqueda:
     * 0 por DNI
     * 1 por Nombre
     */
    private int tipoBusqueda;

    public BeanBusquedaUsuario() {
        limpiarOtrosDatos();
    }

    @PreDestroy
    public void destroy() {
        ((Bo) bo).closeEmf();
    }

    public void limpiarOtrosDatos() {
        textoBusqueda = "";
        Nombres = "";
        numero_documento = "";
        lstDtoUsuarios = null;
        tipoBusqueda = 0;
        bo = new BoRegistroUsuarioImpl();
    }

    public void buscarUsuario() {
        if (tipoBusqueda == 0) {
            if (textoBusqueda.matches("[0-9]*")) {
                lstDtoUsuarios = bo.consultarUsuarioPorDocumento(textoBusqueda);
            } else {
                BeanNotificacionData.show(1, "Solo permite Numero!");
            }

        } else {
            if (textoBusqueda.matches("[A-Za-z ]*")) {
                lstDtoUsuarios = bo.consultarUsuario(textoBusqueda);
            } else {
                BeanNotificacionData.show(1, "Solo permite Letras");
            }
        }
    }


    public String irActualizacionUsuario(Integer id) {
        String pagina = "actualizacionUsuario";
        BeanActualizacionUsuario beeen = new BeanActualizacionUsuario();
        beeen.setRetorno("busquedaUsuario");
        beeen.cargarUsuario(id);
        JsfUtil.establecerObjetoSesion("beanActualizacionUsuario", beeen);
        limpiarOtrosDatos();
        return pagina;
    }


    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public List<DtoUsuarios> getLstDtoUsuarios() {
        return lstDtoUsuarios;
    }

    public void setLstDtoUsuarios(List<DtoUsuarios> lstDtoUsuario) {
        this.lstDtoUsuarios = lstDtoUsuario;
    }

    public int getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(int tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }
}
