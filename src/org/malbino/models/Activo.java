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
public class Activo {

    //pk
    private Integer idActivo;
    private String codigo;
    private String codigoAntiguo;
    private String descripcion;
    private String estado;
    private String observaciones;
    private String foto;

    private Categoria categoria;
    private Ubicacion ubicacion;
    private Custodio custodio;

    public Activo(String codigo, String codigoAntiguo, String descripcion, String estado, String observaciones, Categoria categoria, Ubicacion ubicacion, Custodio custodio) {
        this.codigo = codigo;
        this.codigoAntiguo = codigoAntiguo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.observaciones = observaciones;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.custodio = custodio;
    }

    public Activo() {
    }

    public Activo(String codigo, String codigoAntiguo, String descripcion, String estado) {
        this.codigo = codigo;
        this.codigoAntiguo = codigoAntiguo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    

    /**
     * @return the idActivo
     */
    public Integer getIdActivo() {
        return idActivo;
    }

    /**
     * @param idActivo the idActivo to set
     */
    public void setIdActivo(Integer idActivo) {
        this.idActivo = idActivo;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codigoAntiguo
     */
    public String getCodigoAntiguo() {
        return codigoAntiguo;
    }

    /**
     * @param codigoAntiguo the codigoAntiguo to set
     */
    public void setCodigoAntiguo(String codigoAntiguo) {
        this.codigoAntiguo = codigoAntiguo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
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

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the ubicacion
     */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the custodio
     */
    public Custodio getCustodio() {
        return custodio;
    }

    /**
     * @param custodio the custodio to set
     */
    public void setCustodio(Custodio custodio) {
        this.custodio = custodio;
    }    

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }
}
