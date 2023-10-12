package com.metalsa.contralor.model;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.contralor.controller.UenContralor;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "Configuracion.findByUen",
            resultClass = Configuracion.class,
            query = "select\n"
            + "    p.id_parameter,\n"
            + "    p.parameter_descripcion,\n"
            + "    p.parameter_name,\n"
            + "    pu.id_parameter_uen,\n"
            + "    pu.parameter_condition,\n"
            + "    (\n"
            + "        select parameter_type\n"
            + "        from nvc_tbl_parameter_type\n"
            + "        where id_type = p.parameter_type\n"
            + "    ) type\n"
            + "from nvc_tbl_parameter p\n"
            + "join nvc_tbl_parameter_uen pu\n"
            + "on pu.id_parameter = p.id_parameter\n"
            + "where pu.id_uen = ?1"
    )
    ,
    @NamedNativeQuery(
            name = "Configuracion.findByUenName",
            resultClass = Configuracion.class,
            query = "select\n"
            + "	p.id_parameter,\n"
            + "	p.parameter_descripcion,\n"
            + "	p.parameter_name,\n"
            + "	pu.id_parameter_uen,\n"
            + "	pu.parameter_condition,\n"
            + "	(\n"
            + "		select parameter_type\n"
            + "		from nvc_tbl_parameter_type\n"
            + "		where id_type = p.parameter_type\n"
            + "	) type\n"
            + "from nvc_tbl_parameter p\n"
            + "join nvc_tbl_parameter_uen pu\n"
            + "on pu.id_parameter = p.id_parameter\n"
            + "where pu.id_uen = ?1\n"
            + "and p.parameter_name = ?2"
    )
    ,
    @NamedNativeQuery(
            name = "Configuracion.getAuthorizedUensByName",
            resultClass = NvcTblOrganizacionesH.class,
            query = "SELECT o.id_uen, o.nombre_uen, o.currency_code\n"
            + "FROM nvc_tbl_organizaciones_h o\n"
            + "WHERE o.active = 1\n"
            + "and o.id_uen in (\n"
            + "   SELECT pu.id_uen\n"
            + "   FROM nvc_tbl_parameter_uen  pu\n"
            + "   inner join nvc_tbl_parameter_value pv\n"
            + "   on pv.id_parameter_uen = pu.id_parameter_uen\n"
            + "   WHERE id_parameter in (\n"
            + "       select id_parameter  \n"
            + "       from nvc_tbl_parameter\n"
            + "       where parameter_name = ?1\n"
            + "   )\n"
            + "   and (pv.parameter_value = 'Y' or pv.parameter_condition  = 'Y' )\n"
            + "   and (pu.parameter_condition = 'Y' or pu.parameter_condition is null)\n"
            + "   and pu.id_uen in (select uu.id_uen from uen_por_usuario uu where uu.id_usuario = ?2 )"
            + ")"
    )

})
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "Configuracion.update_configuration",
            procedureName = "nvc_pkg_parameters_spx.update_parameter",
            parameters = {
                @StoredProcedureParameter(name = "p_uen",type = Integer.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_user",type = String.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_key",type = String.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_condition",type = String.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_values",type = String.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_remplazable",type = String.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_out_result",type = String.class ,mode = ParameterMode.OUT)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Configuracion.INSERTA_ROL",
            procedureName = "NVC_PKG_UEN_USU_APROBACION.INSERTA_ROL",
            parameters = {
                @StoredProcedureParameter(name = "p_id_usuario",type = String.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_proceso_aprobacion",type = Integer.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "out_value",type = Integer.class ,mode = ParameterMode.OUT),
                 @StoredProcedureParameter(name = "out_message",type = String.class ,mode = ParameterMode.OUT)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Configuracion.getUensByApprovalProcess",
            procedureName = "nvc_pkg_parameters_spx.find_uens_by_approv_process",
            resultClasses = UenContralor.class,
            parameters = {
                @StoredProcedureParameter(name = "p_user",type = String.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_process",type = String.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cursor_out",type = Void.class ,mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Configuracion.getUensByParamValue",
            procedureName = "nvc_pkg_parameters_spx.find_uens_by_param_value",
            resultClasses = UenContralor.class,
            parameters = {
                @StoredProcedureParameter(name = "p_parameter_name",type = String.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_parameter_value",type = String.class ,mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cursor_out",type = Void.class ,mode = ParameterMode.REF_CURSOR)
            }
    )
})

@Getter
@Setter
@Entity
public class Configuracion implements Serializable {

    @Id
    @Column(name = "id_parameter")
    private Long idParameter;

    @Column(name = "id_parameter_uen")
    private Long idParameterUen;

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "parameter_descripcion")
    private String parameterDescription;

    @Column(name = "parameter_condition")
    private String condition;

    @Column(name = "type")
    private String type;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_parameter_uen", referencedColumnName = "id_parameter_uen", insertable = false, updatable = false)
    private List<Valores> values;

    @Transient
    private String remplazable;
}
