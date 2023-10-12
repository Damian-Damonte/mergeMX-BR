package com.metalsa.aprobacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.metalsa.utils.Constants;

import lombok.Data;

@Data
@Entity
@NamedQueries({
    @NamedQuery(name = "NvcTblCatalogoItemDoc.getCatalogDocumentByIdItem",
            query = "SELECT d "            
            + "FROM NvcTblCatalogoItemDoc d "            
            + "WHERE d.activo = 1 "
            + " AND d.idItem = :idItem"            
            + " AND d.idTipoDocumento = 1"
            + " ORDER BY d.posicion ASC"),
})
public class NvcTblCatalogoItemDoc {
    
    @Id
    @Column(name="ID_CATALOGO_ITEM_DOC")
    private Long id;

    @Column(name="ID_ITEM")
    private Long idItem;

    @Column(name="ID_TIPO_DOCUMENTO")
    private Long idTipoDocumento;

    @Column(name="NOMBRE_ARCHIVO_FTP")
    private String nombreArchivoFtp;

    @Column(name="NOMBRE_ARCHIVO")
    private String nombreArchivo;

    @Column(name="EXTENSION_ARCHIVO")
    private String extensionArchivo;
    
    @Column(name="ACTIVO")
    private String activo;

    @Column(name="URL_FTP")
    private String urlFtp;

    @Column(name="POSICION")
    private Integer posicion;

    public String getFullUrl() {
        if(this.urlFtp == null || this.nombreArchivo.length() == 0){
            return Constants.URL_IMAGES_FTP_NOT_FOUND;
        }
        
        return Constants.URL_IMAGES_FTP + this.getUrlFtp().concat("large.").concat(this.getNombreArchivoFtp()).concat(this.getExtensionArchivo());
    }
}
