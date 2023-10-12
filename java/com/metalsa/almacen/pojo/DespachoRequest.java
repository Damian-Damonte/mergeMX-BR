package com.metalsa.almacen.pojo;

import com.metalsa.almacen.model.DetalleDespacho;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.recibos.model.Requisitor;
import java.util.List;

/**
 *
 * @author APOMR10051
 */
public class DespachoRequest {

    private String idUsuario;
    private String idUsuarioRecibe;
    private String idIdioma;
    private List<NvcTblOrganizacionesH> uens;
    private Integer idItem;
    private Integer idRequisicion;
    private String fechaReqIni;
    private String fechaReqFin;
    private List<Almacen> almacenes;
    private String descripcion;
    private List<Requisitor> requisitores;
    private String fechaApIni;
    private String fechaApFin;
    private String requisiciones;
    private List<DetalleDespacho> requisPorDespachar;
    private List<DetalleDespacho> lineasPorDespachar;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdUsuarioRecibe() {
        return idUsuarioRecibe;
    }

    public void setIdUsuarioRecibe(String idUsuarioRecibe) {
        this.idUsuarioRecibe = idUsuarioRecibe;
    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public List<NvcTblOrganizacionesH> getUens() {
        return uens;
    }

    public void setUens(List<NvcTblOrganizacionesH> uens) {
        this.uens = uens;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public String getFechaReqIni() {
        return fechaReqIni;
    }

    public void setFechaReqIni(String fechaReqIni) {
        this.fechaReqIni = fechaReqIni;
    }

    public String getFechaReqFin() {
        return fechaReqFin;
    }

    public void setFechaReqFin(String fechaReqFin) {
        this.fechaReqFin = fechaReqFin;
    }

    public List<Almacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(List<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Requisitor> getRequisitores() {
        return requisitores;
    }

    public void setRequisitores(List<Requisitor> requisitores) {
        this.requisitores = requisitores;
    }

    public String getFechaApIni() {
        return fechaApIni;
    }

    public void setFechaApIni(String fechaApIni) {
        this.fechaApIni = fechaApIni;
    }

    public String getFechaApFin() {
        return fechaApFin;
    }

    public void setFechaApFin(String fechaApFin) {
        this.fechaApFin = fechaApFin;
    }

    public String getRequisiciones() {
        return requisiciones;
    }

    public void setRequisiciones(String requisiciones) {
        this.requisiciones = requisiciones;
    }

    public List<DetalleDespacho> getRequisPorDespachar() {
        return requisPorDespachar;
    }

    public void setRequisPorDespachar(List<DetalleDespacho> requisPorDespachar) {
        this.requisPorDespachar = requisPorDespachar;
    }

    public List<DetalleDespacho> getLineasPorDespachar() {
        return lineasPorDespachar;
    }

    public void setLineasPorDespachar(List<DetalleDespacho> lineasPorDespachar) {
        this.lineasPorDespachar = lineasPorDespachar;
    }
}
