package edu.universidad.unibi.curegistrousuario;

import edu.universidad.unibi.curegistrousuario.dto.DtoUsuarios;

import java.util.List;

public interface BoRegistroUsuario {
    public List<DtoUsuarios> consultarUsuario(String nombres);

    public List<DtoUsuarios> consultarUsuarioPorDocumento(String nroDocumento);

    public DtoUsuarios consultarUsuarioPorId(Integer id);

    public void registroUsuario(String nombres, String apellidoPaterno, String apellidoMaterno, String numeroDocumento,
                                Integer TipoDocumento, String email);

    public void actualizarUsuario(Integer Id, String nombres, String apellidoPaterno, String apellidoMaterno,
                                  String numeroDocumento, Integer TipoDocumento, String email);
    public int verificarDocumento(String numeroDocumento);
    public List<DtoUsuarios> consultarHomonimo(String Nombres, String apePaterno, String apMaterno, String nroDocumento);
    public List<DtoUsuarios> consultarNroDocumento(String nroDocumento) ;
}
