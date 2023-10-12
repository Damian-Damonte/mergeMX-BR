/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "NVC_TBL_RFQ")
public class NvcTblRfq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRfq")
    @SequenceGenerator(name = "seqRfq", sequenceName = "SEQ_RFQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RFQ")
    private Integer idRfq;

    @Column(name = "ESTATUS")
    private Integer estatus;

    @Column(name = "FECHA_EDICION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEdicion;

    @Column(name = "ID_USUARIO_EDICION")
    private String idUsuarioEdicion;

    @Column(name = "ID_UEN")
    private Integer idUen;

    @Column(name = "ID_MEDIO")
    private Integer idMedio;

    @Column(name = "ID_TERM")
    private Integer idTerm;

    /*@ManyToOne()
    @JoinColumn(name = "ID_INCOTERM")
    private NvcTblIncoterm idIncoterm;*/
    
    /*@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRfq", fetch = FetchType.LAZY)
    private List<NvcTblRfqProv> nvcTblRfqProvList;*/

    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Size(max = 50)
    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "PESO_ARCHIVO")
    private BigDecimal pesoArchivo;

    @Column(name = "INICIO_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioVigencia;

    @Column(name = "FIN_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finVigencia;

    @Size(max = 100)
    @Column(name = "SHIP_TO")
    private String shipTo;

    @Size(max = 100)
    @Column(name = "BLL_TO")
    private String bllTo;
    
    @Column(name = "ENVIADO_PROVEEDOR")
    private Character enviadoProveedor;

    //@OrderBy("detalleDeRequisicion.detalleDeRequisicionPK.idRequisicion, detalleDeRequisicion.detalleDeRequisicionPK.idPartida ASC")
    /*@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRfq")
    private List<NvcTblRfqLinea> nvcTblRfqLineaList;*/
    
    @Column(name = "ID_USUARIO")
    private String idUsuario;
}
