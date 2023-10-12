/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "NVC_TBL_FAMILIAS")
@XmlRootElement
/*@NamedQueries({
    @NamedQuery(
            name = "NvcTblFamilias.findByIdFamilia",
            query = "SELECT n FROM NvcTblFamilias n WHERE n.idFamilia = ?1"
    )
})*/
public class NvcTblFamilias implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FAMILIA", nullable = false, precision = 22)
    private BigDecimal id;
    
    @Column(name = "NIVEL")
    private BigInteger nivel;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public BigInteger getNivel() {
        return nivel;
    }

    public void setNivel(BigInteger nivel) {
        this.nivel = nivel;
    }
}
