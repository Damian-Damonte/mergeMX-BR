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
@Table(name = "NVC_TBL_V_ITEM_MES")
public class NvcTblItemVmes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "NVC_TBL_V_ITEM_MES_SEQ", sequenceName = "NVC_TBL_V_ITEM_MES_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "NVC_TBL_V_ITEM_MES_SEQ")
    @Column(name = "ID_ITEM_MES")
    private Integer idItemMes;
    
    @Column(name = "ID_ITEM")
    private Integer idItem;
    
    @Column(name = "ID_MES")
    private Integer idMes;

    @Column(name = "ANIO")
    private String anio;

    @Column(name = "UNIT_PRICE")
    private Double precioUnitario;

    @JoinColumn(name = "ID_ITEM_V", referencedColumnName = "ID_ITEM_V")
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    private NvcTblVCatalogoItem itemsBymes;

    public NvcTblItemVmes() {
    }

    public NvcTblItemVmes(NvcTblItemMes mes,NvcTblVCatalogoItem item,Integer idItem) { //idItem  --> idItem valor original idVItem se genera con la secuencias
        
        this.idMes = mes.getIdMes();
        this.anio = mes.getAnio();
        this.precioUnitario = mes.getPrecioUnitario();
        this.itemsBymes=item;
        this.idItem=idItem;
    }

    public Integer getIdItemMes() {
        return idItemMes;
    }

    public void setIdItemMes(Integer idItemMes) {
        this.idItemMes = idItemMes;
    }

    public Integer getIdMes() {
        return idMes;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
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
        hash = 53 * hash + Objects.hashCode(this.idItemMes);
        hash = 53 * hash + Objects.hashCode(this.idMes);
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
        final NvcTblItemVmes other = (NvcTblItemVmes) obj;
        if (!Objects.equals(this.anio, other.anio)) {
            return false;
        }
        if (!Objects.equals(this.idItemMes, other.idItemMes)) {
            return false;
        }
        if (!Objects.equals(this.idMes, other.idMes)) {
            return false;
        }
        if (!Objects.equals(this.precioUnitario, other.precioUnitario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NvcTblItemMes{" + "idItemMes=" + idItemMes + ", idItem=" + ", idMes=" + idMes + ", anio=" + anio + ", precioUnitario=" + precioUnitario + '}';
    }

    public NvcTblVCatalogoItem getItemsBymes() {
        return itemsBymes;
    }

    public void setItemsBymes(NvcTblVCatalogoItem itemsBymes) {
        this.itemsBymes = itemsBymes;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

}
