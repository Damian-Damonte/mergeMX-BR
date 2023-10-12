/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.metalsa.core.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "NVC_TBL_OA_CC_H")
@XmlRootElement
@NamedQueries({
    @NamedQuery(
            name = "NvcTblOaCcH.findByPK",
            query = "SELECT n FROM NvcTblOaCcH n WHERE n.nvcTblOaCcHPK.idUen = ?1 AND n.nvcTblOaCcHPK.idCc = ?2 AND n.active = 1 ORDER BY n.codigoCc"
    )
})
public class NvcTblOaCcH implements Serializable {
    @Size(max = 450)
    @Column(name = "CODIGO_CC", length = 450)
    private String codigoCc;
    @Size(max = 720)
    @Column(name = "NOMBRE_CC", length = 720)
    private String nombreCc;
    @Column(name = "ACCESO_RESTRINGIDO")
    private Character accesoRestringido;
    @Size(max = 12)
    @Column(name = "LENGUAJE", length = 12)
    private String lenguaje;
    @Column(name = "ACTIVE")
    private BigInteger active;
    @Transient
    private String nombreIdioma;
    @EmbeddedId
    protected NvcTblOaCcHPK nvcTblOaCcHPK;
    
    @Column(name = "ID_CC", insertable=false, updatable = false)
    private BigInteger idCentroCosto;
    
    @Column(name = "ID_UEN", insertable=false, updatable = false)
    private BigInteger idUen;
    
    /*public static NvcTblOaCcH byId(Integer idUen, Integer idCentroCosto, EntityManager em) {
        return em.createNamedQuery("NvcTblOaCcH.findByPK", NvcTblOaCcH.class)
                .setParameter("1", idUen)
                .setParameter("2", idCentroCosto)
                .getSingleResult();
    }*/
    
    public void setIdCentroCosto(BigInteger id) {
        this.idCentroCosto = id;
    }
    
    public BigInteger getIdCentroCosto() {
        return this.idCentroCosto;
    }
    
    public BigInteger getIdUen() {
        return this.idUen;
    }
    
    public void setIdUen(BigInteger id) {
        this.idUen = id;
    }
    
    public String getNombreCc() {
        return this.nombreCc;
    }
    
    public void setNombreCc(String nombre) {
        this.nombreCc = nombre;
    }
}
