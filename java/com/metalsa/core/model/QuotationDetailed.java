/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

/**
 *
 * @author APOJO9952
 */
public class QuotationDetailed {

    private Quotation quotation;
//    private Iterable<SupplierSel> listCompras;
    private Iterable<SupplierSelSPX> listSPX;
    private String motivo_seleccion;

    public QuotationDetailed(Quotation quotation, 
//            Iterable<SupplierSel> listCompras, 
            Iterable<SupplierSelSPX> listSPX) {
        this.quotation = quotation;
//        this.listCompras = listCompras;
        this.listSPX = listSPX;
        this.motivo_seleccion = null;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

//    public Iterable<SupplierSel> getListCompras() {
//        return listCompras;
//    }
//
//    public void setListCompras(Iterable<SupplierSel> listCompras) {
//        this.listCompras = listCompras;
//    }

    public Iterable<SupplierSelSPX> getListSPX() {
        return listSPX;
    }

    public void setListSPX(Iterable<SupplierSelSPX> listSPX) {
        this.listSPX = listSPX;
    }

    public String getMotivo_seleccion() {
        return motivo_seleccion;
    }

    public void setMotivo_seleccion(String motivo_seleccion) {
        this.motivo_seleccion = motivo_seleccion;
    }
}
