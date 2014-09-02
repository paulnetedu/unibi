package edu.universidad.unibi.util;

import edu.universidad.dominio.util.EmUtil;

import javax.annotation.PreDestroy;

import javax.persistence.EntityManager;

public class Bo {
    
    protected EntityManager em;
    
    public Bo() {
        em = EmUtil.obtenerEmUnibi();
    }
    
    public void closeEmf() {
        em.getEntityManagerFactory().close();
    }
}
