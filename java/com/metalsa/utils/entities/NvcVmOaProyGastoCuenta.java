/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utils.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "NVC_VM_OA_PROY_GASTO_CUENTA", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcVmOaProyGastoCuenta.findAll", query = "SELECT n FROM NvcVmOaProyGastoCuenta n")
    ,
    @NamedQuery(name = "NvcVmOaProyGastoCuenta.findByIdTarea", query = "SELECT n FROM NvcVmOaProyGastoCuenta n WHERE n.nvcVmOaProyGastoCuentaPK.idTarea = :idTarea")
    ,
    @NamedQuery(name = "NvcVmOaProyGastoCuenta.findByCodTarea", query = "SELECT n FROM NvcVmOaProyGastoCuenta n WHERE n.codTarea = :codTarea")
    ,
    @NamedQuery(name = "NvcVmOaProyGastoCuenta.findByTipoGasto", query = "SELECT n FROM NvcVmOaProyGastoCuenta n WHERE n.tipoGasto = :tipoGasto")
    ,
    @NamedQuery(name = "NvcVmOaProyGastoCuenta.findByIdCuenta", query = "SELECT n FROM NvcVmOaProyGastoCuenta n WHERE n.nvcVmOaProyGastoCuentaPK.idCuenta = :idCuenta")
    ,
    @NamedQuery(name = "NvcVmOaProyGastoCuenta.findByReqAlm", query = "SELECT n FROM NvcVmOaProyGastoCuenta n WHERE n.reqAlm = :reqAlm")
    ,
    @NamedQuery(name = "NvcVmOaProyGastoCuenta.findByTipo", query = "SELECT n FROM NvcVmOaProyGastoCuenta n WHERE n.nvcVmOaProyGastoCuentaPK.tipo = :tipo")
    ,
    @NamedQuery(name = "NvcVmOaProyGastoCuenta.findByExpType", query = "SELECT n FROM NvcVmOaProyGastoCuenta n WHERE n.expType = :expType")
    ,
    @NamedQuery(name = "NvcVmOaProyGastoCuenta.findByResourceName", query = "SELECT n FROM NvcVmOaProyGastoCuenta n WHERE n.resourceName = :resourceName")
    ,
    @NamedQuery(name = "NvcVmOaProyGastoCuenta.findByResourceId", query = "SELECT n FROM NvcVmOaProyGastoCuenta n WHERE n.nvcVmOaProyGastoCuentaPK.resourceId = :resourceId")
    ,
    @NamedQuery(name = "NvcVmOaProyGastoCuenta.findByResourceListMemberId", query = "SELECT n FROM NvcVmOaProyGastoCuenta n WHERE n.resourceListMemberId = :resourceListMemberId")})
public class NvcVmOaProyGastoCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NvcVmOaProyGastoCuentaPK nvcVmOaProyGastoCuentaPK;
    @Size(max = 75)
    @Column(name = "COD_TAREA", length = 75)
    private String codTarea;
    @Size(max = 240)
    @Column(name = "TIPO_GASTO", length = 240)
    private String tipoGasto;
    @Size(max = 1)
    @Column(name = "REQ_ALM", length = 1)
    private String reqAlm;
    @Size(max = 90)
    @Column(name = "EXP_TYPE", length = 90)
    private String expType;
    @Size(max = 240)
    @Column(name = "RESOURCE_NAME", length = 240)
    private String resourceName;
    @Column(name = "RESOURCE_LIST_MEMBER_ID")
    private Long resourceListMemberId;
    
    public NvcVmOaProyGastoCuenta() {
    }

    public NvcVmOaProyGastoCuenta(NvcVmOaProyGastoCuentaPK nvcVmOaProyGastoCuentaPK) {
        this.nvcVmOaProyGastoCuentaPK = nvcVmOaProyGastoCuentaPK;
    }

    public NvcVmOaProyGastoCuenta(long idTarea, BigInteger idCuenta, String tipo, long resourceId) {
        this.nvcVmOaProyGastoCuentaPK = new NvcVmOaProyGastoCuentaPK(idTarea, idCuenta, tipo, resourceId);
    }

    public NvcVmOaProyGastoCuentaPK getNvcVmOaProyGastoCuentaPK() {
        return nvcVmOaProyGastoCuentaPK;
    }

    public void setNvcVmOaProyGastoCuentaPK(NvcVmOaProyGastoCuentaPK nvcVmOaProyGastoCuentaPK) {
        this.nvcVmOaProyGastoCuentaPK = nvcVmOaProyGastoCuentaPK;
    }

    public String getCodTarea() {
        return codTarea;
    }

    public void setCodTarea(String codTarea) {
        this.codTarea = codTarea;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getReqAlm() {
        return reqAlm;
    }

    public void setReqAlm(String reqAlm) {
        this.reqAlm = reqAlm;
    }

    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Long getResourceListMemberId() {
        return resourceListMemberId;
    }

    public void setResourceListMemberId(Long resourceListMemberId) {
        this.resourceListMemberId = resourceListMemberId;
    }
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nvcVmOaProyGastoCuentaPK != null ? nvcVmOaProyGastoCuentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcVmOaProyGastoCuenta)) {
            return false;
        }
        NvcVmOaProyGastoCuenta other = (NvcVmOaProyGastoCuenta) object;
        if ((this.nvcVmOaProyGastoCuentaPK == null && other.nvcVmOaProyGastoCuentaPK != null) || (this.nvcVmOaProyGastoCuentaPK != null && !this.nvcVmOaProyGastoCuentaPK.equals(other.nvcVmOaProyGastoCuentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.suite.NvcVmOaProyGastoCuenta[ nvcVmOaProyGastoCuentaPK=" + nvcVmOaProyGastoCuentaPK + " ]";
    }
    
}
