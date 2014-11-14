package edu.universidad.unibi.cubusquedaejemplar;

import edu.universidad.unibi.cubusquedaejemplar.dto.dtoEjemplaresPrestados;
import edu.universidad.unibi.cubusquedaejemplar.dto.dtoUsuario;
import edu.universidad.unibi.cubusquedaejemplar.dto.dtoBusquedaEjemplar;

import java.util.List;

public interface BoBusquedaEjemplar {
    
    public dtoUsuario getUsuarioDto(String nroDocumento);
    public List<dtoEjemplaresPrestados> getlistaEjemplaresPrestados();
    public List<dtoBusquedaEjemplar> consultarEjemplarPorArea(String titulo);
    public List<dtoBusquedaEjemplar> consultarEjemplarPorTitulo(String titulo);
    public List<dtoBusquedaEjemplar> consultarEjemplarPorAutor(String nombre);
    
}
