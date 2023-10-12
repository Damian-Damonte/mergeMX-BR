/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.pojo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jose.jimenez07
 */
public class NvcTblCatalogoVarsPojo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idVariable;
    private boolean obligatorio;
    private Integer orden;
    private String nombre;
    private String tipo;
    private String unidadMedida;
    private String literal;
    private List<NvcTblCatalogoComboPojo> combo;
    private List<NvcTblCatalogoDescuentoPojo> descuentos;
    private Double test;
    private boolean disable;
    private String tipoVariable;
    
    private String valor; // valor que guardara los inputs de la configuaracion y la edicion del item en el carro

    public Long getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(Long idVariable) {
        this.idVariable = idVariable;
    }

    public boolean isObligatorio() {
        return obligatorio;
    }
    
    public boolean getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public List<NvcTblCatalogoComboPojo> getCombo() {
        return combo;
    }

    public void setCombo(List<NvcTblCatalogoComboPojo> combo) {
        this.combo = combo;
    }

    public List<NvcTblCatalogoDescuentoPojo> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(List<NvcTblCatalogoDescuentoPojo> descuentos) {
        this.descuentos = descuentos;
    }

    public Double getTest() {
        return test;
    }

    public void setTest(Double test) {
        this.test = test;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getTipoVariable() {
        return tipoVariable;
    }

    public void setTipoVariable(String tipoVariable) {
        this.tipoVariable = tipoVariable;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
