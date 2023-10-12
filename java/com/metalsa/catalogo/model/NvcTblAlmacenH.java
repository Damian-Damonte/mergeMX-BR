/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.catalogo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "NVC_TBL_ALMACEN_H")
@XmlRootElement
@NamedQueries({
    @NamedQuery(
            name = "NvcTblAlmacenH.findByIdAlmacen",
            query = "SELECT n FROM NvcTblAlmacenH n WHERE n.idAlmacen = :idAlmacen"
    )
})
public class NvcTblAlmacenH implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ALMACEN", nullable = false, precision = 22)
    private BigDecimal idAlmacen;
    
    @Column(name = "NAME")
    private String name;
    
    public static NvcTblAlmacenH getOneByIdAlmacen(Integer idAlmacen, EntityManager em) {
        return em.createNamedQuery("NvcTblAlmacenH.findByIdAlmacen", NvcTblAlmacenH.class)
                .setParameter("idAlmacen", BigDecimal.valueOf(idAlmacen))
                .getSingleResult();
    }
    
    public BigDecimal getIdAlmance() {
        return this.idAlmacen;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

}
