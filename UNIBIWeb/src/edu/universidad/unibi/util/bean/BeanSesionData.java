package edu.universidad.unibi.util.bean;

import java.io.Serializable;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "beanSesionData")
@SessionScoped
public class BeanSesionData implements Serializable {
    
    
    public BeanSesionData() {
    }

    
}
