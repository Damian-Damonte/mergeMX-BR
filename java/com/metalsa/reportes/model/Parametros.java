package com.metalsa.reportes.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@Getter
@Setter
public class Parametros {

    private Integer idUen;
    private Integer periodoInicial;
    private Integer periodoFinal;
    private Integer anio;
    private Integer tipoVista;
    private Long numeroSolicitud;
    private String idioma;
    private String iduens;
    private String iduensorigen;
    private String iduensdestino;
    private String periodos;

    private String ccInicial;
    private String ccFinal;
    private String idcreadores;
    private String idrequisitores;
    private String idaprobadores;
    private String idcategorias;
    private String idcategoriasOrigen;
    private String idcategoriasDestino;
    private String idccs;
    private String idccsOrigen;
    private String idccsDestino;
    private String fechaInicial;
    private String fechaFinal;
    private boolean showAll;

    @Override
    public String toString() {
        return "Parametros{" + "idUen=" + idUen + ", periodoInicial=" + periodoInicial + ", periodoFinal=" + periodoFinal + ",\n"
                + "anio=" + anio + ", tipoVista=" + tipoVista + ", numeroSolicitud=" + numeroSolicitud + ", \n"
                + "idioma=" + idioma + ", iduens=" + iduens + ", periodos=" + periodos + ", \n"
                + "ccInicial=" + ccInicial + ", ccFinal=" + ccFinal + ", idrequisitores=" + idrequisitores + ", \n"
                + "idaprobadores=" + idaprobadores + ", idcategorias=" + idcategorias + ", \n"
                + "idcategoriasOrigen=" + idcategoriasOrigen + ", idcategoriasDestino=" + idcategoriasDestino + ", \n"
                + "idccs=" + idccs + ", idccsOrigen=" + idccsOrigen + ", idccsDestino=" + idccsDestino + ", \n"
                + "fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + ", showAll=" + showAll + '}';
    }

    
    public String filtrosBalanza() {
        return "Parametros{ idioma=" + idioma + ", idUen=" + idUen + ", idccs=" + idccs + ", periodoInicial=" + periodoInicial + ", periodoFinal=" + periodoFinal + ", idcategorias=" + idcategorias + ", ccInicial=" + ccInicial + ", ccFinal=" + ccFinal + '}';
    }

    public String filtrosVariacion() {
        return "Parametros{ idioma=" + idioma + ", idUen=" + idUen + ", periodoInicial=" + periodoInicial + ", periodoFinal=" + periodoFinal + ", idcategorias=" + idcategorias + ", ccInicial=" + ccInicial + ", ccFinal=" + ccFinal + '}';
    }

    public String filtrosPresupuestoUen() {
        return "Parametros{ idioma=" + idioma + ", uens=" + iduens + ", periodoInicial=" + periodoInicial + ", periodoFinal=" + periodoFinal + ", idccs=" + idccs + ", anio=" + anio + ", tipoVista=" + tipoVista + '}';
    }

    public String filtrosEstadoCuenta() {
        return "Parametros{ idioma=" + idioma + ", idUen=" + idUen + ", periodoInicial=" + periodoInicial + ", periodoFinal=" + periodoFinal + ", idcc=" + idccs + '}';
    }

    public String filtrosTransferencia() {
        return "Parametros{ idioma=" + idioma + ", uens=" + iduens + ", periodos=" + periodos + ", ccs=" + idccs + ", ccsOrigen=" + idccsOrigen+ ", ccsDestino=" + idccsDestino+ ", categorias=" + idcategorias+ ", aprobadores=" + idaprobadores+ ", requisitores=" + idrequisitores +'}';
    }

}
