package com.metalsa.catalogo.pojo;

import com.metalsa.catalogo.model.NvcTblCatalogo;
import com.metalsa.catalogo.model.NvcTblCatalogoUen;
import java.util.List;

/**
 *
 * @author APOMR10051
 */
public class CatalogoResponse {

    private NvcTblCatalogo catalogo;
    private NvcTblCatalogoUen catalogoUen;
    private List<NvcTblCatalogoUen> catalogosUen;
    private String outMessage;

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

    public List<NvcTblCatalogoUen> getCatalogosUen() {
        return catalogosUen;
    }

    public void setCatalogosUen(List<NvcTblCatalogoUen> catalogosUen) {
        this.catalogosUen = catalogosUen;
    }

    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }
}
