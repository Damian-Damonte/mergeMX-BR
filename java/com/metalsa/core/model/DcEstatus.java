/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.math.BigInteger;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "DC_ESTATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(
            name = "DcEstatus.findByDescEstatus",
            query = "SELECT d FROM DcEstatus d WHERE d.descEstatus = ?1"
    )
})

public class DcEstatus implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SC_ID", nullable = false, precision = 22)
    private BigInteger id;
    
    @Column(name = "DESC_ESTATUS")
    private String descEstatus;
    
    @Column(name = "ACTIVE")
    private String active;

    public BigInteger getId() {
        return this.id;
    }
    
    public String getDescEstatus() {
        return this.descEstatus;
    }
    
    public void setDescEstatus(String description) {
        this.descEstatus = description;
    }
    
    public String getActive() {
        return this.active;
    }
    
    public void setActive(String active) {
        this.active = active;
    }
}
