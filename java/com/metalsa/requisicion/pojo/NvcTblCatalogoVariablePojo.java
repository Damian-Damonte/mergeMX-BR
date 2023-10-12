/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jose.jimenez07
 */
public class NvcTblCatalogoVariablePojo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idTemplate;
    private String nombreTemplate;
    private String formula;
    private String uom;
    private Integer sinDescuento;
    private boolean sinDescuentoBool=false;
    private List<NvcTblCatalogoVarsPojo> variables;
    private List<NvcTblCatalogoVarsPojo> vars2; // dato @transiend, guarda el filtro de variables, se usa para ver las versiones
    private Integer accion;
    private String id_usuario;
    private Integer sinFormula;
    private String nombreFormula;
    private boolean sinFormulaBool=false;
    private Long idUen;
    private Long tipoPrecio;
    

    public NvcTblCatalogoVariablePojo() {
    }

    public Long getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Long idTemplate) {
        this.idTemplate = idTemplate;
    }

    public String getNombreTemplate() {
        return nombreTemplate;
    }

    public void setNombreTemplate(String nombreTemplate) {
        this.nombreTemplate = nombreTemplate;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Integer getSinDescuento() {
        return sinDescuento;
    }

    public void setSinDescuento(Integer sinDescuento) {
        this.sinDescuento = sinDescuento;
    }

    public List<NvcTblCatalogoVarsPojo> getVariables() {
        return variables;
    }

    public void setVariables(List<NvcTblCatalogoVarsPojo> variables) {
        this.variables = variables;
    }

    public Integer getAccion() {
        return accion;
    }

    public void setAccion(Integer accion) {
        this.accion = accion;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getSinFormula() {
        return sinFormula;
    }

    public void setSinFormula(Integer sinFormula) {
        this.sinFormula = sinFormula;
    }

    public String getNombreFormula() {
        return nombreFormula;
    }

    public void setNombreFormula(String nombreFormula) {
        this.nombreFormula = nombreFormula;
    }

    public List<NvcTblCatalogoVarsPojo> getVars2() {
        if (variables != null && !variables.isEmpty()) {
            vars2=new ArrayList<>();
            vars2.addAll(variables.stream().
                    filter(v -> v.getTipo().equals("NUM") || v.getTipo().equals("NONE")).
                    collect(Collectors.toList()));
        }
        return vars2;
    }

    public void setVars2(List<NvcTblCatalogoVarsPojo> vars2) {
        this.vars2 = vars2;
    }

    public boolean isSinDescuentoBool() {
        if(sinDescuento!=null && sinDescuento==1){
            sinDescuentoBool=true;
    }
        return sinDescuentoBool;
    }

    public void setSinDescuentoBool(boolean sinDescuentoBool) {
        this.sinDescuentoBool = sinDescuentoBool;
    }

    public boolean isSinFormulaBool() {
        if(sinFormula!=null && sinFormula==1){
        sinFormulaBool=true;
        }
            
        return sinFormulaBool;
    }

    public void setSinFormulaBool(boolean sinFormulaBool) {
        this.sinFormulaBool = sinFormulaBool;
    }

    public Long getIdUen() {
        return idUen;
    }

    public void setIdUen(Long idUen) {
        this.idUen = idUen;
    }

    public Long getTipoPrecio() {
        return tipoPrecio;
    }

    public void setTipoPrecio(Long tipoPrecio) {
        this.tipoPrecio = tipoPrecio;
    }

    
    
    
    
    
    
    
    
}
