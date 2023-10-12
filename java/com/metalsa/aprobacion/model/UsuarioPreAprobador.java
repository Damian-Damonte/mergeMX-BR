package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity
@Table(name = "preaprobadores_x_proyecto")
@IdClass(UsuarioPreAprobador.Pk.class)
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "addRolPreaprobador",
                procedureName = "nvc_pkg_spx_approvals.add_preapproval_rol",
                parameters = {
                        @StoredProcedureParameter(name = "p_uen", type=Long.class),
                        @StoredProcedureParameter(name = "p_proyecto", type=Long.class),
                        @StoredProcedureParameter(name = "p_usuario", type=String.class),
                        @StoredProcedureParameter(name = "p_result", type=Integer.class, mode = ParameterMode.OUT)
                }
        )
})
@Data
public class UsuarioPreAprobador implements Serializable {
    @Id
    private String idUsuario;
    @Id
    private Long idUen;
    @Id
    private Long idProyecto;
    private String nombreUsuario;
    private String nombreProyecto;

    /**
     * Pk
     */
    @Data
    public static class Pk implements Serializable {
        @Id
        private String idUsuario;
        @Id
        private Long idUen;
        @Id
        private Long idProyecto;
    }
}
