/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.malbino.models;

/**
 *
 * @author Tincho
 */
public class Custodio {
    //pk

    private Integer idCustodio;
    private String nombre;
    private String observaciones;

    public Custodio(String nombre, String observaciones) {
        this.nombre = nombre;
        this.observaciones = observaciones;
    }

    public Custodio() {
    }

    /**
     * @return the idCustodio
     */
    public Integer getIdCustodio() {
        return idCustodio;
    }

    /**
     * @param idCustodio the idCustodio to set
     */
    public void setIdCustodio(Integer idCustodio) {
        this.idCustodio = idCustodio;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
