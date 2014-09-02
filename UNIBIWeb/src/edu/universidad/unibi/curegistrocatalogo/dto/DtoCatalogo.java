package edu.universidad.unibi.curegistrocatalogo.dto;

public class DtoCatalogo {
    private int id;
    
    private String descripcion;

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

}
