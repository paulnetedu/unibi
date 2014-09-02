package edu.universidad.unibi.curegistrocatalogo;

import edu.universidad.unibi.curegistrocatalogo.dto.DtoArea;

import edu.universidad.unibi.curegistrocatalogo.dto.DtoAutor;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoCatalogo;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoCiudad;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoEjemplar;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoPais;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoPublicacion;
import edu.universidad.unibi.curegistrocatalogo.dto.DtoResultado;

import java.util.Date;
import java.util.List;

public interface BoRegistroCatalogo {

    public void actualizarLibro(String idPublicacion, String titulo, Date fechaPublicacion,
            Integer numeroPaginas, Double valorFisico, Double valorDigital, Integer tipo, Integer paisId,
            Integer ciudadId, String isbn, Integer volumen, Integer tomo, Integer edicion, Integer editorial);
    
    public List<DtoArea> consultarAreas();
    
    public DtoPublicacion consultarPublicacionPorId(String idPublicacion);
    
    public List<DtoResultado> consultarPublicacionPorTitulo(String titulo);
    public List<DtoResultado> consultarPublicacionPorAutor(String titulo);
    
    public List<DtoEjemplar> consultarEjemplarPorPublicacion(String idPublicacion);

   
    public DtoEjemplar consultarEjemplarPorId(String idEjemplar);

    public void agregarEjemplar(String id, int estado_fisico, int estado_disponiblilidad, double valor,
                                String observaciones, int forma, String publicacion_id, int procedencia);

    public void actualizarEjemplar(String id, int estado_fisico, int estado_disponiblilidad, double valor,
                                   String observaciones, int forma, String publicacion_id, int procedencia);

    public List<DtoCatalogo> consultarCatalogo(int cod);

    public void agregarcatalogoitem(int id, String descripcion, int selec);

    public List<DtoCiudad> consultarCiudadporpais(int id_pais);

    public List<DtoPais> consultarPaises();

    public List<DtoAutor> consultarautores();

    public List<DtoArea> consultarAreaNombre(String area);

    public void guardarLibro(String id, String titulo, Date fechaPublicacion, int numeroPaginas, double valorfisico,
                             double valordigital, int tipo, int id_pais, int id_ciudad, String isbn,int volumen,int tomo, int edicion,
                             int editorial_id);

    public void guardarRevista(String id, String titulo, Date fechaPublicacion, int numeroPaginas, double valorfisico,
                               double valordigital, int tipo, int id_pais, int id_ciudad, int volumen, int numero);

    public void guardarTesis(String id, String titulo, Date fechaPublicacion, int numeroPaginas, double valorfisico,
                             double valordigital, int tipo, int id_pais, int id_ciudad, int institucion_id,
                             int titulo_optado);

    public void guardarPublicacionRelaciones(String id, int listArea, int listAutor, int listCarrera,
                                             int listIdiomas);

    public void actualizarRevista(String id, String titulo, Date fechaPublicacion, Integer numeroPaginas,
                                  double valorfisico, double valordigital, Integer tipo, Integer id_pais, Integer id_ciudad,
                                  Integer volumen, Integer numero);

    public void actualizarTesis(String id, String titulo, Date fechaPublicacion, Integer numeroPaginas, double valorfisico,
                                double valordigital, Integer tipo, Integer id_pais, Integer id_ciudad, Integer institucion_id,
                                Integer titulo_optado);

    public void actualizarPublicacionRelaciones(String id, int area, int autor, int carrera,int idioma);
}
