package com.metalsa.requisicion.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author omar.estrella
 */
public class NvcTblItemRangoInfoPojo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer idItemRango;
    
    private Integer from;
    
    private Integer to;
    
    private Double unitPrice;
    
    private String codigoItem;
    
    private int eliminado;
    
    public NvcTblItemRangoInfoPojo() {
    }

    public NvcTblItemRangoInfoPojo(Integer idItemRango, Integer from, Integer to, Double unitPrice) {
        this.from = from;
        this.to = to;
        this.unitPrice = unitPrice;
    }

    public Integer getIdItemRango() {
        return idItemRango;
    }

    public void setIdItemRango(Integer idItemRango) {
        this.idItemRango = idItemRango;
    }
    
    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
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
        final NvcTblItemRangoInfoPojo other = (NvcTblItemRangoInfoPojo) obj;
        if (!Objects.equals(this.from, other.from)) {
            return false;
        }
        if (!Objects.equals(this.to, other.to)) {
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
    
    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }
}
