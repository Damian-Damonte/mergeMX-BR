package com.metalsa.core.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

/**
 *
 * @author Edgar Leal
 *
 *
 *
 */
@Entity
@Table(name = "DC_COMPRADOR_FAMILIA")
@NamedQuery(name = "User.findByEmailAddress",
        query = "select u from User u where u.numeroFamilia = ?1")
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "addRolUsers",
            procedureName = "ASIGNAR_ROL_USUARIO",
            parameters = {
                @StoredProcedureParameter(name = "p_usuarios", type = String.class, mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(name = "p_nombre_rol", type = String.class, mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(name = "p_result", type = Integer.class, mode = ParameterMode.OUT)
            }
    )
    ,
    @NamedStoredProcedureQuery(
                name = "removeRolPreaprobador",
                procedureName = "REMOVER_ROL_PREAPROBADOR_PROY"
        ),
    @NamedStoredProcedureQuery(
            name="getRolByNombreRolUsuario",
            procedureName ="getRolByNombreRolUsuario",
            parameters = {
                @StoredProcedureParameter(name = "idUsuario", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "resultado", type = Integer.class, mode = ParameterMode.OUT)
            }
    )
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_COMPRADOR_FAMILIA")
    private Long idCompradorFamilia;
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    @Column(name = "ID_FAMILIA")
    private Long idFamilia;
    @Column(name = "ID_UEN")
    private Long idUen;
    @Column(name = "NOMBRE_FAMILIA")
    private String nombreFamilia;
    @Column(name = "NUMERO_FAMILIA")
    private String numeroFamilia;

    public User() {
    }

    public Long getIdCompradorFamilia() {
        return idCompradorFamilia;
    }

    public void setIdCompradorFamilia(Long idCompradorFamilia) {
        this.idCompradorFamilia = idCompradorFamilia;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Long idFamilia) {
        this.idFamilia = idFamilia;
    }

    public Long getIdUen() {
        return idUen;
    }

    public void setIdUen(Long idUen) {
        this.idUen = idUen;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public String getNumeroFamilia() {
        return numeroFamilia;
    }

    public void setNumeroFamilia(String numeroFamilia) {
        this.numeroFamilia = numeroFamilia;
    }

}
