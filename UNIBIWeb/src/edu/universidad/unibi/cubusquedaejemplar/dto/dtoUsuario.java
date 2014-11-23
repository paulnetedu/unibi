package edu.universidad.unibi.cubusquedaejemplar.dto;

public class dtoUsuario {
    protected int id;
    protected String nroDocumento;
    protected String apellidosNombres;
    protected String estado;
    protected String apellidos;
    protected Boolean tienePrestamosActivos=false;
    protected int cantPrestamosActivos = 0;
    
    public dtoUsuario(String nroDocumento, String nombresApellidos,String apellidos, String estado, Boolean tienePrestamosActivos) {
        super();
        this.nroDocumento=nroDocumento;
        this.apellidosNombres=nombresApellidos;
        this.estado=estado;
        this.apellidos=apellidos;
        this.tienePrestamosActivos=tienePrestamosActivos;
    }

    public void setId(int id) {
            this.id = id;
        }

    public void setCantPrestamosActivos(int cantPrestamosActivos) {
        this.cantPrestamosActivos = cantPrestamosActivos;
    }

    public int getCantPrestamosActivos() {
        return cantPrestamosActivos;
    }

    public int getId() {
            return id;
        }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }


    public void setTienePrestamosActivos(Boolean tienePrestamosActivos) {
        this.tienePrestamosActivos = tienePrestamosActivos;
    }

    public Boolean getTienePrestamosActivos() {
        return tienePrestamosActivos;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setApellidosNombres(String nombresApellidos) {
        this.apellidosNombres = nombresApellidos;
    }

    public String getApellidosNombres() {
        return apellidosNombres;
    }

    
}

