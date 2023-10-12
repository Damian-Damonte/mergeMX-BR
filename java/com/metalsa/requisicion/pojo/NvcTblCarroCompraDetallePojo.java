/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.pojo;

import com.metalsa.requisicion.model.NvcTblOaCombinaciones;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class NvcTblCarroCompraDetallePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCarroCompraDetalle;
    private Integer idCarroCompra;
    private Integer idTipoAsignacion;
    private Integer idProyecto;
    private Integer idCentroCosto;
    private Date fechaCreacion;
    private String strFechaCreacion;
    private Date fechaActualizacion;
    private String strFechaActualizacion;
    private String usuarioCreacion;
    private String usuarioActualizacion;
    private Integer idTarea;
    private String tipoGasto;
    private Integer idCuenta;
    private String comprador;
    private Integer idLocalizacion;
    private String segmento1;
    private String segmento2;
    private String segmento3;
    private String segmento4;
    private String segmento5;
    private String segmento6;
    private String segmento7;
    private String segmento8;
    private Double porcentaje;
    private String compradorInicial;
    private Integer idRequisicion;
    private Integer idPartida;
    private boolean segmentoProducto;
    private Double monto;
    private boolean segProdMultiple;
    private String siguienteAprobador;
    
    /*Multi cc Edicion de requisiciones*/
    List<NvcTblOaCombinaciones> listCuentas;

    public NvcTblCarroCompraDetallePojo() {
    }

    public NvcTblCarroCompraDetallePojo(Integer idCarroCompraDetalle,
            Integer idCarroCompra,
            Integer idTipoAsignacion,
            Integer idProyecto,
            Integer idCentroCosto,
            Date fechaCreacion,
            Date fechaActualizacion,
            String usuarioCreacion,
            String usuarioActualizacion,
            Integer idTarea,
            String tipoGasto,
            Integer idCuenta,
            String comprador,
            Integer idLocalizacion,
            String segmento1,
            String segmento2,
            String segmento3,
            String segmento4,
            String segmento5,
            String segmento6,
            String segmento7,
            String segmento8,
            Double porcentaje,
            Integer segmentoProducto,
            Double monto) {
        try {
            this.idCarroCompraDetalle = idCarroCompraDetalle;
            this.idCarroCompra = idCarroCompra;
            this.idTipoAsignacion = idTipoAsignacion;
            this.idProyecto = idProyecto;
            this.idCentroCosto = idCentroCosto;
            this.fechaCreacion = fechaCreacion;
            this.fechaActualizacion = fechaActualizacion;
            this.usuarioCreacion = usuarioCreacion;
            this.usuarioActualizacion = usuarioActualizacion;
            this.idTarea = idTarea;
            this.tipoGasto = tipoGasto;
            this.idCuenta = idCuenta;
            this.comprador = comprador;
            this.idLocalizacion = idLocalizacion;
            this.segmento1 = segmento1;
            this.segmento2 = segmento2;
            this.segmento3 = segmento3;
            this.segmento4 = segmento4;
            this.segmento5 = segmento5;
            this.segmento6 = segmento6;
            this.segmento7 = segmento7;
            this.segmento8 = segmento8;
            this.porcentaje = porcentaje==null?0.0:porcentaje*100;
            this.segmentoProducto = segmentoProducto == null ? false : segmentoProducto == 1;
            this.monto=monto;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public NvcTblCarroCompraDetallePojo(BigDecimal idCarroCompraDetalle, BigInteger idProyecto, BigInteger idCentroCosto, BigInteger idCarroCompra) {
        this.idCarroCompraDetalle = idCarroCompraDetalle == null ? 0 : idCarroCompraDetalle.intValue();
        this.idProyecto = idProyecto == null ? 0 : idProyecto.intValue();
        this.idCentroCosto = idCentroCosto == null ? 0 : idCentroCosto.intValue();
        this.idCarroCompra = idCarroCompra == null ? 0 : idCarroCompra.intValue();
    }

    public Integer getIdCarroCompraDetalle() {
        return idCarroCompraDetalle;
    }

    public void setIdCarroCompraDetalle(Integer idCarroCompraDetalle) {
        this.idCarroCompraDetalle = idCarroCompraDetalle;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdCentroCosto() {
        return idCentroCosto;
    }

    public void setIdCentroCosto(Integer idCentroCosto) {
        this.idCentroCosto = idCentroCosto;
    }

    public Integer getIdCarroCompra() {
        return idCarroCompra;
    }

    public void setIdCarroCompra(Integer idCarroCompra) {
        this.idCarroCompra = idCarroCompra;
    }

    /**
     * @return the comprador
     */
    public String getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    /**
     * @return the idCuenta
     */
    public Integer getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param idCuenta the idCuenta to set
     */
    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * @return the segmento1
     */
    public String getSegmento1() {
        return segmento1;
    }

    /**
     * @param segmento1 the segmento1 to set
     */
    public void setSegmento1(String segmento1) {
        this.segmento1 = segmento1;
    }

    /**
     * @return the segmento2
     */
    public String getSegmento2() {
        return segmento2;
    }

    /**
     * @param segmento2 the segmento2 to set
     */
    public void setSegmento2(String segmento2) {
        this.segmento2 = segmento2;
    }

    /**
     * @return the segmento3
     */
    public String getSegmento3() {
        return segmento3;
    }

    /**
     * @param segmento3 the segmento3 to set
     */
    public void setSegmento3(String segmento3) {
        this.segmento3 = segmento3;
    }

    /**
     * @return the segmento4
     */
    public String getSegmento4() {
        return segmento4;
    }

    /**
     * @param segmento4 the segmento4 to set
     */
    public void setSegmento4(String segmento4) {
        this.segmento4 = segmento4;
    }

    /**
     * @return the segmento5
     */
    public String getSegmento5() {
        return segmento5;
    }

    /**
     * @param segmento5 the segmento5 to set
     */
    public void setSegmento5(String segmento5) {
        this.segmento5 = segmento5;
    }

    /**
     * @return the segmento6
     */
    public String getSegmento6() {
        return segmento6;
    }

    /**
     * @param segmento6 the segmento6 to set
     */
    public void setSegmento6(String segmento6) {
        this.segmento6 = segmento6;
    }

    /**
     * @return the segmento7
     */
    public String getSegmento7() {
        return segmento7;
    }

    /**
     * @param segmento7 the segmento7 to set
     */
    public void setSegmento7(String segmento7) {
        this.segmento7 = segmento7;
    }

    /**
     * @return the segmento8
     */
    public String getSegmento8() {
        return segmento8;
    }

    /**
     * @param segmento8 the segmento8 to set
     */
    public void setSegmento8(String segmento8) {
        this.segmento8 = segmento8;
    }

    /**
     * @return the idTarea
     */
    public Integer getIdTarea() {
        return idTarea;
    }

    /**
     * @param idTarea the idTarea to set
     */
    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    /**
     * @return the tipoGasto
     */
    public String getTipoGasto() {
        return tipoGasto;
    }

    /**
     * @param tipoGasto the tipoGasto to set
     */
    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    /**
     * @return the idTipoAsignacion
     */
    public Integer getIdTipoAsignacion() {
        return idTipoAsignacion;
    }

    /**
     * @param idTipoAsignacion the idTipoAsignacion to set
     */
    public void setIdTipoAsignacion(Integer idTipoAsignacion) {
        this.idTipoAsignacion = idTipoAsignacion;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaActualizacion
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the usuarioCreacion
     */
    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    /**
     * @param usuarioCreacion the usuarioCreacion to set
     */
    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    /**
     * @return the usuarioActualizacion
     */
    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    /**
     * @param usuarioActualizacion the usuarioActualizacion to set
     */
    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    /**
     * @return the idLocalizacion
     */
    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    /**
     * @param idLocalizacion the idLocalizacion to set
     */
    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    /**
     * @return the porcentaje
     */
    public Double getPorcentaje() {
        return porcentaje;
    }
    
    public Double getPorcentaje(Double defaultValue) {
        return porcentaje != null ? porcentaje : defaultValue;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setStrFechaCreacion(String strFechaCreacion) {
        this.strFechaCreacion = strFechaCreacion;
    }

    public void setStrFechaActualizacion(String strFechaActualizacion) {
        this.strFechaActualizacion = strFechaActualizacion;
    }

    public String getStrFechaCreacion() {
        return strFechaCreacion;
    }

    public String getStrFechaActualizacion() {
        return strFechaActualizacion;
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public String getCompradorInicial() {
        return compradorInicial;
    }

    public void setCompradorInicial(String compradorInicial) {
        this.compradorInicial = compradorInicial;
    }

    public boolean isSegmentoProducto() {
        return segmentoProducto;
    }

    public void setSegmentoProducto(boolean segmentoProducto) {
        this.segmentoProducto = segmentoProducto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public boolean isSegProdMultiple() {
        return segProdMultiple;
    }

    public void setSegProdMultiple(boolean segProdMultiple) {
        this.segProdMultiple = segProdMultiple;
    }

    public List<NvcTblOaCombinaciones> getListCuentas() {
        return listCuentas;
    }

    public void setListCuentas(List<NvcTblOaCombinaciones> listCuentas) {
        this.listCuentas = listCuentas;
    }

    public String getSiguienteAprobador() {
        return siguienteAprobador;
    }

    public void setSiguienteAprobador(String siguienteAprobador) {
        this.siguienteAprobador = siguienteAprobador;
    }

    public NvcTblCarroCompraDetallePojo(Integer idCarroCompraDetalle, Integer idCarroCompra, Integer idTipoAsignacion, Integer idProyecto, Integer idCentroCosto, String usuarioCreacion, Integer idTarea, String tipoGasto, Integer idCuenta, Integer idLocalizacion, String segmento1, String segmento2, String segmento3, String segmento4, String segmento5, String segmento6, String segmento7, String segmento8, Double porcentaje, String compradorInicial, Integer idRequisicion, Integer idPartida, boolean segmentoProducto, Double monto, boolean segProdMultiple) {
        this.idCarroCompraDetalle = idCarroCompraDetalle;
        this.idCarroCompra = idCarroCompra;
        this.idTipoAsignacion = idTipoAsignacion;
        this.idProyecto = idProyecto;
        this.idCentroCosto = idCentroCosto;
        this.usuarioCreacion = usuarioCreacion;
        this.idTarea = idTarea;
        this.tipoGasto = tipoGasto;
        this.idCuenta = idCuenta;
        this.idLocalizacion = idLocalizacion;
        this.segmento1 = segmento1;
        this.segmento2 = segmento2;
        this.segmento3 = segmento3;
        this.segmento4 = segmento4;
        this.segmento5 = segmento5;
        this.segmento6 = segmento6;
        this.segmento7 = segmento7;
        this.segmento8 = segmento8;
        this.porcentaje = porcentaje;
        this.compradorInicial = compradorInicial;
        this.idRequisicion = idRequisicion;
        this.idPartida = idPartida;
        this.segmentoProducto = segmentoProducto;
        this.monto = monto;
        this.segProdMultiple = segProdMultiple;
    }

    @Override
    public String toString() {
        return "NvcTblCarroCompraDetallePojo{" +
                "idCarroCompraDetalle=" + idCarroCompraDetalle +
                ", idCarroCompra=" + idCarroCompra +
                ", idTipoAsignacion=" + idTipoAsignacion +
                ", idProyecto=" + idProyecto +
                ", idCentroCosto=" + idCentroCosto +
                ", usuarioCreacion='" + usuarioCreacion + '\'' +
                ", idTarea=" + idTarea +
                ", tipoGasto='" + tipoGasto + '\'' +
                ", idCuenta=" + idCuenta +
                ", idLocalizacion=" + idLocalizacion +
                ", segmento1='" + segmento1 + '\'' +
                ", segmento2='" + segmento2 + '\'' +
                ", segmento3='" + segmento3 + '\'' +
                ", segmento4='" + segmento4 + '\'' +
                ", segmento5='" + segmento5 + '\'' +
                ", segmento6='" + segmento6 + '\'' +
                ", segmento7='" + segmento7 + '\'' +
                ", segmento8='" + segmento8 + '\'' +
                ", idRequisicion=" + idRequisicion +
                ", idPartida=" + idPartida +
                ", segmentoProducto=" + segmentoProducto +
                ", segProdMultiple=" + segProdMultiple +
                ", siguienteAprobador='" + siguienteAprobador + '\'' +
                ", listCuentas='" + listCuentas + '\'' +
                '}';
    }
}
