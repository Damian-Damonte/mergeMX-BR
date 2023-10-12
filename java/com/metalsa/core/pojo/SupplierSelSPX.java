/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.pojo;

/**
 *
 * @author edgar.leal
 */
public class SupplierSelSPX {

    private Integer id_rfq;
    private Integer id_proveedor;
    private String descripcion_producto;
    private long num_cotizacion;
    private Integer id_sucursal_proveedor;
    private String nombre_proveedor;
    private Double precio_cotizado;
    private String lead_time;
    private Double monto;
    private String moneda;
    private String comentario;
    private String recomendada;
    private Double gastos_importacion;
    private Double attribute7;
    private Integer id_uen;
    private Integer ext;
    private Integer mejor_opcion;
    private Double gasto;
    private Integer linea_prov;
    private Boolean razon_seleccion_cotizacion;

    public Boolean getRazon_seleccion_cotizacion() {
        return razon_seleccion_cotizacion;
    }

    public void setRazon_seleccion_cotizacion(Boolean razon_seleccion_cotizacion) {
        this.razon_seleccion_cotizacion = razon_seleccion_cotizacion;
    }
    
    
//More than 10 parameters (found 20).
    public SupplierSelSPX(Integer id_rfq, Integer id_proveedor, 
            String descripcion_producto, long num_cotizacion, 
            Integer id_sucursal_proveedor, String nombre_proveedor, 
            Double precio_cotizado, String lead_time, Double monto, 
            String moneda, String comentario, String recomendada, 
            Double gastos_importacion, Double attribute7, Integer id_uen, 
            Integer ext, Integer mejor_opcion, Double gasto, Integer linea_prov, 
            Boolean razon_seleccion_cotizacion) {
        this.id_rfq = id_rfq;
        this.id_proveedor = id_proveedor;
        this.descripcion_producto = descripcion_producto;
        this.num_cotizacion = num_cotizacion;
        this.id_sucursal_proveedor = id_sucursal_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.precio_cotizado = precio_cotizado;
        this.lead_time = lead_time;
        this.monto = monto;
        this.moneda = moneda;
        this.comentario = comentario;
        this.recomendada = recomendada;
        this.gastos_importacion = gastos_importacion;
        this.attribute7 = attribute7;
        this.id_uen = id_uen;
        this.ext = ext;
        this.mejor_opcion = mejor_opcion;
        this.gasto = gasto;
        this.linea_prov = linea_prov;
        this.razon_seleccion_cotizacion = razon_seleccion_cotizacion;
    }

    public Integer getId_rfq() {
        return id_rfq;
    }

    public void setId_rfq(Integer id_rfq) {
        this.id_rfq = id_rfq;
    }

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public long getNum_cotizacion() {
        return num_cotizacion;
    }

    public void setNum_cotizacion(long num_cotizacion) {
        this.num_cotizacion = num_cotizacion;
    }

    public Integer getId_sucursal_proveedor() {
        return id_sucursal_proveedor;
    }

    public void setId_sucursal_proveedor(Integer id_sucursal_proveedor) {
        this.id_sucursal_proveedor = id_sucursal_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public Double getPrecio_cotizado() {
        return precio_cotizado;
    }

    public void setPrecio_cotizado(Double precio_cotizado) {
        this.precio_cotizado = precio_cotizado;
    }

    public String getLead_time() {
        return lead_time;
    }

    public void setLead_time(String lead_time) {
        this.lead_time = lead_time;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getRecomendada() {
        return recomendada;
    }

    public void setRecomendada(String recomendada) {
        this.recomendada = recomendada;
    }

    public Double getGastos_importacion() {
        return gastos_importacion;
    }

    public void setGastos_importacion(Double gastos_importacion) {
        this.gastos_importacion = gastos_importacion;
    }

    public Double getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(Double attribute7) {
        this.attribute7 = attribute7;
    }

    public Integer getId_uen() {
        return id_uen;
    }

    public void setId_uen(Integer id_uen) {
        this.id_uen = id_uen;
    }

    public Integer getExt() {
        return ext;
    }

    public void setExt(Integer ext) {
        this.ext = ext;
    }

    public Integer getMejor_opcion() {
        return mejor_opcion;
    }

    public void setMejor_opcion(Integer mejor_opcion) {
        this.mejor_opcion = mejor_opcion;
    }

    public Double getGasto() {
        return gasto;
    }

    public void setGasto(Double gasto) {
        this.gasto = gasto;
    }

    public Integer getLinea_prov() {
        return linea_prov;
    }

    public void setLinea_prov(Integer linea_prov) {
        this.linea_prov = linea_prov;
    }

    /*@Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id_rfq);
        hash = 59 * hash + Objects.hashCode(this.id_proveedor);
        hash = 59 * hash + Objects.hashCode(this.descripcion_producto);
        hash = 59 * hash + (int) (this.num_cotizacion ^ (this.num_cotizacion >>> 32));
        hash = 59 * hash + Objects.hashCode(this.id_sucursal_proveedor);
        hash = 59 * hash + Objects.hashCode(this.nombre_proveedor);
        hash = 59 * hash + Objects.hashCode(this.precio_cotizado);
        hash = 59 * hash + Objects.hashCode(this.lead_time);
        hash = 59 * hash + Objects.hashCode(this.monto);
        hash = 59 * hash + Objects.hashCode(this.moneda);
        hash = 59 * hash + Objects.hashCode(this.comentario);
        hash = 59 * hash + Objects.hashCode(this.recomendada);
        hash = 59 * hash + Objects.hashCode(this.gastos_importacion);
        hash = 59 * hash + Objects.hashCode(this.attribute7);
        hash = 59 * hash + Objects.hashCode(this.id_uen);
        hash = 59 * hash + Objects.hashCode(this.ext);
        hash = 59 * hash + Objects.hashCode(this.mejor_opcion);
        hash = 59 * hash + Objects.hashCode(this.gasto);
        hash = 59 * hash + Objects.hashCode(this.linea_prov);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SupplierSelSPX other = (SupplierSelSPX) obj;
        if (this.num_cotizacion != other.num_cotizacion) {
            return false;
        }
        if (!Objects.equals(this.descripcion_producto, other.descripcion_producto)) {
            return false;
        }
        if (!Objects.equals(this.nombre_proveedor, other.nombre_proveedor)) {
            return false;
        }
        if (!Objects.equals(this.lead_time, other.lead_time)) {
            return false;
        }
        if (!Objects.equals(this.moneda, other.moneda)) {
            return false;
        }
        if (!Objects.equals(this.comentario, other.comentario)) {
            return false;
        }
        if (!Objects.equals(this.recomendada, other.recomendada)) {
            return false;
        }
        if (!Objects.equals(this.id_rfq, other.id_rfq)) {
            return false;
        }
        if (!Objects.equals(this.id_proveedor, other.id_proveedor)) {
            return false;
        }
        if (!Objects.equals(this.id_sucursal_proveedor, other.id_sucursal_proveedor)) {
            return false;
        }
        if (!Objects.equals(this.precio_cotizado, other.precio_cotizado)) {
            return false;
        }
        if (!Objects.equals(this.monto, other.monto)) {
            return false;
        }
        if (!Objects.equals(this.gastos_importacion, other.gastos_importacion)) {
            return false;
        }
        if (!Objects.equals(this.attribute7, other.attribute7)) {
            return false;
        }
        if (!Objects.equals(this.id_uen, other.id_uen)) {
            return false;
        }
        if (!Objects.equals(this.ext, other.ext)) {
            return false;
        }
        if (!Objects.equals(this.mejor_opcion, other.mejor_opcion)) {
            return false;
        }
        if (!Objects.equals(this.gasto, other.gasto)) {
            return false;
        }
        if (!Objects.equals(this.linea_prov, other.linea_prov)) {
            return false;
        }
        return true;
    }*/

    @Override
    public String toString() {
        return "SupplierSelSPX{" + "id_rfq=" + id_rfq + ", id_proveedor=" + id_proveedor + ", descripcion_producto=" + descripcion_producto + ", num_cotizacion=" + num_cotizacion + ", id_sucursal_proveedor=" + id_sucursal_proveedor + ", nombre_proveedor=" + nombre_proveedor + ", precio_cotizado=" + precio_cotizado + ", lead_time=" + lead_time + ", monto=" + monto + ", moneda=" + moneda + ", comentario=" + comentario + ", recomendada=" + recomendada + ", gastos_importacion=" + gastos_importacion + ", attribute7=" + attribute7 + ", id_uen=" + id_uen + ", ext=" + ext + ", mejor_opcion=" + mejor_opcion + ", gasto=" + gasto + ", linea_prov=" + linea_prov + '}';
    }    
}
