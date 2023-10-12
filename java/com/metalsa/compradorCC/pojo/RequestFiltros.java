/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.compradorCC.pojo;

import com.metalsa.recibos.pojo.TipoRequisicion;
import java.util.List;

/**
 *
 * @author jose.jimenez07
 */
public class RequestFiltros {

    private List<UenPojo> modelListaUensCc;

    private List<CentroCostoPojo> modelListaCc;

    private List<CompradorCcPojo> modelCompradorXcC;

    private List<CompradorCcPojo> modelAdministradorCc;

    private List<CompradorCcPojo> modelPrevComprador;

    private List<String> columnasExcel;

    private String fechaInicio;

    private String fechaFin;

    private Integer tipoFiltro; // 1 actual, 2 completo 

    private String tipoExcel;

    /// pestaña 2
    private List<CompradorCcPojo> modelCompradorFam;

    private List<Integer> listIdCategoria;

    private List<Integer> listIdFamilia;

    private List<Integer> listIdSubFamilia;

    private List<CompradorCcPojo> modelAdministradorFam;

    private List<CompradorCcPojo> modelPrevCompradorFam;

    private String fechaInicioFam;

    private String fechaFinFam;

    private String tipoAprobacion;
    
    private List<TipoRequisicion> modelTipoRequisicion;
    
    private boolean sinRestricciones;

    public List<UenPojo> getModelListaUensCc() {
        return modelListaUensCc;
    }

    public void setModelListaUensCc(List<UenPojo> modelListaUensCc) {
        this.modelListaUensCc = modelListaUensCc;
    }

    public List<CentroCostoPojo> getModelListaCc() {
        return modelListaCc;
    }

    public void setModelListaCc(List<CentroCostoPojo> modelListaCc) {
        this.modelListaCc = modelListaCc;
    }

    public List<CompradorCcPojo> getModelCompradorXcC() {
        return modelCompradorXcC;
    }

    public void setModelCompradorXcC(List<CompradorCcPojo> modelCompradorXcC) {
        this.modelCompradorXcC = modelCompradorXcC;
    }

    public List<CompradorCcPojo> getModelAdministradorCc() {
        return modelAdministradorCc;
    }

    public void setModelAdministradorCc(List<CompradorCcPojo> modelAdministradorCc) {
        this.modelAdministradorCc = modelAdministradorCc;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<CompradorCcPojo> getModelPrevComprador() {
        return modelPrevComprador;
    }

    public void setModelPrevComprador(List<CompradorCcPojo> modelPrevComprador) {
        this.modelPrevComprador = modelPrevComprador;
    }

    public Integer getTipoFiltro() {
        return tipoFiltro;
    }

    public void setTipoFiltro(Integer tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }

    public String getTipoExcel() {
        return tipoExcel;
    }

    public void setTipoExcel(String tipoExcel) {
        this.tipoExcel = tipoExcel;
    }

    public List<String> getColumnasExcel() {
        return columnasExcel;
    }

    public void setColumnasExcel(List<String> columnasExcel) {
        this.columnasExcel = columnasExcel;
    }

    public List<CompradorCcPojo> getModelCompradorFam() {
        return modelCompradorFam;
    }

    public void setModelCompradorFam(List<CompradorCcPojo> modelCompradorFam) {
        this.modelCompradorFam = modelCompradorFam;
    }

    public List<Integer> getListIdCategoria() {
        return listIdCategoria;
    }

    public void setListIdCategoria(List<Integer> listIdCategoria) {
        this.listIdCategoria = listIdCategoria;
    }

    public List<Integer> getListIdFamilia() {
        return listIdFamilia;
    }

    public void setListIdFamilia(List<Integer> listIdFamilia) {
        this.listIdFamilia = listIdFamilia;
    }

    public List<Integer> getListIdSubFamilia() {
        return listIdSubFamilia;
    }

    public void setListIdSubFamilia(List<Integer> listIdSubFamilia) {
        this.listIdSubFamilia = listIdSubFamilia;
    }

    public List<CompradorCcPojo> getModelAdministradorFam() {
        return modelAdministradorFam;
    }

    public void setModelAdministradorFam(List<CompradorCcPojo> modelAdministradorFam) {
        this.modelAdministradorFam = modelAdministradorFam;
    }

    public List<CompradorCcPojo> getModelPrevCompradorFam() {
        return modelPrevCompradorFam;
    }

    public void setModelPrevCompradorFam(List<CompradorCcPojo> modelPrevCompradorFam) {
        this.modelPrevCompradorFam = modelPrevCompradorFam;
    }

    public String getFechaInicioFam() {
        return fechaInicioFam;
    }

    public void setFechaInicioFam(String fechaInicioFam) {
        this.fechaInicioFam = fechaInicioFam;
    }

    public String getFechaFinFam() {
        return fechaFinFam;
    }

    public void setFechaFinFam(String fechaFinFam) {
        this.fechaFinFam = fechaFinFam;
    }

    public String getTipoAprobacion() {
        return tipoAprobacion;
    }

    public void setTipoAprobacion(String tipoAprobacion) {
        this.tipoAprobacion = tipoAprobacion;
    }

    /**
     * @return the modelTipoRequisicion
     */
    public List<TipoRequisicion> getModelTipoRequisicion() {
        return modelTipoRequisicion;
    }

    /**
     * @param modelTipoRequisicion the modelTipoRequisicion to set
     */
    public void setModelTipoRequisicion(List<TipoRequisicion> modelTipoRequisicion) {
        this.modelTipoRequisicion = modelTipoRequisicion;
    }

    /**
     * @return the sinRestricciones
     */
    public boolean isSinRestricciones() {
        return sinRestricciones;
    }

    /**
     * @param sinRestricciones the sinRestricciones to set
     */
    public void setSinRestricciones(boolean sinRestricciones) {
        this.sinRestricciones = sinRestricciones;
    }
}
