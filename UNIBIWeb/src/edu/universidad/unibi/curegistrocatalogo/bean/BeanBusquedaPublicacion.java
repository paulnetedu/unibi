package edu.universidad.unibi.curegistrocatalogo.bean;

import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogo;

import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogoImpl;

import edu.universidad.unibi.curegistrocatalogo.dto.DtoEjemplar;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoResultado;
import edu.universidad.unibi.util.Bo;

import edu.universidad.unibi.util.JsfUtil;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PreDestroy;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "beanBusquedaPublicacion")
@SessionScoped
public class BeanBusquedaPublicacion implements Serializable {
    
    private BoRegistroCatalogo bo;
    
    private String textoBusqueda;
    
    /**
     * Tipo de busqueda:
     * 0 por titulo
     * 1 por autor
     */
    private int tipoBusqueda;
    
    private List<DtoResultado> lstDtoResultado;
    
    private List<DtoEjemplar> lstDtoEjemplar;
    
    public BeanBusquedaPublicacion() {
        bo = new BoRegistroCatalogoImpl();
    }
    
    @PreDestroy
    public void destroy() {
        ((Bo) bo).closeEmf();
    }
    
    public void buscarPublicaciones()
    {
        lstDtoResultado=null;
        if (tipoBusqueda == 0)
        {
            buscarPublicacionesPorTitulo();
        }
        else
        {
            buscarPublicacionesPorAutor();
        }
    }
    public void buscarPublicacionesPorTitulo() {
        System.out.println("buscarPublicacionesPorTitulo");
        lstDtoResultado =  bo.consultarPublicacionPorTitulo(textoBusqueda);
    }
    public void buscarPublicacionesPorAutor() {
        System.out.println("buscarPublicacionesPorAutor");
        lstDtoResultado =  bo.consultarPublicacionPorAutor(textoBusqueda);
    }
    public String irBuscarEjemplares(String idPublicacion) {
        String pagina = "busquedaEjemplares";
        BeanBusquedaEjemplares bean = new BeanBusquedaEjemplares();
        bean.cargarEjemplares(idPublicacion);
        JsfUtil.establecerObjetoSesion("beanBusquedaEjemplares", bean);
        return pagina;
    } 
    public String irActualizacionPublicacion(String idPublicacion) {
        String pagina = "IrActualizacionPublicacion";
        BeanActualizacionPublicacion bean = new BeanActualizacionPublicacion();
        bean.cargarPublicacion(idPublicacion);
        JsfUtil.establecerObjetoSesion("beanActualizacionPublicacion", bean);
        return pagina;
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }

    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    public void setTipoBusqueda(int tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public int getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setLstDtoResultado(List<DtoResultado> lstDtoResultado) {
        this.lstDtoResultado = lstDtoResultado;
    }

    public List<DtoResultado> getLstDtoResultado() {
        return lstDtoResultado;
    }

    public void setLstDtoEjemplar(List<DtoEjemplar> lstDtoEjemplar) {
        this.lstDtoEjemplar = lstDtoEjemplar;
    }

    public List<DtoEjemplar> getLstDtoEjemplar() {
        return lstDtoEjemplar;
    }


}
