package com.metalsa.requisicion.pojo;

import com.metalsa.requisicion.model.Fuentes;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public class CarroCompraItem {
    private Integer idUen;
    private Long idCarroCompra;
    private Integer punchout;
    private Integer idAlmacen;
    private String razonSpot;
    private String fuente;
    private Integer idItem;
    
    public Integer getIdUen() {
        return this.idUen;
    }
    
    public void setIdUen(Integer id) {
        this.idUen = id;
    }
    
    public Long getIdCarroCompra() {
        return this.idCarroCompra;
    }
    
    public void setIdCarroCompra(Long id) {
        this.idCarroCompra = id;
    }
    
    public Integer getPunchout() {
        return punchout;
    }

    public void setPunchout(Integer punchout) {
        this.punchout = punchout;
    }
    
    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getRazonSpot() {
        return razonSpot;
    }
    
    public String getFuente() {
        return this.fuente;
    }
    
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
    
    public boolean isFuente(Fuentes fuente) {
        return this.fuente != null && this.fuente.equals(fuente.getFuente());
    }
    
    public Integer getIdItem() {
        return this.idItem;
    }
    
    public void setIdItem(Integer id) {
        this.idItem = id;
    }
}
