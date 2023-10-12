package com.metalsa.requisicion.pojo;

import com.metalsa.requisicion.model.NvcTblDocumento;
import java.io.Serializable;

/**
 *
 * @author APOMS7376
 */
public class NvcTblDocumentoPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idDocumento;
    private String nombreArchivo;
    private String nombreArchivoFtp;
    private String extensionArchivo;
    private Integer tamanioArchivo;
    private String ubicacionFtp;
    private byte[] data;
    private String nombreCompleto;

    public NvcTblDocumentoPojo() {
    }

    public NvcTblDocumentoPojo(Integer idDocumento, String nombreArchivo, String nombreArchivoFtp, String extensionArchivo,
            Integer tamanioArchivo, String ubicacionFtp) {
        this.idDocumento = idDocumento;
        this.nombreArchivo = nombreArchivo;
        this.nombreArchivoFtp = nombreArchivoFtp;
        this.extensionArchivo = extensionArchivo;
        this.tamanioArchivo = tamanioArchivo;
        this.ubicacionFtp = ubicacionFtp;
    }
    
    public NvcTblDocumentoPojo(NvcTblDocumento documento) {
        this.idDocumento = documento.getIdDocumento();
        this.nombreArchivo = documento.getNombreArchivo();
        this.nombreArchivoFtp = documento.getNombreArchivoFtp();
        this.extensionArchivo = documento.getExtensionArchivo();
        this.tamanioArchivo = documento.getTamanioArchivo();
        this.ubicacionFtp = documento.getUbicacionFtp();
    }
    
    

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
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

    public String getExtensionArchivo() {
        return extensionArchivo;
    }

    public void setExtensionArchivo(String extensionArchivo) {
        this.extensionArchivo = extensionArchivo;
    }

    public Integer getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setTamanioArchivo(Integer tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    public String getUbicacionFtp() {
        return ubicacionFtp;
    }

    public void setUbicacionFtp(String ubicacionFtp) {
        this.ubicacionFtp = ubicacionFtp;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getNombreCompleto() {
        this.nombreCompleto = this.nombreArchivo + this.extensionArchivo;
        return this.nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

}
