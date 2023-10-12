package com.metalsa.core.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "CentroCostoUsuario.get_by_user_uen",
            procedureName = "NVC_PKG_CENTROS_COSTOS.GET_BY_USER_UEN",
            resultClasses = CentroCostoUsuario.class,
            parameters = {
                @StoredProcedureParameter(name = "p_lang", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_user", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_sbu", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "CentroCostoUsuario.get_by_user_uen_cc",
            procedureName = "NVC_PKG_CENTROS_COSTOS.GET_BY_USER_UEN_CC",
            resultClasses = CentroCostoUsuario.class,
            parameters = {
                @StoredProcedureParameter(name = "p_lang", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_user", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_sbu", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_cc", type = Long.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "CentroCostoUsuario.get_by_user_relation",
            procedureName = "NVC_PKG_CENTROS_COSTOS.GET_BY_USER_UEN_RELATION",
            resultClasses = CentroCostoUsuario.class,
            parameters = {
                @StoredProcedureParameter(name = "p_lang", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_user", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_relation", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_sbu", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "CentroCostoUsuario.get_by_user_uen_relations",
            procedureName = "NVC_PKG_CENTROS_COSTOS.GET_BY_USER_UEN_RELATIONS",
            resultClasses = CentroCostoUsuario.class,
            parameters = {
                @StoredProcedureParameter(name = "p_lang", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_user", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_relations", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_sbu", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "CentroCostoUsuario.get_dist_by_user_uen_relations",
            procedureName = "NVC_PKG_CENTROS_COSTOS.GET_DIS_BY_USER_UEN_RELATIONS",
            resultClasses = CentroCostoUsuario.class,
            parameters = {
                @StoredProcedureParameter(name = "p_lang", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_user", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_relations", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_sbu", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    )
})
@Entity
@Getter
@Setter
/**
 *
 * @author Oscar del Angel
 */
public class CentroCostoUsuario implements Serializable {

    @Id
    private Long rownum;

    
    @Column(name = "id_cc")
    private Long idCC;

    @Column(name = "id_cc",insertable = false,updatable = false)
    private Long cc;

    @Column(name = "id_uen")
    private Long idUen;

    @Column(name = "id_proceso_uen")
    private Long idProcesoUen;

    @Column(name = "codigo_cc")
    private String codigoCC;

    @Column(name = "nombre_cc")
    private String nombreCC;

    @Column(name = "tipo_de_relacion")
    private String relacion;

    @Column(name = "id_usuario")
    private String idUsuario;
    
    @Transient
    private Double balance;

}
