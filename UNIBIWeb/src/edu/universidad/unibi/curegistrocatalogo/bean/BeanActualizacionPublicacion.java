package edu.universidad.unibi.curegistrocatalogo.bean;

import edu.universidad.dominio.unibi.TblLibros;
import edu.universidad.dominio.unibi.TblRevistas;
import edu.universidad.dominio.unibi.TblTesis;
import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogo;

import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogoImpl;

import edu.universidad.unibi.curegistrocatalogo.dto.DtoArea;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoAutor;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoCatalogo;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoCiudad;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoPais;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoPublicacion;

import edu.universidad.unibi.util.Bo;

import edu.universidad.unibi.util.bean.BeanNotificacionData;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PreDestroy;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "beanActualizacionPublicacion")
@SessionScoped
public class BeanActualizacionPublicacion implements Serializable {
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
    
    private int area;
    private int autor;
    private int carrera;
    private int idioma;


    public void setArea(int area) {
        this.area = area;
    }

    public int getArea() {
        return area;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public int getAutor() {
        return autor;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }

    public int getCarrera() {
        return carrera;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }

    public int getIdioma() {
        return idioma;
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

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setEditorial_id(int editorial_id) {
        this.editorial_id = editorial_id;
    }

    public int getEditorial_id() {
        return editorial_id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
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

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
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

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }


    public BeanActualizacionPublicacion(){
        bo = new BoRegistroCatalogoImpl();
        id_pais=1;
        tipo=0;
        indlibro=true;
    }

    @PreDestroy
    public void destroy() {
        ((Bo) bo).closeEmf();
    }
    
    
    public void validar(FacesContext context, UIComponent component, Object value) 
            throws ValidatorException {
        Date hoy = new Date();
        if (fechaPublicacion.after(hoy)) {
            throw new ValidatorException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación de fecha de publicación", "La fecha de publicación no puede ser posterior a hoy"));
        }
    }

    public void cargarPublicacion(String idPublicacion) {
        DtoPublicacion dto = bo.consultarPublicacionPorId(idPublicacion);
        System.out.println("---------------------------cargar dto.getIdPublicacion()" + dto.getIdPublicacion());
        
        this.id=dto.getIdPublicacion();
        this.titulo=dto.getTitulo();
        this.fechaPublicacion=dto.getFechaPublicacion();
        
        this.numeroPaginas=dto.getNumeroPaginas();
        this.valorfisico=dto.getValorFisico();
        this.valordigital=dto.getValorDigital();
        
        this.tipo=dto.getTipo();
        
        this.id_pais=dto.getPaisId();
        this.id_ciudad=dto.getPaisId();
        
        System.out.println("---------------------------se cargo los primeros datos");
        BeanNotificacionData.show(1, dto.getTipo()+"CARGANDO ISBN:"+dto.getIsbn()+"  "+dto.getVolumen()+"  "+dto.getTomo()+"  "+dto.getEdicion()+" "+dto.getEditorial());
        
        if (tipo == 0) {
            this.isbn=dto.getIsbn();
            this.volumen=dto.getVolumen();
            this.tomo=dto.getTomo();
            this.edicion=dto.getEdicion();
            this.editorial_id=dto.getEditorial();
        } else if (tipo == 1) {
            
            this.volumen=dto.getVolumen();
            this.numero=dto.getNumero();
        } else if (tipo == 2) {
            this.titulo_optado=dto.getTituloOptado();
            this.institucion_id=dto.getInstitucion();
        }
        /*
        this.area=dto.getArea();
        this.autor=dto.getAutor();
        this.carrera=dto.getCarrera();
        this.idioma=dto.getIdioma();        
        */
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
    
    public List<SelectItem> getListapais() {
            System.out.println("Entro a lista pais");
            List<SelectItem> lstSi = new LinkedList<SelectItem>();
            List<DtoPais> lstDto = bo.consultarPaises();
            for (DtoPais dto : lstDto) {
                SelectItem si = new SelectItem();
                si.setLabel(dto.getNombre());
                si.setValue(dto.getId());
                lstSi.add(si);
            }            
            System.out.println("termino lista pais");
            return lstSi;
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

        System.out.println("========= lista Carreas ==========");
        for(DtoCatalogo idioma: listDtoIdiomas){
            SelectItem si = new SelectItem();
            System.out.println(idioma.getId()+" "+ idioma.getDescripcion());
            si.setLabel(idioma.getDescripcion());
            si.setValue(idioma.getId());
            lstBuscaIdiomas.add(si);
    }
        return lstBuscaIdiomas;
    }

    public String actualizar() {
        System.out.println("guardar");
        //guardar en publicaciones
        System.out.println("-------------- Tipo: "+tipo);
        //este es un libro
        
        String pagina="irActualizarPublicacion2";
        
        
        if(tipo==0){
            bo.actualizarLibro(id, titulo, fechaPublicacion, numeroPaginas, valorfisico, valordigital, tipo, id_pais, id_ciudad, isbn,volumen,tomo ,edicion, editorial_id);
        }else if(tipo==1){
            bo.actualizarRevista(id, titulo, fechaPublicacion, numeroPaginas, valorfisico, valordigital, tipo, id_pais, id_ciudad,volumen,numero);
        }else if(tipo==2){

            bo.actualizarTesis(id, titulo, fechaPublicacion, numeroPaginas, valorfisico, valordigital, tipo, id_pais, id_ciudad,institucion_id,titulo_optado);
        }
        BeanNotificacionData.show(1, "Se agrego actualizo la publicacion corretamente");
        return pagina;
    }
    public void actualizarRelaaciones() {
        //bo.actualizarPublicacionRelaciones(id,area,autor,carrera,idioma);
    
        BeanNotificacionData.show(1, "Guardar relaciones");
    }
}
