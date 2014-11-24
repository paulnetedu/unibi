package edu.universidad.unibi.cuprestamoejemplar.bean;

import edu.universidad.dominio.unibi.TblUsuarios;
import edu.universidad.unibi.cuprestamoejemplar.BoPrestamoEjemplar;

import edu.universidad.unibi.cuprestamoejemplar.BoPrestamoEjemplarImpl;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoEjemplar;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoLector;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoSancion;
import edu.universidad.unibi.util.bean.BeanNotificacionData;
import edu.universidad.unibi.cubusquedaejemplar.dto.dtoUsuario;
import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogo;
import edu.universidad.unibi.curegistrocatalogo.BoRegistroCatalogoImpl;
import edu.universidad.unibi.curegistrocatalogo.bean.BeanActualizacionPublicacion;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoResultado;

import edu.universidad.unibi.curegistrousuario.bean.BeanRegistroUsuario;
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
    private boolean presactivos;
    private String apellidos;
    private String estado;
    private int canPrestamoActivos;
    private int nroMaxLibros;
    private int nroLibrosSeleccionadas;
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
        nroMaxLibros=parametro.getMaxPrestamosDia();
        
    }
    
    public void numerodelibrosPrestados()
    {
       int lib=idEjemplares.size();
           nroLibrosSeleccionadas=lib;
       }
    @PreDestroy
    public void destroy() {
        ((Bo) bo).closeEmf();
    }
  

    public void guardarPrestamo()
    {
        if (id == 0){
            BeanNotificacionData.show(2, "Debe ingresar sus datos de usuario.");
        }else{
            if(estado == "Sancionado"){
                BeanNotificacionData.show(2, "No procede: Usuario sancionado.");
            }else if(canPrestamoActivos == 0){
                    if(nroLibrosSeleccionadas > 2){
                        BeanNotificacionData.show(2, "No puede seleccionar más de dos ejemplares.");
                    }
                    else{
                    bo.guardarPrestamo(idEjemplares, id, id);
                    BeanNotificacionData.show(2, "Los Datos se Guardaron con Éxito");
                }
            }else if(canPrestamoActivos < 2){
                if(nroLibrosSeleccionadas > 1){
                        BeanNotificacionData.show(2, "No puede seleccionar más de un ejemplar.");
                    }
                    else{
                    bo.guardarPrestamo(idEjemplares, id, id);
                    BeanNotificacionData.show(2, "Los Datos se Guardaron con Éxito");
                }                  
            }else{
                BeanNotificacionData.show(2, "No procede: Posee prestamos activos.");
            }
        }
    }
    public void validar(Date fechaFin)
    {
        Date fechaPrestamo=new Date();
        if(fechaFin.getDate()>=fechaPrestamo.getDate())
        {
            estado="Sancionado";
        }  
    }

    //-------------------------------------------------------------
    public void buscarUsuarioPorDocumento(){
        dtoUsuario usuario =bo.getUsuarioDto(numeroDocumento);
        this.id = usuario.getId();
        this.nombres = usuario.getApellidosNombres();
        this.apellidos = usuario.getApellidos();
        this.estado = usuario.getEstado();
        this.canPrestamoActivos = usuario.getCantPrestamosActivos();
        this.presactivos = usuario.getTienePrestamosActivos();
    }

    public void setCanPrestamoActivos(int canPrestamoActivos) {
        this.canPrestamoActivos = canPrestamoActivos;
    }

    public int getCanPrestamoActivos() {
        return canPrestamoActivos;
    }

    public boolean usuarioRegistrado(){
        if (this.estado == "Sin Registrarse" ){
            return true;
        }else{
            return false;    
        }  
    }
//-------------------------------------

    public void guardarUsuario(){
        if(nombres == ""){
            BeanNotificacionData.show(2, "Ingresar nombres");
        }else if(apellidos == ""){
            BeanNotificacionData.show(2, "Ingresar apellidos");
        }else{
            bo.registrarUsuario(nombres, apellidos, numeroDocumento, 0);
            BeanNotificacionData.show(2, "Se registro nuevo usuario");
            buscarUsuarioPorDocumento();
        }  
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setPresactivos(boolean presactivos) {
        this.presactivos = presactivos;
    }

    public boolean isPresactivos() {
        return presactivos;
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

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setNroMaxLibros(int nroMaxLibros) {
        this.nroMaxLibros = nroMaxLibros;
    }

    public int getNroMaxLibros() {
        return nroMaxLibros;
    }

    public void setnroLibrosSeleccionadas(int nroLibrosSeleccionadas) {
        this.nroLibrosSeleccionadas = nroLibrosSeleccionadas;
    }

    public int getnroLibrosSeleccionadas() {
        return nroLibrosSeleccionadas;
    }
    
}
