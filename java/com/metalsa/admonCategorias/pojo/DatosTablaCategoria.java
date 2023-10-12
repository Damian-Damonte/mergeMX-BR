/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.admonCategorias.pojo;

import com.metalsa.compradorCC.pojo.CompradorCcPojo;
import com.metalsa.compradorCC.pojo.ListDc;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose.jimenez07
 */
public class DatosTablaCategoria {
    private Long id_uen;
    private Long id_cc;
    private String nombre_cc;
    private List<CompradorCcPojo> comprador = new ArrayList();
    private String compradorFilter;
    private CompradorCcPojo administrador = new CompradorCcPojo();
    private String administradorFilter;
    private CompradorCcPojo prevComprador = new CompradorCcPojo();
    private String prevCompradorFilter;
    private String creation_date;
    private boolean editado;
    private String tipoAprobacion;
    private List<ListDc> listDc;

    public Long getId_uen() {
        return id_uen;
    }

    public void setId_uen(Long id_uen) {
        this.id_uen = id_uen;
    }

    public Long getId_cc() {
        return id_cc;
    }

    public void setId_cc(Long id_cc) {
        this.id_cc = id_cc;
    }

    public String getNombre_cc() {
        return nombre_cc;
    }

    public void setNombre_cc(String nombre_cc) {
        this.nombre_cc = nombre_cc;
    }

    public List<CompradorCcPojo> getComprador() {
        return comprador;
    }

    public void setComprador(List<CompradorCcPojo> comprador) {
        this.comprador = comprador;
    }

    public CompradorCcPojo getAdministrador() {
        return administrador;
    }

    public void setAdministrador(CompradorCcPojo administrador) {
        this.administrador = administrador;
    }

    public CompradorCcPojo getPrevComprador() {
        return prevComprador;
    }

    public void setPrevComprador(CompradorCcPojo prevComprador) {
        this.prevComprador = prevComprador;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getCompradorFilter() {
        return compradorFilter;
    }

    public void setCompradorFilter(String compradorFilter) {
        this.compradorFilter = compradorFilter;
    }

    public String getAdministradorFilter() {
        return administradorFilter;
    }

    public void setAdministradorFilter(String administradorFilter) {
        this.administradorFilter = administradorFilter;
    }

    public String getPrevCompradorFilter() {
        return prevCompradorFilter;
    }

    public void setPrevCompradorFilter(String prevCompradorFilter) {
        this.prevCompradorFilter = prevCompradorFilter;
    }

    public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    public List<ListDc> getListDc() {
        return listDc;
    }

    public void setListDc(List<ListDc> listDc) {
        this.listDc = listDc;
    }

    public String getTipoAprobacion() {
        return tipoAprobacion;
    }

    public void setTipoAprobacion(String tipoAprobacion) {
        this.tipoAprobacion = tipoAprobacion;
    }
}
