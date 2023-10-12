package com.metalsa.requisicion.model;

import com.metalsa.core.model.Rol;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "DCP_MENUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcpMenus.findAll", query = "SELECT d FROM DcpMenus d")
    ,
    @NamedQuery(name = "DcpMenus.findByIdMenu", query = "SELECT d FROM DcpMenus d WHERE d.idMenu = :idMenu")
    ,
    @NamedQuery(name = "DcpMenus.findByDescEs", query = "SELECT d FROM DcpMenus d WHERE d.descEs = :descEs")
    ,
    @NamedQuery(name = "DcpMenus.findByOrden", query = "SELECT d FROM DcpMenus d WHERE d.orden = :orden")
    ,
    @NamedQuery(name = "DcpMenus.findByAction", query = "SELECT d FROM DcpMenus d WHERE d.action = :action")
    ,
    @NamedQuery(name = "DcpMenus.findByActivo", query = "SELECT d FROM DcpMenus d WHERE d.activo = :activo")
    ,
    @NamedQuery(name = "DcpMenus.findByFaces", query = "SELECT d FROM DcpMenus d WHERE d.faces = :faces")})
public class DcpMenus implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "DCP_MENUS_SEQ", sequenceName = "DCP_MENUS_SEQ", allocationSize = 1) //<LOOKNFEEL>
    @GeneratedValue(generator = "DCP_MENUS_SEQ") //<LOOKNFEEL>
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MENU", nullable = false, precision = 22)
    private BigDecimal idMenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "DESC_ES", nullable = false, length = 250)
    private String descEs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "DESC_EN", nullable = false, length = 250)
    private String descEn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "DESC_PT", nullable = false, length = 250)
    private String descPt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN", nullable = false)
    private BigInteger orden;
    @Size(max = 250)
    @Column(name = "ACTION", length = 250)
    private String action;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO", nullable = false)
    private BigInteger activo;
    @Size(max = 250)
    @Column(name = "FACES", length = 250)
    private String faces;
    @JoinTable(name = "DCP_ROLES_MENU", joinColumns = {
        @JoinColumn(name = "ID_MENU", referencedColumnName = "ID_MENU", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Rol> rolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcpMenus", fetch = FetchType.LAZY)
    private List<DcpMenusIdioma> dcpMenusIdiomaList;
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CAT", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcpCategorias idCategoria;
    @Size(max = 250)
    @Column(name = "MOBILE", length = 250)
    private String mobile;
    
    //<LOOKNFEEL>
    @Column(name = "ID_MENU_PADRE")
    private Integer idMenuPadre;
    @Column(name = "MULTINIVEL")
    private Integer multinivel;
    @Column(name = "CSS_CLASS")
    private String cssClass;
    @Column(name = "MENU_ROOT")
    private Integer menuRoot;
    //</LOOKNFEEL>
    
    public DcpMenus() {
    }

    public DcpMenus(BigDecimal idMenu) {
        this.idMenu = idMenu;
    }

    public DcpMenus(BigDecimal idMenu, String descEs, BigInteger orden, BigInteger activo) {
        this.idMenu = idMenu;
        this.descEs = descEs;
        this.orden = orden;
        this.activo = activo;
    }

    public BigDecimal getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(BigDecimal idMenu) {
        this.idMenu = idMenu;
    }

    public String getDescEs() {
        return descEs;
    }

    public void setDescEs(String descEs) {
        this.descEs = descEs;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigInteger getActivo() {
        return activo;
    }

    public void setActivo(BigInteger activo) {
        this.activo = activo;
    }

    public String getFaces() {
        return faces;
    }

    public void setFaces(String faces) {
        this.faces = faces;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    @XmlTransient
    public List<DcpMenusIdioma> getDcpMenusIdiomaList() {
        return dcpMenusIdiomaList;
    }

    public void setDcpMenusIdiomaList(List<DcpMenusIdioma> dcpMenusIdiomaList) {
        this.dcpMenusIdiomaList = dcpMenusIdiomaList;
    }

    public DcpCategorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(DcpCategorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcpMenus)) {
            return false;
        }
        DcpMenus other = (DcpMenus) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.DcpMenus[ idMenu=" + idMenu + " ]";
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescEn() {
        return descEn;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public String getDescPt() {
        return descPt;
    }

    public void setDescPt(String descPt) {
        this.descPt = descPt;
    }

    //<LOOKNFEEL>
    public Integer getIdMenuPadre() {
        return idMenuPadre;
    }

    public void setIdMenuPadre(Integer idMenuPadre) {
        this.idMenuPadre = idMenuPadre;
    }

    public Integer getMultinivel() {
        return multinivel;
    }

    public void setMultinivel(Integer multinivel) {
        this.multinivel = multinivel;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public Integer getMenuRoot() {
        return menuRoot;
    }

    public void setMenuRoot(Integer menuRoot) {
        this.menuRoot = menuRoot;
    }
    //</LOOKNFEEL>
}
