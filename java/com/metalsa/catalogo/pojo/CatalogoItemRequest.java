package com.metalsa.catalogo.pojo;

import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import java.util.List;

/**
 *
 * @author APOMR10051
 */
public class CatalogoItemRequest {

    private String idUsuario;
    private List<NvcTblCatalogoItem> items;
    private Integer activeVal;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<NvcTblCatalogoItem> getItems() {
        return items;
    }

    public void setItems(List<NvcTblCatalogoItem> items) {
        this.items = items;
    }

    public Integer getActiveVal() {
        return activeVal;
    }

    public void setActiveVal(Integer activeVal) {
        this.activeVal = activeVal;
    }
}
