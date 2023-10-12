package com.metalsa.aprobacion.model;

import com.metalsa.core.model.Rol;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Modelo de roles por usuario
 *
 * @author ruben.bresler
 */
@Entity(name = "ROLES_POR_USUARIO")
@IdClass(RolesPorUsuario.Pk.class)
@Data
@NamedStoredProcedureQuery(
    name = "RolesPorUsuario.getUsersByRol",
    procedureName = "get_users_by_rol_uen",
    resultClasses = com.metalsa.recibos.model.Requisitor.class,
    parameters = {
        @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "p_clave_rol", type = String.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
    }
)
public class RolesPorUsuario implements Serializable {

    @Column(name = "id_rol")
    @Id
    private Long rol;

    @Column(name = "id_usuario")
    @Id
    private String usuario;

    @Column(name = "datos_de_auditoria")
    private String auditoria;

    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", updatable = false, insertable = false)
    @OneToOne(optional = false)
    private Rol erol;

    /**
     * Llave combinada de clase
     */
    @Data
    public static class Pk implements Serializable {

        @Column(name = "id_rol")
        @Id
        private Long rol;

        @Column(name = "id_usuario")
        @Id
        private String usuario;
    }
}
