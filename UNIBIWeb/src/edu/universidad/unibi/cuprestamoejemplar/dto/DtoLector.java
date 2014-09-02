package edu.universidad.unibi.cuprestamoejemplar.dto;

import java.io.Serializable;

import java.util.Date;

public class DtoLector implements Serializable{
    
    private int id;
    
    private String nombres;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno ;
    
    
    
    public DtoLector() {
        
            }

    public void setId(int idUsuario) {
        this.id = idUsuario;
    }

    public int getId() {
        return id;
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
}
