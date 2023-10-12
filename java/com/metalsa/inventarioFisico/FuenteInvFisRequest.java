/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.inventarioFisico;

import java.util.Objects;

/**
 *
 * @author hector.gutierrez02
 */
public class FuenteInvFisRequest {

    String tipo;
    String desc;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FuenteInvFisRequest f = (FuenteInvFisRequest) obj;
        return tipo.equals(f.tipo) && desc.equals(f.desc);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.tipo);
        hash = 71 * hash + Objects.hashCode(this.desc);
        return hash;
    }

}
