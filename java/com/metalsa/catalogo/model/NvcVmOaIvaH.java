package com.metalsa.catalogo.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
<<<<<<< HEAD
=======
import javax.persistence.EntityManager;
>>>>>>> mexico
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juliocisneros
 */
@Entity
@Table(name = "NVC_VM_OA_IVA_H", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcVmOaIvaH.findAll", query = "SELECT n FROM NvcVmOaIvaH n")
    ,
<<<<<<< HEAD
    @NamedQuery(name = "NvcVmOaIvaH.getIvasByIdUen", query = "SELECT n FROM NvcVmOaIvaH n WHERE n.nvcVmOaIvaHPK.idUen = :idUen and n.active = 1")
=======
    @NamedQuery(name = "NvcVmOaIvaH.getIvasByIdUen", query = "SELECT n FROM NvcVmOaIvaH n WHERE n.nvcVmOaIvaHPK.idUen = :idUen and n.active = 1"),
    @NamedQuery(name = "NvcVmOaIvaH.findByTaxId", query = "SELECT n FROM NvcVmOaIvaH n WHERE n.taxId = :taxId")
>>>>>>> mexico
})
public class NvcVmOaIvaH implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private NvcVmOaIvaHPK nvcVmOaIvaHPK;
    @Column(name = "TAX_ID")
    private BigInteger taxId;
    @Column(name = "ACTIVE")
    private Integer active;

    public NvcVmOaIvaH() {
    }

    public NvcVmOaIvaHPK getNvcVmOaIvaHPK() {
        return nvcVmOaIvaHPK;
    }

    public void setNvcVmOaIvaHPK(NvcVmOaIvaHPK nvcVmOaIvaHPK) {
        this.nvcVmOaIvaHPK = nvcVmOaIvaHPK;
    }

    public BigInteger getTaxId() {
        return taxId;
    }

    public void setTaxId(BigInteger taxId) {
        this.taxId = taxId;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
<<<<<<< HEAD
=======
    
    public static NvcVmOaIvaH byTaxId(BigInteger taxId, EntityManager em) {
        return em.createNamedQuery("NvcVmOaIvaH.findByTaxId", NvcVmOaIvaH.class)
                .setParameter("taxId", taxId)
                .getSingleResult();
    }
>>>>>>> mexico

}
