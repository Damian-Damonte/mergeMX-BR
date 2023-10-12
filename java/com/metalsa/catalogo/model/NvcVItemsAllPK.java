package com.metalsa.catalogo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Embeddable
public class NvcVItemsAllPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "IDITEM")
    private Integer idItem;
    @Column(name = "UEN")
    private Integer uen;
    @Column(name = "IDALMACEN")
    private Integer idAlmacen;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getUen() {
        return uen;
    }

    public void setUen(Integer uen) {
        this.uen = uen;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }
}
