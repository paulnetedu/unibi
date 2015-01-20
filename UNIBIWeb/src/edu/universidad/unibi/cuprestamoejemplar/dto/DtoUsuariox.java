package edu.universidad.unibi.cuprestamoejemplar.dto;

public class DtoUsuariox {
    public DtoUsuariox(String nroDocumento, String nombresApellidos,String apellidosp, String apellidosm ) {
        super();
        this.nroDocumento=nroDocumento;
        this.apellidosNombres=nombresApellidos;
        this.apellidop=apellidosp;  
        this.apellidom=apellidosm;
    }
    protected int id;
    protected String nroDocumento;
    protected String apellidosNombres;
    protected String apellidop;
    protected String apellidom;

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getApellidom() {
        return apellidom;
    }
    protected String estado;
    protected String apellidos;
    public void setId(int id) {
            this.id = id;
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
    protected Boolean tienePrestamosActivos=false;

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
