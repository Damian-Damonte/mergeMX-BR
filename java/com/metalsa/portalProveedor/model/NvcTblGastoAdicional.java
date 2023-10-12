/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author mlopez
 */
@Data
@Entity
@Table(name = "NVC_TBL_GASTO_ADICIONAL")
public class NvcTblGastoAdicional implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGastoAdicional")
    @SequenceGenerator(name = "seqGastoAdicional", sequenceName = "SEQ_GASTO_ADICIONAL", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_GASTO_ADICIONAL")
    private Integer idGastoAdicional;

    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;

    @Size(max = 18)
    @Column(name = "ID_MONEDA")
    private String idMoneda;

    @Size(max = 200)
    @Column(name = "DETALLE_GASTO")
    private String detalleGasto;

    @Column(name = "PRECIO_UNITARIO")
    private BigDecimal precioUnitario;
    
    private String idUnidadDeMedida;

    @Column(name = "TIEMPO_ENTREGA")
    private Integer tiempoEntrega;

    @Column(name = "ID_CLASIFICACION")
    private Integer idClasificacion;
   
    @JoinColumn(name = "ID_REQ_LINEA_PROV", referencedColumnName = "ID_REQ_LINEA_PROV")
    @ManyToOne(optional = false)
    private NvcTblReqLineaProv idReqLineaProv;
    
    @Transient
    private boolean eliminar;

}
