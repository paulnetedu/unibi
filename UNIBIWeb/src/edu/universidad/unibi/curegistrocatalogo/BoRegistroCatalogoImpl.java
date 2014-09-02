package edu.universidad.unibi.curegistrocatalogo;

import edu.universidad.dominio.unibi.TblAutores;
import edu.universidad.dominio.unibi.TblCatalogos;
import edu.universidad.dominio.unibi.TblCatalogosItems;

import edu.universidad.dominio.unibi.TblCiudades;
import edu.universidad.dominio.unibi.TblEjemplares;
import edu.universidad.dominio.unibi.TblLibros;
import edu.universidad.dominio.unibi.TblPaises;
import edu.universidad.dominio.unibi.TblPublicaciones;
import edu.universidad.dominio.unibi.TblPublicacionesAreas;
import edu.universidad.dominio.unibi.TblPublicacionesAutores;
import edu.universidad.dominio.unibi.TblPublicacionesCarreras;
import edu.universidad.dominio.unibi.TblPublicacionesIdiomas;
import edu.universidad.dominio.unibi.TblRevistas;
import edu.universidad.dominio.unibi.TblTesis;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoArea;

import edu.universidad.unibi.curegistrocatalogo.dto.DtoAutor;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoCatalogo;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoCiudad;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoEjemplar;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoPais;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoPublicacion;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoResultado;
import edu.universidad.unibi.util.Bo;

import edu.universidad.unibi.util.bean.BeanNotificacionData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class BoRegistroCatalogoImpl extends Bo implements BoRegistroCatalogo {

    public List<DtoArea> consultarAreas() {
        System.out.println("consultarAreas");
        List<DtoArea> lst = new LinkedList<DtoArea>();
        DtoArea dto = null;
        Query query = em.createNamedQuery("TblCatalogosItems.consultarPorCatalogo");
        TblCatalogos cat = em.find(TblCatalogos.class, 1);
        query.setParameter("catalogoId", cat);
        List<TblCatalogosItems> lstCatItems = query.getResultList();
        for (TblCatalogosItems catItem : lstCatItems) {
            System.out.println("catItem=" + catItem);
            dto = new DtoArea();
            dto.setDescripcion(catItem.getDescripcion());
            dto.setId(catItem.getId());
            lst.add(dto);
        }
        return lst;
    }
    @Override
    public DtoEjemplar consultarEjemplarPorId(String idEjemplar)
    {
        System.out.println("Metodo consultar ejemplar por id: " + idEjemplar);
           
        TblEjemplares ejem=em.find(TblEjemplares.class, idEjemplar);
       // System.out.println("encontro el ejemplar: "+ejem.getProcedencia().getId());
             DtoEjemplar dto = new DtoEjemplar();
             dto.setId(ejem.getId());
             
        System.out.println("-----------agrego id ");
             dto.setEstadoFisico(ejem.getEstadoFisico());
             dto.setEstadoDisponibilidad(ejem.getEstadoDisponibilidad());

             dto.setValor(ejem.getValor());
        System.out.println("-----------agrego valor ");
             dto.setObservaciones(ejem.getObservaciones());
           
             dto.setForma(ejem.getForma());
             dto.setPublicacion_id(ejem.getPublicacionId().getId());
             
        System.out.println("-----------procedencia "+ejem.getProcedencia().getId());
             
             dto.setProcedencia(ejem.getProcedencia().getId());
             
        System.out.println("-----------agrego procedencia ");

             return dto; 

        
    }
    public DtoPublicacion consultarPublicacionPorId(String idPublicacion) {
        TblPublicaciones pub = em.find(TblPublicaciones.class, idPublicacion);
        System.out.println("---------------------------se encontro la publicacion ID: "+ pub.getId());
        DtoPublicacion dto = new DtoPublicacion();
        
        dto.setIdPublicacion(pub.getId());
        dto.setTitulo(pub.getTitulo());
        dto.setFechaPublicacion(pub.getFechaPublicacion());
        
        if(pub.getNumeroPaginas()== null){
            dto.setNumeroPaginas(0);
        }else{ dto.setNumeroPaginas(pub.getNumeroPaginas());}
        if(pub.getValorDigital()== null){
            dto.setValorDigital(0.0);
        }else{dto.setValorDigital(pub.getValorDigital());}
        if(pub.getValorFisico()==null){
            dto.setValorFisico(0.0);
        }else{dto.setValorFisico(pub.getValorFisico());}
        dto.setTipo(pub.getTipo());        
        dto.setPaisId((pub.getPaisId() == null) ? 1 : pub.getPaisId().getId());        
        dto.setCiudadId((pub.getCiudadId() == null) ? 1 : pub.getCiudadId().getId());
        
        if (pub.getTipo() == 0) {
            TblLibros libro = em.find(TblLibros.class, idPublicacion);
            System.out.println("libro:" + libro);
            dto.setIsbn(libro.getIsbn());
            dto.setVolumen(libro.getVolumen());
            dto.setTomo(libro.getTomo());
            dto.setEdicion(libro.getEdicion());
            dto.setEditorial((libro.getEditorialId() == null) ? -1 : libro.getEditorialId().getId());
        } else if (pub.getTipo() == 1) {
            TblRevistas revista = em.find(TblRevistas.class, idPublicacion);
            dto.setVolumen(revista.getVolumen());
            dto.setNumero(revista.getNumero());
        } else if (pub.getTipo() == 2) {
            TblTesis tesis = em.find(TblTesis.class, idPublicacion);
            dto.setTituloOptado(tesis.getTituloOptado());
            dto.setInstitucion((tesis.getInstitucionId() == null) ? -1 : tesis.getInstitucionId().getId());
        }
        
        /*
        //area
        Query query = em.createNamedQuery("TblPublicacionesAreas.consultarPorPublicacion");
        query.setParameter("publicacionId", pub);
        TblPublicacionesAreas tblPubArea =(TblPublicacionesAreas) query.getSingleResult();
    dto.setArea(tblPubArea.getAreaId().getId());
        
        //autor
        query = em.createNamedQuery("TblPublicacionesAutores.consultarPorPublicacion");
        query.setParameter("publicacionId", pub);
        TblPublicacionesAutores tblPubAutor=(TblPublicacionesAutores) query.getSingleResult();
        dto.setAutor(tblPubAutor.getAutorId().getId());
        
        //carrera
        query = em.createNamedQuery("TblPublicacionesCarreras.consultarPorPublicacion");
        query.setParameter("publicacionId", pub);
        TblPublicacionesCarreras tblPubCarrera=(TblPublicacionesCarreras) query.getSingleResult();
        dto.setCarrera(tblPubCarrera.getCarreraId().getId());        
        
        //idioma
        query = em.createNamedQuery("TblPublicacionesIdiomas.consultarPorPublicacion");
        query.setParameter("publicacionId", pub);
        TblPublicacionesIdiomas tblPubIdioma=(TblPublicacionesIdiomas) query.getSingleResult();
        dto.setIdioma(tblPubIdioma.getIdiomaId().getId()); 
        */
        return dto; 

    } 

    public List<DtoResultado> consultarPublicacionPorTitulo(String titulo) {
        List<DtoResultado> lstDto = new ArrayList<DtoResultado>();
        TblPublicaciones entidad = new TblPublicaciones();
        Query query = em.createNamedQuery("TblLibros.consultarPorTitulo");
        String cadenaBusqueda = "%" + titulo.toUpperCase() + "%";
        System.out.println("cadenaBusqueda=" + cadenaBusqueda);
        query.setParameter("titulo", cadenaBusqueda);
        List<TblPublicaciones> lstTbl = query.getResultList();
        System.out.println("lstTbl = " + ((lstTbl == null) ? -1 : lstTbl.size()));
        DtoResultado dto = null;
        for (TblPublicaciones p : lstTbl) {
            System.out.println("p.getFechaPublicacion()=" + p.getFechaPublicacion());
            dto = new DtoResultado();
            dto.setId(p.getId());
            dto.setFechaPublicacion(p.getFechaPublicacion());
            dto.setTitulo(p.getTitulo());
            dto.setTipo(p.getTipo());
            lstDto.add(dto);
        }
        return lstDto;
    }
    public List<DtoResultado> consultarPublicacionPorAutor(String titulo) {
        List<DtoResultado> lstDto = new ArrayList<DtoResultado>();
        TblPublicaciones entidad = new TblPublicaciones();
        Query query = em.createNamedQuery("TblLibros.consultarPorTitulo");
        String cadenaBusqueda = "%" + titulo.toUpperCase() + "%";
        System.out.println("cadenaBusqueda=" + cadenaBusqueda);
        query.setParameter("titulo", cadenaBusqueda);
        List<TblPublicaciones> lstTbl = query.getResultList();
        System.out.println("lstTbl = " + ((lstTbl == null) ? -1 : lstTbl.size()));
        DtoResultado dto = null;
        for (TblPublicaciones p : lstTbl) {
            System.out.println("p.getFechaPublicacion()=" + p.getFechaPublicacion());
            dto = new DtoResultado();
            dto.setId(p.getId());
            dto.setFechaPublicacion(p.getFechaPublicacion());
            dto.setTitulo(p.getTitulo());
            lstDto.add(dto);
        }
        return lstDto;
    }
    
    public List<DtoEjemplar> consultarEjemplarPorPublicacion(String idPublicacion) {
        List<DtoEjemplar> lstDto = new ArrayList<DtoEjemplar>();
        TblPublicaciones pub = em.find(TblPublicaciones.class, idPublicacion);
        Query query = em.createNamedQuery("TblEjemplares.consultarPorPublicacion");
        query.setParameter("publicacion", pub);
        List<TblEjemplares> lstTbl = query.getResultList();
        DtoEjemplar dto = null;
        for (TblEjemplares e : lstTbl) {
            dto = new DtoEjemplar();
            dto.setId(e.getId());
            dto.setTitulo(pub.getTitulo());
            dto.setEstadoFisico(e.getEstadoFisico());
            dto.setEstadoDisponibilidad(e.getEstadoDisponibilidad());
            List<TblPublicacionesAutores> lstPubAut = pub.getTblPublicacionesAutoresList();
            String autores = "";
            for (TblPublicacionesAutores pa : lstPubAut) {
                TblAutores a = pa.getAutorId();
                autores += a.getNombre() + ", ";
            }
            dto.setAutor(autores);
            lstDto.add(dto);
        }
        return lstDto;
    }

    @Override
    public void actualizarLibro(String idPublicacion, String titulo, Date fechaPublicacion, 
                                Integer numeroPaginas, Double valorFisico, Double valorDigital, Integer tipo, Integer paisId,
                                Integer ciudadId, String isbn, Integer volumen, Integer tomo, Integer edicion, Integer editorial) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TblLibros libro = em.find(TblLibros.class, idPublicacion);
        
        libro.setTitulo(titulo);
        libro.setFechaPublicacion(fechaPublicacion);
        libro.setNumeroPaginas(numeroPaginas);
        libro.setValorFisico(valorFisico);
        libro.setValorDigital(valorDigital);
        libro.setTipo(tipo);
        TblPaises pais = (paisId == null) ? null : em.find(TblPaises.class, paisId);
        libro.setPaisId(pais);
        TblCiudades ciudad = (ciudadId == null) ? null : em.find(TblCiudades.class, ciudadId);
        libro.setCiudadId(ciudad);
        
        libro.setIsbn(isbn);
        libro.setVolumen(volumen);
        libro.setTomo(tomo);
        libro.setEdicion(edicion);
        TblCatalogosItems tblEditorial = (editorial == null) ? null : em.find(TblCatalogosItems.class, editorial);
        libro.setEditorialId(tblEditorial);    
        
        em.persist(libro);
        tx.commit();

    }
    @Override
    public void agregarEjemplar(String id, int estado_fisico, int estado_disponiblilidad, double valor,
                                String observaciones, int forma, String publicacion_id, int procedencia) {
        System.out.println("metodo agregar ejemplar implementacion");
          EntityTransaction tx = em.getTransaction();
          tx.begin();
          TblEjemplares ejemplar = new TblEjemplares();
          ejemplar.setId(id);
          ejemplar.setEstadoFisico(estado_fisico);
          ejemplar.setEstadoDisponibilidad(estado_disponiblilidad);
          ejemplar.setValor(valor);
          ejemplar.setObservaciones(observaciones);
          ejemplar.setForma(forma);
          
          TblCatalogosItems catalogoitems= em.find(TblCatalogosItems.class,procedencia);
          ejemplar.setProcedencia(catalogoitems);
          System.out.println("$$$$$$$ CATALOGO ITEM AGREGADO"+catalogoitems.getDescripcion());
          
          System.out.println("publicacion "+publicacion_id+" "+procedencia);
          
          TblPublicaciones publicacion=(TblPublicaciones) em.getReference(TblPublicaciones.class, publicacion_id);
          System.out.println("==============SE AGREGO el atributo publicacion"+publicacion.getId());
          ejemplar.setPublicacionId(publicacion);
          
          em.persist(ejemplar);
          tx.commit();
          System.out.println("Se agrego un ejemplar");

    }

    @Override
    public void actualizarEjemplar(String id, int estado_fisico, int estado_disponiblilidad, double valor,
                                   String observaciones, int forma, String publicacion_id, int procedencia) {
        System.out.println("metodo actualizar ejemplar implementacion");
          EntityTransaction tx = em.getTransaction();
          tx.begin();
          TblEjemplares ejemplar = em.find(TblEjemplares.class, id);
          System.out.println("===========    ejemplar encontrado"+ejemplar.getId()+ " "+ ejemplar.getPublicacionId().getTitulo());
          ejemplar.setEstadoFisico(estado_fisico);
          ejemplar.setEstadoDisponibilidad(estado_disponiblilidad);
          ejemplar.setValor(valor);
          ejemplar.setObservaciones(observaciones);
          ejemplar.setForma(forma);
          
          TblCatalogosItems catalogoitems= em.find(TblCatalogosItems.class,procedencia);
          ejemplar.setProcedencia(catalogoitems);
          System.out.println("$$$$$$$ CATALOGO ITEM AGREGADO"+catalogoitems.getDescripcion());
          
          System.out.println("publicacion "+publicacion_id+" "+procedencia);
            TblPublicaciones publicacion=(TblPublicaciones) em.getReference(TblPublicaciones.class, publicacion_id);
          System.out.println("==============SE AGREGO el atributo publicacion "+publicacion.getId());
          ejemplar.setPublicacionId(publicacion);
          
          em.persist(ejemplar);
          tx.commit();
          System.out.println("Se actualizo un ejemplar");

    }

    @Override
    public List<DtoCatalogo> consultarCatalogo(int cod) {
        System.out.println("BO Consultar Catalogo");
                List<DtoCatalogo> lstCatalogo= new ArrayList<DtoCatalogo>();
                DtoCatalogo catalogo;
                Query query = em.createNamedQuery("TblCatalogosItems.consultarPorCatalogo");
                TblCatalogos cat = em.find(TblCatalogos.class, cod);
                query.setParameter("catalogoId", cat);
                List<TblCatalogosItems> lstCatItems = query.getResultList();
                for (TblCatalogosItems catItem : lstCatItems) {
                    System.out.println("catItem=" + catItem);
                    catalogo = new DtoCatalogo();
                    catalogo.setDescripcion(catItem.getDescripcion());
                    catalogo.setId(catItem.getId());
                    lstCatalogo.add(catalogo);
                }
                return lstCatalogo;
    }

    @Override
    public void agregarcatalogoitem(int id, String descripcion, int selec) {
        System.out.println("metodo agregar catalogo items " + id+ " "+descripcion+" "+selec);
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                TblCatalogosItems item = new TblCatalogosItems();
                item.setId(id);
                System.out.println("se agrego id");
                item.setDescripcion(descripcion);
                System.out.println("se agrego descripcion");
                TblCatalogos catalogo=em.find(TblCatalogos.class, selec);
                item.setCatalogoId(catalogo);

                System.out.println("se agrego catalogo_id");
                em.persist(item);
                tx.commit();
                System.out.println("Se agrego un catalogo item");

    }

    @Override
    public List<DtoCiudad> consultarCiudadporpais(int id_pais) {
        List<DtoCiudad> lstDto = new ArrayList<DtoCiudad>();
        Query query = em.createNamedQuery("TblCiudades.consultarPorIdpais");
        TblPaises pais=em.find(TblPaises.class, id_pais);
        System.out.println("cadenaBusqueda= " + pais);
        query.setParameter("paisid", pais);
        List<TblCiudades> lstTbl = query.getResultList();
        
        for (TblCiudades p : lstTbl) {
            DtoCiudad dto=new DtoCiudad();
           System.out.println("p.getFechaPublicacion()=" + p.getNombre());
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            lstDto.add(dto);
        }
        return lstDto;
    }

    @Override
    public List<DtoPais> consultarPaises() {
        System.out.println("consultarPaises");
        List<DtoPais> lst = new LinkedList<DtoPais>();
        DtoPais dto = null;
        Query query = em.createNamedQuery("TblPaises.consultarPaises");
        TblPaises cat = em.find(TblPaises.class, 1);
        List<TblPaises> lstCatItems = query.getResultList();
        for (TblPaises paisItem : lstCatItems) {
            System.out.println("paisItem=" + paisItem);
            dto = new DtoPais();
            dto.setNombre(paisItem.getNombre());
            dto.setId(paisItem.getId());
            lst.add(dto);
        }
        return lst;
    }

    @Override
    public List<DtoAutor> consultarautores() {
        List<DtoAutor> lstDto = new LinkedList<DtoAutor>();
        DtoAutor dto = null;
        Query query = em.createNamedQuery("TblAutores.consultarAutor");
        List<TblAutores> lstTbl = query.getResultList();
        
        
        System.out.println("---------------ejecuto cosulta");
        for (TblAutores p : lstTbl) {
            
            System.out.println(p.getId()+" "+p.getNombre());
            dto = new DtoAutor();
            dto.setId(p.getId());
           
            dto.setNombre(p.getNombre());
            
            lstDto.add(dto);
        }
        
        return lstDto;
    }

    @Override
    public List<DtoArea> consultarAreaNombre(String area) {
        List<DtoArea> lstDto = new ArrayList<DtoArea>();
        System.out.print("//////////////////////////////////"+area+"///////////////////////////////////////////");
        
        Query query = em.createNamedQuery("TblCatalogosItems.consultarPorCatalogonombre");
        TblCatalogos pais=em.find(TblCatalogos.class, "1");
        query.setParameter("catalogoId",pais);
        query.setParameter("descripcion","%"+area+"%");
        
        List<DtoArea> lstTbl = query.getResultList();
        DtoArea dto = null;
        
        System.out.println("---------------ejecuto cosulta");
        for (DtoArea p : lstTbl) {
            
            System.out.println(p.getId()+" "+p.getDescripcion());
            dto = new DtoArea();
            dto.setId(p.getId());
            dto.setDescripcion(p.getDescripcion());   
            lstDto.add(dto);
        }
        return lstDto;
    }


    @Override
    public void guardarLibro(String id, String titulo, Date fechaPublicacion, int numeroPaginas, double valorfisico,
                             double valordigital, int tipo, int id_pais, int id_ciudad, String isbn,int volumen,int tomo,int edicion,
                             int editorial_id) {
        System.out.println("metodo agregar Libro");
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                TblLibros item = new TblLibros();
                item.setId(id);
                item.setTitulo(titulo);
                System.out.println("se agrego el titulo");
                item.setFechaPublicacion(fechaPublicacion);
                System.out.println("se agrego la fecha");
                
                item.setNumeroPaginas(numeroPaginas);
                item.setValorFisico(valorfisico);
                item.setValorDigital(valordigital);
                System.out.println("ingresando tipo"+tipo);
                item.setTipo(tipo);
                
                TblPaises pais=em.find(TblPaises.class, id_pais);
                System.out.println("encontro pais" + pais.getNombre());
                item.setPaisId(pais);
                TblCiudades ciudad=em.find(TblCiudades.class, id_ciudad);
                item.setCiudadId(ciudad);
                
                //Datos del libro
                item.setIsbn(isbn);
                item.setVolumen(volumen);
                item.setTomo(tomo);
                item.setEdicion(edicion);
                TblCatalogosItems editorial=em.find(TblCatalogosItems.class, editorial_id);
                item.setEditorialId(editorial);
                
                em.persist(item);
                tx.commit();
                System.out.println("Se agrego un libro");
        

    }

    @Override
    public void guardarRevista(String id, String titulo, Date fechaPublicacion, int numeroPaginas, double valorfisico,
                               double valordigital, int tipo, int id_pais, int id_ciudad, int volumen, int numero) {
        System.out.println("metodo agregar revista");
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                TblRevistas item = new TblRevistas();
                item.setId(id);
                item.setTitulo(titulo);
                System.out.println("se agrego el titulo");
                item.setFechaPublicacion(fechaPublicacion);
                System.out.println("se agrego la fecha");
                
                item.setNumeroPaginas(numeroPaginas);
                item.setValorFisico(valorfisico);
                item.setValorDigital(valordigital);
                System.out.println("ingresando tipo"+tipo);
                item.setTipo(tipo);
                
                TblPaises pais=em.find(TblPaises.class, id_pais);
                System.out.println("encontro pais" + pais.getNombre());
                item.setPaisId(pais);
                TblCiudades ciudad=em.find(TblCiudades.class, id_ciudad);
                item.setCiudadId(ciudad);
                
                //Datos dela revista
                item.setVolumen(volumen);
                item.setNumero(numero);
                
                em.persist(item);
                tx.commit();
                System.out.println("Se agrego una revista");

    }

    @Override
    public void guardarTesis(String id, String titulo, Date fechaPublicacion, int numeroPaginas, double valorfisico,
                             double valordigital, int tipo, int id_pais, int id_ciudad, int institucion_id,
                             int titulo_optado) {
        System.out.println("metodo agregar tesis");
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                TblTesis item = new TblTesis();
                item.setId(id);
                item.setTitulo(titulo);
                System.out.println("se agrego el titulo");
                item.setFechaPublicacion(fechaPublicacion);
                System.out.println("se agrego la fecha");
                
                item.setNumeroPaginas(numeroPaginas);
                item.setValorFisico(valorfisico);
                item.setValorDigital(valordigital);
                System.out.println("ingresando tipo"+tipo);
                item.setTipo(tipo);
                
                TblPaises pais=em.find(TblPaises.class, id_pais);
                System.out.println("encontro pais" + pais.getNombre());
                item.setPaisId(pais);
                TblCiudades ciudad=em.find(TblCiudades.class, id_ciudad);
                item.setCiudadId(ciudad);
                
                //Datos del tesis
                TblCatalogosItems institucion=em.find(TblCatalogosItems.class, institucion_id);
                item.setInstitucionId(institucion);
                item.setTituloOptado(titulo_optado);
                
                em.persist(item);
                tx.commit();
                System.out.println("Se agrego una tesis");


    }

    @Override
    public void guardarPublicacionRelaciones(String id, int listArea, int listAutor, int listCarrera,
                                             int listIdiomas) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        TblPublicaciones publicacion=em.find(TblPublicaciones.class, id);
        System.out.println("------------Se encontro la publicacion: "+publicacion.getId()+ " " +publicacion.getTitulo());
        //para las areas
            TblCatalogosItems area=em.find(TblCatalogosItems.class, listArea);
            TblPublicacionesAreas PubliArea=new TblPublicacionesAreas();
            PubliArea.setPublicacionId(publicacion);
            PubliArea.setAreaId(area);
            em.persist(PubliArea);
      
        
        //para los autores
            TblAutores autor=em.find(TblAutores.class, listAutor);
            TblPublicacionesAutores PubliAutor=new TblPublicacionesAutores();
            PubliAutor.setPublicacionId(publicacion);
            PubliAutor.setAutorId(autor);
            em.persist(PubliAutor);
            System.out.println("===========Se guardo el autor: "+listAutor);
        
        
        //para las carreras
            TblCatalogosItems carrera=em.find(TblCatalogosItems.class, listCarrera);
            TblPublicacionesCarreras PubliCarrera=new TblPublicacionesCarreras();
            PubliCarrera.setPublicacionId(publicacion);
            PubliCarrera.setCarreraId(carrera);
            em.persist(PubliCarrera);
            System.out.println("=============Se guardo el autor: "+listCarrera);
        
        
        //para los idiomas
            TblCatalogosItems idioma=em.find(TblCatalogosItems.class, listIdiomas);
            TblPublicacionesIdiomas PubliIdioma=new TblPublicacionesIdiomas();
            PubliIdioma.setPublicacionId(publicacion);
            PubliIdioma.setIdiomaId(idioma);
            em.persist(PubliIdioma);
            System.out.println("=============Se guardo el autor: "+listIdiomas);
        
        tx.commit();

    }
    
    @Override
    public void actualizarRevista(String id, String titulo, Date fechaPublicacion, Integer numeroPaginas,
                                  double valorfisico, double valordigital, Integer tipo, Integer id_pais, Integer id_ciudad,
                                  Integer volumen, Integer numero) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TblRevistas revista = em.find(TblRevistas.class, id);
        
        revista.setTitulo(titulo);
        revista.setFechaPublicacion(fechaPublicacion);
        revista.setNumeroPaginas(numeroPaginas);
        revista.setValorFisico(valorfisico);
        revista.setValorDigital(valordigital);
        revista.setTipo(tipo);
        TblPaises pais = (id_pais == null) ? null : em.find(TblPaises.class, id_pais);
        revista.setPaisId(pais);
        TblCiudades ciudad = (id_ciudad == null) ? null : em.find(TblCiudades.class, id_ciudad);
        revista.setCiudadId(ciudad);
        
        revista.setVolumen(volumen);
        revista.setNumero(numero);    
        
        em.persist(revista);
        tx.commit();

    }

    @Override
    public void actualizarTesis(String id, String titulo, Date fechaPublicacion, Integer numeroPaginas, double valorfisico,
                                double valordigital, Integer tipo, Integer id_pais, Integer id_ciudad, Integer institucion_id,
                                Integer titulo_optado) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TblTesis tesis = em.find(TblTesis.class, id);
        
        tesis.setTitulo(titulo);
        tesis.setFechaPublicacion(fechaPublicacion);
        tesis.setNumeroPaginas(numeroPaginas);
        tesis.setValorFisico(valorfisico);
        tesis.setValorDigital(valordigital);
        tesis.setTipo(tipo);
        TblPaises pais = (id_pais == null) ? null : em.find(TblPaises.class, id_pais);
        tesis.setPaisId(pais);
        TblCiudades ciudad = (id_ciudad == null) ? null : em.find(TblCiudades.class, id_ciudad);
        tesis.setCiudadId(ciudad);
        
        TblCatalogosItems editorial=(institucion_id == null) ? null : em.find(TblCatalogosItems.class,institucion_id);
        tesis.setInstitucionId(editorial);
        tesis.setTituloOptado(titulo_optado);
        
        
        em.persist(tesis);
        tx.commit();

    }


    @Override
    public void actualizarPublicacionRelaciones(String id, int area, int autor, int carrera, int idioma) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TblPublicaciones publicacion=em.find(TblPublicaciones.class, id);
        
        //area
        Query query = em.createNamedQuery("TblPublicacionesAreas.consultarPorPublicacion");
        query.setParameter("publicacionId", publicacion);
        TblPublicacionesAreas tblPubArea =(TblPublicacionesAreas) query.getSingleResult();
        TblCatalogosItems objArea=em.find(TblCatalogosItems.class, area);
        tblPubArea.setAreaId(objArea);
        em.persist(tblPubArea);
        
        //autor
        query = em.createNamedQuery("TblPublicacionesAutores.consultarPorPublicacion");
        query.setParameter("publicacionId", publicacion);
        TblPublicacionesAutores tblPubAutor=(TblPublicacionesAutores) query.getSingleResult();
        TblAutores objAutor=em.find(TblAutores.class, autor);
        tblPubAutor.setAutorId(objAutor);
        em.persist(tblPubAutor);
        
        //carrera
        query = em.createNamedQuery("TblPublicacionesCarreras.consultarPorPublicacion");
        query.setParameter("publicacionId", publicacion);
        TblPublicacionesCarreras tblPubCarrera=(TblPublicacionesCarreras) query.getSingleResult();
        TblCatalogosItems objCarrera=em.find(TblCatalogosItems.class, autor);
        tblPubCarrera.setCarreraId(objCarrera);
        em.persist(tblPubCarrera);
        //idioma
        query = em.createNamedQuery("TblPublicacionesIdiomas.consultarPorPublicacion");
        query.setParameter("publicacionId", publicacion);
        TblPublicacionesIdiomas tblPubIdioma=(TblPublicacionesIdiomas) query.getSingleResult();
        TblCatalogosItems objIdioma=em.find(TblCatalogosItems.class, autor);
        tblPubIdioma.setIdiomaId(objIdioma);
        em.persist(tblPubIdioma);
        
        tx.commit();

    }
}
