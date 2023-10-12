/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author mlopez
 */
@Data
@Entity
@Table(name = "NVC_TBL_DOCUMENTOS_RFQ")
public class NvcTblDocumentosRfq implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "nvcSecDocsRfq")
    @SequenceGenerator(name = "nvcSecDocsRfq",
            sequenceName = "NVC_SEQ_DOCS_RFQ",
            allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer idDocumento;
    
    @Size(max = 3)
    @Column(length = 3)
    private String origen;
    
    @Lob
    private byte[] documento;
    
    private Integer idProveedor;
    
    private Integer usuarioModifica;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModifica;
    
    @Size(max = 300)
    @Column(length = 300)
    private String rutaRaiz;

    private Integer consecutivo;
    
    private Character enviado;
    
    /*@JoinColumn(name = "LLAVE_ID", referencedColumnName = "LLAVE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblRfqLinea rfqLinea;*/
    
    @Size(max = 150)
    @Column(length = 150)
    private String descripcion;
    
    @Column(length = 150)
    private String nombre;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 100)
    @Column(length = 100)
    private String mime;

    private BigInteger tamano;
    
    @Size(max = 300)
    @Column(length = 300)
    private String ruta;
    
    @Size(max = 100)
    @Column(length = 100)
    private String nombreFormato;
    
    private Integer usuario;
}
