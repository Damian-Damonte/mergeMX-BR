/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.portalProveedor.model.NvcTblIncoterm;
import com.metalsa.portalProveedor.model.NvcTblRfqProv;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "DC_IDIOMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcIdioma.findAll", query = "SELECT d FROM DcIdioma d")
    ,
    @NamedQuery(name = "DcIdioma.findByScId", query = "SELECT d FROM DcIdioma d WHERE d.scId = :scId")
    ,
    @NamedQuery(name = "DcIdioma.findByScDescripcion", query = "SELECT d FROM DcIdioma d WHERE d.scDescripcion = :scDescripcion")
    ,
    @NamedQuery(name = "DcIdioma.findByCodApli", query = "SELECT d FROM DcIdioma d WHERE d.codApli = :codApli")})
public class DcIdioma implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma")
    private Collection<NvcTblRazonUrgenciaIdioma> nvcTblRazonUrgenciaIdiomaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma")
    private Collection<NvcTblCatUnidadesTiempo> nvcTblCatUnidadesTiempoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idioma")
    private Collection<NvcTblCorreo> nvcTblCorreoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma", fetch = FetchType.LAZY)
    private List<NvcTblIncoterm> nvcTblIncotermList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma", fetch = FetchType.LAZY)
    private List<NvcTblTermTransporte> nvcTblTermTransporteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcIdioma", fetch = FetchType.LAZY)
    private List<DcEstatusIdioma> dcEstatusIdiomaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma")
    private Collection<NvcTblRfqProv> nvcTblRfqProvCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcIdioma")
    private List<NvcTblCategoriesLng> nvcTblCategoriesLngList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcIdioma", fetch = FetchType.LAZY)
    private List<ParametrosConfigIdioma> parametrosConfigIdiomaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcIdioma", fetch = FetchType.LAZY)
    private List<NvcTblOaCcLngH> nvcTblOaCcLngHList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcIdioma", fetch = FetchType.LAZY)
    private List<NvcTblFamiliasIdioma> nvcTblFamiliasIdiomaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcIdioma", fetch = FetchType.LAZY)
    private List<DcpMenusIdioma> dcpMenusIdiomaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcIdioma", fetch = FetchType.LAZY)
    private List<DcpCategoriasIdioma> dcpCategoriasIdiomaList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SC_ID", nullable = false, length = 10)
    private String scId;
    @Size(max = 10)
    @Column(name = "SC_DESCRIPCION", length = 10)
    private String scDescripcion;
    @Size(max = 10)
    @Column(name = "COD_APLI", length = 10)
    private String codApli;
    @OneToMany(mappedBy = "idioma", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    public DcIdioma() {
    }

    public DcIdioma(String scId) {
        this.scId = scId;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getScDescripcion() {
        return scDescripcion;
    }

    public void setScDescripcion(String scDescripcion) {
        this.scDescripcion = scDescripcion;
    }

    public String getCodApli() {
        return codApli;
    }

    public void setCodApli(String codApli) {
        this.codApli = codApli;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scId != null ? scId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcIdioma)) {
            return false;
        }
        DcIdioma other = (DcIdioma) object;
        if ((this.scId == null && other.scId != null) || (this.scId != null && !this.scId.equals(other.scId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.DcIdioma[ scId=" + scId + " ]";
    }

    @XmlTransient
    public List<DcpMenusIdioma> getDcpMenusIdiomaList() {
        return dcpMenusIdiomaList;
    }

    public void setDcpMenusIdiomaList(List<DcpMenusIdioma> dcpMenusIdiomaList) {
        this.dcpMenusIdiomaList = dcpMenusIdiomaList;
    }

    @XmlTransient
    public List<DcpCategoriasIdioma> getDcpCategoriasIdiomaList() {
        return dcpCategoriasIdiomaList;
    }

    public void setDcpCategoriasIdiomaList(List<DcpCategoriasIdioma> dcpCategoriasIdiomaList) {
        this.dcpCategoriasIdiomaList = dcpCategoriasIdiomaList;
    }

    @XmlTransient
    public List<NvcTblFamiliasIdioma> getNvcTblFamiliasIdiomaList() {
        return nvcTblFamiliasIdiomaList;
    }

    public void setNvcTblFamiliasIdiomaList(List<NvcTblFamiliasIdioma> nvcTblFamiliasIdiomaList) {
        this.nvcTblFamiliasIdiomaList = nvcTblFamiliasIdiomaList;
    }

    @XmlTransient
    public List<NvcTblOaCcLngH> getNvcTblOaCcLngHList() {
        return nvcTblOaCcLngHList;
    }

    public void setNvcTblOaCcLngHList(List<NvcTblOaCcLngH> nvcTblOaCcLngHList) {
        this.nvcTblOaCcLngHList = nvcTblOaCcLngHList;
    }

    @XmlTransient
    public List<ParametrosConfigIdioma> getParametrosConfigIdiomaList() {
        return parametrosConfigIdiomaList;
    }

    public void setParametrosConfigIdiomaList(List<ParametrosConfigIdioma> parametrosConfigIdiomaList) {
        this.parametrosConfigIdiomaList = parametrosConfigIdiomaList;
    }

    @XmlTransient
    public List<NvcTblCategoriesLng> getNvcTblCategoriesLngList() {
        return nvcTblCategoriesLngList;
    }

    public void setNvcTblCategoriesLngList(List<NvcTblCategoriesLng> nvcTblCategoriesLngList) {
        this.nvcTblCategoriesLngList = nvcTblCategoriesLngList;
    }

    @XmlTransient
    public Collection<NvcTblRfqProv> getNvcTblRfqProvCollection() {
        return nvcTblRfqProvCollection;
    }

    public void setNvcTblRfqProvCollection(Collection<NvcTblRfqProv> nvcTblRfqProvCollection) {
        this.nvcTblRfqProvCollection = nvcTblRfqProvCollection;
    }

    @XmlTransient
    public List<DcEstatusIdioma> getDcEstatusIdiomaList() {
        return dcEstatusIdiomaList;
    }

    public void setDcEstatusIdiomaList(List<DcEstatusIdioma> dcEstatusIdiomaList) {
        this.dcEstatusIdiomaList = dcEstatusIdiomaList;
    }

    @XmlTransient
    public List<NvcTblIncoterm> getNvcTblIncotermList() {
        return nvcTblIncotermList;
    }

    public void setNvcTblIncotermList(List<NvcTblIncoterm> nvcTblIncotermList) {
        this.nvcTblIncotermList = nvcTblIncotermList;
    }

    @XmlTransient
    public List<NvcTblTermTransporte> getNvcTblTermTransporteList() {
        return nvcTblTermTransporteList;
    }

    public void setNvcTblTermTransporteList(List<NvcTblTermTransporte> nvcTblTermTransporteList) {
        this.nvcTblTermTransporteList = nvcTblTermTransporteList;
    }

    @XmlTransient
    public Collection<NvcTblCorreo> getNvcTblCorreoCollection() {
        return nvcTblCorreoCollection;
    }

    public void setNvcTblCorreoCollection(Collection<NvcTblCorreo> nvcTblCorreoCollection) {
        this.nvcTblCorreoCollection = nvcTblCorreoCollection;
    }

    @XmlTransient
    public Collection<NvcTblCatUnidadesTiempo> getNvcTblCatUnidadesTiempoCollection() {
        return nvcTblCatUnidadesTiempoCollection;
    }

    public void setNvcTblCatUnidadesTiempoCollection(Collection<NvcTblCatUnidadesTiempo> nvcTblCatUnidadesTiempoCollection) {
        this.nvcTblCatUnidadesTiempoCollection = nvcTblCatUnidadesTiempoCollection;
    }

    @XmlTransient
    public Collection<NvcTblRazonUrgenciaIdioma> getNvcTblRazonUrgenciaIdiomaCollection() {
        return nvcTblRazonUrgenciaIdiomaCollection;
    }

    public void setNvcTblRazonUrgenciaIdiomaCollection(Collection<NvcTblRazonUrgenciaIdioma> nvcTblRazonUrgenciaIdiomaCollection) {
        this.nvcTblRazonUrgenciaIdiomaCollection = nvcTblRazonUrgenciaIdiomaCollection;
    }

}
//   <p:remoteCommand name="test" actionListener="#{adminCuenta.test}" />
//                                <p:inputText id="selConcat" style="width:240px !important;" value="#{adminCuenta.userConcat}"  title="#{msgs['sc_criterio']}" >
//                                    <p:watermark for="selConcat" value="#{msgs['sc_criterio']}" />
//                                </p:inputText>&nbsp;&nbsp;<p:commandButton value="#{msgs['sc_filtrar']}" id="ajax" actionListener="#{adminCuenta.test}" styleClass="ui-priority-primary" process="@this, selConcat"  update="selCC"/>

//<p:autoComplete id="selConcat" minQueryLength="3" required="true"  style="width:350px !important;" styleClass="menu" 
//                                                value="#{adminCuenta.userConcat}"
//                                                completeMethod="#{adminCuenta.test}" 
//                                                var="employee"
//                                                itemLabel="#{employee.nvcTblFamilias.concaten} #{employee.nvcTblFamilias.nivel == 2 ? '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' : (employee.nvcTblFamilias.nivel == 3 ? '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' :'&nbsp;')}- #{employee.nombreFamiliaIdioma} (#{employee.nvcTblFamiliasIdiomaPK.idFamilia})"  
//                                                itemValue="#{employee.nvcTblFamilias.concaten}" 
//                                                effect="fade"/>
