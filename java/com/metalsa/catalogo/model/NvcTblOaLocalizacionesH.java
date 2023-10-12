package com.metalsa.catalogo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author apomr10051
 */
@Entity
@Table(name = "NVC_TBL_OA_LOCALIZACIONES_H")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblOaLocalizacionesH.findAll", query = "SELECT n FROM NvcTblOaLocalizacionesH n")
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "NvcTblOaLocalizacionesH.getShipToByIdUen",
            resultClass = NvcTblOaLocalizacionesH.class,
            query = "select b.* from "
            + "localizaciones_por_uen_cia a, nvc_tbl_oa_localizaciones_h b where a.id_uen = :idUen "
            + "and b.ship_to_site_flag = 'Y' and a.id_localizacion = b.id_localizacion and a.estatus = 1 "
            + "order by a.localizacion_default "
    )
    ,
    @NamedNativeQuery(
            name = "NvcTblOaLocalizacionesH.getBillToByIdUen",
            resultClass = NvcTblOaLocalizacionesH.class,
            query = "select b.* from "
            + "localizaciones_por_uen_cia a, nvc_tbl_oa_localizaciones_h b where a.id_uen = :idUen "
            + "and b.bill_to_site_flag = 'Y' and a.id_localizacion = b.id_localizacion and a.estatus = 1 "
            + "order by a.localizacion_default "
    )
})
public class NvcTblOaLocalizacionesH implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LOCALIZACION")
    private BigDecimal idLocalizacion;
    @Size(max = 180)
    @Column(name = "CODIGO_LOCALIZACION")
    private String codigoLocalizacion;
    @Size(max = 720)
    @Column(name = "NOMBRE_LOCALIZACION")
    private String nombreLocalizacion;
    @Size(max = 720)
    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;
    @Size(max = 720)
    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;
    @Size(max = 90)
    @Column(name = "TOWN_OR_CITY")
    private String townOrCity;
    @Size(max = 360)
    @Column(name = "REGION_1")
    private String region1;
    @Size(max = 360)
    @Column(name = "REGION_2")
    private String region2;
    @Size(max = 180)
    @Column(name = "COUNTRY")
    private String country;
    @Size(max = 90)
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Size(max = 180)
    @Column(name = "TELEPHONE_NUMBER_1")
    private String telephoneNumber1;
    @Column(name = "ACTIVE")
    private BigInteger active;

    public NvcTblOaLocalizacionesH() {
    }

    public NvcTblOaLocalizacionesH(BigDecimal idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public BigDecimal getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(BigDecimal idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public String getCodigoLocalizacion() {
        return codigoLocalizacion;
    }

    public void setCodigoLocalizacion(String codigoLocalizacion) {
        this.codigoLocalizacion = codigoLocalizacion;
    }

    public String getNombreLocalizacion() {
        return nombreLocalizacion;
    }

    public void setNombreLocalizacion(String nombreLocalizacion) {
        this.nombreLocalizacion = nombreLocalizacion;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getTownOrCity() {
        return townOrCity;
    }

    public void setTownOrCity(String townOrCity) {
        this.townOrCity = townOrCity;
    }

    public String getRegion1() {
        return region1;
    }

    public void setRegion1(String region1) {
        this.region1 = region1;
    }

    public String getRegion2() {
        return region2;
    }

    public void setRegion2(String region2) {
        this.region2 = region2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTelephoneNumber1() {
        return telephoneNumber1;
    }

    public void setTelephoneNumber1(String telephoneNumber1) {
        this.telephoneNumber1 = telephoneNumber1;
    }

    public BigInteger getActive() {
        return active;
    }

    public void setActive(BigInteger active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocalizacion != null ? idLocalizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblOaLocalizacionesH)) {
            return false;
        }
        NvcTblOaLocalizacionesH other = (NvcTblOaLocalizacionesH) object;
        if ((this.idLocalizacion == null && other.idLocalizacion != null) || (this.idLocalizacion != null && !this.idLocalizacion.equals(other.idLocalizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.NvcTblOaLocalizacionesH[ idLocalizacion=" + idLocalizacion + " ]";
    }

}
