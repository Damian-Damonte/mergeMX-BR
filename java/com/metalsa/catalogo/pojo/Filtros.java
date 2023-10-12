package com.metalsa.catalogo.pojo;

import java.util.List;

/**
 *
 * @author APOMR10051
 */
public class Filtros {

    private String idIdioma;
    private List<CatalogoFiltro> items;
    private List<CatalogoFiltro> estatus;

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public List<CatalogoFiltro> getItems() {
        return items;
    }

    public void setItems(List<CatalogoFiltro> items) {
        this.items = items;
    }

    public List<CatalogoFiltro> getEstatus() {
        return estatus;
    }

    public void setEstatus(List<CatalogoFiltro> estatus) {
        this.estatus = estatus;
    }
    
}
