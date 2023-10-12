package com.metalsa.requisicion.pojo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class SitesPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String siteCode;
    private String organizationName;
    private Integer vendorSiteId;
    private Integer organizationId;
    private List<SiteContactPojo> siteContacts;
    private String companyName;
    private String vendorName;
    private String vendorId;
    private boolean tieneIva;
    private boolean tieneRetencion;

    private NvcTblCatalogoUenSitePojo catalogoUenSitePojo;

    public NvcTblCatalogoUenSitePojo getCatalogoUenSitePojo() {
        return catalogoUenSitePojo;
    }

    public void setCatalogoUenSitePojo(NvcTblCatalogoUenSitePojo catalogoUenSitePojo) {
        this.catalogoUenSitePojo = catalogoUenSitePojo;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public Integer getVendorSiteId() {
        return vendorSiteId;
    }

    public void setVendorSiteId(Integer vendorSiteId) {
        this.vendorSiteId = vendorSiteId;
    }

    public List<SiteContactPojo> getSiteContacts() {
        return siteContacts;
    }

    public void setSiteContacts(List<SiteContactPojo> siteContacts) {
        this.siteContacts = siteContacts;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("siteCode=").append(this.siteCode == null ? "null" : siteCode)
                .append("organizationName=").append(this.organizationName == null ? "null" : organizationName)
                .append("vendorSiteId=").append(this.vendorSiteId == null ? "null" : vendorSiteId)
                .append("organizationId=").append(this.organizationId == null ? "null" : organizationId)
                .append("companyName=").append(this.companyName == null ? "null" : companyName);
        return sb.toString();
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public boolean isTieneIva() {
        return tieneIva;
    }

    public void setTieneIva(boolean tieneIva) {
        this.tieneIva = tieneIva;
    }

    public boolean isTieneRetencion() {
        return tieneRetencion;
    }

    public void setTieneRetencion(boolean tieneRetencion) {
        this.tieneRetencion = tieneRetencion;
    }
    
    

}
