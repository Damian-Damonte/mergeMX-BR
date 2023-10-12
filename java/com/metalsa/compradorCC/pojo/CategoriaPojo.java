/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.compradorCC.pojo;

import com.metalsa.recibos.pojo.TipoRequisicion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jose.jimenez07
 */
public class CategoriaPojo {

    private Integer id_familia;
    private String nombre_familia;
    private Integer id_padre;
    List<CategoriaPojo> nodo = new ArrayList();
    private String comentarioUs;
    private String comentarioEsa;
    private String comentarioPtb;

    ///  solo son usados en la sub familia
    //private DatosTabla datosTabla;
    private Long id_uen;
    private Long id_dc_comprador; // id para la tabla DC_COMPRADOR_FAMILIA
    private List<CompradorCcPojo> comprador = new ArrayList();
    private String compradorFilter;
    private CompradorCcPojo administrador = new CompradorCcPojo();
    private String administradorFilter;
    private CompradorCcPojo prevComprador = new CompradorCcPojo();
    private String prevCompradorFilter;
    private String creation_date;
    private Date date;  // servira para hacer la comparacion exacata y subir esa fecha a los niveles famila y categoria al igual que el administrador
    private String editado = "N"; //N -> normal, I -> incompleto, C->     
    private Integer noSeleccionados = 0;
///
    /*MDA ADMINISTRACION DE CUENTAS*/
    private TipoRequisicion tipoRequisicion;

    /*MDA ADMINISTRACION DE CUENTAS*/
    
    

    public Integer getId_familia() {
        return id_familia;
    }

    public void setId_familia(Integer id_familia) {
        this.id_familia = id_familia;
    }

    public String getNombre_familia() {
        return nombre_familia;
    }

    public void setNombre_familia(String nombre_familia) {
        this.nombre_familia = nombre_familia;
    }

    public Integer getId_padre() {
        return id_padre;
    }

    public void setId_padre(Integer id_padre) {
        this.id_padre = id_padre;
    }

    public List<CategoriaPojo> getNodo() {
        return nodo;
    }

    public void setNodo(List<CategoriaPojo> nodo) {
        this.nodo = nodo;
    }

    public Long getId_dc_comprador() {
        return id_dc_comprador;
    }

    public void setId_dc_comprador(Long id_dc_comprador) {
        this.id_dc_comprador = id_dc_comprador;
    }

    public List<CompradorCcPojo> getComprador() {
        return comprador;
    }

    public void setComprador(List<CompradorCcPojo> comprador) {
        this.comprador = comprador;
    }

    public String getCompradorFilter() {
        return compradorFilter;
    }

    public void setCompradorFilter(String compradorFilter) {
        this.compradorFilter = compradorFilter;
    }

    public CompradorCcPojo getAdministrador() {
        return administrador;
    }

    public void setAdministrador(CompradorCcPojo administrador) {
        this.administrador = administrador;
    }

    public String getAdministradorFilter() {
        return administradorFilter;
    }

    public void setAdministradorFilter(String administradorFilter) {
        this.administradorFilter = administradorFilter;
    }

    public CompradorCcPojo getPrevComprador() {
        return prevComprador;
    }

    public void setPrevComprador(CompradorCcPojo prevComprador) {
        this.prevComprador = prevComprador;
    }

    public String getPrevCompradorFilter() {
        return prevCompradorFilter;
    }

    public void setPrevCompradorFilter(String prevCompradorFilter) {
        this.prevCompradorFilter = prevCompradorFilter;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getEditado() {
        return editado;
    }

    public void setEditado(String editado) {
        this.editado = editado;
    }

    public Long getId_uen() {
        return id_uen;
    }

    public void setId_uen(Long id_uen) {
        this.id_uen = id_uen;
    }

    public Date getDate() {
        if (date != null) {
            return new Date(this.date.getTime());
        }
        return null;
    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = new Date(date.getTime());
        }else{
            this.date = null;
        }

    }

    public TipoRequisicion getTipoRequisicion() {
        return tipoRequisicion;
}

    public void setTipoRequisicion(TipoRequisicion tipoRequisicion) {
        this.tipoRequisicion = tipoRequisicion;
    }

    /**
     * @return the selecccionados
     */
    public Integer getNoSeleccionados() {
        return noSeleccionados;
    }

    /**
     * @param noSeleccionados
     */
    public void setNoSeleccionados(Integer noSeleccionados) {
        this.noSeleccionados = noSeleccionados;
    }

    public String getComentarioUs() {
        return comentarioUs;
    }

    public void setComentarioUs(String comentarioUs) {
        this.comentarioUs = comentarioUs;
    }

    public String getComentarioEsa() {
        return comentarioEsa;
    }

    public void setComentarioEsa(String comentarioEsa) {
        this.comentarioEsa = comentarioEsa;
    }

    public String getComentarioPtb() {
        return comentarioPtb;
    }

    public void setComentarioPtb(String comentarioPtb) {
        this.comentarioPtb = comentarioPtb;
    }
    
    
    
}
