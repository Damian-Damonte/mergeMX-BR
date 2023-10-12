/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.finanzas.model;

/**
 *
 * @author lorena
 */
public class SeguimientoTransferencia {
    
    private String periodoD;
    private String categoriaD;
    private int categoriaDId;
    private String uenD;
    private String ccDCodigo;
    
    private String periodoO;
    private String categoriaO;
    private int categoriaOId;
    private String uenO;
    private String ccOCodigo;
    private String razon;
    private Double monto;
    private String solicitador;
    private String aprobador;

    /**
     * @return the periodoD
     */
    public String getPeriodoD() {
        return periodoD;
    }

    /**
     * @param periodoD the periodoD to set
     */
    public void setPeriodoD(String periodoD) {
        this.periodoD = periodoD;
    }

    /**
     * @return the categoriaD
     */
    public String getCategoriaD() {
        return categoriaD;
    }

    /**
     * @param categoriaD the categoriaD to set
     */
    public void setCategoriaD(String categoriaD) {
        this.categoriaD = categoriaD;
    }

    /**
     * @return the categoriaDId
     */
    public int getCategoriaDId() {
        return categoriaDId;
    }

    /**
     * @param categoriaDId the categoriaDId to set
     */
    public void setCategoriaDId(int categoriaDId) {
        this.categoriaDId = categoriaDId;
    }

    /**
     * @return the uenD
     */
    public String getUenD() {
        return uenD;
    }

    /**
     * @param uenD the uenD to set
     */
    public void setUenD(String uenD) {
        this.uenD = uenD;
    }

    /**
     * @return the ccDCodigo
     */
    public String getCcDCodigo() {
        return ccDCodigo;
    }

    /**
     * @param ccDCodigo the ccDCodigo to set
     */
    public void setCcDCodigo(String ccDCodigo) {
        this.ccDCodigo = ccDCodigo;
    }

    /**
     * @return the periodoO
     */
    public String getPeriodoO() {
        return periodoO;
    }

    /**
     * @param periodoO the periodoO to set
     */
    public void setPeriodoO(String periodoO) {
        this.periodoO = periodoO;
    }

    /**
     * @return the categoriaO
     */
    public String getCategoriaO() {
        return categoriaO;
    }

    /**
     * @param categoriaO the categoriaO to set
     */
    public void setCategoriaO(String categoriaO) {
        this.categoriaO = categoriaO;
    }

    /**
     * @return the categoriaOId
     */
    public int getCategoriaOId() {
        return categoriaOId;
    }

    /**
     * @param categoriaOId the categoriaOId to set
     */
    public void setCategoriaOId(int categoriaOId) {
        this.categoriaOId = categoriaOId;
    }

    /**
     * @return the uenO
     */
    public String getUenO() {
        return uenO;
    }

    /**
     * @param uenO the uenO to set
     */
    public void setUenO(String uenO) {
        this.uenO = uenO;
    }

    /**
     * @return the ccOCodigo
     */
    public String getCcOCodigo() {
        return ccOCodigo;
    }

    /**
     * @param ccOCodigo the ccOCodigo to set
     */
    public void setCcOCodigo(String ccOCodigo) {
        this.ccOCodigo = ccOCodigo;
    }

    /**
     * @return the razon
     */
    public String getRazon() {
        return razon;
    }

    /**
     * @param razon the razon to set
     */
    public void setRazon(String razon) {
        this.razon = razon;
    }

    /**
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * @return the solicitador
     */
    public String getSolicitador() {
        return solicitador;
    }

    /**
     * @param solicitador the solicitador to set
     */
    public void setSolicitador(String solicitador) {
        this.solicitador = solicitador;
    }

    /**
     * @return the aprobador
     */
    public String getAprobador() {
        return aprobador;
    }

    /**
     * @param aprobador the aprobador to set
     */
    public void setAprobador(String aprobador) {
        this.aprobador = aprobador;
    }
    
}
