package edu.universidad.unibi.cubusquedaejemplar.bean;

import edu.universidad.dominio.unibi.TblUsuarios;
import edu.universidad.unibi.cubusquedaejemplar.BoBusquedaEjemplar;
import edu.universidad.unibi.cubusquedaejemplar.BoBusquedaEjemplarImpl;
import edu.universidad.unibi.cubusquedaejemplar.dto.dtoEjemplaresPrestados;
import edu.universidad.unibi.cubusquedaejemplar.dto.dtoBusquedaEjemplar;
import edu.universidad.unibi.cubusquedaejemplar.dto.dtoUsuario;
import edu.universidad.unibi.cubusquedaejemplar.dto.dtoAutor;
import edu.universidad.unibi.util.Bo;
import edu.universidad.unibi.util.bean.BeanNotificacionData;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "beanBusquedaEjemplar")
@SessionScoped
public class BeanBusquedaEjemplar implements Serializable {

    //---- METODOS DEL INGENIERO-----------------------------------
    //   -----------------------------------------------------------------------------------------------------------------
    private BoBusquedaEjemplar bo;

    public BeanBusquedaEjemplar() {
        bo = new BoBusquedaEjemplarImpl();

        //this.listaEjemplaresSeleccionados=new ArrayList<dtoBusquedaEjemplar>();

        //this.listaEjemplaresSeleccionados.add(new dtoBusquedaEjemplar("1","titulo1","autor1","area1","Bueno","Disponible","00/00/0000"));
        //this.listaEjemplaresSeleccionados.add(new dtoBusquedaEjemplar("2","titulo2","autor2","area1","Malo","Disponible",null));
        //this.listaEjemplaresSeleccionados.add(new dtoBusquedaEjemplar("3","titulo3","autor3","area1","Bueno","Disponible",null));
        //this.listaEjemplaresSeleccionados.add(new dtoBusquedaEjemplar("4","titulo3","autor3","area1","Bueno","Disponible","00/00/0000"));
        //this.listaEjemplaresSeleccionados.add(new dtoBusquedaEjemplar("5","titulo3","autor3","area1","Bueno","Disponible","00/00/0000"));
    }

    @PreDestroy
    public void destroy() {
        ((Bo) bo).closeEmf();
    }

    public void guardar() {
        System.out.println("guardar");
        // Aqui invocar a la logica de negocio (bo) pasando la referencia this al metodo a invocar
        BeanNotificacionData.show(1, "Mensaje de prueba");
    }

    public void validar(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        System.out.println("validar");
        System.out.println("id del control =" + component.getClientId(context) + ", valor " + value);
        
    }
  

    //---- codigo para la vista BUSQUEDA EJEMPLAR ------------------------------------------------------------------------
    //   -----------------------------------------------------------------------------------------------------------------
    protected List<dtoBusquedaEjemplar> listaEjemplaresSeleccionados; //esta es la lista donde guardaran los ejemplares que seleccione el usuario.
    protected List<dtoBusquedaEjemplar> listaEjemplaresPrestar = new ArrayList<dtoBusquedaEjemplar>();//Lista de ejemplares para prestar.
    private List<dtoAutor> lstDtoAutor;
    private int count;
    private int row;
 
    public void setListaEjemplaresPrestar(List<dtoBusquedaEjemplar> listaEjemplaresPrestar) {
        this.listaEjemplaresPrestar = listaEjemplaresPrestar;
    }

    public List<dtoBusquedaEjemplar> getListaEjemplaresPrestar() {
        return listaEjemplaresPrestar;
    } 
    
    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        row=listaEjemplaresSeleccionados.size();
        if (row == count)
        { row=0;
        count=1;
          }
         else
        { count++;
          }
         return count;
    }
    protected int tipoBusqueda;

    public void setTipoBusqueda(int tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public int getTipoBusqueda() {
        return tipoBusqueda;
    }
    private String textoBusqueda;

    public List<dtoBusquedaEjemplar> getListaEjemplaresSeleccionados() {
        return listaEjemplaresSeleccionados;

    }

    public void buscarEjemplar() 
    {
           if (tipoBusqueda == 0) {
            buscarEjemplarPorTitulo();
        } else if (tipoBusqueda == 1) {
            buscarEjemplarPorAutor();
        } else if(tipoBusqueda == 2){
                buscarEjemplarPorArea();
        }
         count=0; 
    }

    public void setlstDtoBusqueda(List<dtoBusquedaEjemplar> lstDtoBusqueda) {
        this.listaEjemplaresSeleccionados = lstDtoBusqueda;
    }

    public void buscarEjemplarPorArea(){
        listaEjemplaresSeleccionados = bo.consultarEjemplarPorArea(textoBusqueda);
    }
    public void buscarEjemplarPorTitulo() {
        listaEjemplaresSeleccionados = bo.consultarEjemplarPorTitulo(textoBusqueda);
    }

    public void buscarEjemplarPorAutor() {
        System.out.println("buscarEjemplarPorAutor");
        listaEjemplaresSeleccionados = bo.consultarEjemplarPorAutor(textoBusqueda);
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }

    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    //   ---- codigo para la vista LISTA PRESTAMO ------------------------------------------------------------------------
    //   -----------------------------------------------------------------------------------------------------------------
    //accesos
    public void Agregar(){
        List<dtoBusquedaEjemplar> resultado = new ArrayList<dtoBusquedaEjemplar>();

        for (dtoBusquedaEjemplar ejemplar : this.listaEjemplaresSeleccionados) {
            if (ejemplar.getSeleccionado() == true) {
                resultado.add(ejemplar);
                listaEjemplaresPrestar.add(ejemplar);
            }
        }
        
        for (dtoBusquedaEjemplar ejemplar : resultado) {
            this.listaEjemplaresSeleccionados.remove(ejemplar);
            System.out.println("lista ejemplares seleccionados size=" + this.listaEjemplaresSeleccionados.size());
        }
        
    }
    
    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNombresApellidos(String apellidosNombres) {
        this.apellidosNombres = apellidosNombres;
    }

    public String getNombresApellidos() {
        return apellidosNombres;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setTienePrestamosActivos(Boolean tienePrestamosActivos) {
        this.tienePrestamosActivos = tienePrestamosActivos;
    }

    public Boolean getTienePrestamosActivos() {
        return this.tienePrestamosActivos;
    }

    //variables
    protected String nroDocumento;
    protected String apellidosNombres;
    protected String estado;
    protected Boolean tienePrestamosActivos;

    public void NroDocumentoValueChanged(ValueChangeEvent vce) {
        String newValue = vce.getNewValue().toString();
        
        dtoUsuario usuario = bo.getUsuarioDto(newValue);
        this.apellidosNombres = usuario.getApellidosNombres();
        this.estado = usuario.getEstado();
        this.tienePrestamosActivos = usuario.getTienePrestamosActivos();
        System.out.println("tiene prestamos activos: " + String.valueOf(this.tienePrestamosActivos));
    }

    public String VerPrestamosRealizados() {
        this.listaPrestamos = bo.getlistaEjemplaresPrestados();
        return "prestamosRealizados";
    }

    public String QuitarSeleccionados() {
        List<dtoBusquedaEjemplar> resultado = new ArrayList<dtoBusquedaEjemplar>();

        for (dtoBusquedaEjemplar ejemplar : this.listaEjemplaresPrestar) {
            if (ejemplar.getSeleccionado() == true) {
                resultado.add(ejemplar);
            }
        }

        for (dtoBusquedaEjemplar ejemplar : resultado) {
            this.listaEjemplaresPrestar.remove(ejemplar);
            System.out.println("lista ejemplares seleccionados size=" + this.listaEjemplaresPrestar.size());
        }

        return "listaPrestamo";
    }


    //---- codigo para la vista PRESTAMOS REALIZADOS  ----------------------------------------------------------------
    //   -----------------------------------------------------------------------------------------------------------------
    public List<dtoEjemplaresPrestados> getListaPrestamos() {
        return listaPrestamos;
    }

    protected transient List<dtoEjemplaresPrestados> listaPrestamos;

}
