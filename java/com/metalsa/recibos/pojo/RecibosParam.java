/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yair.nunez
 */
public class RecibosParam {
    private Integer idRequi;
    private Integer idPartida;
    private String usuario; 
    private Integer proveedor; 
    private Integer oc;
    private String idioma;
    private String ordenCompra;
    private String fIni;
    private String fFin;
    private String folio;
    private String tipoUsuario;
    private String unidad;
    private List<FolioCorreo> foliosCorreo; 
    //<R17486>
    private String idUens;
    private boolean mostrarRecibos;
    private Integer pageNumber;
    private Integer rowsPerPage;
    //</R17486>
    

    public RecibosParam() {
        foliosCorreo = new ArrayList<>();
    }

    public List<FolioCorreo> getFoliosCorreo() {
        return foliosCorreo;
    }

    public void setFoliosCorreo(List<FolioCorreo> foliosCorreo) {
        this.foliosCorreo = foliosCorreo;
    }
    
    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public String getfIni() {
        return fIni;
    }

    public void setfIni(String fIni) {
        this.fIni = fIni;
    }

    public String getfFin() {
        return fFin;
    }

    public void setfFin(String fFin) {
        this.fFin = fFin;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Integer getIdRequi() {
        return idRequi;
    }

    public void setIdRequi(Integer idRequi) {
        this.idRequi = idRequi;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getProveedor() {
        return proveedor;
    }

    public void setProveedor(Integer proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getOc() {
        return oc;
    }

    public void setOc(Integer oc) {
        this.oc = oc;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    //<R17486>
    public String getIdUens() {
        return idUens;
    }

    public void setIdUens(String idUens) {
        this.idUens = idUens;
    }

    public boolean isMostrarRecibos() {
        return mostrarRecibos;
    }

    public void setMostrarRecibos(boolean mostrarRecibos) {
        this.mostrarRecibos = mostrarRecibos;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }
    
    //</R17486>

    @Override
    public String toString() {
        return "RecibosParam{" + "idRequi=" + idRequi + ", idPartida=" + idPartida + ", usuario=" + usuario + ", proveedor=" + proveedor + ", oc=" + oc + ", idioma=" + idioma + ", ordenCompra=" + ordenCompra + ", fIni=" + fIni + ", fFin=" + fFin + ", folio=" + folio + ", tipoUsuario=" + tipoUsuario + ", unidad=" + unidad + ", foliosCorreo=" + foliosCorreo.size() + '}';
    }
    
    

    
}
