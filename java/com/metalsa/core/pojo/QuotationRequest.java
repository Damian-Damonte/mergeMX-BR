/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.pojo;

import java.util.List;

/**
 *
 * @author edgar.leal
 */
public class QuotationRequest {
    private Quotation quotation;
    private String list_compras;
    private List<SupplierSelSPX> list_spx;
    private String motivo_seleccion;

   

    public QuotationRequest() {
    }
    
    public String getMotivo_seleccion() {
        return motivo_seleccion;
    }

    public void setMotivo_seleccion(String motivo_seleccion) {
        this.motivo_seleccion = motivo_seleccion;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public String getList_compras() {
        return list_compras;
    }

    public void setList_compras(String listCompras) {
        this.list_compras = listCompras;
    }

    public List<SupplierSelSPX> getList_spx() {
        return list_spx;
    }

    public void setList_spx(List<SupplierSelSPX> list_spx) {
        this.list_spx = list_spx;
    }

    @Override
    public String toString() {
        return "QuotationRequest{" + "quotation=" + quotation + ", list_compras=" + list_compras + ", list_spx=" + list_spx + ", motivo_seleccion=" + motivo_seleccion + '}';
    }

   
   
}
