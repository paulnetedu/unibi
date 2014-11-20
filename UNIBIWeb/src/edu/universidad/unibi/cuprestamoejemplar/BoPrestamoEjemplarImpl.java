package edu.universidad.unibi.cuprestamoejemplar;

import edu.universidad.dominio.unibi.TblAutores;
import edu.universidad.dominio.unibi.TblCatalogosItems;
import edu.universidad.dominio.unibi.TblCiudades;
import edu.universidad.dominio.unibi.TblDevoluciones;
import edu.universidad.dominio.unibi.TblEjemplares;
import edu.universidad.dominio.unibi.TblLibros;
import edu.universidad.dominio.unibi.TblPaises;
import edu.universidad.dominio.unibi.TblPrestamos;
import edu.universidad.dominio.unibi.TblPrestamosDetalle;
import edu.universidad.dominio.unibi.TblPublicaciones;
import edu.universidad.dominio.unibi.TblPublicacionesAutores;
import edu.universidad.dominio.unibi.TblRevistas;
import edu.universidad.dominio.unibi.TblSanciones;
import edu.universidad.dominio.unibi.TblTesis;
import edu.universidad.dominio.unibi.TblUsuarios;
import edu.universidad.unibi.cubusquedaejemplar.dto.dtoUsuario;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoEjemplar;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoLector;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoPrestamo;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoSancion;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoPublicacion;
import edu.universidad.unibi.util.Bo;

import edu.universidad.unibi.util.bean.BeanNotificacionData;

import edu.universidad.unibi.util.bean.BeanParametroData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import javax.swing.JOptionPane;

public class BoPrestamoEjemplarImpl extends Bo implements BoPrestamoEjemplar {

    public List<DtoPrestamo> consultarPrestamoporCodigoPrestamo(int codigo){
            List<DtoPrestamo> lstDto = new ArrayList<DtoPrestamo>();
            TblPrestamos prestamo = em.find(TblPrestamos.class, codigo);
              List<TblPrestamosDetalle> lstPrestamoDet = prestamo.getTblPrestamosDetalleList();   
              for (TblPrestamosDetalle pd : lstPrestamoDet) {
                 
                      DtoPrestamo dto = new DtoPrestamo();
                      TblEjemplares ejemplar = pd.getEjemplarId();
                      TblPublicaciones publicacion = ejemplar.getPublicacionId();   
                if (pd.getEstado() == 0) {  //pendiente =0
                      dto.setId(prestamo.getId());
                      dto.setIdejemplar(ejemplar.getId());
                      dto.setTitulo(publicacion.getTitulo());
                      dto.setFechaDevolucion(prestamo.getFechaDevolucionMax());
                      //bueno-regular-malo
                      dto.setEstadoFisico(ejemplar.getEstadoFisico());
                      //prestado-devuelto
                      dto.setEstadoPrestamo(prestamo.getEstado());
                      dto.setIdDetalle(pd.getId());
                      
                      lstDto.add(dto);
                  }
              }

        return lstDto;

    
    }
    public List<DtoEjemplar> consultarEjemplarPorTitulo(String titulo) {
       
     
        ////////////////////////////////////
        List<DtoEjemplar> lstDto = new ArrayList<DtoEjemplar>();
        Query query = em.createNamedQuery("TblEjemplares.consultarPorTitulo");
        query.setParameter("titulo", "%" + titulo + "%");
        List<TblEjemplares> lstEntidad = query.getResultList();
        for (TblEjemplares ejemplar : lstEntidad) {
            DtoEjemplar dto = new DtoEjemplar();
            dto.setEstadoDisponibilidad(ejemplar.getEstadoDisponibilidad());
            dto.setFechaPublicacion(ejemplar.getPublicacionId().getFechaPublicacion());
            dto.setId(ejemplar.getId());
            dto.setTitulo(ejemplar.getPublicacionId().getTitulo());
            if (ejemplar.getEstadoDisponibilidad() == 1) { // prestado
                Query queryPrestamo = em.createNamedQuery("TblPrestamosDetalle.consultarUltimoPorEjemplarEstado");
                queryPrestamo.setParameter("ejemplarId", ejemplar.getId());
                queryPrestamo.setParameter("estado", 0);
                TblPrestamosDetalle detalle = (TblPrestamosDetalle) queryPrestamo.getSingleResult();
                if (detalle != null) {
                    dto.setFechaDevolucion(detalle.getPrestamoId().getFechaDevolucionMax());
                }
            }
            String autores = "";
            for (TblPublicacionesAutores pubAut : ejemplar.getPublicacionId().getTblPublicacionesAutoresList()) {
                autores += pubAut.getAutorId().getNombre() + ", ";
            }
            dto.setAutores(autores);
            lstDto.add(dto);
        }
        return lstDto;
    }
    
    public List<DtoPrestamo> consultarPrestamoporCodigoEjemplar(String codigo){
        List<DtoPrestamo> lstDto = new ArrayList<DtoPrestamo>();
        Query query = em.createNamedQuery("TblPrestamosDetalle.consultarPorEjemplarPrestado");
        query.setParameter("ejemplarId","%" +codigo+"%");
        query.setParameter("estado", 0); // mostrar los que estan pendientes
        List<TblPrestamosDetalle> lstEntidad = query.getResultList();
        for (TblPrestamosDetalle detalle : lstEntidad) {
            DtoPrestamo dto = new DtoPrestamo();
            TblEjemplares ejemplar = detalle.getEjemplarId();
            TblPublicaciones publicacion = ejemplar.getPublicacionId();
            TblPrestamos prestamo = detalle.getPrestamoId();
            dto.setId(prestamo.getId());
            dto.setIdejemplar(ejemplar.getId());
            dto.setTitulo(publicacion.getTitulo());
            dto.setFechaDevolucion(prestamo.getFechaDevolucionMax());
            dto.setEstadoFisico(ejemplar.getEstadoFisico());
            dto.setEstadoPrestamo(prestamo.getEstado());
            dto.setIdDetalle(detalle.getId());
         //   dto.setEstado_detalle_prestamo(detalle.getEstado());
           // dto.setEstadofisico_devolucion(detalle.getEstadoFisico());
          

            lstDto.add(dto);
        }
        return lstDto;

    
    }
    //------------------------------------------aqui
          protected TblUsuarios usuario; //usuario para la vista listaPrestamos y PrestamosRealizados
        
        public  dtoUsuario getUsuarioDto(String nroDocumento){
            usuario= getUsuarioTbl(nroDocumento);
            
            if (usuario!=null){
                Boolean tienePrestamosActivos=false;
                if (getEstadoPrestamosActivos()!=""){tienePrestamosActivos=true;}
                return new dtoUsuario(nroDocumento, ApellidosNombresUsuario(),ApellidosUsuario(),getEstadoUsuario(),tienePrestamosActivos);    
            }else{
                return new dtoUsuario(nroDocumento,null,null,"Sin Registrarse",false);
            }
        }
            
        private TblUsuarios getUsuarioTbl(String dni) {
            Query query = em.createNamedQuery("TblUsuarios.consultarPorNumeroDocumento");
            query.setParameter("numeroDocumento",dni);
            List<TblUsuarios> busqueda = query.getResultList();
            //EXCEPTION: edu.universidad.dominio.unibi.TblUsuarios cannot be cast to edu.universidad.dominio.unibi.TblUsuarios
            //Evento: al realizar run por segunda vez a la pagina.
            //Solucion; cerrar UNIBI e IntegratedWebLogicServer y volver a iniciar
            if (busqueda.size()>0){
                TblUsuarios usuario= busqueda.get(0);
                System.out.println("se encontro al usuario="+usuario.getNumeroDocumento());
                return usuario;
            }else{
                return null;
            }
        }
        private String ApellidosNombresUsuario( ){
               return usuario.getNombres();
                  //usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno();
                
        }
         
        private String ApellidosUsuario(){
               //return usuario.getNombres();
                 return usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno();      
        }
        private String getEstadoUsuario(){
            String estado;
            estado=getEstadoAmonestado();
           if (estado==""){estado=getEstadoPrestamosActivos();}
            if (estado==""){estado="Apto";}
          
            return estado;
        }
        private String getEstadoAmonestado() {
            String estado="";
            if (usuario!=null){
                TblSanciones sancionMasLarga=null;
                List<TblSanciones> sanciones=usuario.getTblSancionesList();
                
                //revisar las sanciones
                for (TblSanciones sancion:sanciones){
                    if (sancionMasLarga==null){sancionMasLarga=sancion;}
                    if (sancionMasLarga.getFechaFin().before(sancion.getFechaFin())){sancionMasLarga=sancion;}
                }
                
                //elaborar mensaje de amonestacion
               // if (sancionMasLarga!=null){return "Esta sancionado hasta "+ getStringFromDate(sancionMasLarga.getFechaFin(),"dd/MM/yyyy")+", Motivo:"+sancionMasLarga.getMotivo();}
               if (sancionMasLarga!=null){return "Sancionado";}
            }
            
            return estado;
        }
        private String getEstadoPrestamosActivos(){
            String estado="";
            int nroEjemplares=0;
            
            if (usuario!=null){
                List<TblPrestamos> prestamos = usuario.getTblPrestamosList();
                for (TblPrestamos prestamo:prestamos){
                    nroEjemplares+=prestamo.getTblPrestamosDetalleList().size();        
                }
            }
            
            if (nroEjemplares>0){return "Tiene ("+String.valueOf(nroEjemplares)+") ejemplares prestados a la fecha.";}
            
            return estado;
        }
        //------------------------------------------------
   public void guardarDevolucion(int iddetalle, Date fechaDev, int idBibliotecario, int estadoFisico, int dias) {
        //JOptionPane.showMessageDialog(null, iddetalle+ fechaDev.toString() + idBibliotecario + estadoFisico + dias); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TblDevoluciones dtodev =  new TblDevoluciones();
        TblPrestamosDetalle detalle = em.find(TblPrestamosDetalle.class, iddetalle);
        TblUsuarios usuario = em.find(TblUsuarios.class, idBibliotecario);
        dtodev.setPrestamoDetalleId(detalle);
        dtodev.setFechaDevolucion(fechaDev);
        dtodev.setBibliotecarioId(usuario);
        dtodev.setEstadoFisico(estadoFisico);
        dtodev.setDiasAtraso(dias);

        
        em.persist(dtodev);
        tx.commit();
        

    }
   
    public void actualizarDetalle(int iddetalle){
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TblPrestamosDetalle detalle =  em.find(TblPrestamosDetalle.class, iddetalle);
            int estado;
            estado = detalle.getEstado();
            //JOptionPane.showMessageDialog(null, iddetalle +" "+ estado); 
            detalle.setEstado(1);
            estado = detalle.getEstado();
            //JOptionPane.showMessageDialog(null, iddetalle +" "+ estado);
            em.persist(detalle);
            tx.commit();
        }
    
    public void actualizarEjemplar(String idEjemplar, int estadoDisponibilidad, int estadoFisico){
             EntityTransaction tx = em.getTransaction();
             tx.begin();
             TblEjemplares ejemplar =  em.find(TblEjemplares.class, idEjemplar);
             ejemplar.setEstadoFisico(estadoFisico);
             ejemplar.setEstadoDisponibilidad(estadoDisponibilidad);
             em.persist(ejemplar);
             tx.commit();
         }

public void guardarPrestamo(List<DtoEjemplar> idEjemplares, int lectorId, int bibliotecarioId)
    {
        List<DtoEjemplar> lstDtoEjem=idEjemplares;
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TblUsuarios usuario = em.find(TblUsuarios.class, lectorId);
        TblUsuarios bibliotecario = em.find(TblUsuarios.class, bibliotecarioId);
        TblPrestamos prestamo=new TblPrestamos();
        prestamo.setBibliotecarioId(bibliotecario);
        prestamo.setLectorId(usuario);
        Date fechaPrestamo=new Date();
        Date fechaDevolucion=new Date();
        BeanParametroData parametro=new BeanParametroData();
        fechaDevolucion.setDate(fechaPrestamo.getDate()+parametro.getMaxDiasPrestamo());
        
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucionMax(fechaDevolucion);
        prestamo.setEstado(0);
        prestamo.setLectorId(usuario);
        prestamo.setBibliotecarioId(bibliotecario);
        em.persist(prestamo);
        tx.commit();
        
        
        Query query = em.createNamedQuery("TblPrestamos.consultarPrestamos");
        List<TblPrestamos> lstPrestamo=query.getResultList();
        TblPrestamos prestamo2=new TblPrestamos();
        for(TblPrestamos p:lstPrestamo)
        {
            prestamo2=p;
        }
        
        
        for(DtoEjemplar s:lstDtoEjem)
        {
            EntityTransaction tx2 = em.getTransaction();
            tx2.begin();
            //TblUsuarios usuario = em.find(TblUsuarios.class, lectorId);
            //TblUsuarios bibliotecario = em.find(TblUsuarios.class, bibliotecarioId);
            TblPrestamosDetalle detallePrestamo=new TblPrestamosDetalle();
            TblEjemplares ejemplar=em.find(TblEjemplares.class, s.getId());
            detallePrestamo.setEjemplarId(ejemplar);
            detallePrestamo.setEstado(0);
            detallePrestamo.setPrestamoId(prestamo2);
            detallePrestamo.setEstadoFisico(ejemplar.getEstadoFisico());
            em.persist(detallePrestamo);
            ejemplar.setEstadoDisponibilidad(1);
            em.persist(ejemplar);
            tx2.commit();
            
           
        }
        
    }
    
    
    
    public List<DtoSancion> consultarSancionesPorDocumento(int id)
    {
        List<DtoSancion> lstDto=new ArrayList<DtoSancion>();
        TblUsuarios san = em.find(TblUsuarios.class, id);
        //Query query = em.createNamedQuery("TblEjemplares.consultarPorPublicacion");
        //query.setParameter("publicacion", );
        Query query = em.createNamedQuery("TblSanciones.consultarPorLector");
        //String cadenaBusqueda =  id.toUpperCase();
        String cadenaBusqueda=id+"";
        System.out.println("cadenaBusqueda ----- -------=" + id);
        query.setParameter("usuarioId", san);
        List<TblSanciones> lstSan=query.getResultList();
        DtoSancion dto=null;
        for(TblSanciones s:lstSan)
        {
            dto=new DtoSancion();
            dto.setId(s.getId());
            dto.setFechaFin(s.getFechaFin());
            dto.setFechaInicio(s.getFechaInicio());
            dto.setMotivo(s.getMotivo());
            dto.setPrestamoDetalleId(id);
            //dto.setUsuarioId(s.getUsuarioId());
            lstDto.add(dto);
        }
        return lstDto;
    }
        
    public List<DtoLector> consultarUsuarioPorNumeroDocumento2(String titulo) {
        List<DtoLector> lstDto = new ArrayList<DtoLector>();
        TblUsuarios entidad = new TblUsuarios();
        Query query = em.createNamedQuery("TblUsuarios.consultarPorNumeroDocumento");
        String cadenaBusqueda =  titulo.toUpperCase();
        System.out.println("cadenaBusqueda ----- -------=" + cadenaBusqueda);
        query.setParameter("numeroDocumento", cadenaBusqueda);
        List<TblUsuarios> lstTbl = query.getResultList();
        System.out.println("lstTbl = " + ((lstTbl == null) ? -1 : lstTbl.size()));
        DtoLector dto = null;
        for (TblUsuarios p : lstTbl) {
            
            dto = new DtoLector();
            dto.setId(p.getId());
            dto.setNombres(p.getNombres());
            dto.setApellidoPaterno(p.getApellidoPaterno());
            dto.setApellidoMaterno(p.getApellidoMaterno());
            
            lstDto.add(dto);
        }
        System.out.println(lstDto);
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
            //lstDto.add(dto);
            lstDto.add(dto);
        }
        return lstDto;
    }
    
    public void actualizarPrestamo(int idPrestamo, int estado){
                boolean control = false;
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                TblPrestamos prestamo =  em.find(TblPrestamos.class, idPrestamo);
                Query query = em.createNamedQuery("TblPrestamosDetalle.consultarPorPrestamo");
                query.setParameter("idPrestamo", idPrestamo);
                List<TblPrestamosDetalle> lstEntidad = query.getResultList();
                
                for (TblPrestamosDetalle detalle : lstEntidad) {
                  //  DtoEjemplar dto = new DtoEjemplar();
                    if (detalle.getEstado() == 1) { // prestado
                       control = true;
                       break;
                    }
                  }
                
                if(control==false){
                    
                    prestamo.setEstado(estado);
                    }
                
                em.persist(prestamo);
                tx.commit();
            }


    @Override
    public List<DtoEjemplar> consultarEjemplarPorCodigoEjemplar(String cadenaBusqueda) {
        List<DtoEjemplar> lstDto = new ArrayList<DtoEjemplar>();
        Query query = em.createNamedQuery("TblEjemplares.consultarPorCodigo");
        query.setParameter("codigo", "%" + cadenaBusqueda + "%");
        List<TblEjemplares> lstEntidad = query.getResultList();
        for (TblEjemplares ejemplar : lstEntidad) {
            DtoEjemplar dto = new DtoEjemplar();
            dto.setEstadoDisponibilidad(ejemplar.getEstadoDisponibilidad());
            dto.setFechaPublicacion(ejemplar.getPublicacionId().getFechaPublicacion());
            dto.setId(ejemplar.getId());
            dto.setTitulo(ejemplar.getPublicacionId().getTitulo());
            if (ejemplar.getEstadoDisponibilidad() == 1) { // prestado
                Query queryPrestamo = em.createNamedQuery("TblPrestamosDetalle.consultarUltimoPorEjemplarEstado");
                queryPrestamo.setParameter("ejemplarId", ejemplar.getId());
                queryPrestamo.setParameter("estado", 0);
                TblPrestamosDetalle detalle = (TblPrestamosDetalle) queryPrestamo.getSingleResult();
                if (detalle != null) {
                    dto.setFechaDevolucion(detalle.getPrestamoId().getFechaDevolucionMax());
                }
            }
            String autores = "";
            for (TblPublicacionesAutores pubAut : ejemplar.getPublicacionId().getTblPublicacionesAutoresList()) {
                autores += pubAut.getAutorId().getNombre() + ", ";
            }
            dto.setAutores(autores);
            lstDto.add(dto);
        }
        return lstDto;
    }
}
