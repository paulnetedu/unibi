package edu.universidad.unibi.curegistrocatalogo.bean;

import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogo;
import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogoImpl;

import edu.universidad.unibi.curegistrocatalogo.dto.DtoArea;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoAutor;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoCatalogo;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoCiudad;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoPais;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoPublicacion;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoResultado;
import edu.universidad.unibi.util.Bo;
import edu.universidad.unibi.util.bean.BeanNotificacionData;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

import java.util.LinkedList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PreDestroy;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "beanNuevaPublicacion")
@SessionScoped
public class BeanNuevaPublicacion implements Serializable {
    private BoRegistroCatalogo bo;
    
    private int tipo;
    private String id;
    private String titulo;
    private Date fechaPublicacion;
    private int numeroPaginas;
    private double valorfisico;
    private double valordigital;
    private int id_pais;
    private int id_ciudad;
    
    private boolean indlibro;
    private boolean indrevista;
    private boolean indtesis;
    
    private String isbn;
    private int volumen;
    private int tomo;
    private int edicion;
    private int editorial_id;
    
    private int numero;
    private int institucion_id;
    private int titulo_optado;
    
    
    private int listArea;
    private int listAutor;
    private int listCarrera;
    private int listIdiomas;
    
    
    ////////////////
    
    
    
    private List<DtoAutor> lstDtoResultadoAutores;
    private List<DtoArea> lstDtoResultadoArea;
    
    
    
    
    private List<DtoAutor> lstDtoResultadoAutoresfinal;
    private List<DtoArea> lstDtoResultadoAreafinal;
    
   
    
    
    
   
    
    


    
    
    private String autores;
    
    
    private String salida;
    private String buscarAutor;


    public void setListArea(int listArea) {
        this.listArea = listArea;
    }

    public int getListArea() {
        return listArea;
    }

    public void setListAutor(int listAutor) {
        this.listAutor = listAutor;
    }

    public int getListAutor() {
        return listAutor;
    }

    public void setListCarrera(int listCarrera) {
        this.listCarrera = listCarrera;
    }

    public int getListCarrera() {
        return listCarrera;
    }

    public void setListIdiomas(int listIdiomas) {
        this.listIdiomas = listIdiomas;
    }

    public int getListIdiomas() {
        return listIdiomas;
    }

    public void setIndlibro(boolean indlibro) {
        this.indlibro = indlibro;
    }

    public boolean isIndlibro() {
        return indlibro;
    }

    public void setIndrevista(boolean indrevista) {
        this.indrevista = indrevista;
    }

    public boolean isIndrevista() {
        return indrevista;
    }

    public void setIndtesis(boolean indtesis) {
        this.indtesis = indtesis;
    }

    public boolean isIndtesis() {
        return indtesis;
    }
    
    public void setSalida(String salida) {
        this.salida = salida;
    }

    public void setBuscarAutor(String buscarAutor) {
        this.buscarAutor = buscarAutor;
    }

    public String getBuscarAutor() {
        return buscarAutor;
    }

    public String getSalida() {
        return salida;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setInstitucion_id(int institucion_id) {
        this.institucion_id = institucion_id;
    }

    public int getInstitucion_id() {
        return institucion_id;
    }

    public void setTitulo_optado(int titulo_optado) {
        this.titulo_optado = titulo_optado;
    }

    public int getTitulo_optado() {
        return titulo_optado;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setValorfisico(double valorfisico) {
        this.valorfisico = valorfisico;
    }

    public double getValorfisico() {
        return valorfisico;
    }

    public void setValordigital(double valordigital) {
        this.valordigital = valordigital;
    }

    public double getValordigital() {
        return valordigital;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getAutores() {
        return autores;
    }
    
    public void setLstDtoResultadoArea(List<DtoArea> lstDtoResultadoArea) {
        this.lstDtoResultadoArea = lstDtoResultadoArea;
    }

    public List<DtoArea> getLstDtoResultadoArea() {
        return lstDtoResultadoArea;
    }

    public void setLstDtoResultadoAreafinal(List<DtoArea> lstDtoResultadoAreafinal) {
        this.lstDtoResultadoAreafinal = lstDtoResultadoAreafinal;
    }

    public List<DtoArea> getLstDtoResultadoAreafinal() {
        return lstDtoResultadoAreafinal;
    }

   


    public void setLstDtoResultadoAutoresfinal(List<DtoAutor> lstDtoResultadoAutoresfinal) {
        this.lstDtoResultadoAutoresfinal = lstDtoResultadoAutoresfinal;
    }

    public List<DtoAutor> getLstDtoResultadoAutoresfinal() {
        return lstDtoResultadoAutoresfinal;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setTomo(int tomo) {
        this.tomo = tomo;
    }

    public int getTomo() {
        return tomo;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEditorial_id(int editorial_id) {
        this.editorial_id = editorial_id;
    }

    public int getEditorial_id() {
        return editorial_id;
    }  

    public void setLstDtoResultadoAutores(List<DtoAutor> lstDtoResultadoAutores) {
        this.lstDtoResultadoAutores = lstDtoResultadoAutores;
    }

    public List<DtoAutor> getLstDtoResultadoAutores() {
        return lstDtoResultadoAutores;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }
    
    
    public List<SelectItem> getListaciudad() {
        System.out.println("getCiudad");
        List<SelectItem> lstSi = new LinkedList<SelectItem>();
        List<DtoCiudad> lstDto = bo.consultarCiudadporpais(id_pais);
        
        for (DtoCiudad dto : lstDto) {
            SelectItem si = new SelectItem();
            System.out.println("p.getFechaPublicacion()=" + dto.getNombre());
            si.setLabel(dto.getNombre());
            si.setValue(dto.getId());
            lstSi.add(si);
        }
        return lstSi;
    }

    public List<SelectItem> getListapais() {
           
            List<SelectItem> lstSi = new LinkedList<SelectItem>();
            List<DtoPais> lstDto = bo.consultarPaises();
            for (DtoPais dto : lstDto) {
                SelectItem si = new SelectItem();
                si.setLabel(dto.getNombre());
                si.setValue(dto.getId());
                lstSi.add(si);
            }            
            return lstSi;
        }
    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<SelectItem> getListaBuscaAreas() {
        System.out.println("getAreas");
        List<SelectItem> lstSi = new LinkedList<SelectItem>();
        List<DtoArea> lstDto = bo.consultarAreas();
        for (DtoArea dto : lstDto) {
            SelectItem si = new SelectItem();
            si.setLabel(dto.getDescripcion());
            si.setValue(dto.getId());
            lstSi.add(si);
        }
        return lstSi;
    }
    public List<SelectItem> getLstBuscaAutores() {
        List<SelectItem> lstBuscaAutores=new LinkedList<SelectItem>();
        List<DtoAutor> listDtoAutor= bo.consultarautores();

        System.out.println("========= lista autor ==========");
        for(DtoAutor autor: listDtoAutor){
            SelectItem si = new SelectItem();
            System.out.println(autor.getId()+" "+ autor.getNombre());
            si.setLabel(autor.getNombre());
            si.setValue(autor.getId());
            lstBuscaAutores.add(si);
    }
        return lstBuscaAutores;
    }
    public List<SelectItem> getLstBuscaCarreras() {
        List<SelectItem> lstBuscaCarreras=new LinkedList<SelectItem>();
        List<DtoCatalogo> listDtoCarreras= bo.consultarCatalogo(2);

        System.out.println("========= lista Carreas ==========");
        for(DtoCatalogo carrera: listDtoCarreras){
            SelectItem si = new SelectItem();
            System.out.println(carrera.getId()+" "+ carrera.getDescripcion());
            si.setLabel(carrera.getDescripcion());
            si.setValue(carrera.getId());
            lstBuscaCarreras.add(si);
    }
        return lstBuscaCarreras;
    }
    public List<SelectItem> getLstBuscaIdiomas() {
       
        List<SelectItem> lstBuscaIdiomas=new LinkedList<SelectItem>();
        List<DtoCatalogo> listDtoIdiomas= bo.consultarCatalogo(5);

        System.out.println("========= lista Idiomas ==========");
        for(DtoCatalogo idioma: listDtoIdiomas){
            SelectItem si = new SelectItem();
            System.out.println(idioma.getId()+" "+ idioma.getDescripcion());
            si.setLabel(idioma.getDescripcion());
            si.setValue(idioma.getId());
            lstBuscaIdiomas.add(si);
    }

        return lstBuscaIdiomas;
    }
    
    public BeanNuevaPublicacion() {
        bo = new BoRegistroCatalogoImpl();
        id_pais=1;
        tipo=0;
        indlibro=true;
       
        lstDtoResultadoAutoresfinal= new ArrayList<DtoAutor>();
    }
    
    @PreDestroy
    public void destroy() {
        ((Bo) bo).closeEmf();
    }
    
    public String siguientePagina(){
        String pagina="";
        if(tipo==0){
            pagina="irNuevoLibro";
        }else if(tipo==1){
            pagina="irNuevaRevista";
        }else if(tipo==2){
            pagina="irNuevaTesis";
        }
        return pagina;
    }
        
    
    
    public String guardar() {
        System.out.println("guardar");
        //guardar en publicaciones
        System.out.println("-------------- Tipo: "+tipo);
        //este es un libro
      
        String pagina="irNuevaPublicacion2";
        
        if(tipo==0){
            bo.guardarLibro(id, titulo, fechaPublicacion, numeroPaginas, valorfisico, valordigital, tipo, id_pais, id_ciudad, isbn,volumen,tomo,edicion, editorial_id);
        }else if(tipo==1){
            bo.guardarRevista(id, titulo, fechaPublicacion, numeroPaginas, valorfisico, valordigital, tipo, id_pais, id_ciudad,volumen,numero);
        }else if(tipo==2){

            bo.guardarTesis(id, titulo, fechaPublicacion, numeroPaginas, valorfisico, valordigital, tipo, id_pais, id_ciudad,institucion_id,titulo_optado);
        }
        BeanNotificacionData.show(1, "Se agrego la publicacion corretamente");
        return pagina;
        
    }
    
    public void guardarRelaaciones() {
        System.out.println("****************** guardando las relaciones");
        
        bo.guardarPublicacionRelaciones(id,listArea,listAutor,listCarrera,listIdiomas);
    
        BeanNotificacionData.show(1, "Registro actualizado");
    }
    
    public void validar(FacesContext context, UIComponent component, Object value) 
            throws ValidatorException {
        System.out.println("validar");
        System.out.println("value " + value);
        System.out.println("clientid=" + component.getClientId(context));
        if (value.equals("13-05-14")) {
            throw new ValidatorException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error validator bean", "Error valid bean"));
        }
    }


   
    
    public List<SelectItem> getlistaEditorial(){
        
        List<SelectItem> lstSi = new LinkedList<SelectItem>();
        List<DtoCatalogo> lstDto = bo.consultarCatalogo(3);
        for (DtoCatalogo dto : lstDto) {
            SelectItem si = new SelectItem();
            si.setLabel(dto.getDescripcion());
            si.setValue(dto.getId());
            lstSi.add(si);            
        }
                return lstSi;
    }

    public void listaautores() {
    // Add event code here...
    System.out.println("############" + autores +"############");
        lstDtoResultadoAutores=bo.consultarautores();
        
        for (DtoAutor p : lstDtoResultadoAutores)
        {
            System.out.println(p.getNombre());
            }
        
        System.out.println("===========================mrd se lleno");
    
    }
      
    public void llenarlistaautoresverdad(int idA, String nombreA) {
        
        System.out.println("=================================BOTON LLENAR LISTA=================================================");
        System.out.println(idA+" "+ nombreA);
        DtoAutor j;
        j= new DtoAutor();
        j.setId(idA);
        j.setNombre(nombreA);
        //lstDtoResultadoAutoresfinal.add(j);
        boolean ban=true;
        for (DtoAutor p : lstDtoResultadoAutoresfinal) {
            
            if(p.getId()==idA)
            {
                ban=false;
                }
            
        }
        if(ban)
        {
                lstDtoResultadoAutoresfinal.add(j);
            }
        
    }
    public void quitarlistaautoresverdad(int idA, String nombreA) {
        
        System.out.println("=================================BOTON quitar LISTA=================================================");
        System.out.println(idA+" "+ nombreA);
        
        //lstDtoResultadoAutoresfinal.add(j);
        List<DtoAutor> l;
        l= new ArrayList<DtoAutor>();
        boolean ban=false;
        for (DtoAutor p : lstDtoResultadoAutoresfinal) {
            
            if(p.getId()==idA)
            {
                    
                    for (DtoAutor y : lstDtoResultadoAutoresfinal) {
                        DtoAutor j;
                        if(y.getId()!= idA){
                        j= new DtoAutor();
                        j.setId(y.getId());
                        j.setNombre(y.getNombre());
                        l.add(j);
                        }
                    }
                     
                ban=true;
                }
            
        }
        if(ban)
        {
                lstDtoResultadoAutoresfinal=l;
            }
        
    }
    
    
    
   
    

    
    

    public void cualaparecer(AjaxBehaviorEvent ajaxBehaviorEvent) {
        if(tipo==0)
        {
            indlibro=true;
            indtesis=false;
            indrevista=false;
            }
        else
        {
            if(tipo==1)
            {
                    indlibro=false;
                    indtesis=false;
                    indrevista=true;
                }
            else
            {
                    indlibro=false;
                    indtesis=true;
                    indrevista=false;
                }
            }
    }

        public void validar1(FacesContext context, UIComponent component, Object value) 
            throws ValidatorException {
                
                String valor = (String)value;       
                Pattern mask =  Pattern.compile("[0-9]{1,20}");
    
                 Matcher matcher = mask.matcher( valor);
    
                 if(!matcher.matches()){
    
                    throw new ValidatorException(new FacesMessage("error solo numero y como maximo 20"));         
                 }
                
            }
 
    public List<SelectItem> getlistinstitucion() {
            System.out.println("getCiudad");
            List<SelectItem> lstSi = new LinkedList<SelectItem>();
            List<DtoCatalogo> lstDto = bo.consultarCatalogo(4);
            
            for (DtoCatalogo dto : lstDto) {
                SelectItem si = new SelectItem();        
                si.setLabel(dto.getDescripcion());
                si.setValue(dto.getId());
                lstSi.add(si);
            }
            return lstSi;
        }
   
}


