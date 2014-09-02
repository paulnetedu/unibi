package edu.universidad.unibi.cuprestamoejemplar.bean;

import edu.universidad.dominio.unibi.TblUsuarios;
import edu.universidad.unibi.cuprestamoejemplar.BoPrestamoEjemplar;

import edu.universidad.unibi.cuprestamoejemplar.BoPrestamoEjemplarImpl;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoEjemplar;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoLector;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoSancion;
import edu.universidad.unibi.util.bean.BeanNotificacionData;
import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogo;
import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogoImpl;
import edu.universidad.unibi.curegistrocatalogo.bean.BeanActualizacionPublicacion;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoResultado;

import edu.universidad.unibi.util.Bo;

import edu.universidad.unibi.util.JsfUtil;
import edu.universidad.unibi.util.bean.BeanParametroData;

import java.io.Serializable;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "beanDatosUsuario")
@SessionScoped

public class BeanDatosAlumno implements Serializable {
    private BoPrestamoEjemplar bo;
    private int id;
    private String numeroDocumento;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String estado;
    private String nroMaxLibros;
    private String nroLibrosPrestados;
    private List<DtoLector> lstDtoLector;
    private List<DtoSancion> lstDtoSancion;
    private String fecha;
    private List<DtoEjemplar> idEjemplares;
    private Date fechaDevolucion;
    private BeanParametroData parametro;
    private BeanPrestamoEjemplar beanPrestamo;
    //private DtoEjemplar dtoEjemplar = new DtoEjemplar();
    
    
    public BeanDatosAlumno() {
        bo = new BoPrestamoEjemplarImpl();
        verEjemplares("ABC0001");  
        numerodelibrosPrestados();
    }
    
    public void verEjemplares(String idPublicacion) {
        parametro=new BeanParametroData();
        beanPrestamo=(BeanPrestamoEjemplar) JsfUtil.obtenerObjetoSesion("beanPrestamoEjemplar");
        idEjemplares = beanPrestamo.getLstDtoEjemplar();
        fechaDevolucion=new Date();
        nroMaxLibros=parametro.getMaxPrestamosDia()+"";
        
    }
    
    public void numerodelibrosPrestados()
    {
       int lib=idEjemplares.size();
           nroLibrosPrestados=lib+"";
       }
    @PreDestroy
    public void destroy() {
        ((Bo) bo).closeEmf();
    }
  

    public void guardarPrestamo()
    {
        bo.guardarPrestamo(idEjemplares, id, id);
        BeanNotificacionData.show(2, "Los Datos se Guardaron con Exito");
    }
    public void validar(Date fechaFin)
    {
        Date fechaPrestamo=new Date();
        if(fechaFin.getDate()>=fechaPrestamo.getDate())
        {
            estado="Sancionado";
        }
        
        
    }
    public void buscarUsuarioPorDocumento()
    {
        
        lstDtoLector=bo.consultarUsuarioPorNumeroDocumento2(numeroDocumento);
        if (lstDtoLector==null)
            BeanNotificacionData.show(3, "No Existe el Usuario");
        
        lstDtoSancion=new ArrayList<DtoSancion>();
        
        for (DtoLector p : lstDtoLector) {
            
            id=(p.getId());
            nombres=p.getNombres();
            apellidoMaterno=p.getApellidoMaterno();
            apellidoPaterno=p.getApellidoPaterno();
            }
        
        lstDtoSancion=bo.consultarSancionesPorDocumento(id);
        
        
        for (DtoSancion s : lstDtoSancion) {

            estado = s.getId()+"";
            validar(s.getFechaFin());
        }
      
        
        Date fechaPrestamo=new Date();
        
        fechaDevolucion.setDate(fechaPrestamo.getDate()+parametro.getMaxDiasPrestamo());
       
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLstDtoLector(List<DtoLector> lstDtoLector) {
        this.lstDtoLector = lstDtoLector;
    }

    public List<DtoLector> getLstDtoLector() {
        return lstDtoLector;
    }

    public void setLstDtoSancion(List<DtoSancion> lstDtoSancion) {
        this.lstDtoSancion = lstDtoSancion;
    }

    public List<DtoSancion> getLstDtoSancion() {
        return lstDtoSancion;
    }

    public void setIdEjemplares(List<DtoEjemplar> idEjemplares) {
        this.idEjemplares = idEjemplares;
    }

    public List<DtoEjemplar> getIdEjemplares() {
        return idEjemplares;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setNroMaxLibros(String nroMaxLibros) {
        this.nroMaxLibros = nroMaxLibros;
    }

    public String getNroMaxLibros() {
        return nroMaxLibros;
    }

    public void setNroLibrosPrestados(String nroLibrosPrestados) {
        this.nroLibrosPrestados = nroLibrosPrestados;
    }

    public String getNroLibrosPrestados() {
        return nroLibrosPrestados;
    }
    
}
