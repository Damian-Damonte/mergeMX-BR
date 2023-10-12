package com.metalsa.requisicion.pojo;

import java.util.Date;

/**
 *
 * @author APOMS7376
 */
public class NvcTblCatalogoItemDocPojo {

    private Integer activo;
    private String extensionArchivo;
    private Date fechaActualizacion;
    private Date fechaCreacion;
    private Integer idCatalogoItemDoc;
    private Integer idItem;
    private Integer idTipoDocumento;
    private String nombreArchivo;
    private String nombreArchivoFtp;
    private Integer posicion;
    private Double tamanioArchivo;
    private String urlFtp;
    private String usuarioActualizacion;
    private String usuarioCreacion;
    private String codigoProducto;
    private Integer idUen;
    private byte[] byteArray;

    public NvcTblCatalogoItemDocPojo() {
    }

    public NvcTblCatalogoItemDocPojo(Integer activo,
            String extensionArchivo,
            Date fechaActualizacion,
            Date fechaCreacion,
            Integer idCatalogoItemDoc,
            Integer idItem,
            String codigoProducto,
            Integer idUen,
            Integer idTipoDocumento,
            String nombreArchivo,
            String nombreArchivoFtp,
            Integer posicion,
            Double tamanioArchivo,
            String urlFtp,
            String usuarioActualizacion,
            String usuarioCreacion) {
        this.activo = activo;
        this.extensionArchivo = extensionArchivo;
        this.fechaActualizacion = fechaActualizacion;
        this.fechaCreacion = fechaCreacion;
        this.idCatalogoItemDoc = idCatalogoItemDoc;
        this.idItem = idItem;
        this.codigoProducto = codigoProducto;
        this.idUen = idUen;
        this.idTipoDocumento = idTipoDocumento;
        this.nombreArchivo = nombreArchivo;
        this.nombreArchivoFtp = nombreArchivoFtp;
        this.posicion = posicion;
        this.tamanioArchivo = tamanioArchivo;
        this.urlFtp = urlFtp;
        this.usuarioActualizacion = usuarioActualizacion;
        this.usuarioCreacion = usuarioCreacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getExtensionArchivo() {
        return extensionArchivo;
    }

    public void setExtensionArchivo(String extensionArchivo) {
        this.extensionArchivo = extensionArchivo;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdCatalogoItemDoc() {
        return idCatalogoItemDoc;
    }

    public void setIdCatalogoItemDoc(Integer idCatalogoItemDoc) {
        this.idCatalogoItemDoc = idCatalogoItemDoc;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivoFtp() {
        return nombreArchivoFtp;
    }

    public void setNombreArchivoFtp(String nombreArchivoFtp) {
        this.nombreArchivoFtp = nombreArchivoFtp;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Double getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setTamanioArchivo(Double tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    public String getUrlFtp() {
        return urlFtp;
    }

    public void setUrlFtp(String urlFtp) {
        this.urlFtp = urlFtp;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }
    
    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("\n")
                .append("activo=").append(activo == null ? "null" : activo).append(" ")
                .append("extensionArchivo=").append(extensionArchivo == null ? "null" : extensionArchivo).append(" ")
                .append("fechaActualizacion=").append(fechaActualizacion == null ? "null" : fechaActualizacion).append(" ")
                .append("fechaCreacion =").append(fechaCreacion == null ? "null" : fechaCreacion).append(" ")
                .append("idCatalogoItemDoc =").append(idCatalogoItemDoc == null ? "null" : idCatalogoItemDoc).append(" ")
                .append("idItem =").append(idItem == null ? "null" : idItem).append(" ")
                .append("idTipoDocumento=").append(idTipoDocumento == null ? "null" : idTipoDocumento).append(" ")
                .append("nombreArchivo=").append(nombreArchivo == null ? "null" : nombreArchivo).append(" ")
                .append("nombreArchivoFtp=").append(nombreArchivoFtp == null ? "null" : nombreArchivoFtp).append(" ")
                .append("posicion=").append(posicion == null ? "null" : posicion).append(" ")
                .append("tamanioArchivo=").append(tamanioArchivo == null ? "null" : tamanioArchivo).append(" ")
                .append("urlFtp=").append(urlFtp == null ? "null" : urlFtp).append(" ")
                .append("usuarioActualizacion=").append(usuarioActualizacion == null ? "null" : usuarioActualizacion).append(" ")
                .append("usuarioCreacion=").append(usuarioCreacion == null ? "null" : usuarioCreacion);
        return sb.toString();
    }

}
