/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utils.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.metalsa.aprobacion.model.CarroCompra;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "NVC_TBL_CARRO_COMPRA_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblCarroCompraDetalle.findAll", query = "SELECT n FROM NvcTblCarroCompraDetalle n"),
    @NamedQuery(name = "NvcTblCarroCompraDetalle.findByIdCarroCompraDetalle", query = "SELECT n FROM NvcTblCarroCompraDetalle n WHERE n.idCarroCompraDetalle = :idCarroCompraDetalle"),
    @NamedQuery(name = "NvcTblCarroCompraDetalle.findByIdCarroCom", query = "SELECT n FROM NvcTblCarroCompraDetalle n WHERE n.idCarroCompra.id = :idCarroCompra")
})
public class NvcTblCarroCompraDetalle implements Serializable {

    @Column(name = "ID_TAREA")
    private Integer idTarea;
    @Column(name = "ID_CUENTA")
    private Integer idCuenta;
    @Column(name = "ID_LOCALIZACION")
    private Integer idLocalizacion;
    @Size(max = 150)
    @Column(name = "SEGMENTO_1")
    private String segmento1;
    @Size(max = 150)
    @Column(name = "SEGMENTO_2")
    private String segmento2;
    @Size(max = 150)
    @Column(name = "SEGMENTO_3")
    private String segmento3;
    @Size(max = 150)
    @Column(name = "SEGMENTO_4")
    private String segmento4;
    @Size(max = 150)
    @Column(name = "SEGMENTO_5")
    private String segmento5;
    @Size(max = 150)
    @Column(name = "SEGMENTO_6")
    private String segmento6;
    @Size(max = 150)
    @Column(name = "SEGMENTO_7")
    private String segmento7;
    @Size(max = 150)
    @Column(name = "SEGMENTO_8")
    private String segmento8;
    @Column(name = "PORCENTAJE")
    private Double porcentaje;
    @Column(name = "SEGMENTO_PRODUCTO")
    private Integer segmentoProducto;
    @Column(name = "MONTO")
    private Double monto;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "SEQ_NVC_CARRO_COMPRA_DET_GEN", sequenceName = "SEQ_NVC_CARRO_COMPRA_DET", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_NVC_CARRO_COMPRA_DET_GEN")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CARRO_COMPRA_DETALLE")
    private Integer idCarroCompraDetalle;
    @Column(name = "ID_PROYECTO")
    private Integer idProyecto;
    @Column(name = "ID_CENTRO_COSTO")
    private Integer idCentroCosto;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Size(min = 1, max = 256)
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;

    @JsonBackReference
    @JoinColumn(name = "ID_CARRO_COMPRA", referencedColumnName = "ID_CARRO_COMPRA")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private CarroCompra idCarroCompra;
    @Column(name = "ID_TIPO_ASIGNACION")
    private Integer idTipoAsignacion;
    @Column(name = "TIPO_GASTO")
    private String tipoGasto;
    @Size(min = 1, max = 256)
    @Column(name = "USUARIO_COMPRADOR")
    private String comprador;

    public NvcTblCarroCompraDetalle() {
    }

    public NvcTblCarroCompraDetalle(Integer idCarroCompraDetalle) {
        this.idCarroCompraDetalle = idCarroCompraDetalle;
    }

    

    public Integer getIdCarroCompraDetalle() {
        return idCarroCompraDetalle;
    }

    public void setIdCarroCompraDetalle(Integer idCarroCompraDetalle) {
        this.idCarroCompraDetalle = idCarroCompraDetalle;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }
    
    public Integer getIdProyecto(int defaultValue) {
        return this.idProyecto != null ? this.idProyecto : defaultValue;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }
    
    public Integer getIdCentroCosto(int defaultValue) {
        return this.idCentroCosto != null ? this.idCentroCosto : defaultValue;
    }

    public Integer getIdCentroCosto() {
        return idCentroCosto;
    }

    public void setIdCentroCosto(Integer idCentroCosto) {
        this.idCentroCosto = idCentroCosto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public CarroCompra getIdCarroCompra() {
        return idCarroCompra;
    }

    public void setIdCarroCompra(CarroCompra idCarroCompra) {
        this.idCarroCompra = idCarroCompra;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarroCompraDetalle != null ? idCarroCompraDetalle.hashCode() : 0);
        return hash;
    }

    /*
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblCarroCompraDetalle)) {
            return false;
        }
        NvcTblCarroCompraDetalle other = (NvcTblCarroCompraDetalle) object;
        if ((this.idCarroCompraDetalle == null && other.idCarroCompraDetalle != null) || (this.idCarroCompraDetalle != null && !this.idCarroCompraDetalle.equals(other.idCarroCompraDetalle))) {
            return false;
        }
        return true;
    }
    */

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblCarroCompraDetalle[ idCarroCompraDetalle=" + idCarroCompraDetalle + " ]";
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public String getSegmento1() {
        return segmento1;
    }

    public void setSegmento1(String segmento1) {
        this.segmento1 = segmento1;
    }

    public String getSegmento2() {
        return segmento2;
    }

    public void setSegmento2(String segmento2) {
        this.segmento2 = segmento2;
    }

    public String getSegmento3() {
        return segmento3;
    }

    public void setSegmento3(String segmento3) {
        this.segmento3 = segmento3;
    }

    public String getSegmento4() {
        return segmento4;
    }

    public void setSegmento4(String segmento4) {
        this.segmento4 = segmento4;
    }

    public String getSegmento5() {
        return segmento5;
    }

    public void setSegmento5(String segmento5) {
        this.segmento5 = segmento5;
    }

    public String getSegmento6() {
        return segmento6;
    }

    public void setSegmento6(String segmento6) {
        this.segmento6 = segmento6;
    }

    public String getSegmento7() {
        return segmento7;
    }

    public void setSegmento7(String segmento7) {
        this.segmento7 = segmento7;
    }

    public String getSegmento8() {
        return segmento8;
    }

    public void setSegmento8(String segmento8) {
        this.segmento8 = segmento8;
    }

    /**
     * @return the idTipoAsignacion
     */
    public Integer getIdTipoAsignacion() {
        return idTipoAsignacion;
    }

    /**
     * @param idTipoAsignacion the idTipoAsignacion to set
     */
    public void setIdTipoAsignacion(Integer idTipoAsignacion) {
        this.idTipoAsignacion = idTipoAsignacion;
    }

    /**
     * @return the comprador
     */
    public String getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    /**
     * @return the tipoGasto
     */
    public String getTipoGasto() {
        return tipoGasto;
    }

    /**
     * @param tipoGasto the tipoGasto to set
     */
    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    /**
     * @return the porcentaje
     */
    public Double getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getSegmentoProducto() {
        return segmentoProducto;
    }

    public void setSegmentoProducto(Integer segmentoProducto) {
        this.segmentoProducto = segmentoProducto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }


    
}
