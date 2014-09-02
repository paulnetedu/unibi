package edu.universidad.unibi.cuprestamoejemplar;

import edu.universidad.unibi.cuprestamoejemplar.dto.DtoDevolucion;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoEjemplar;


import edu.universidad.unibi.cuprestamoejemplar.dto.DtoLector;
import edu.universidad.unibi.cuprestamoejemplar.dto.DtoPrestamo;

import edu.universidad.unibi.cuprestamoejemplar.dto.DtoSancion;

import java.util.Date;
import java.util.List;

public interface BoPrestamoEjemplar {
    
    public List<DtoEjemplar> consultarEjemplarPorTitulo(String titulo);
    
    public List<DtoPrestamo> consultarPrestamoporCodigoPrestamo (int codigo);
    
    public List<DtoPrestamo> consultarPrestamoporCodigoEjemplar(String titulo);
    
    public void guardarDevolucion(int iddetalle, Date fechaDev, int idBibliotecario, int estadoFisico, int dias);
    
    public void actualizarDetalle(int iddetalle);
    
    public void actualizarEjemplar(String idEjemplar, int estadoDisponibilidad, int estadoFisico);

    public List<DtoLector> consultarUsuarioPorNumeroDocumento2(String numeroDocumento);

    public List<DtoSancion> consultarSancionesPorDocumento(int id);

    public void guardarPrestamo(List<DtoEjemplar> idEjemplares, int lectorId, int bibliotecarioId);
    
    public List<DtoEjemplar> consultarEjemplarPorPublicacion(String idPublicacion);
    
    public void actualizarPrestamo(int idPrestamo, int estado);

    public List<DtoEjemplar> consultarEjemplarPorCodigoEjemplar(String cadenaBusqueda);
}
