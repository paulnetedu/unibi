package edu.universidad.unibi.util.bean;

import edu.universidad.unibi.util.JsfUtil;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "beanNotificacionData")
@RequestScoped
public class BeanNotificacionData {
    
    private static final String BEAN_NAME = "beanNotificacionData";
    
    /**
     * 0 no mostrar mensaje de notificación
     * 1 mostrar mensaje de notificación
     */
    private int tipo;
    
    /**
     * Mensaje a mostrar en la notificacion
     */
    private String mensaje;
    
    public BeanNotificacionData() {
    }
    
    public static BeanNotificacionData lookup() {
        Object o = JsfUtil.obtenerObjetoRequest(BEAN_NAME);
        if (o == null) {
            o = new BeanNotificacionData();
            JsfUtil.establecerObjetoRequest(BEAN_NAME, o);
        } 
        return (BeanNotificacionData) o;
    }

    public static void show(int tipo, String mensaje) {
        BeanNotificacionData data = lookup();
        data.tipo = tipo;
        data.mensaje = mensaje;
    }


    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
