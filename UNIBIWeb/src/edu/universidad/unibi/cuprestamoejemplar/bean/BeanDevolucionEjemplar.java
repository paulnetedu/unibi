package edu.universidad.unibi.cuprestamoejemplar.bean;

import edu.universidad.unibi.cubusquedaejemplar.BoBusquedaEjemplar;
import edu.universidad.unibi.cuprestamoejemplar.BoPrestamoEjemplar;
import edu.universidad.unibi.cuprestamoejemplar.BoPrestamoEjemplarImpl;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoDevolucion;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoEjemplar;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoPrestamo;
import edu.universidad.unibi.util.Bo;
import edu.universidad.unibi.util.bean.BeanNotificacionData;
import edu.universidad.unibi.util.bean.BeanSesionData;
import javax.swing.JOptionPane;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="beanDevolucionEjemplar")
@SessionScoped
public class BeanDevolucionEjemplar implements Serializable {

    private BoPrestamoEjemplar bo;
    
    private String cadenaBusqueda;
    
    /**
     * Tipo de busqueda:
     * 0 por ejemplar
     * 1 por prestamo
     */
    private int tipoBusqueda;
    private int estadoDevolucionTable;
    
    private List<DtoPrestamo> lstDtoDevolucion;
    
    private List<DtoPrestamo> lstDtoResultado;

    public void setEstadoDevolucionTable(int estadoDevolucionTable) {
        this.estadoDevolucionTable = estadoDevolucionTable;
    }

    public int getEstadoDevolucionTable() {
        return estadoDevolucionTable;
    }

    public BeanDevolucionEjemplar() {
        bo = new BoPrestamoEjemplarImpl();
        lstDtoDevolucion = new ArrayList<DtoPrestamo>();
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
            //limpiar la tabla 
            //lstDtoDevolucion = null;
            //lstDtoResultado = null;
        System.out.println("buscarPrestamo");
        System.out.println("cadenaBusqueda=" + cadenaBusqueda);
        //JOptionPane.showMessageDialog(null, cadenaBusqueda);  
        if (tipoBusqueda == 1) {
                int entero = Integer.parseInt(cadenaBusqueda);
            lstDtoResultado = bo.consultarPrestamoporCodigoPrestamo(entero);}
       
        if (tipoBusqueda == 0){            
            lstDtoResultado = bo.consultarPrestamoporCodigoEjemplar(cadenaBusqueda+"");
            }         
         
        }
        
        
        public void devolverEjemplar(String ejemplarId, int EstadoDevolucion) {
           // JOptionPane.showMessageDialog(null, ejemplarId);
            DtoPrestamo dto = null;
            DtoDevolucion dtodev = null;
            //  DtoPrestamo o =null;
            for (DtoPrestamo o : lstDtoResultado) {
                if (o.getIdejemplar().equals(ejemplarId)) {
                    int idDetalle, idBibliotecario, estadoFisicoDev, diasAtraso, idPrestamo;
                    Date fechaDev;
                    java.util.Date fecha = new Date();
                    
                    long dias = fecha.getTime() - o.getFechaDevolucion().getTime();
                    double diaA = Math.floor(dias/(3600*24*1000));
                    int entero = (int)diaA;
                    
                    idDetalle = o.getIdDetalle();
                    idBibliotecario = 10000000;
                    estadoFisicoDev = EstadoDevolucion;
                    diasAtraso = entero;
                    fechaDev = fecha;
                    idPrestamo = o.getId();
                   //JOptionPane.showMessageDialog(null, idDetalle + idBibliotecario + estadoFisicoDev + diasAtraso);                                         
                   
                   //JOptionPane.showMessageDialog(null,estadoFisicoDev);
                    bo.guardarDevolucion(idDetalle, fechaDev, idBibliotecario, estadoFisicoDev, diasAtraso);
                    bo.actualizarDetalle(idDetalle);
                    bo.actualizarEjemplar(ejemplarId, 0, estadoFisicoDev);
                // JOptionPane.showMessageDialog(null, idPrestamo);
                    bo.actualizarPrestamo(idPrestamo, 1);
                    break;
             }
         }
         lstDtoDevolucion.add(dto);
         BeanNotificacionData.show(2, "Ejemplar devuelto satisfactoriamente");
        //buscarPrestamo();
    }
        public void sancionar(){
            BeanNotificacionData.show(2, "Si desea sancionar al lector vaya a ACCIONES- SANCIÓN");
            
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


    public void setLstDtoDevolucion(List<DtoPrestamo> lstDtoDevolucion) {
        this.lstDtoDevolucion = lstDtoDevolucion;
    }

    public List<DtoPrestamo> getLstDtoDevolucion() {
        return lstDtoDevolucion;
    }


    public void setLstDtoResultado(List<DtoPrestamo> lstDtoResultado) {
        this.lstDtoResultado = lstDtoResultado;
        }

        public List<DtoPrestamo> getLstDtoResultado() {
        return lstDtoResultado;
        }

        }
