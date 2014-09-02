package edu.universidad.unibi.curegistrocatalogo.dto;

import java.io.Serializable;

public class DtoAutor implements Serializable {
    private int id;
    private String nombre;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
