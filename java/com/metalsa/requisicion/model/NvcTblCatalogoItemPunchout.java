package com.metalsa.requisicion.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "NVC_TBL_CATALOGO_ITEM_PUNCHOUT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblCatalogoItemPunchout.findAll", query = "SELECT n FROM NvcTblCatalogoItemPunchout n")
    ,
    @NamedQuery(name = "NvcTblCatalogoItemPunchout.findByIdItemPunchout", query = "SELECT n FROM NvcTblCatalogoItemPunchout n WHERE n.nvcTblCatalogoItemPunchoutPK.idItemPunchout = :idItemPunchout")
    ,
    @NamedQuery(name = "NvcTblCatalogoItemPunchout.findByIdItem", query = "SELECT n FROM NvcTblCatalogoItemPunchout n WHERE n.nvcTblCatalogoItemPunchoutPK.idItem = :idItem")
})
public class NvcTblCatalogoItemPunchout implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NvcTblCatalogoItemPunchoutPK nvcTblCatalogoItemPunchoutPK;

    public NvcTblCatalogoItemPunchout() {
    }

    public NvcTblCatalogoItemPunchoutPK getNvcTblCatalogoItemPunchoutPK() {
        return nvcTblCatalogoItemPunchoutPK;
    }

    public void setNvcTblCatalogoItemPunchoutPK(NvcTblCatalogoItemPunchoutPK nvcTblCatalogoItemPunchoutPK) {
        this.nvcTblCatalogoItemPunchoutPK = nvcTblCatalogoItemPunchoutPK;
    }
    
}
