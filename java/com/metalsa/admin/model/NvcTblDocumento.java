/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.admin.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Data
@Entity
@Table(name = "NVC_TBL_DOCUMENTO")
public class NvcTblDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_NVC_TBL_DOCUMENTO_GEN", sequenceName = "SEQ_NVC_TBL_DOCUMENTO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_NVC_TBL_DOCUMENTO_GEN")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DOCUMENTO")
    private Integer idDocumento;
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @Column(name = "NOMBRE_ARCHIVO_FTP")
    private String nombreArchivoFtp;
    @Column(name = "EXTENSION_ARCHIVO")
    private String extensionArchivo;
    @Column(name = "TAMANIO_ARCHIVO")
    private Integer tamanioArchivo;
    @Column(name = "UBICACION_FTP")
    private String ubicacionFtp;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    @Column(name = "ACTIVO")
    private Character activo;
    @Column(name="PROVEEDOR_DIRIGIDO_ID")
    private Long provDirId;
    @Column(name = "ID_UEN")
    private Integer idUen;
        
    public Long getProvDirId() {
        return this.provDirId;
    }
    
    public Integer getIdUen() {
        return this.idUen;
    }

}
