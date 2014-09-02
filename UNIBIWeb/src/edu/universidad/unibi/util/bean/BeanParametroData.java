package edu.universidad.unibi.util.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "beanParametroData")
@ApplicationScoped
public class BeanParametroData {
    
    /**
     * Máximo cantidad de ejemplares de préstamo por día.
     */
    private int maxPrestamosDia = 2;
    
    /**
     * Máxima cantidad de días de préstamo.
     */
    private int maxDiasPrestamo = 3;
    
    /**
     * Máxima cantidad de días de atraso en la devolución 
     * antes de considerar una notificación para sanción del lector.
     */
    private int maxDiasDevolucionAtraso = 7;
    
    /**
     * Máxima cantidad de días de atraso en la devolución
     * antes de considerar el ejemplar como perdido.
     */
    private int maxDiasPrestamoPerdida = 10;
    
    public BeanParametroData() {
    }


    public void setMaxPrestamosDia(int maxPrestamosDia) {
        this.maxPrestamosDia = maxPrestamosDia;
    }

    public int getMaxPrestamosDia() {
        return maxPrestamosDia;
    }

    public void setMaxDiasPrestamo(int maxDiasPrestamo) {
        this.maxDiasPrestamo = maxDiasPrestamo;
    }

    public int getMaxDiasPrestamo() {
        return maxDiasPrestamo;
    }

    public void setMaxDiasDevolucionAtraso(int maxDiasDevolucionAtraso) {
        this.maxDiasDevolucionAtraso = maxDiasDevolucionAtraso;
    }

    public int getMaxDiasDevolucionAtraso() {
        return maxDiasDevolucionAtraso;
    }

    public void setMaxDiasPrestamoPerdida(int maxDiasPrestamoPerdida) {
        this.maxDiasPrestamoPerdida = maxDiasPrestamoPerdida;
    }

    public int getMaxDiasPrestamoPerdida() {
        return maxDiasPrestamoPerdida;
    }
}
