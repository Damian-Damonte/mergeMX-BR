package com.metalsa.requisicion.pojo;

import com.metalsa.requisicion.model.NvcTblItemVmes;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author omar.estrella
 */
public class NvcTblIMonthItemInfoPojo implements Serializable {

    private NvcTblCatalogoMesPojo month;
    
    private Integer idItemMes;

    private String anio;

    private Double unitPrice;

    private int idMes;

    private String codigoItem;
    
    private int idItem;
    
    private int eliminado;

    public NvcTblIMonthItemInfoPojo() {
    }

    //- Alfred
    public NvcTblIMonthItemInfoPojo(NvcTblItemVmes mes) {
        this.idItemMes = mes.getIdItemMes();
        this.month = null;
        this.anio = mes.getAnio();
        this.unitPrice = mes.getPrecioUnitario();
        this.idMes = mes.getIdMes();
        this.codigoItem = null;
    }
    //- Alfred

    public NvcTblCatalogoMesPojo getMonth() {
        this.month = new NvcTblCatalogoMesPojo();
        return month;
    }

    public void setMonth(NvcTblCatalogoMesPojo month) {
        this.month = month;
    }

    public Integer getIdItemMes() {
        return idItemMes;
    }

    public void setIdItemMes(Integer idItemMes) {
        this.idItemMes = idItemMes;
    }
    
    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getIdMes() {
        return idMes;
    }

    public void setIdMes(int idMes) {
        this.idMes = idMes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NvcTblIMonthItemInfoPojo other = (NvcTblIMonthItemInfoPojo) obj;
        if (this.idMes != other.idMes) {
            return false;
        }
        if (!Objects.equals(this.anio, other.anio)) {
            return false;
        }
        if (!Objects.equals(this.unitPrice, other.unitPrice)) {
            return false;
        }
        return true;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
    
    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }
    
}
