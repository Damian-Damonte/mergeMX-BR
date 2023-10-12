/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.admonCategorias.pojo;

/**
 *
 * @author apojm20015
 * @date 8-Ago-2019
 */
public class ConfFamPojo {

    private Integer idUen;
    private Integer idFamilia;
    private boolean eliminar;
    private String tipoRequisicion;
    private String idioma;
    private String comentario;

    /**
     * @return the idUen
     */
    public Integer getIdUen() {
        return idUen;
    }

    /**
     * @param idUen the idUen to set
     */
    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    /**
     * @return the idFamilia
     */
    public Integer getIdFamilia() {
        return idFamilia;
    }

    /**
     * @param idFamilia the idFamilia to set
     */
    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    /**
     * @return the eliminar
     */
    public boolean isEliminar() {
        return eliminar;
    }

    /**
     * @param eliminar the eliminar to set
     */
    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    /**
     * @return the tipoRequisicion
     */
    public String getTipoRequisicion() {
        return tipoRequisicion;
    }

    /**
     * @param tipoRequisicion the tipoRequisicion to set
     */
    public void setTipoRequisicion(String tipoRequisicion) {
        this.tipoRequisicion = tipoRequisicion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
