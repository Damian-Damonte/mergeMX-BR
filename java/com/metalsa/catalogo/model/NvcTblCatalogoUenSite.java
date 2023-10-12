package com.metalsa.catalogo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.metalsa.core.model.NvcTblProvSitesH;

@Entity
@Table(name = "NVC_TBL_CATALOGO_UEN_SITE")
@NamedQuery(
        name = "NvcTblCatalogoUenSite.findByIdLocalizacion",
        query = "select c from NvcTblCatalogoUenSite c where c.idCatalogoLocalizacion = :idCatalogoLocalizacion"
)
public class NvcTblCatalogoUenSite implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATALOGO_UEN_SITE")
    private Integer id;
    
    @Column(name = "id_catalogo_localizacion")
    private Integer idCatalogoLocalizacion;
    
    @JoinColumns({
        @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR"),
        @JoinColumn(name = "ID_SUCURSAL_PROVEEDOR", referencedColumnName = "ID_SUCURSAL_PROVEEDOR")})
    @ManyToOne(optional = false)
    private NvcTblProvSitesH provedorSites;
    
    public Integer getIdCatalogoLocalizacion() {
        return this.idCatalogoLocalizacion;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public NvcTblProvSitesH getProveedorSites() {
        return this.provedorSites;
    }
}