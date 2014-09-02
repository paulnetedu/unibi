package edu.universidad.unibi.curegistrousuario;

import edu.universidad.dominio.unibi.TblUsuarios;
import edu.universidad.unibi.curegistrousuario.dto.DtoUsuarios;
import edu.universidad.unibi.util.Bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BoRegistroUsuarioImpl extends Bo implements BoRegistroUsuario {
    public List<DtoUsuarios> consultarUsuario(String nombres) {
        List<DtoUsuarios> lstDto = new ArrayList<DtoUsuarios>();
        Query query = em.createNamedQuery("TblUsuarios.consultarPorNombreCompleto");
        String cadenaBusqueda = "%" + nombres.toUpperCase() + "%";
        // System.out.println("cadenaBusqueda = " + cadenaBusqueda);
        query.setParameter("nomcompleto", cadenaBusqueda);
        List<TblUsuarios> lstTbl = query.getResultList();
        System.out.println("lstTbl = " + ((lstTbl == null) ? -1 : lstTbl.size()));
        DtoUsuarios dto = null;
        for (TblUsuarios p : lstTbl) {
            System.out.println("p.getNombres() = " + p.getNombres());
            dto = new DtoUsuarios();
            dto.setId(p.getId());
            dto.setNombres(p.getNombres());
            dto.setApellido_materno(p.getApellidoMaterno());
            dto.setApellido_paterno(p.getApellidoPaterno());
            dto.setTipo_documento(p.getTipoDocumento());
            dto.setNumero_documento(p.getNumeroDocumento());
            dto.setEmail(p.getEmail());
            dto.setNombre_completo(p.getNombres() + " " + p.getApellidoPaterno() + " " + p.getApellidoMaterno());
            lstDto.add(dto);
        }
        return lstDto;
    }

    public DtoUsuarios consultarUsuarioPorId(Integer id) {
        TblUsuarios p = em.find(TblUsuarios.class, id);
        DtoUsuarios dto = new DtoUsuarios();
        dto.setId(p.getId());
        dto.setNombres(p.getNombres());
        dto.setApellido_materno(p.getApellidoMaterno());
        dto.setApellido_paterno(p.getApellidoPaterno());
        dto.setTipo_documento(p.getTipoDocumento());
        dto.setNumero_documento(p.getNumeroDocumento());
        dto.setEmail(p.getEmail());
        dto.setNombre_completo(p.getNombres() + " " + p.getApellidoPaterno() + " " + p.getApellidoMaterno());
        return dto;
    }

    @Override
    public void registroUsuario(String nombres, String apellidoPaterno, String apellidoMaterno, String numeroDocumento,
                                Integer TipoDocumento, String email) {
        TblUsuarios usuario = new TblUsuarios();
        usuario.setNombres(nombres);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setApellidoPaterno(apellidoPaterno);
        if (email.length() > 0) {
            usuario.setEmail(email);
        }


        usuario.setTipoDocumento(Integer.valueOf(TipoDocumento));
        usuario.setNumeroDocumento(numeroDocumento);
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    @Override
    public void actualizarUsuario(Integer Id, String nombres, String apellidoPaterno, String apellidoMaterno,
                                  String numeroDocumento, Integer TipoDocumento, String email) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TblUsuarios usuario = em.find(TblUsuarios.class, Id);
        usuario.setNombres(nombres);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setEmail(email);
        usuario.setTipoDocumento(Integer.valueOf(TipoDocumento));
        usuario.setNumeroDocumento(numeroDocumento);
        em.persist(usuario);
        tx.commit();
    }

    @Override
    public List<DtoUsuarios> consultarUsuarioPorDocumento(String nroDocumento) {
        List<DtoUsuarios> lstDto = new ArrayList<DtoUsuarios>();
        Query query = em.createNamedQuery("TblUsuarios.consultarPorNumeroDocumento");
        String cadenaBusqueda = "%" + nroDocumento.toUpperCase() + "%";
        //System.out.println("cadenaBusqueda = " + cadenaBusqueda);
        query.setParameter("numeroDocumento", cadenaBusqueda);
        List<TblUsuarios> lstTbl = query.getResultList();
        // System.out.println("lstTbl = " + ((lstTbl == null) ? -1 : lstTbl.size()));
        DtoUsuarios dto = null;
        for (TblUsuarios p : lstTbl) {
            System.out.println("p.getNombres() = " + p.getNombres());
            dto = new DtoUsuarios();
            dto.setId(p.getId());
            dto.setNombres(p.getNombres());
            dto.setApellido_materno(p.getApellidoMaterno());
            dto.setApellido_paterno(p.getApellidoPaterno());
            dto.setTipo_documento(p.getTipoDocumento());
            dto.setNumero_documento(p.getNumeroDocumento());
            dto.setEmail(p.getEmail());
            dto.setNombre_completo(p.getNombres() + " " + p.getApellidoPaterno() + " " + p.getApellidoMaterno());
            lstDto.add(dto);
        }
        return lstDto;
    }

    @Override
    public int verificarDocumento(String numeroDocumento) {
        Query query = em.createNamedQuery("TblUsuarios.consultarPorNumeroDocumento");
        String cadenaBusqueda = "%" + numeroDocumento.toUpperCase() + "%";
        query.setParameter("numeroDocumento", cadenaBusqueda);
        List<TblUsuarios> lstTbl = query.getResultList();
        //System.out.println("lstTbl = " + ((lstTbl == null) ? -1 : lstTbl.size()));
        if (lstTbl == null) {
            return 0;
        } else {
            return lstTbl.size();
        }
    }

    @Override
    public List<DtoUsuarios> consultarHomonimo(String Nombres, String apePaterno, String apMaterno,
                                               String nroDocumento) {
        List<DtoUsuarios> lstDto = new ArrayList<DtoUsuarios>();
        Query query = em.createNamedQuery("TblUsuarios.consultarHomonimia");
        query.setParameter("nombres", "%" + Nombres.toUpperCase() + "%");
        query.setParameter("apellidoPaterno", "%" + apePaterno.toUpperCase() + "%");
        query.setParameter("apellidoMaterno", "%" + apMaterno.toUpperCase() + "%");
        query.setParameter("documento", nroDocumento.toUpperCase());
        List<TblUsuarios> lstTbl = query.getResultList();
        //System.out.println("lstTbl = " + ((lstTbl == null) ? -1 : lstTbl.size()));
        DtoUsuarios dto = null;
        for (TblUsuarios p : lstTbl) {
            dto = new DtoUsuarios();
            dto.setId(p.getId());
            dto.setNombres(p.getNombres());
            dto.setApellido_materno(p.getApellidoMaterno());
            dto.setApellido_paterno(p.getApellidoPaterno());
            dto.setTipo_documento(p.getTipoDocumento());
            dto.setNumero_documento(p.getNumeroDocumento());
            dto.setEmail(p.getEmail());
            dto.setNombre_completo(p.getNombres() + " " + p.getApellidoPaterno() + " " + p.getApellidoMaterno());
            lstDto.add(dto);
        }
        return lstDto;
    }

    @Override
    public List<DtoUsuarios> consultarNroDocumento(String nroDocumento) {
        List<DtoUsuarios> lstDto = new ArrayList<DtoUsuarios>();
        Query query = em.createNamedQuery("TblUsuarios.consultarNroDocumento");
        query.setParameter("numeroDocumento", nroDocumento.toUpperCase());
        List<TblUsuarios> lstTbl = query.getResultList();
        //System.out.println("lstTbl = " + ((lstTbl == null) ? -1 : lstTbl.size()));
        DtoUsuarios dto = null;
        for (TblUsuarios p : lstTbl) {
            dto = new DtoUsuarios();
            dto.setId(p.getId());
            dto.setNombres(p.getNombres());
            dto.setApellido_materno(p.getApellidoMaterno());
            dto.setApellido_paterno(p.getApellidoPaterno());
            dto.setTipo_documento(p.getTipoDocumento());
            dto.setNumero_documento(p.getNumeroDocumento());
            dto.setEmail(p.getEmail());
            dto.setNombre_completo(p.getNombres() + " " + p.getApellidoPaterno() + " " + p.getApellidoMaterno());
            lstDto.add(dto);
        }
        return lstDto;
    }
}
