/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.catalogo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.EntityManager;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "ICOM_ACC_CATEGORIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(
            name = "IcomAccCategories.findByAccCatId",
            query = "SELECT i FROM IcomAccCategories i WHERE i.id = :accCatId"
    )
})
public class IcomAccCategories implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACC_CAT_ID", nullable = false, precision = 22)
    private BigDecimal id;
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public static IcomAccCategories byId(BigDecimal id, EntityManager em) {
        return em.createNamedQuery("IcomAccCategories.findByAccCatId", IcomAccCategories.class)
                .setParameter("accCatId", id)
                .getSingleResult();
    }
}
