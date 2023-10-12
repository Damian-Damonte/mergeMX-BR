package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;

/**
 *
 * @author APOOD9272
 */
@Entity
@NamedStoredProcedureQuery(
        name = "getGastosAdicionales",
        resultClasses = GastoAdicional.class,
        procedureName = "nvc_pkg_aprobacion_spx.get_gasto_adicional",
        parameters = {
                @StoredProcedureParameter(mode=REF_CURSOR, name="cursor_out", type=void.class),
                @StoredProcedureParameter(mode=IN, name="p_id_requisicion", type=Integer.class),
                @StoredProcedureParameter(mode=IN, name="p_id_partida", type=Integer.class),
                @StoredProcedureParameter(mode=IN, name="p_id_idioma", type=String.class),
                @StoredProcedureParameter(mode=IN, name="p_aprobador", type=String.class)
        }
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GastoAdicional implements Serializable{

    @Id
    @Column(name="id_gasto_adicional")
    private Integer idGastoAdicional; 

    @Column(name="id_requisicion")
    private Integer idRequisicion; 

    @Column(name="id_linea")
    private Integer idPartida; 

    @Column(name="id_clasificacion")
    private Integer idClasificacion; 

    @Column(name="clasificacion")
    private String clasificacion; 

    @Column(name="cantidad")
    private double cantidad; 

    @Column(name="id_unidad_de_medida")
    private String idUDM; 

    @Column(name="udm")
    private String UDM; 

    @Column(name="detalle_gasto")
    private String DetalleGasto; 

    @Column(name="precio_unitario")
    private double precioUnitario; 

    @Column(name="id_moneda")
    private String idMoneda; 

    @Column(name="tiempo_entrega")
    private Integer tiempoEntrega; 

    @Column(name="id_categoria")
    private Integer idCategoria; 

    @Column(name="categoria")
    private String Categoria; 

    @Column(name="id_familia")
    private Integer idfamilia; 
    
    @Column(name="familia")
    private String familia; 

    @Column(name="id_subfamilia")
    private Integer idSubfamilia; 

    @Column(name="subfamilia")
    private String Subfamilia; 
    
    @Column(name="distribucion")
    private String distribucion; 

    /**
     * @return the idGastoAdicional
     */
    public Integer getIdGastoAdicional() {
        return idGastoAdicional;
    }

    /**
     * @param idGastoAdicional the idGastoAdicional to set
     */
    public void setIdGastoAdicional(Integer idGastoAdicional) {
        this.idGastoAdicional = idGastoAdicional;
    }

    /**
     * @return the idRequisicion
     */
    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    /**
     * @param idRequisicion the idRequisicion to set
     */
    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    /**
     * @return the idPartida
     */
    public Integer getIdPartida() {
        return idPartida;
    }

    /**
     * @param idPartida the idPartida to set
     */
    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    /**
     * @return the idClasificacion
     */
    public Integer getIdClasificacion() {
        return idClasificacion;
    }

    /**
     * @param idClasificacion the idClasificacion to set
     */
    public void setIdClasificacion(Integer idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    /**
     * @return the clasificacion
     */
    public String getClasificacion() {
        return clasificacion;
    }

    /**
     * @param clasificacion the clasificacion to set
     */
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * @return the cantidad
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the idUDM
     */
    public String getIdUDM() {
        return idUDM;
    }

    /**
     * @param idUDM the idUDM to set
     */
    public void setIdUDM(String idUDM) {
        this.idUDM = idUDM;
    }

    /**
     * @return the UDM
     */
    public String getUDM() {
        return UDM;
    }

    /**
     * @param UDM the UDM to set
     */
    public void setUDM(String UDM) {
        this.UDM = UDM;
    }

    /**
     * @return the DetalleGasto
     */
    public String getDetalleGasto() {
        return DetalleGasto;
    }

    /**
     * @param DetalleGasto the DetalleGasto to set
     */
    public void setDetalleGasto(String DetalleGasto) {
        this.DetalleGasto = DetalleGasto;
    }

    /**
     * @return the precioUnitario
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @param precioUnitario the precioUnitario to set
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @return the idMoneda
     */
    public String getIdMoneda() {
        return idMoneda;
    }

    /**
     * @param idMoneda the idMoneda to set
     */
    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    /**
     * @return the tiempoEntrega
     */
    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    /**
     * @param tiempoEntrega the tiempoEntrega to set
     */
    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    /**
     * @return the idCategoria
     */
    public Integer getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return the Categoria
     */
    public String getCategoria() {
        return Categoria;
    }

    /**
     * @param Categoria the Categoria to set
     */
    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    /**
     * @return the idfamilia
     */
    public Integer getIdfamilia() {
        return idfamilia;
    }

    /**
     * @param idfamilia the idfamilia to set
     */
    public void setIdfamilia(Integer idfamilia) {
        this.idfamilia = idfamilia;
    }

    /**
     * @return the familia
     */
    public String getFamilia() {
        return familia;
    }

    /**
     * @param familia the familia to set
     */
    public void setFamilia(String familia) {
        this.familia = familia;
    }

    /**
     * @return the idSubfamilia
     */
    public Integer getIdSubfamilia() {
        return idSubfamilia;
    }

    /**
     * @param idSubfamilia the idSubfamilia to set
     */
    public void setIdSubfamilia(Integer idSubfamilia) {
        this.idSubfamilia = idSubfamilia;
    }

    /**
     * @return the Subfamilia
     */
    public String getSubfamilia() {
        return Subfamilia;
    }

    /**
     * @param Subfamilia the Subfamilia to set
     */
    public void setSubfamilia(String Subfamilia) {
        this.Subfamilia = Subfamilia;
    }
//

    /**
     * @return the distribucion
     */
    public String getDistribucion() {
        return distribucion;
    }

    /**
     * @param distribucion the distribucion to set
     */
    public void setDistribucion(String distribucion) {
        this.distribucion = distribucion;
    }



}
