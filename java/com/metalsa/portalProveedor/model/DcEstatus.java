/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author mlopez
 */
@Data
@Entity
@Table(name = "DC_ESTATUS")
public class DcEstatus implements Serializable{
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SC_ID", nullable = false, precision = 22)
    private BigDecimal scId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "DESC_ESTATUS", nullable = false, length = 80)
    private String descEstatus;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "DESC_ESP", nullable = false, length = 80)
    private String descEsp;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "DESC_ENG", nullable = false, length = 80)
    private String descIng;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "DESC_PORT", nullable = false, length = 80)
    private String descPort;
    
    @Size(max = 180)
    @Column(name = "ORIGEN_ESTATUS", length = 180)
    private String origenEstatus;
    
    @Size(max = 800)
    @Column(name = "DESCRIPCION", length = 800)
    private String descripcion;
    
    @Size(max = 50)
    @Column(name = "ACTIVE", length = 50)
    private String active;
}
