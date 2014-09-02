package edu.universidad.unibi.cuprestamoejemplar.bean;

import edu.universidad.unibi.cubusquedaejemplar.BoBusquedaEjemplar;
import edu.universidad.unibi.cuprestamoejemplar.BoPrestamoEjemplar;
import edu.universidad.unibi.cuprestamoejemplar.BoPrestamoEjemplarImpl;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoEjemplar;
import edu.universidad.unibi.util.Bo;
import edu.universidad.unibi.util.bean.BeanNotificacionData;
import edu.universidad.unibi.util.bean.BeanSesionData;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="beanPrestamoEjemplar")
@SessionScoped
public class BeanPrestamoEjemplar implements Serializable {

    private BoPrestamoEjemplar bo;
    
    private String cadenaBusqueda;
    
    /**
     * Tipo de busqueda:
     * 0 por titulo
     * 1 por codigo ejemplar
     * 2 por codigo solicitud
     */
    private int tipoBusqueda;
    
    private List<DtoEjemplar> lstDtoEjemplar;
    
    private List<DtoEjemplar> lstDtoResultado;
    
    public BeanPrestamoEjemplar() {
        bo = new BoPrestamoEjemplarImpl();
        lstDtoEjemplar = new ArrayList<DtoEjemplar>();
    }

    @PreDestroy
    public void destroy() {
        ((Bo) bo).closeEmf();
    }
    
    public void setCadenaBusqueda(String cadenaBusqueda) {
        this.cadenaBusqueda = cadenaBusqueda;
    }

    public String getCadenaBusqueda() {
        return cadenaBusqueda;
    }

    public String irPrestarPublicacion() {
        System.out.println("irPrestarPublicacion");
        return "prestarPublicacion";
    }
    
    public void buscarPrestamo() {
        System.out.println("buscarPrestamo");
        String cadena = cadenaBusqueda.toUpperCase();
        System.out.println("cadenaBusqueda=" + cadena);
        if (tipoBusqueda == 0) {
            lstDtoResultado = bo.consultarEjemplarPorTitulo(cadena);
        }
        if (tipoBusqueda == 1) {
            lstDtoResultado = bo.consultarEjemplarPorCodigoEjemplar(cadena);
        }
        
    }

    public void agregarEjemplar(String ejemplarId) {
        DtoEjemplar dto = null;
        for (DtoEjemplar o : lstDtoResultado) {
            if (o.getId().equals(ejemplarId)) {
                dto = o;
                break;
            }
        }
        lstDtoEjemplar.add(dto);
    }

    public void quitarEjemplar(String ejemplarId) {
        DtoEjemplar dto = null;
        for (DtoEjemplar o : lstDtoEjemplar) {
            if (o.getId().equals(ejemplarId)) {
                dto = o;
                break;
            }
        }
        lstDtoEjemplar.remove(dto);
    }
    public String ir()
    {
        return "enviardatos";
        }

    public void setBo(BoPrestamoEjemplar bo) {
        this.bo = bo;
    }

    public BoPrestamoEjemplar getBo() {
        return bo;
    }

    public void setTipoBusqueda(int tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public int getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setLstDtoEjemplar(List<DtoEjemplar> lstDtoEjemplar) {
        this.lstDtoEjemplar = lstDtoEjemplar;
    }

    public List<DtoEjemplar> getLstDtoEjemplar() {
        return lstDtoEjemplar;
    }


    public void setLstDtoResultado(List<DtoEjemplar> lstDtoResultado) {
        this.lstDtoResultado = lstDtoResultado;
    }

    public List<DtoEjemplar> getLstDtoResultado() {
        return lstDtoResultado;
    }
    
        
}


