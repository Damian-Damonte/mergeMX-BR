/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.model;
 
import com.fasterxml.jackson.annotation.JsonProperty;
import com.metalsa.core.model.Uens;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import static javax.persistence.ParameterMode.*;
import lombok.Data;

/**
 * @author jose.rivera02
 */
@Entity 
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "getRequisPorAprobar",
            resultClasses = ReqPorAprobar.class,
            procedureName = "NVC_PKG_APROBACION_SPX.GET_REQUISICIONES",
            parameters = {
                        @StoredProcedureParameter(mode = REF_CURSOR, name = "cursor_out", type = void.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class)
            }
        ),
        @NamedStoredProcedureQuery(
            name = "getRequisPorPreAprobar",
            resultClasses = ReqPorAprobar.class,
            procedureName = "nvc_pkg_aprobacion_spx.get_preaprobadas",
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "cursor_out", type = void.class)
                ,
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class)
            }
        ),
        @NamedStoredProcedureQuery(
            name = "getRequisFueraRango",
            resultClasses = ReqPorAprobar.class,
            procedureName = "nvc_pkg_aprobacion_spx.get_fuerarango",
            parameters = {
                        @StoredProcedureParameter(mode = REF_CURSOR, name = "cursor_out", type = void.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class)
            }
        ),
        @NamedStoredProcedureQuery(
            name = "getRequisCriticas",
            resultClasses = ReqPorAprobar.class,
            procedureName = "nvc_pkg_aprobacion_spx.get_criticas",
            parameters = {
                        @StoredProcedureParameter(mode = REF_CURSOR, name = "cursor_out", type = void.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_uen", type = Long.class)
            }
        ),
        @NamedStoredProcedureQuery(
            name = "countRequisiciones",
            procedureName = "nvc_pkg_spx_approvals.count_lines",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "p_type", type = Integer.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_uen", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_id_uens", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "p_counter", type = Integer.class)
            }
        ),
        @NamedStoredProcedureQuery(
            name = "regresarRequisiciones",
            procedureName = "nvc_pkg_spx_approvals.return_requisition",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "p_requisicion", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_partida", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_comentario", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_modulo", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "p_result", type = Integer.class)
                }),
        @NamedStoredProcedureQuery(
            name = "cancelarRequisiciones",
            procedureName = "NVC_PKG_APROBACION_SPX.CANCELAR_REQUISICIONES",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "P_REQUISICIONES", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "P_RAZON_DE_RECHAZO", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "P_ID_APROBADOR", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "P_MODULO", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "P_OUT_ESTATUS", type = Integer.class)
                }),
        @NamedStoredProcedureQuery(
            name = "cancelByLine",
            procedureName = "nvc_pkg_spx_approvals.cancel_requisition",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "p_requisicion", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_partida", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_reason", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_modulo", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "p_result", type = Integer.class),
                }),
        @NamedStoredProcedureQuery(
            name = "preaprobar_requisicion",
            procedureName = "NVC_PKG_APROBACION_SPX.PRE_APROBAR_REQUISICION",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "p_requisicion", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_partida", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_modulo", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "p_result", type = Integer.class)
                }),
        @NamedStoredProcedureQuery(
            name = "aprobar_requisicion",
            procedureName = "nvc_pkg_spx_approvals.approve_requisition",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "p_requisicion", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_partida", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_modulo", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_id_cuenta", type = Integer.class),
                        @StoredProcedureParameter(mode = IN, name = "p_log", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "p_result", type = Integer.class)
                }),
        @NamedStoredProcedureQuery(
            name = "ini_aprobar_requisicion",
            procedureName = "nvc_pkg_spx_approvals.begin_approval",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "p_requisicion", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_partida", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_modulo", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_id_cuenta", type = Integer.class),
                        @StoredProcedureParameter(mode = OUT, name = "p_result", type = Integer.class)
                }),				
        @NamedStoredProcedureQuery(
            name = "aprobar_requisicion_critica",
            procedureName = "nvc_pkg_spx_approvals.approve_critical",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "p_requisicion", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_partida", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_solicitante", type = String.class),//<R17424>
                        @StoredProcedureParameter(mode = IN, name = "p_razon", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_modulo", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "p_result", type = Integer.class)
                }),
        @NamedStoredProcedureQuery(
            name = "blockRequisitionDetail",
            procedureName = "nvc_pkg_spx_approvals_external.select_line",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "p_requisicion", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_partida", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_estado", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_id_cuenta", type = Integer.class)
                }),
        @NamedStoredProcedureQuery(
            name = "verifyBudget",
            procedureName = "nvc_pkg_spx_approvals.verify_budget",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "p_requisicion", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_partida", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_cuenta", type = Integer.class),
                        @StoredProcedureParameter(mode = OUT, name = "p_result", type = Integer.class)
                }),
        
        //<ERM#14135>
        @NamedStoredProcedureQuery(
            name = "getRequisSuperAprobacion",
            resultClasses = ReqPorAprobar.class,
            procedureName = "NVC_PKG_APROBACION_SPX.get_superaprobaciones",
            parameters = {
                        @StoredProcedureParameter(mode = REF_CURSOR, name = "cursor_out", type = void.class),
                        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_id_uens", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_id_requisicion", type = String.class)
                }),
        //</ERM#14135>
        //<ERM#17422>
        @NamedStoredProcedureQuery(
            name = "preaprobarRequisiciones",
            procedureName = "NVC_PKG_APROBACION_SPX.PRE_APROBAR_REQUISICIONES",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "P_REQUISICIONES", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "P_APROBADOR", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "P_MODULO", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "P_OUT_ESTATUS", type = Integer.class)
            }
        ),
        //</ERM#17422>
        @NamedStoredProcedureQuery(
            name = "updateRazonExceso",
            procedureName = "nvc_pkg_spx_approvals.update_exeeded",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "p_requisicion", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_partida", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_razon", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_solucion", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "p_result", type = Integer.class)
            }
    ),
   //<T395493>
    @NamedStoredProcedureQuery(
        name = "guardaMailAprobacion",
        procedureName = "NVC_NOTIFICACIONES.ENVIA_NOTIFICACION",
        parameters = {
            @StoredProcedureParameter(mode = IN, name = "P_DESTINO", type = String.class),
            @StoredProcedureParameter(mode = IN, name = "P_DESTINO_CC", type = String.class),
            @StoredProcedureParameter(mode = IN, name = "P_ASUNTO", type = String.class),
            @StoredProcedureParameter(mode = IN, name = "P_MAIL_CUERPO", type = String.class),
            @StoredProcedureParameter(mode = OUT, name = "P_MESSAGE_OUT", type = String.class)
        }
    )//</T395493>
    ,@NamedStoredProcedureQuery(
            name = "ReqPorAprobar.updatePreaprobacion",
            procedureName = "nvc_pkg_spx_approvals.editar_proyecto_requisicion",
            parameters = {
                @StoredProcedureParameter(mode = IN, name = "p_idRequisicion", type = Integer.class)
                ,
                @StoredProcedureParameter(mode = IN, name = "p_idProyecto", type = Long.class)
                ,
                @StoredProcedureParameter(mode = IN, name = "p_idTarea", type = Long.class)
                ,
                @StoredProcedureParameter(mode = IN, name = "p_tipoGasto", type = String.class)
                ,
                @StoredProcedureParameter(mode = IN, name = "p_idCuenta", type = Long.class)
                ,
                @StoredProcedureParameter(mode = IN, name = "p_siguienteAprobador", type = String.class)
                ,
                @StoredProcedureParameter(mode = IN, name = "p_codProyecto", type = String.class)
                ,
                @StoredProcedureParameter(mode = IN, name = "p_codTarea", type = String.class)
                ,
                @StoredProcedureParameter(mode = IN, name = "p_segmento3", type = String.class)
                ,
                @StoredProcedureParameter(mode = IN, name = "p_segmento5", type = String.class)
                ,
                @StoredProcedureParameter(mode = OUT, name = "p_result", type = Integer.class)
            }
    ),
            @NamedStoredProcedureQuery(
            name = "guardaComentarioFad",
            procedureName = "NVC_PKG_SPX_FAD.GUARDA_COMENTARIO_FAD",
            parameters = {
                @StoredProcedureParameter(mode = IN, name = "P_ID_FAD", type = Integer.class),
                @StoredProcedureParameter(mode = IN, name = "P_APROBACION_ESPECIAL", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "P_COMENTARIO", type = String.class),
                @StoredProcedureParameter(mode = OUT, name = "VALUE_OUT", type = Integer.class)
            }
    ),
            @NamedStoredProcedureQuery(
            name = "getSiguienteAprobador",
            procedureName = "NVC_PKG_SPX_APPROVALS.GET_NEXT_APPROVER",
            parameters = {
                @StoredProcedureParameter(mode = IN, name = "P_ID_REQUISICION", type = Long.class),
                @StoredProcedureParameter(mode = IN, name = "P_ID_PARTIDA", type = Long.class),
                @StoredProcedureParameter(mode = IN, name = "P_APROBADOR", type = String.class),
                @StoredProcedureParameter(mode = OUT, name = "OUT_APPROVER", type = String.class),
                @StoredProcedureParameter(mode = OUT, name = "OUT_SPECIAL_APPROVAL", type = String.class)
            }
    ),
    @NamedStoredProcedureQuery(//<T420188>
        name = "ReqPorAprobar.verifyBudgetProyecto",
        procedureName = "nvc_pkg_spx_approvals.verify_budget_proyecto",
        parameters = {
            @StoredProcedureParameter(mode = IN, name = "p_requisicion", type = Long.class),
            @StoredProcedureParameter(mode = IN, name = "p_partida", type = Long.class),
            @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
            @StoredProcedureParameter(mode = OUT, name = "p_result", type = Integer.class)
        }
    ),
    @NamedStoredProcedureQuery(//<T428940>
        name = "ReqPorAprobar.verifyBudgetProyectoCheck",
        procedureName = "nvc_pkg_spx_approvals.VALIDA_PRESUP_PROY_SPX_CHECK",
        parameters = {
            @StoredProcedureParameter(mode = IN, name = "P_ID_UEN", type = Long.class),
            @StoredProcedureParameter(mode = IN, name = "P_ID_PROYECTO", type = Long.class),
            @StoredProcedureParameter(mode = IN, name = "P_MONTO_MXP", type = Double.class),
            @StoredProcedureParameter(mode = IN, name = "P_ID_CUENTA", type = Long.class),
            @StoredProcedureParameter(mode = OUT, name = "P_RETURN", type = Integer.class)
        }
    ),
    @NamedStoredProcedureQuery(//<T428940>
        name = "ReqPorAprobar.validaPresupProySpx",
        procedureName = "nvc_pkg_spx_approvals.VALIDA_PRESUP_PROY_SPX",
        parameters = {
            @StoredProcedureParameter(mode = IN, name = "P_ID_UEN", type = Long.class),
            @StoredProcedureParameter(mode = IN, name = "P_ID_PROYECTO", type = Long.class),
            @StoredProcedureParameter(mode = IN, name = "P_ID_TAREA", type = Long.class),
            @StoredProcedureParameter(mode = IN, name = "P_TIPO_GASTO", type = String.class),
            @StoredProcedureParameter(mode = IN, name = "P_MONTO_MXP", type = Double.class),
            @StoredProcedureParameter(mode = IN, name = "P_ACUMULAR", type = String.class),
            @StoredProcedureParameter(mode = IN, name = "P_ID_VAL", type = Long.class),
            @StoredProcedureParameter(mode = IN, name = "P_REFERENC", type = String.class),
            @StoredProcedureParameter(mode = OUT, name = "P_RETURN", type = Integer.class)
        }
    )
})
@NamedNativeQueries({
        //ReqPorAprobar.getRequisicionesCriticas: DEPRECADO
        //<ERM#14135>
        @NamedNativeQuery(
        name = "getUensSuperAprobador",
        resultSetMapping = "mapUensSuperAprobador",
        query = "SELECT\n"
        + "    param.id_uen,\n"
        + "    uen.nombre_uen\n"
        + "FROM Parametros_Por_Uen_Cia param,\n"
        + "NVC_TBL_ORGANIZACIONES_H uen\n"
        + "WHERE Param.Valor = :id_usuario\n"
        + "AND ( Param.Id_Parametro = 41 OR Param.Id_Parametro = 42)\n"
        + "AND uen.id_uen = param.id_uen "
        + "GROUP BY param.id_uen,uen.nombre_uen"
        //</ERM#14135>
    )
    ,
        @NamedNativeQuery(
            name = "getUensPreAprobador",
            query = "SELECT \n"
            + "  Uenusuario.Id_Uen\n"
            + "FROM Roles_Por_Usuario rolUsuario,\n"
            + "Uen_Por_Usuario uenUsuario,\n"
            + "Parametros_Por_Uen_Cia uenCia\n"
            + "WHERE Rolusuario.Id_Rol = 44 -- ROL PRE APROBADOR\n"
            + "AND Rolusuario.Id_Usuario = :id_usuario\n"
            + "AND Uenusuario.Id_Usuario = Rolusuario.Id_Usuario\n"
            + "AND Uenusuario.Id_Uen = Uencia.Id_Uen \n"
            + "AND Uencia.Id_Parametro = 40 -- ID PARAMETRO PRE-APROBACION\n"
            + "AND Uencia.Valor = 'true'    -- PREAPROBACION ACTIVADA\n"
    )
    ,
        @NamedNativeQuery(
            name = "ReqPorAprobar.notificacionActiva",
            query = "SELECT \n"
            + "    perfil.valor\n"
            + "FROM NVC_TBL_PERFIL perfil\n"
            + "WHERE perfil.ID_USUARIO = :id_usuario \n"
            + "AND perfil.id_notificacion = 25"
    )
    ,
        @NamedNativeQuery(
            name = "ReqPorAprobar.obtenerPerfil",
            query = "SELECT \n"
            + "    perfil.valor\n"
            + "FROM NVC_TBL_PERFIL perfil\n"
            + "WHERE perfil.ID_USUARIO = :id_usuario \n"
            + "AND perfil.id_notificacion = :id_notificacion\n"
    )
    ,
        @NamedNativeQuery(
            name = "ReqPorAprobar.countRequisicionCancApro",
            query = "SELECT\n"
            + "    count(1)\n"
            + "FROM detalle_de_requisicion detalle\n"
            + "WHERE detalle.id_requisicion = :id_requisicion\n"
            + "AND detalle.id_estatus  in(\n"
            + "    1, --APROBADA,\n"
            + "    3 -- CANCELADA\n"
            + ")"
    )//<RELEASE ARG>
    ,
    @NamedNativeQuery(
        name = "ReqPorAprobar.getDetalleRequisicion",
        query = "select\n"
        + "	id_partida,\n"
        + "	id_requisicion,\n"
        + " descripcion_producto,\n"
        + " fecha_requerida,\n"
        + " cantidad_requerida,\n"
        + " id_unidad_de_medida,\n"
        + " precio,\n"
        + " monto_extendido\n"
        + " FROM detalle_De_Requisicion\n"
        + " WHERE 1 = 1\n"
        + " AND id_requisicion = ?1\n"
        + " AND estatus = 'POR APROBAR'",
        resultSetMapping = "map.getDetalleRequisicion"
    )
    ,
    @NamedNativeQuery(
        name = "ReqPorAprobar.getOneByIdRequisicion",
        query = "select\n"
        + "	req.id_requisicion,\n"
        + "    req.id_usuario,\n"
        + "    uen.id_uen,\n"
        + "    uen.nombre_uen, \n"
        + "    sysdate fecha_requerida,\n"
        + "    detalle.fuente,\n"
        + "    DECODE (detalle.id_proyecto,0,NULL,detalle.id_proyecto) id_proyecto,\n"
        + "    DECODE (detalle.id_cc,0,NULL,detalle.id_cc) id_cc\n"
        + "FROM requisicion req,\n"
        + "detalle_de_requisicion detalle,\n"
        + "nvc_tbl_organizaciones_h uen\n"
        + "WHERE  1 = 1\n"
        + "AND req.id_requisicion      = ?1\n"
        + "AND detalle.id_requisicion  = req.id_requisicion\n"
        + "AND detalle.id_partida      = 1\n"
        + "AND req.id_uen              = uen.id_uen",
        resultSetMapping = "map.requisicionUnica"
    ),
    @NamedNativeQuery(
        name = "ReqPorAprobar.getByIdRequisicion",
        query = "select  id_requisicion,id_partida,aprobacion_especial from "
                + "detalle_de_requisicion "
                + "where id_partida=:id_partida  and id_requisicion =:id_requisicion",
        resultSetMapping = "map.preAprobacion"
    )
})

@SqlResultSetMappings(
    {
        @SqlResultSetMapping(
            name = "mapUensSuperAprobador",
            classes = @ConstructorResult(
                targetClass = Uens.class,
                columns = {
                    @ColumnResult(name = "id_uen", type = Integer.class)
                    ,
                        @ColumnResult(name = "nombre_uen", type = String.class)
                }
        )
)
        ,
        @SqlResultSetMapping(
            name = "map.getDetalleRequisicion",
            classes = {
                @ConstructorResult(
                    targetClass = ReqPorAprobar.class,
                    columns = {
                        @ColumnResult(name = "id_partida", type = Integer.class),
                        @ColumnResult(name = "cantidad_requerida", type = Double.class),
                        @ColumnResult(name = "id_unidad_de_medida", type = String.class),
                        @ColumnResult(name = "descripcion_producto", type = String.class),
                        @ColumnResult(name = "precio", type = Double.class),
                        @ColumnResult(name = "fecha_requerida", type = Date.class),
                        @ColumnResult(name = "monto_extendido", type = Double.class)
                    }
                )
            }
        ),
         
        @SqlResultSetMapping(
            name = "map.requisicionUnica",
            classes = {
                @ConstructorResult(
                    targetClass = ReqPorAprobar.class,
                    columns = {
                        @ColumnResult(name = "id_requisicion", type = Integer.class),
                        @ColumnResult(name = "id_usuario", type = String.class),
                        @ColumnResult(name = "id_uen", type = Long.class),
                        @ColumnResult(name = "nombre_uen", type = String.class),
                        @ColumnResult(name = "fecha_requerida", type = Date.class),
                        @ColumnResult(name = "fuente", type = String.class),
                        @ColumnResult(name = "id_proyecto", type = Integer.class),
                        @ColumnResult(name = "id_cc", type = Integer.class)
                    }
                )
            }
        ),
        @SqlResultSetMapping(
            name = "map.preAprobacion",
            classes = {
                @ConstructorResult(
                    targetClass = ReqPorAprobar.class,
                    columns = {
                        @ColumnResult(name = "id_requisicion", type = Integer.class),
                        @ColumnResult(name = "id_partida", type = Integer.class),
                        @ColumnResult(name = "aprobacion_especial", type = String.class)
                    }
                )
            }
        )

    })
//</RELEASE ARG>
@IdClass(ReqPorAprobarPK.class)
@Data
public class ReqPorAprobar implements Serializable {

    @Id
    @Column(name = "id_requisicion")
    private Integer idRequisicion;
    @Id
    @Column(name = "id_partida")
    private Integer idPartida;

    @Column(name = "fuente")
    private String fuente;
    @Column(name = "nombre_fuente")
    private String nfuente;
    @Column(name = "codigo_cc")
    private String codigoCC;
    @Column(name = "id_usuario")
    private String idUsuario;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Column(name = "siguiente_aprobador")
    private String siguienteAprobador;

    @Column(name = "cantidad_requerida")
    private Double cantidadRequerida;//</RELEASE ARG>
    @Column(name = "id_unidad_de_medida")
    private String idUnidadMedida;
    @Column(name = "urgente")
    private String urgente;
    @Column(name = "razon_urgencia")
    private String razonUrgencia;
    @Column(name = "id_producto")
    private String idItem;
    @Column(name = "cod_producto")
    private String codigoItem;
    @Column(name = "descripcion_producto")
    private String descripcionItem;
    @Column(name = "id_moneda")
    private String idMoneda;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Column(name = "nombre_proveedor")
    private String proveedor;
    @Column(name = "tipo_requisicion")
    private String tipoRequisicion;
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Column(name = "cod_proyecto")
    private String codigoProyecto;
    @Column(name = "cod_tarea")
    private String codigoTarea;
    @Column(name = "nombre_tarea")
    private String nombreTarea;
    @Column(name = "tipo_gasto")
    private String tipoGasto;
    @Column(name = "seleccion")
    private String seleccion;
    @Column(name = "razon_seleccion")
    private String razonSeleccion;
    @Column(name = "id_uen")
    private Long idUen;
    @Column(name = "nombre_uen")
    private String uen;
    @Column(name = "id_centro_de_costos")
    private Integer idCC;
    @Column(name = "proceso")
    private String proceso;
    @Column(name = "fecha_requerida")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRequerida;
    @Column(name = "mejor_opcion")
    private String mejorOpcion;
    @Column(name = "monto_extendido")
    private Double montoExtendido;
    @Column(name = "gasto_ad")
    private Double gastoAd;
    @Column(name = "category_id")
    private Integer idCategoria;

    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @Column(name = "combinacion")
    private String combinacion;

    @Column(name = "id_comprador")
    private String idComprador;

    @Column(name = "comprador")
    private String comprador;

    @Column(name = "num_cotizacion")
    private String cotizacion;

    @Column(name = "tipo_de_relacion")
    private String tipoRelacion;

    @Column(name = "nombre_cc")
    private String nombreCc;

    @Column(name = "nombre_proyecto")
    private String proyecto;

    @Column(name = "id_tarea")
    private Integer idTarea;

    @Column(name = "incoterm")
    private String incoterm;

    @Column(name = "monto_convertido")
    private Double montoConvertido;

    @Column(name = "id_moneda_uen")
    private String idMonedaUen;

    @Transient
    @JsonProperty("orden_fuente")
    private int ordenFuente;

    @JsonProperty("id_familia")
    private Integer idFamilia;

    //<R20742>
    @Column(name = "app_origen")
    private String appOrigen;
    //</R20742>
    
    @Column(name = "id_fad")
    private Integer idFad;
    
    @Column(name = "tiene_fad")
    private Boolean tieneFad;
    
    @Column(name = "aprobacion_especial")
    private String aprobacionEspecial;
    
    @Column(name = "aprobaciones_especiales")
    private String aprobaciones_especiales;
    
    @Column(name = "estatus")
    private String estatus;
    
    @Column(name = "aprueba_exceso")
    private Integer apruebaExceso;

    @JsonProperty("ng_checked")
    @Transient
    private boolean ngChecked;

    @JsonProperty("ng_semaforo")
    @Transient
    private String ngSemaforo;

    @JsonProperty("ng_activo")
    @Transient
    private boolean ng_activo;

    @JsonProperty("razon_exceso")
    @Transient
    private String razonExceso;

    @JsonProperty("solucion")
    @Transient
    private String solucion;

    @JsonProperty("disable_btn")
    @Transient
    private boolean disableBtn;
	
    @Column(name = "id_proceso_uen")
    private String procesoUen;
    
    @Column(name = "id_tipo_cargo")
    private Integer idTipoCargo;
    
    @Column(name="NOMBRE_CATEGORIA")
    private String nombreCategoria;
    
    @Column(name="DESC_ORIGINAL")
    private String descOriginal;
    
    @Column(name="DESC_CAMBIADA")
    private String descCambiada;
    
    @Column(name = "pedido_especial")
    private Integer pedidoEspecial;
    
	//<RELEASE ARG>
    public ReqPorAprobar() {
    }

    public ReqPorAprobar(
            Integer idRequisicion,
            Integer idPartida,
            String aprobacionEspecial
    ) {
        this.idRequisicion = idRequisicion;
        this.idPartida =idPartida;
        this.aprobacionEspecial=aprobacionEspecial;
    }

    public ReqPorAprobar(
        Integer idRequisicion,
        String idUsuario,
        Long idUen,
        String uen,
        Date fechaRequerida,
        String fuente,
        Integer idProyecto,
        Integer idCC
    ) {
        this.idRequisicion = idRequisicion;
        this.idUsuario = idUsuario;
        this.idUen = idUen;
        this.uen = uen;
        this.fechaRequerida = fechaRequerida == null ? null : new Date(fechaRequerida.getTime());
        this.fuente = fuente;
        this.idProyecto = idProyecto;
        this.idCC       = idCC;
    }

    public ReqPorAprobar(Integer idPartida, Double cantidadRequerida, String idUnidadMedida, String descripcionItem, Double precio, Date fechaRequerida, Double montoExtendido) {
        this.idPartida = idPartida;
        this.cantidadRequerida = cantidadRequerida;
        this.idUnidadMedida = idUnidadMedida;
        this.descripcionItem = descripcionItem;
        this.precio = precio;
        this.fechaRequerida = fechaRequerida == null ? null : new Date(fechaRequerida.getTime());
        this.montoExtendido = montoExtendido;
    }

    //<ERM#14135>
    public ReqPorAprobar(String fuente, Integer idRequisicion, 
            String codigoCC, String idUsuario, String nombreUsuario, String siguienteAprobador,Integer idPartida,
            Double cantidadRequerida, String idUnidadMedida, String urgente, String descripcionItem,
            String idMoneda, Double precio, Integer idProveedor, String proveedor, String tipoRequisicion,
            Integer idProyecto, String codigoProyecto, Long idUen, String uen, Integer idCC,
            Date fechaRequerida, Double montoExtendido, Integer idCategoria, Integer idCuenta, String tipoRelacion) {
        this.fuente = fuente;
        this.idRequisicion = idRequisicion;
        this.idPartida = idPartida;
        this.codigoCC = codigoCC;
        this.idUsuario = idUsuario;
        this.siguienteAprobador = siguienteAprobador;
        this.nombreUsuario = nombreUsuario;
        this.cantidadRequerida = cantidadRequerida;
        this.idUnidadMedida = idUnidadMedida;
        this.urgente = urgente;
        this.descripcionItem = descripcionItem;
        this.idMoneda = idMoneda;
        this.precio = precio;
        this.idProveedor = idProveedor;
        this.proveedor = proveedor;
        this.tipoRequisicion = tipoRequisicion;
        this.idProyecto = idProyecto;
        this.codigoProyecto = codigoProyecto;
        this.idUen = idUen;
        this.uen = uen;
        this.idCC = idCC;
        this.fechaRequerida = fechaRequerida == null ? null : new Date(fechaRequerida.getTime());
        this.montoExtendido = montoExtendido;
        this.idCategoria = idCategoria;
        this.idCuenta = idCuenta;
        this.tipoRelacion = tipoRelacion;
    }
    //</ERM#14135>
    
    public int getOrdenFuente() {

        if (null == fuente || fuente.isEmpty()) {
            return (ordenFuente = 4);
        }

        if (ordenFuente != 0) {
            return ordenFuente;
        }

        switch (fuente.toUpperCase()) {
            case "A":
            case "L":
            case "K":
            case "D":
                ordenFuente = 1;
                break;
            case "E":
            case "P":
            case "V":
                ordenFuente = 2;
                break;
            case "C":
                ordenFuente = 3;
                break;
            default:
                ordenFuente = 4;
                break;
        }

        return ordenFuente;
    }

    public Date getFechaRequerida() {
        return fechaRequerida == null ? null : new Date(fechaRequerida.getTime());
    }

    public void setFechaRequerida(Date fechaRequerida) {
        if (fechaRequerida == null) {
            this.fechaRequerida = null;
        } else {
            this.fechaRequerida = new Date(fechaRequerida.getTime());
        }
    }

    
    @Override
    public String toString() {
        return "ReqPorAprobar{" + "idRequisicion=" + idRequisicion + ", idPartida=" + idPartida + ", cantidadRequerida=" + cantidadRequerida + ", idUnidadMedida=" + idUnidadMedida + ", descripcionItem=" + descripcionItem + ", precio=" + precio + ", fechaRequerida=" + fechaRequerida + ", montoExtendido=" + montoExtendido + '}';
    }
    
    
    
    }
