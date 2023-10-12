/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.pojo;

import java.util.List;

/**
 *
 * @author edgar.leal
 */
public class PayloadRecibos {
    
    Integer id_requisicion;
    Integer id_orden_compra;
    List<LineaPayload> lineas;
    Integer current;
    String comentario;
    String p_0;
    String p_1;
    String p_2;
    String p_3;
    String p_4;
    String p_5;

    public Integer getId_orden_compra() {
        return id_orden_compra;
    }

    public void setId_orden_compra(Integer id_orden_compra) {
        this.id_orden_compra = id_orden_compra;
    }
    
    public String getP_0() {
        return p_0;
    }

    public void setP_0(String p_0) {
        this.p_0 = p_0;
    }

    public String getP_1() {
        return p_1;
    }

    public void setP_1(String p_1) {
        this.p_1 = p_1;
    }

    public String getP_2() {
        return p_2;
    }

    public void setP_2(String p_2) {
        this.p_2 = p_2;
    }

    public String getP_3() {
        return p_3;
    }

    public void setP_3(String p_3) {
        this.p_3 = p_3;
    }

    public String getP_4() {
        return p_4;
    }

    public void setP_4(String p_4) {
        this.p_4 = p_4;
    }

    public String getP_5() {
        return p_5;
    }

    public void setP_5(String p_5) {
        this.p_5 = p_5;
    }
    
    

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    

    public Integer getId_requisicion() {
        return id_requisicion;
    }

    public void setId_requisicion(Integer id_requisicion) {
        this.id_requisicion = id_requisicion;
    }

    public List<LineaPayload> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPayload> lineas) {
        this.lineas = lineas;
    }

    public PayloadRecibos(Integer id_requisicion, List<LineaPayload> lineas) {
        this.id_requisicion = id_requisicion;
        this.lineas = lineas;
    }

    public PayloadRecibos() {
    }
    
    
    
    
    
}
