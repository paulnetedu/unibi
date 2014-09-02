package edu.universidad.dominio.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmUtil {
    
    public static EntityManager obtenerEmUnibi() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-unibi");
        return emf.createEntityManager();
    }
}
