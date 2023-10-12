package com.metalsa.aprobacion.model;

import com.metalsa.requisicion.model.DcIdioma;
import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Usuario
 */
@Entity(name = "usuario")
@Data
@NamedQueries({
    @NamedQuery(
            name = "Usuario.findByIdUsuario",
            query = "SELECT u FROM com.metalsa.aprobacion.model.Usuario u WHERE u.id = ?1"
    )
})
public class Usuario implements Serializable {

    @Id
    @Column(name = "id_usuario")
    private String id;
    
    private String password;
    
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    
    private String email;
    
    private String idioma;
    
    private String estatus;
    
    @Column(name = "ultima_fecha_de_entrada")
    private Date ultimaEntrada;

    @Column(name = "conteo_de_intentos_fallidos")
    private Integer intentosFallidos;
    
    @Column(name = "fecha_expiracion_cuenta")
    private Date fechaExpiracionCuenta;
    
    @Column(name = "fecha_expira_password")
    private Date fechaExpiraPassword;

    @Column(name = "perfil_de_busqueda")
    private String perfilDeBusqueda;
    
    @Column(name = "id_sucursal_proveedor")
    private Long idSucursalProveedor;
    
    @Column(name = "DATOS_DE_AUDITORIA", length = 50)
    private String datosDeAuditoria;

    private String owner;
    
    @Column(name = "id_proveedor")
    private Long idProveedor;
    
    @Column(name = "id_empleado")
    private Long idEmpleado;
    
    @JoinColumn(name = "IDIOMA", referencedColumnName = "SC_ID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private DcIdioma idiomaEntity;

    @Column(name = "business_group_id")
    private Long businessGroupId;
	//<RELEASE ARG>
    public Usuario() {
        
    }

    
    public Usuario(String id, String nombreUsuario, String email,String idioma) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.idioma = idioma;
    }
    //</RELEASE ARG>
    
    public Date getUltimaEntrada() {
        return ultimaEntrada == null ? null : (Date) ultimaEntrada.clone();
    }

    public void setUltimaEntrada(Date ultimaEntrada) {
        if (ultimaEntrada == null)
            this.ultimaEntrada = null;
        else
            this.ultimaEntrada = (Date) ultimaEntrada.clone();
    }

    public Date getFechaExpiracionCuenta() {
        return fechaExpiracionCuenta == null ? null : (Date) fechaExpiracionCuenta.clone();
    }

    public void setFechaExpiracionCuenta(Date fechaExpiracionCuenta) {
        if (fechaExpiracionCuenta == null)
            this.fechaExpiracionCuenta = null;
        else
            this.fechaExpiracionCuenta = (Date) fechaExpiracionCuenta.clone();
    }

    public Date getFechaExpiraPassword() {
        return fechaExpiraPassword == null ? null : (Date) fechaExpiraPassword.clone();
    }

    public void setFechaExpiraPassword(Date fechaExpiraPassword) {
        if (fechaExpiraPassword == null)
            this.fechaExpiraPassword = null;
        else
            this.fechaExpiraPassword = (Date) fechaExpiraPassword.clone();
    }
    //<RELEASE ARG>
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }
    //</RELEASE ARG>
    
    public DcIdioma getIdiomaEntity() {
        return this.idiomaEntity;
    }
    
    public String getDatosDeAuditoria() {
        return this.datosDeAuditoria;
    }
}
