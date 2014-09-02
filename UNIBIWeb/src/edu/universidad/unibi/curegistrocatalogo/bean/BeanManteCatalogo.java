package edu.universidad.unibi.curegistrocatalogo.bean;

import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogo;

import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogoImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoCatalogo;

import java.util.List;


@ManagedBean(name = "beanManteCatalogo")
@RequestScoped
public class BeanManteCatalogo {
    private int selec;
    private List<DtoCatalogo> lstCatalogo;
    
    private BoRegistroCatalogo bo;
    
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    private String descripcion;
    
    

    public void setLstCatalogo(List<DtoCatalogo> lstCatalogo) {
        this.lstCatalogo = lstCatalogo;
    }

    public List<DtoCatalogo> getLstCatalogo() {
        return lstCatalogo;
    }
    
    
    
    
    public BeanManteCatalogo() {
        bo=new BoRegistroCatalogoImpl();
    }

    public void setSelec(int selec) {
        this.selec = selec;
    }

    public int getSelec() {
        return selec;
    }
    
    
    public void listarCatalogo(){
        if(selec==1){
            System.out.println("Listar catalogo 1");
            //seleccionar areas
            lstCatalogo=bo.consultarCatalogo(1);
        }
        else if(selec==2){
            System.out.println("Listar catalogo 2");
            lstCatalogo=bo.consultarCatalogo(2);
        }
        else if(selec==3){
            lstCatalogo=bo.consultarCatalogo(3);
        }
        else if(selec==4){
            lstCatalogo=bo.consultarCatalogo(4);
        }
        else if(selec==5){
            lstCatalogo=bo.consultarCatalogo(5);
        }
        else if(selec==6){
            lstCatalogo=bo.consultarCatalogo(6);
        }
        else if(selec==7){
            lstCatalogo=bo.consultarCatalogo(7);
        }
    }
    
    public void agregar(){
        System.out.println("Agragar catalogo item "+ id+ " "+descripcion+" "+getSelec());
        bo.agregarcatalogoitem(id,descripcion,getSelec());
        lstCatalogo=bo.consultarCatalogo(getSelec());
    }
    
    
    
}
