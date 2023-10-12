/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "NVC_TBL_INCOTERM")
public class NvcTblIncoterm implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_incoterm", nullable = false, precision = 0, scale = -127)
    private BigDecimal idIncoterm;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column( nullable = false, length = 100)
    private String descripcion;
    
    private BigInteger active;
    
    /*@OneToMany(mappedBy = "idIncoterm", fetch = FetchType.LAZY)
    private List<NvcTblRfq> nvcTblRfqList;*/
    
    /*@OneToMany(mappedBy = "idIncoterm")
    private Collection<NvcTblCotizaciones> nvcTblCotizacionesCollection;*/
    
    private String idIdioma;
    
}
