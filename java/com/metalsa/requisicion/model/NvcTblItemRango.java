package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author omar.estrella
 */
@Entity
@Table(name = "NVC_TBL_ITEM_RANGO")
@NamedQueries({
    @NamedQuery(name = "NvcTblItemRango.findAll", query = "SELECT n FROM NvcTblItemRango n"),
    @NamedQuery(name = "NvcTblItemRango.findByIdItem", query = "SELECT n FROM NvcTblItemRango n WHERE n.idItem = :idItem"),
    @NamedQuery(name = "NvcTblItemRango.countByIdItem", query = "SELECT count(n) FROM NvcTblItemRango n WHERE n.idItem = :idItem"),
})
public class NvcTblItemRango implements Serializable{
    @Id
    @SequenceGenerator(name = "NVC_TBL_ITEM_RANGO_SEQ_SPX", sequenceName = "NVC_TBL_ITEM_RANGO_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "NVC_TBL_ITEM_RANGO_SEQ_SPX")
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

    public Integer getIdItemRango() {
        return idItemRango;
    }

    public void setIdItemRango(Integer idItemRango) {
        this.idItemRango = idItemRango;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
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
        hash = 79 * hash + Objects.hashCode(this.idItem);
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
        final NvcTblItemRango other = (NvcTblItemRango) obj;
        if (!Objects.equals(this.idItemRango, other.idItemRango)) {
            return false;
        }
        if (!Objects.equals(this.idItem, other.idItem)) {
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
        return "NvcTblItemRango{" + "idItemRango=" + idItemRango + ", idItem=" + idItem + ", rangoFrom=" + rangoFrom + ", rangoTo=" + rangoTo + ", precioUnitario=" + precioUnitario + '}';
    }
    
    
    

    
}
