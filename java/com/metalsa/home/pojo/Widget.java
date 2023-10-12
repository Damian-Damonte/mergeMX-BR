/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.home.pojo;

/**
 *
 * @author edgar.leal
 */
public class Widget {

    private Integer idWidget;
    private String idUsuario;
    private Integer posX;
    private Integer posY;
    private Integer width;
    private Integer height;
    private Integer fijo;

    public Widget() {
    }

    public Integer getIdWidget() {
        return idWidget;
    }

    public void setIdWidget(Integer idWidget) {
        this.idWidget = idWidget;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getFijo() {
        return fijo;
    }

    public void setFijo(Integer fijo) {
        this.fijo = fijo;
    }

    @Override
    public String toString() {
        return "Widget{" + "idWidget=" + idWidget + ", idUsuario=" + idUsuario + ", posX=" + posX + ", posY=" + posY + ", width=" + width + ", height=" + height + ", fijo=" + fijo + '}';
    }
}
