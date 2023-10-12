/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author edgar.leal
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "UserUenRol.usuario", query="SELECT u FROM UserUenRol u INNER JOIN u.uenPorUsuarioList uen ON uen.uenPorUsuarioPK.idUen = ?1 INNER JOIN u.rolesPorUsuarioList r ON r.rolesPorUsuarioPK.idRol = ?2 WHERE u.estatus = 'A' ORDER BY u.nombreUsuario ASC") 
    , @NamedQuery(name = "UserUenRol.findAll", query = "SELECT u FROM UserUenRol u")
    , @NamedQuery(name = "UserUenRol.findByIdUsuario", query = "SELECT u FROM UserUenRol u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "UserUenRol.findByIdEmpleado", query = "SELECT u FROM UserUenRol u WHERE u.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "UserUenRol.findByPassword", query = "SELECT u FROM UserUenRol u WHERE u.password = :password")
    , @NamedQuery(name = "UserUenRol.findByUltimaFechaDeEntrada", query = "SELECT u FROM UserUenRol u WHERE u.ultimaFechaDeEntrada = :ultimaFechaDeEntrada")
    , @NamedQuery(name = "UserUenRol.findByConteoDeIntentosFallidos", query = "SELECT u FROM UserUenRol u WHERE u.conteoDeIntentosFallidos = :conteoDeIntentosFallidos")
    , @NamedQuery(name = "UserUenRol.findByNombreUsuario", query = "SELECT u FROM UserUenRol u WHERE u.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "UserUenRol.findByEmail", query = "SELECT u FROM UserUenRol u WHERE u.email = :email")
    , @NamedQuery(name = "UserUenRol.findByPerfilDeBusqueda", query = "SELECT u FROM UserUenRol u WHERE u.perfilDeBusqueda = :perfilDeBusqueda")
    , @NamedQuery(name = "UserUenRol.findByEstatus", query = "SELECT u FROM UserUenRol u WHERE u.estatus = :estatus")
    , @NamedQuery(name = "UserUenRol.findByFechaExpiracionCuenta", query = "SELECT u FROM UserUenRol u WHERE u.fechaExpiracionCuenta = :fechaExpiracionCuenta")
    , @NamedQuery(name = "UserUenRol.findByFechaExpiraPassword", query = "SELECT u FROM UserUenRol u WHERE u.fechaExpiraPassword = :fechaExpiraPassword")
    , @NamedQuery(name = "UserUenRol.findByDatosDeAuditoria", query = "SELECT u FROM UserUenRol u WHERE u.datosDeAuditoria = :datosDeAuditoria")
    , @NamedQuery(name = "UserUenRol.findByOwner", query = "SELECT u FROM UserUenRol u WHERE u.owner = :owner")
    , @NamedQuery(name = "UserUenRol.findByBusinessGroupId", query = "SELECT u FROM UserUenRol u WHERE u.businessGroupId = :businessGroupId")})
public class UserUenRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    @Column(name = "ID_EMPLEADO")
    private Long idEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ULTIMA_FECHA_DE_ENTRADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaDeEntrada;
    @Column(name = "CONTEO_DE_INTENTOS_FALLIDOS")
    private Short conteoDeIntentosFallidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 10)
    @Column(name = "PERFIL_DE_BUSQUEDA")
    private String perfilDeBusqueda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "FECHA_EXPIRACION_CUENTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpiracionCuenta;
    @Column(name = "FECHA_EXPIRA_PASSWORD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpiraPassword;
    @Size(max = 50)
    @Column(name = "DATOS_DE_AUDITORIA")
    private String datosDeAuditoria;
    @Column(name = "OWNER")
    private BigInteger owner;
    @Column(name = "BUSINESS_GROUP_ID")
    private BigInteger businessGroupId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userUenRol")
    private List<UenPorUsuario> uenPorUsuarioList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userUenRol")
    private List<RolesPorUsuario> rolesPorUsuarioList;

    public UserUenRol() {
    }

    public UserUenRol(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UserUenRol(String idUsuario, String password, String nombreUsuario, String email, String estatus) {
        this.idUsuario = idUsuario;
        this.password = password;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.estatus = estatus;
    }
    
    

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUltimaFechaDeEntrada() {
        if(ultimaFechaDeEntrada != null){
            return new Date (ultimaFechaDeEntrada.getTime());
        }else
        {
            Date d = new Date();
            return new Date(d.getTime());
        }
            
       
    }

    public void setUltimaFechaDeEntrada(Date ultimaFechaDeEntrada) {
        if(ultimaFechaDeEntrada != null)
            this.ultimaFechaDeEntrada = new Date (ultimaFechaDeEntrada.getTime());
    }

    public Short getConteoDeIntentosFallidos() {
        return conteoDeIntentosFallidos;
    }

    public void setConteoDeIntentosFallidos(Short conteoDeIntentosFallidos) {
        this.conteoDeIntentosFallidos = conteoDeIntentosFallidos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfilDeBusqueda() {
        return perfilDeBusqueda;
    }

    public void setPerfilDeBusqueda(String perfilDeBusqueda) {
        this.perfilDeBusqueda = perfilDeBusqueda;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechaExpiracionCuenta() {
        if(fechaExpiracionCuenta != null){
            return new Date (fechaExpiracionCuenta.getTime());
        }else
        {
            Date d = new Date();
            return new Date(d.getTime());
        }
    }

    public void setFechaExpiracionCuenta(Date fechaExpiracionCuenta) {
        if(fechaExpiracionCuenta != null)
            this.fechaExpiracionCuenta =new Date(fechaExpiracionCuenta.getTime());
    }

    public Date getFechaExpiraPassword() {
        if(fechaExpiraPassword != null){
            return new Date (fechaExpiraPassword.getTime());
        }else
        {
            Date d = new Date();
            return new Date(d.getTime());
        }
    }

    public void setFechaExpiraPassword(Date fechaExpiraPassword) {
        if(fechaExpiraPassword!=null)
            this.fechaExpiraPassword = new Date(fechaExpiraPassword.getTime());
    }

    public String getDatosDeAuditoria() {
        return datosDeAuditoria;
    }

    public void setDatosDeAuditoria(String datosDeAuditoria) {
        this.datosDeAuditoria = datosDeAuditoria;
    }

    public BigInteger getOwner() {
        return owner;
    }

    public void setOwner(BigInteger owner) {
        this.owner = owner;
    }

    public BigInteger getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(BigInteger businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    @XmlTransient
    public List<UenPorUsuario> getUenPorUsuarioList() {
        return uenPorUsuarioList;
    }

    public void setUenPorUsuarioList(List<UenPorUsuario> uenPorUsuarioList) {
        this.uenPorUsuarioList = uenPorUsuarioList;
    }

    @XmlTransient
    public List<RolesPorUsuario> getRolesPorUsuarioList() {
        return rolesPorUsuarioList;
    }

    public void setRolesPorUsuarioList(List<RolesPorUsuario> rolesPorUsuarioList) {
        this.rolesPorUsuarioList = rolesPorUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof UserUenRol)) {
            return false;
        }
        UserUenRol other = (UserUenRol) object;
        return this.idUsuario.equals(other.idUsuario);
    }

    @Override
    public String toString() {
        return "com.metalsa.core.model.UserUenRol[ idUsuario=" + idUsuario + " ]";
    }
    
}
