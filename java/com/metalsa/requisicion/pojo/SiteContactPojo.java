package com.metalsa.requisicion.pojo;

import java.io.Serializable;

/**
 *
 * @author APOMS7376
 */
public class SiteContactPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer vendorSiteContactId;
    private String emailStop;
    private Integer vendorSiteId;
    private String firstName;
    private String LastName;

    public SiteContactPojo() {
    }

    public SiteContactPojo(Integer vendorSiteId, Integer vendorSiteContactId, String firstName, String LastName, String emailStop) {
        this.vendorSiteContactId = vendorSiteContactId;
        this.emailStop = emailStop;
        this.vendorSiteId = vendorSiteId;
        this.firstName = firstName;
        this.LastName = LastName;
    }

    public String getEmailStop() {
        return emailStop;
    }

    public void setEmailStop(String emailStop) {
        this.emailStop = emailStop;
    }

    public Integer getVendorSiteId() {
        return vendorSiteId;
    }

    public void setVendorSiteId(Integer vendorSiteId) {
        this.vendorSiteId = vendorSiteId;
    }

    public Integer getVendorSiteContactId() {
        return vendorSiteContactId;
    }

    public void setVendorSiteContactId(Integer vendorSiteContactId) {
        this.vendorSiteContactId = vendorSiteContactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("vendorSiteContactId=").append(this.vendorSiteContactId == null ? "null" : vendorSiteContactId).append("\n")
                .append("emailStop=").append(this.emailStop == null ? "null" : emailStop).append("\n")
                .append("vendorSiteId=").append(this.vendorSiteId == null ? "null" : vendorSiteId).append("\n")
                .append("firstName=").append(this.firstName == null ? "null" : firstName).append("\n")
                .append("LastName=").append(this.LastName == null ? "null" : LastName).append("\n");
        return sb.toString();
    }

}
