/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.core.model.NvcTblProvSitesH;
import com.metalsa.catalogo.model.NvcTblAlmacenH;
import com.metalsa.core.utils.Estatus;

/**
 *
 * @author jose.espindola03
 */
@Entity
@Table(name = "REQUISICION", catalog = "")
public class Requisicion  implements Serializable {
    @Id
    @SequenceGenerator(
            name = "SEQ_REQUISICION_S",
            sequenceName = "REQUISICION_S",
            allocationSize = 1
    )
    @GeneratedValue(generator = "SEQ_REQUISICION_S")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REQUISICION", nullable = false)
    private Long id;
    
    @Column(name = "APP_ORIGEN")
    private String appOrigen;
    
    @Column(name = "FECHA_REQUISICION")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
    
    @Size(max = 10)
    @Column(name = "TIPO_REQUISICION", length = 10)
    private String tipo;
    
    @Size(max = 20)
    @Column(name = "ESTATUS", length = 20)
    private String estatus;
    
    @Size(max = 50)
    @Column(name = "DATOS_DE_AUDITORIA", length = 50)
    private String datosDeAuditoria;
    
    /*@JoinColumn(name = "ID_UEN", referencedColumnName = "ID_UEN")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblOrganizacionesH uen;*/
    
    @Column(name = "ID_UEN")
    private Integer idUen;
    
    @Column(name = "SPX", length = 2)
    private String spx;
    
    @JoinColumns({
        @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR"),
        @JoinColumn(name = "ID_SUCURSAL_PROVEEDOR", referencedColumnName = "ID_SUCURSAL_PROVEEDOR")})
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblProvSitesH proveedorSite;
    
    @OrderBy("detalleDeRequisicionPK.idPartida ASC")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleDeRequisicion> detalles;
    
    @JoinColumn(name = "ID_ALMACEN", referencedColumnName = "ID_ALMACEN")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblAlmacenH almacen;
    
    /*@Column(name = "ID_CC")
    private Integer idCentroCosto;*/
    
    @Column(name = "RF_NUMBER")
    private Long rfNumber;
    
    @Column(name = "ID_RF")
    private Long idRf;
    
    @Column(name = "COMMODITY")
    private String commodity;
    
    public Long getId() {
        return this.id;
    }
    
    public void setAppOrigen(String origen) {
        this.appOrigen = origen;
    }
    
    public String getAppOrigen() {
        return this.appOrigen;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setUsuario(Usuario user) {
        this.usuario = user;
    }
    
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo() {
        return this.tipo;
    }
    
    public String getEstatus() {
        return this.estatus;
    }
    
    public String getDatosDeAuditoria() {
        return this.datosDeAuditoria;
    }
    
     public void setDatosDeAuditoria(String datos) {
         this.datosDeAuditoria = datos;
     }
    
     public void setEstatus(String estatus) {
         this.estatus = estatus;
     }
     
     public void setEstatus(Estatus estatus) {
         this.estatus = estatus.getDesc();
     }
     
     public Integer getUen() {
         return this.idUen;
     }
     
     public void setIdUen(Integer uen) {
         this.idUen = uen;
     }
     
     public String getSpx() {
         return this.spx;
     }
     
     public void setSpx(String spx) {
         this.spx = spx;
     }
     
     public void setProveedorSite(NvcTblProvSitesH site) {
         this.proveedorSite = site;
     }
     
     public NvcTblProvSitesH getProveedorSite() {
         return this.proveedorSite;
     }
     
     public List<DetalleDeRequisicion> getDetalles() {
         return this.detalles;
     }
     
     public void setDetalles(List<DetalleDeRequisicion> detalles) {
         this.detalles = detalles;
     }
     
     public NvcTblAlmacenH getAlmacen() {
         return this.almacen;
     }
     
     public void setAlmacen(NvcTblAlmacenH almacen) {
         this.almacen = almacen;
     }
     
     /*public Integer getIdCentroCosto() {
         return this.idCentroCosto;
     }
     
     public void setIdCentroCosto(Integer id) {
         this.idCentroCosto = id;
     }*/
     
     public void setRfNumber(Long number) {
         this.rfNumber = number;
     }
     
     public Long getRfNumber() {
         return this.rfNumber;
     }
     
     public Long getIdRf() {
         return this.idRf;
     }
     
     public void setIdRf(Long id) {
         this.idRf = id;
     }
     
     public String getCommodity() {
        return this.commodity;
        }
    
    public void setCommodity(String commodity) {
            this.commodity = commodity;
    }
}
