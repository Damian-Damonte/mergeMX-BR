package com.metalsa.requisicion.model;

import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APOJA5585
 */
@Entity
@Table(name = "NVC_TBL_MONEDAS_H")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblMonedasH.findAll", query = "SELECT n FROM NvcTblMonedasH n")
    ,
    @NamedQuery(name = "NvcTblMonedasH.findByIdMoneda", query = "SELECT n FROM NvcTblMonedasH n WHERE n.idMoneda = ?1")
    ,
    @NamedQuery(name = "NvcTblMonedasH.findByMoneda", query = "SELECT n FROM NvcTblMonedasH n WHERE n.moneda = :moneda")
    ,
    @NamedQuery(name = "NvcTblMonedasH.findByEnabledFlag", query = "SELECT n FROM NvcTblMonedasH n WHERE n.enabledFlag = :enabledFlag")
    ,
    @NamedQuery(name = "NvcTblMonedasH.findByCurrencyFlag", query = "SELECT n FROM NvcTblMonedasH n WHERE n.currencyFlag = :currencyFlag")
    ,
    @NamedQuery(name = "NvcTblMonedasH.findByActive", query = "SELECT n FROM NvcTblMonedasH n WHERE n.active = ?1 AND n.idMoneda <> 'MXN' order by n.idMoneda asc")
    ,
    @NamedQuery(name = "NvcTblMonedasH.findActiveCustomOrder", query = "SELECT n FROM NvcTblMonedasH n WHERE n.active = 1 AND n.idMoneda not in('MXN', 'USD', 'MXP', 'EUR') order by n.idMoneda asc")
})
public class NvcTblMonedasH implements Serializable {

    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @OneToMany(mappedBy = "idMoneda")
    private Collection<NvcTblCatalogoItem> nvcTblCatalogoItemCollection;
    @OneToMany(mappedBy = "idMoneda", fetch = FetchType.LAZY)
    private List<NvcVmOaExistencias> nvcVmOaExistenciasList;
    @OneToMany(mappedBy = "idMoneda", fetch = FetchType.LAZY)
    private List<CarroDeCompra> carroDeCompraList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ID_MONEDA")
    private String idMoneda;
    @Size(max = 240)
    @Column(name = "MONEDA")
    private String moneda;
    @Size(max = 3)
    @Column(name = "ENABLED_FLAG")
    private String enabledFlag;
    @Size(max = 3)
    @Column(name = "CURRENCY_FLAG")
    private String currencyFlag;
    @Column(name = "ACTIVE")
    private BigInteger active;
    @OneToMany(mappedBy = "idMoneda")
    private Collection<DetalleDeRequisicion> detalleDeRequisicionCollection;

    public NvcTblMonedasH() {
    }

    public NvcTblMonedasH(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getCurrencyFlag() {
        return currencyFlag;
    }

    public void setCurrencyFlag(String currencyFlag) {
        this.currencyFlag = currencyFlag;
    }

    public BigInteger getActive() {
        return active;
    }

    public void setActive(BigInteger active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<DetalleDeRequisicion> getDetalleDeRequisicionCollection() {
        return detalleDeRequisicionCollection;
    }

    public void setDetalleDeRequisicionCollection(Collection<DetalleDeRequisicion> detalleDeRequisicionCollection) {
        this.detalleDeRequisicionCollection = detalleDeRequisicionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMoneda != null ? idMoneda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblMonedasH)) {
            return false;
        }
        NvcTblMonedasH other = (NvcTblMonedasH) object;
        if ((this.idMoneda == null && other.idMoneda != null) || (this.idMoneda != null && !this.idMoneda.equals(other.idMoneda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.NvcTblMonedasH[ idMoneda=" + idMoneda + " ]";
    }

    @XmlTransient
    public List<NvcVmOaExistencias> getNvcVmOaExistenciasList() {
        return nvcVmOaExistenciasList;
    }

    public void setNvcVmOaExistenciasList(List<NvcVmOaExistencias> nvcVmOaExistenciasList) {
        this.nvcVmOaExistenciasList = nvcVmOaExistenciasList;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @XmlTransient
    public Collection<NvcTblCatalogoItem> getNvcTblCatalogoItemCollection() {
        return nvcTblCatalogoItemCollection;
    }

    public void setNvcTblCatalogoItemCollection(Collection<NvcTblCatalogoItem> nvcTblCatalogoItemCollection) {
        this.nvcTblCatalogoItemCollection = nvcTblCatalogoItemCollection;
    }
}
