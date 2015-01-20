package edu.universidad.unibi.cuprestamoejemplar.bean;

import edu.universidad.unibi.cubusquedaejemplar.dto.dtoUsuario;
import edu.universidad.unibi.cuprestamoejemplar.BoPrestamoEjemplar;
import edu.universidad.unibi.cuprestamoejemplar.BoPrestamoEjemplarImpl;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoEjemplar;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoLector;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoSancion;
import edu.universidad.unibi.util.Bo;
import edu.universidad.unibi.util.JsfUtil;
import edu.universidad.unibi.util.bean.BeanNotificacionData;
import edu.universidad.unibi.util.bean.BeanParametroData;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoUsuariox;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "BeanRSancion")
@RequestScoped

public class BeanRegistroSancion {
    private BoPrestamoEjemplar bo;
    private int id;
    private String numeroDocumento;
    private String nombres;
    private boolean presactivos;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String apellidos;
    private String estado;
    private Integer motivo;
    private Date fechainicial;
    private Date fechafinal;

   
    public BeanRegistroSancion() {
        bo = new BoPrestamoEjemplarImpl();
        Date ahora = new Date();            
        fechainicial=ahora;
        
    }

    

    //-------------------------------------------------------------
    public void buscarUsuarioPorDocumento() {
        System.out.print("ddddddddddddgggddddddddddd");
        DtoUsuariox usuario;
        usuario = bo.getUsuario(numeroDocumento);
        this.nombres = usuario.getApellidosNombres();                                                
        this.apellidoPaterno = usuario.getApellidop();
        this.apellidoMaterno = usuario.getApellidom();
         System.out.print("rrrrrrrrrr"+nombres);
        System.out.print("rrrrrrrrrr"+apellidoPaterno);
        System.out.print("rrrrrrrrrr"+apellidoMaterno);
    }
   
    public void guardarSancion() {
        System.out.printf("resultadooooooooooooooo"+numeroDocumento);
        System.out.printf("resultadooooooooooooooo"+motivo);
        System.out.printf("resultadooooooooooooooo"+fechainicial);
        System.out.printf("resultadooooooooooooooo"+fechafinal);
        DtoSancion sancion;
        bo.guardarSancionall(numeroDocumento,motivo,fechainicial,fechafinal);
        BeanNotificacionData.show(2,"Se añadio sancion");      
    }
    //-------------------------------------

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public Date getFechafinal() {
        return fechafinal;
    }
    public String getApellidos() {
        String ap="";
        if (apellidoPaterno==null)
        {
            apellidoPaterno="";
            }
        if (apellidoMaterno==null)
        {
                apellidoMaterno="";
            }
        ap=apellidoPaterno+apellidoMaterno; 
          return ap;
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

    public void setfechainicial(Date fechaPublicacioninicial) {
        this.fechainicial = fechaPublicacioninicial;
    }

    public Date getfechainicial() {
        return fechainicial;
    }


    public void setMotivo(Integer motivo) {
        this.motivo = motivo;
    }

    public Integer getMotivo() {
        return motivo;
    }
    
}
