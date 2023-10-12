package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author APOOD9272
 */
public class OaProyectoCuenta {
    @JsonProperty("id_proyecto")        
    private Long idProyecto;

    @JsonProperty("id_tarea")        
    private Long idTarea;
    
    @JsonProperty("exp_type")        
    private String expType;
    
    @JsonProperty("id_cuenta")    
    private Long idCuenta;

    private String Segmento1;
    
    private String Segmento2;
    
    private String Segmento3;
    
    private String Segmento4;
    
    private String Segmento5;
    
    private String Segmento6;
    
    private String Segmento7;
    
    private String Segmento8;

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType;
    }

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getSegmento1() {
        return Segmento1;
    }

    public void setSegmento1(String Segmento1) {
        this.Segmento1 = Segmento1;
    }

    public String getSegmento2() {
        return Segmento2;
    }

    public void setSegmento2(String Segmento2) {
        this.Segmento2 = Segmento2;
    }

    public String getSegmento3() {
        return Segmento3;
    }

    public void setSegmento3(String Segmento3) {
        this.Segmento3 = Segmento3;
    }

    public String getSegmento4() {
        return Segmento4;
    }

    public void setSegmento4(String Segmento4) {
        this.Segmento4 = Segmento4;
    }

    public String getSegmento5() {
        return Segmento5;
    }

    public void setSegmento5(String Segmento5) {
        this.Segmento5 = Segmento5;
    }

    public String getSegmento6() {
        return Segmento6;
    }

    public void setSegmento6(String Segmento6) {
        this.Segmento6 = Segmento6;
    }

    public String getSegmento7() {
        return Segmento7;
    }

    public void setSegmento7(String Segmento7) {
        this.Segmento7 = Segmento7;
    }

    public String getSegmento8() {
        return Segmento8;
    }

    public void setSegmento8(String Segmento8) {
        this.Segmento8 = Segmento8;
    }

    @Override
    public String toString() {
        return "OaProyectoCuenta{" + "idProyecto=" + idProyecto + ", idTarea=" + idTarea + ", expType=" + expType + ", idCuenta=" + idCuenta + ", Segmento1=" + Segmento1 + ", Segmento2=" + Segmento2 + ", Segmento3=" + Segmento3 + ", Segmento4=" + Segmento4 + ", Segmento5=" + Segmento5 + ", Segmento6=" + Segmento6 + ", Segmento7=" + Segmento7 + ", Segmento8=" + Segmento8 + '}';
    }
    
   
}
