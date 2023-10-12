/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utils.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author APODR7218
 */
@Entity
@Table(name = "NVC_TBL_ITEM_FAVORITO")
@NamedQueries({
    @NamedQuery(name = "NvcTblItemFavorito.findAll", query = "SELECT n FROM NvcTblItemFavorito n")
    ,
    @NamedQuery(name = "NvcTblItemFavorito.findByIdUsuario", query = "SELECT n FROM NvcTblItemFavorito n WHERE n.idUsuario = :idUsuario")
    ,
    @NamedQuery(name = "NvcTblItemFavorito.findByIdItem", query = "SELECT n FROM NvcTblItemFavorito n WHERE n.idItem = :idItem")
    ,
    @NamedQuery(name = "NvcTblItemFavorito.findByIdItemIdUsuario", query = "SELECT n FROM NvcTblItemFavorito n WHERE (n.idItem = :idItem OR (n.idItemAlmacen = :idItemAlmacen AND n.idUen = :idUen )) AND n.idUsuario = :idUsuario")
})
public class NvcTblItemFavorito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_FAVORITOS_SPX", sequenceName = "SEQ_FAVORITOS", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_FAVORITOS_SPX")
    @Column(name = "ID_FAVORITO")
    private Integer idFavorito;
    @Size(max = 50)
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    @Column(name = "ID_ITEM")
    private Integer idItem;
    @Column(name = "ID_ITEM_ALMACEN")
    private Integer idItemAlmacen;
    @Column(name = "ID_UEN")
    private Integer idUen;

    public NvcTblItemFavorito(String idUsuario, Integer idItem) {
        this.idUsuario = idUsuario;
        this.idItem = idItem;
    }

    public NvcTblItemFavorito(String idUsuario, Integer idItemAlmacen, Integer idUen) {
        this.idUsuario = idUsuario;
        this.idItemAlmacen = idItemAlmacen;
        this.idUen = idUen;
    }

    public NvcTblItemFavorito() {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdFavorito() != null ? getIdFavorito().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idFavorito fields are not set
        if (!(object instanceof NvcTblItemFavorito)) {
            return false;
        }
        NvcTblItemFavorito other = (NvcTblItemFavorito) object;
        if ((this.getIdFavorito() == null && other.getIdFavorito() != null) || (this.getIdFavorito() != null && !this.idFavorito.equals(other.idFavorito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblItemFavorito[ idFavorito=" + getIdFavorito() + " ]";
    }

    /**
     * @return the idFavorito
     */
    public Integer getIdFavorito() {
        return idFavorito;
    }

    /**
     * @param idFavorito the idFavorito to set
     */
    public void setIdFavorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
    }

    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idItem
     */
    public Integer getIdItem() {
        return idItem;
    }

    /**
     * @param idItem the idItem to set
     */
    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    /**
     * @return the idItemAlmacen
     */
    public Integer getIdItemAlmacen() {
        return idItemAlmacen;
    }

    /**
     * @param idItemAlmacen the idItemAlmacen to set
     */
    public void setIdItemAlmacen(Integer idItemAlmacen) {
        this.idItemAlmacen = idItemAlmacen;
    }

    /**
     * @return the idUen
     */
    public Integer getIdUen() {
        return idUen;
    }

    /**
     * @param idUen the idUen to set
     */
    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

}
