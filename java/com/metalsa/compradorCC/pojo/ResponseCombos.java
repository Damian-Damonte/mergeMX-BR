/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.compradorCC.pojo;

import com.metalsa.aprobacion.model.CentroCosto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose.jimenez07
 */
public class ResponseCombos {

    private List<CentroCosto> listCc = new ArrayList<>();
    private List<CompradorCcPojo> listCompradorCc = new ArrayList<>();
    private List<CompradorCcPojo> listAdministradorCc = new ArrayList<>();
    private List<CompradorCcPojo> listPrevComprador = new ArrayList<>();
    private List<CompradorCcPojo> listAllComprador = new ArrayList<>();
    private CompradorCcPojo responsableConfig = new CompradorCcPojo();

    //pestaña 2
    private List<CategoriaPojo> listCategoria = new ArrayList<>();
    
    private List<CompradorCcPojo> listCompradorFam = new ArrayList<>();

    public List<CentroCosto> getListCc() {
        return listCc;
    }

    public void setListCc(List<CentroCosto> listCc) {
        this.listCc = listCc;
    }

    public Iterable<CompradorCcPojo> getListCompradorCc() {
        return listCompradorCc;
    }

    public void setListCompradorCc(List<CompradorCcPojo> listCompradorCc) {
        this.listCompradorCc = listCompradorCc;
    }

    public Iterable<CompradorCcPojo> getListAdministradorCc() {
        return listAdministradorCc;
    }

    public void setListAdministradorCc(List<CompradorCcPojo> listAdministradorCc) {
        this.listAdministradorCc = listAdministradorCc;
    }

    public List<CompradorCcPojo> getListPrevComprador() {
        return listPrevComprador;
    }

    public void setListPrevComprador(List<CompradorCcPojo> listPrevComprador) {
        this.listPrevComprador = listPrevComprador;
    }

    public List<CompradorCcPojo> getListAllComprador() {
        return listAllComprador;
    }

    public void setListAllComprador(List<CompradorCcPojo> listAllComprador) {
        this.listAllComprador = listAllComprador;
    }

    public CompradorCcPojo getResponsableConfig() {
        return responsableConfig;
    }

    public void setResponsableConfig(CompradorCcPojo responsableConfig) {
        this.responsableConfig = responsableConfig;
    }
    public List<CategoriaPojo> getListCategoria() {
        return listCategoria;
    }

    public void setListCategoria(List<CategoriaPojo> listCategoria) {
        this.listCategoria = listCategoria;
    }

    public List<CompradorCcPojo> getListCompradorFam() {
        return listCompradorFam;
    }

    public void setListCompradorFam(List<CompradorCcPojo> listCompradorFam) {
        this.listCompradorFam = listCompradorFam;
    }

}
