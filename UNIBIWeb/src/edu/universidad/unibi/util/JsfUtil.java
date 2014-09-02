package edu.universidad.unibi.util;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class JsfUtil {
    
    public static Object obtenerObjetoRequest(String nombre) {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext(); 
        Map<String, Object> requestMap = external.getRequestMap();
        return requestMap.get(nombre);
    }
    
    public static Object obtenerObjetoSesion(String nombre) {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext(); 
        Map<String, Object> sessionMap = external.getSessionMap();
        return sessionMap.get(nombre);
    }
    
    public static void establecerObjetoRequest(String nombre, Object o) {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext(); 
        Map<String, Object> requestMap = external.getRequestMap();
        requestMap.put(nombre, o);
    }
    
    public static void establecerObjetoSesion(String nombre, Object o) {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext(); 
        Map<String, Object> sessionMap = external.getSessionMap();
        sessionMap.put(nombre, o);
    }
}
