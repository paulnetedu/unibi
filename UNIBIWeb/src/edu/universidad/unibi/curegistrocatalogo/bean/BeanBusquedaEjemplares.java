package edu.universidad.unibi.curegistrocatalogo.bean;

import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogo;
import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogoImpl;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoEjemplar;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoPublicacion;

import edu.universidad.unibi.util.JsfUtil;

import java.io.Serializable;

import java.util.Date;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "beanBusquedaEjemplares")
@SessionScoped
public class BeanBusquedaEjemplares implements Serializable {
    private BoRegistroCatalogo bo;
    private String IDPublicacion;
    private String IDEjemplar;
    private List<DtoEjemplar> lstDtoEjemplar;

    public void setLstDtoEjemplar(List<DtoEjemplar> lstDtoEjemplar) {
        this.lstDtoEjemplar = lstDtoEjemplar;
    }

    public List<DtoEjemplar> getLstDtoEjemplar() {
        return lstDtoEjemplar;
    }

    public BeanBusquedaEjemplares()
    {
        bo = new BoRegistroCatalogoImpl();
               
    }
    
    public void cargarEjemplares(String idPublicacion)
        {
            IDPublicacion=idPublicacion;
            lstDtoEjemplar = bo.consultarEjemplarPorPublicacion(idPublicacion);
        }
    public String irActualizarEjemplar(String idEjemplar) {
        String pagina = "irActualizarEjemplar";
        BeanActualizacionEjemplar bean = new BeanActualizacionEjemplar();
        IDEjemplar=idEjemplar;
        bean.cargarEjemplar(IDEjemplar);
        JsfUtil.establecerObjetoSesion("beanActualizacionEjemplar", bean);
        return pagina;
    }
    public String irNuevoEjemplar() {
        String pagina = "nuevoEjemplar";
        BeanNuevoEjemplar bean = new BeanNuevoEjemplar();
        bean.cargarPublicacionid(IDPublicacion);
        JsfUtil.establecerObjetoSesion("beanNuevoEjemplar", bean);
        return pagina;
    }
    
    
    
    
}
