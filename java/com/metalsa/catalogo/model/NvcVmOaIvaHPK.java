package com.metalsa.catalogo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author apomr10051
 */
@Embeddable
public class NvcVmOaIvaHPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UEN", nullable = false)
    private Integer idUen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "IVA", nullable = false, length = 45)
    private String iva;

    public NvcVmOaIvaHPK() {
    }

    public NvcVmOaIvaHPK(Integer idUen, String iva) {
        this.idUen = idUen;
        this.iva = iva;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

}
