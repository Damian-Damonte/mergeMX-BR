/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.search_engine.pojo;

import java.math.BigDecimal;

import com.metalsa.utils.Constants;

/**
 *
 * @author jose.espindola03
 */
public class SearchEngineItem {
 
    private Integer id;
    private String descripcion;
    private Double precioBase;
    private Double precio;
    

    private String urlFtp;
    private Boolean isFavorite;
    private String codigoItem;
    private String idCatalogoUen;
    private String nombreCatalogo;
    private String numeroParteFabricante;
    private String numeroParteProveedor;
    private String fabricante;
    private String udm;
    private String tiempoEntrega;
    private Integer idUen;
    private String nombreUen;
    private String nombreCategoria;    
    private String idMoneda;    
    private Integer idAlmacen;
    private String nombreAlmacen;
    private BigDecimal cantidadDisponible;  
    private Integer tipoItem;
    private String nombreProveedor;

    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setUrlFtp(String urlFtp){
        this.urlFtp = urlFtp;
    }

    public String getUrlFtp(){
        return this.urlFtp;
    }

    public String getFullUrlFtp(){
        if(this.urlFtp == null || this.urlFtp.length() == 0){
            return Constants.URL_IMAGES_FTP_NOT_FOUND;
        }

        return Constants.URL_IMAGES_FTP + this.urlFtp;        
    }

    public String getIdMoneda() {
        return idMoneda;
    }
    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }
    
    public Integer getTipoItem() {
        return tipoItem;
    }
    public void setTipoItem(Integer tipoItem) {
        this.tipoItem = tipoItem;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioBase() {
        return precioBase;
    }
    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }
    public Boolean getIsFavorite() {
        return isFavorite;
    }
    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
    public String getCodigoItem() {
        return codigoItem;
    }
    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }
    public String getIdCatalogoUen() {
        return idCatalogoUen;
    }
    public void setIdCatalogoUen(String idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
    }
    public String getNombreCatalogo() {
        return nombreCatalogo;
    }
    public void setNombreCatalogo(String nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }
    public String getNumeroParteFabricante() {
        return numeroParteFabricante;
    }
    public void setNumeroParteFabricante(String numeroParteFabricante) {
        this.numeroParteFabricante = numeroParteFabricante;
    }
    public String getNumeroParteProveedor() {
        return numeroParteProveedor;
    }
    public void setNumeroParteProveedor(String numeroParteProveedor) {
        this.numeroParteProveedor = numeroParteProveedor;
    }
    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    public String getUdm() {
        return udm;
    }
    public void setUdm(String udm) {
        this.udm = udm;
    }
    public String getTiempoEntrega() {
        return tiempoEntrega;
    }
    public void setTiempoEntrega(String tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }
    public String getNombreUen() {
        return nombreUen;
    }
    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }
    public String getNombreCategoria() {
        return nombreCategoria;
    }
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    public Integer getIdAlmacen() {
        return idAlmacen;
    }
    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }
    public String getNombreAlmacen() {
        return nombreAlmacen;
    }
    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }
    public BigDecimal getCantidadDisponible() {
        return cantidadDisponible;
    }
    public void setCantidadDisponible(BigDecimal cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIdUen() {
        return this.idUen;
    }
    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }
    
    public String getNombreProveedor() {
        return this.nombreProveedor;
    }
    
    public void setNombreProveedor(String nombre) {
        this.nombreProveedor = nombre;
    }

    public static SearchEngineItem createItemBySP_GET_ITEMS(Object[] row){

        SearchEngineItem item = new SearchEngineItem();
                    
        item.setIsFavorite(row[0] == null ? null : Boolean.valueOf(row[0].toString()) );                   
        item.setId(row[1] == null ? null: ((BigDecimal) row[1]).intValue());                    
        item.setCodigoItem(row[2] == null ? null: row[2].toString());                    
        item.setDescripcion(row[3] == null ? null: row[3].toString());                    
        item.setIdCatalogoUen(row[4] == null ? null: row[4].toString());                    
        item.setNombreCatalogo(row[5] == null ? null: row[5].toString());                    
        item.setNumeroParteFabricante(row[6] == null ? null: row[6].toString());                    
        item.setNumeroParteProveedor(row[7] == null ? null: row[7].toString());                    
        item.setFabricante(row[8] == null ? null: row[8].toString());                    
        item.setUdm(row[12] == null ? null: row[12].toString());                    
        item.setTiempoEntrega(row[16] == null ? null: row[16].toString());                    
        item.setIdMoneda(row[17] == null ? null: row[17].toString());                    
        item.setNombreUen(row[21] == null ? null: row[21].toString());                    
        item.setNombreCategoria(row[22] == null ? null: row[22].toString());                    
        item.setTipoItem(row[33] == null ? null: ((BigDecimal) row[33]).intValue());                    
        item.setIdAlmacen(row[34] == null ? null: ((BigDecimal) row[34]).intValue());                    
        item.setNombreAlmacen(row[35] == null ? null: row[35].toString());                    
        item.setCantidadDisponible(row[40] == null ? null: (BigDecimal) row[40]);                    
        item.setPrecioBase(row[48] == null ? null: ((BigDecimal) row[48]).doubleValue());                                           
        item.setPrecio(row[13] == null ? null: ((BigDecimal) row[13]).doubleValue());
        item.setUrlFtp(row[44] == null ? null: row[44].toString());
        item.setIdUen(row[20] == null ? null : ((BigDecimal) row[20]).intValue());
        item.setNombreProveedor((String)row[37]);

        return item;
    }

    public static SearchEngineItem createItemBySP_GET_ITEMS_FAVORITOS(Object[] row){

        SearchEngineItem item = new SearchEngineItem();
                    
        item.setIsFavorite(row[0] == null ? null : Boolean.valueOf(row[0].toString()) );                   
        item.setId(row[1] == null ? null: ((BigDecimal) row[1]).intValue());                    
        item.setCodigoItem(row[2] == null ? null: row[2].toString());                    
        item.setDescripcion(row[3] == null ? null: row[3].toString());                    
        item.setIdCatalogoUen(row[4] == null ? null: row[4].toString());                    
        item.setNombreCatalogo(row[5] == null ? null: row[5].toString());                    
        item.setNumeroParteFabricante(row[6] == null ? null: row[6].toString());                    
        item.setNumeroParteProveedor(row[7] == null ? null: row[7].toString());                    
        item.setFabricante(row[8] == null ? null: row[8].toString());                    
        item.setUdm(row[12] == null ? null: row[12].toString());                    
        item.setTiempoEntrega(row[16] == null ? null: row[16].toString());                    
        item.setIdMoneda(row[17] == null ? null: row[17].toString());                    
        item.setNombreUen(row[21] == null ? null: row[21].toString());                    
        item.setNombreCategoria(row[22] == null ? null: row[22].toString());                    
        item.setTipoItem(row[33] == null ? null: ((BigDecimal) row[33]).intValue());                    
        item.setIdAlmacen(row[34] == null ? null: ((BigDecimal) row[34]).intValue());                    
        //item.setNombreAlmacen(row[35] == null ? null: row[35].toString());                    
        //item.setCantidadDisponible(row[40] == null ? null: (BigDecimal) row[40]);                    
        //item.setPrecioBase(row[48] == null ? null: ((BigDecimal) row[48]).doubleValue());                                           
        item.setPrecio(row[13] == null ? null: ((BigDecimal) row[13]).doubleValue());
        item.setPrecio(row[13] == null ? null: ((BigDecimal) row[13]).doubleValue());
        item.setIdUen(row[20] == null ? null : ((BigDecimal) row[20]).intValue());
        item.setNombreProveedor((String)row[36]);

        return item;
    }
        
    
}
