package edu.universidad.unibi.curegistrocatalogo.bean;

import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogo;
import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogoImpl;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoCatalogo;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoEjemplar;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoPublicacion;

import edu.universidad.unibi.util.bean.BeanNotificacionData;

import java.io.Serializable;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "beanActualizacionEjemplar")
@SessionScoped
public class BeanActualizacionEjemplar implements Serializable 
{

    //datos del ejemplar
        private String id;
        private int estado_fisico;
        private int estado_disponiblilidad;
        private double valor;
        private String observaciones;
        private int forma;
        private String publicacion_id;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setEstado_fisico(int estado_fisico) {
            this.estado_fisico = estado_fisico;
        }

        public int getEstado_fisico() {
            return estado_fisico;
        }

        public void setEstado_disponiblilidad(int estado_disponiblilidad) {
            this.estado_disponiblilidad = estado_disponiblilidad;
        }

        public int getEstado_disponiblilidad() {
            return estado_disponiblilidad;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }

        public double getValor() {
            return valor;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setForma(int forma) {
            this.forma = forma;
        }

        public int getForma() {
            return forma;
        }

        public void setPublicacion_id(String publicacion_id) {
            this.publicacion_id = publicacion_id;
        }

        public String getPublicacion_id() {
            return publicacion_id;
        }

        public void setProcedencia(int procedencia) {
            this.procedencia = procedencia;
        }

        public int getProcedencia() {
            return procedencia;
        }
        private int procedencia;
     

        private BoRegistroCatalogo bo;
        
        
        public BeanActualizacionEjemplar() {
            bo=new BoRegistroCatalogoImpl();
        }
                
        public void cargarEjemplar(String idEjemplar) 
        {
            System.out.println("================antes de cargar dto ejemplar "+ idEjemplar);
            DtoEjemplar dto = bo.consultarEjemplarPorId(idEjemplar);
            System.out.println("------SE ENCONTRO EL EJEMPLAR");
            /////consultar por id el ejemplar
            //System.out.println("cargar dto.getIdEjemplar()" + dto.getId());
            
            id = dto.getId();
            
            estado_fisico=dto.getEstadoFisico();
            estado_disponiblilidad=dto.getEstadoDisponibilidad();
        
            valor=dto.getValor();
            observaciones=dto.getObservaciones();
            forma=dto.getForma();
            publicacion_id=dto.getPublicacion_id();

            procedencia=dto.getProcedencia(); 
            
            BeanNotificacionData.show(1, "Datos: " +dto.getProcedencia());
        }    
         public void actualizar()
         {
            System.out.println("metodo agregar");
            bo.actualizarEjemplar(id,estado_fisico,estado_disponiblilidad,valor,observaciones,forma,publicacion_id,procedencia);
            BeanNotificacionData.show(1, "Guardado");
         }
         
    public List<SelectItem> getListaProcedencia() {
        System.out.println("getProcedencia");
        List<SelectItem> lstSi = new LinkedList<SelectItem>();
        int codCatalogo =7;
        List<DtoCatalogo> lstDto = bo.consultarCatalogo(codCatalogo);
        for (DtoCatalogo dto : lstDto) {
            SelectItem si = new SelectItem();
            si.setLabel(dto.getDescripcion());
            si.setValue(dto.getId());
            lstSi.add(si);
        }
        return lstSi;
    }
   
    
}