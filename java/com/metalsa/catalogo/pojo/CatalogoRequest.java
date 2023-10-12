package com.metalsa.catalogo.pojo;

import com.metalsa.catalogo.model.NvcTblCatalogo;
import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import com.metalsa.catalogo.model.NvcTblCatalogoUen;
import java.util.List;

/**
 *
 * @author APOMR10051
 */
public class CatalogoRequest {

    private NvcTblCatalogo catalogo;
    private NvcTblCatalogoUen catalogoUen;
    private Integer idCatalogoUen;
    private String idUsuario;
    private String idioma;
    private List<NvcTblCatalogoItem> items;
    private Integer activeVal;
    private Filtros filtros;
    private boolean allChecked;

    public NvcTblCatalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(NvcTblCatalogo catalogo) {
        this.catalogo = catalogo;
    }

    public NvcTblCatalogoUen getCatalogoUen() {
        return catalogoUen;
    }

    public void setCatalogoUen(NvcTblCatalogoUen catalogoUen) {
        this.catalogoUen = catalogoUen;
    }

    public Integer getIdCatalogoUen() {
        return idCatalogoUen;
    }

    public void setIdCatalogoUen(Integer idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
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

    public Filtros getFiltros() {
        return filtros;
    }

    public void setFiltros(Filtros filtros) {
        this.filtros = filtros;
    }

    public boolean isAllChecked() {
        return allChecked;
    }

    public void setAllChecked(boolean allChecked) {
        this.allChecked = allChecked;
    }
}
