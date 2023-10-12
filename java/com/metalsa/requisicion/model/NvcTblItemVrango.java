package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author J.Alfred
 */
@Entity
@Table(name = "NVC_TBL_V_ITEM_RANGO")
public class NvcTblItemVrango implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "NVC_TBL_V_ITEM_RANGO_SEQ", sequenceName = "NVC_TBL_V_ITEM_RANGO_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "NVC_TBL_V_ITEM_RANGO_SEQ")
    @Column(name = "ID_ITEM_RANGO")
    private Integer idItemRango;

    @Column(name = "ID_ITEM")
    private Integer idItem;

    @Column(name = "RANGO_FROM")
    private Integer rangoFrom;

    @Column(name = "RANGO_TO")
    private Integer rangoTo;

    @Column(name = "UNIT_PRICE")
    private Double precioUnitario;

    @JoinColumn(name = "ID_ITEM_V", referencedColumnName = "ID_ITEM_V")
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    private NvcTblVCatalogoItem itemsByRango;

    public NvcTblItemVrango() {
    }

    public NvcTblItemVrango(NvcTblItemRango rangoInfo, NvcTblVCatalogoItem item, Integer idItem) {
        this.rangoFrom = rangoInfo.getRangoFrom();
        this.rangoTo = rangoInfo.getRangoTo();
        this.precioUnitario = rangoInfo.getPrecioUnitario();
        this.itemsByRango = item;
        this.idItem=idItem;
    }

    public Integer getIdItemRango() {
        return idItemRango;
    }

    public void setIdItemRango(Integer idItemRango) {
        this.idItemRango = idItemRango;
    }

    public Integer getRangoFrom() {
        return rangoFrom;
    }

    public void setRangoFrom(Integer rangoFrom) {
        this.rangoFrom = rangoFrom;
    }

    public Integer getRangoTo() {
        return rangoTo;
    }

    public void setRangoTo(Integer rangoTo) {
        this.rangoTo = rangoTo;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idItemRango);

        hash = 79 * hash + Objects.hashCode(this.rangoFrom);
        hash = 79 * hash + Objects.hashCode(this.rangoTo);
        hash = 79 * hash + Objects.hashCode(this.precioUnitario);
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
        final NvcTblItemVrango other = (NvcTblItemVrango) obj;
        if (!Objects.equals(this.idItemRango, other.idItemRango)) {
            return false;
        }
        if (!Objects.equals(this.rangoFrom, other.rangoFrom)) {
            return false;
        }
        if (!Objects.equals(this.rangoTo, other.rangoTo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NvcTblItemRango{" + "idItemRango=" + idItemRango + ", idItem=" + ", rangoFrom=" + rangoFrom + ", rangoTo=" + rangoTo + ", precioUnitario=" + precioUnitario + '}';
    }

    public NvcTblVCatalogoItem getItemsByRango() {
        return itemsByRango;
    }

    public void setItemsByRango(NvcTblVCatalogoItem itemsByRango) {
        this.itemsByRango = itemsByRango;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

}
