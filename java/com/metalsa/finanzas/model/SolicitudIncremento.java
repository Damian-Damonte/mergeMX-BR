package com.metalsa.finanzas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.ParameterMode;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.OUT;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.Filter;

/**
 *
 * @author JL
 */
@Data
//@Entity(name = "NVC_TBL_SOLICITUD_INCREMENTO")
@Entity
@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(
            name = "crear_solicitud",
            procedureName = "nvc_pkg_request_manager_spx.crear_solicitud",
            parameters = {

                        @StoredProcedureParameter(mode = IN, name = "p_id_uen", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_id_cc_destino", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_id_cc_origen", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_periodo_destino", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_periodo_origen", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_fecha_necesidad", type = Date.class),
                        @StoredProcedureParameter(mode = IN, name = "p_razon", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_usuario_creacion", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_categorias", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_tipo_solicitud", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "out_value", type = Integer.class),
                        @StoredProcedureParameter(mode = OUT, name = "out_message", type = String.class)
                }),
    @NamedStoredProcedureQuery(
            name = "actualizar_solicitud",
            procedureName = "nvc_pkg_request_manager_spx.orquestador",
            parameters = {

                        @StoredProcedureParameter(mode = IN, name = "p_ids", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_usuario", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_razon", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "p_accion", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "out_value", type = Integer.class),
                        @StoredProcedureParameter(mode = OUT, name = "out_message", type = String.class)
            }),
    @NamedStoredProcedureQuery(
            name = "solicitud_en_visto",
            procedureName = "nvc_pkg_request_manager_spx.solicitud_en_visto",
            parameters = {

                        @StoredProcedureParameter(mode = IN, name = "p_id", type = Long.class),
                        @StoredProcedureParameter(mode = OUT, name = "out_value", type = Integer.class),
                        @StoredProcedureParameter(mode = OUT, name = "out_message", type = String.class)
            }),
    @NamedStoredProcedureQuery(
            name = "get_count_aprob_cc",
            procedureName = "nvc_pkg_request_manager_spx.get_count_aprob_cc",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "P_USUARIO", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "TOTAL", type = Integer.class)
            }),
    @NamedStoredProcedureQuery(
            name = "get_count_aprob_finanzas",
            procedureName = "nvc_pkg_request_manager_spx.get_count_aprob_finanzas",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "P_USUARIO", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "TOTAL", type = Integer.class)
            }),
    @NamedStoredProcedureQuery(
            name = "get_es_preaprobador",
            procedureName = "nvc_pkg_request_manager_spx.get_es_preaprobador",
            parameters = {
                        @StoredProcedureParameter(mode = IN, name = "P_USUARIO", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "TOTAL", type = Integer.class)
            }),
    @NamedStoredProcedureQuery(
            name = "cancelar_solicitud",
            procedureName = "nvc_pkg_request_manager_spx.cancelar_solicitud",
            parameters = {

                        @StoredProcedureParameter(mode = IN, name = "p_id", type = Long.class),
                        @StoredProcedureParameter(mode = IN, name = "p_usuario", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "out_value", type = Integer.class),
                        @StoredProcedureParameter(mode = OUT, name = "out_message", type = String.class)
            }),
    @NamedStoredProcedureQuery(
            name = "siguiente_aprobador",
            procedureName = "nvc_pkg_request_manager_spx.siguiente_aprobador",
            resultSetMappings = "UsuarioMapping",
            parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,  name = "P_ID_SOLICITUD",    type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,  name = "P_ID_UEN",          type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,  name = "P_ID_CC_ORIGEN",    type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,  name = "P_TIPO",            type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_ID_USUARIOS",     type = String.class)
            })
})
//<R16788>
@NamedNativeQueries({ //<MDA_CONTRALOR>
    @NamedNativeQuery(
            name = "SolicitudIncremento.getSolicitudes",
            query = "select * from (\n"
            + "select\n"
            + "	si.id_solicitud_presupuesto,\n"
            + "	si.id_uen,\n"
            + "	si.id_cc_origen,\n"
            + "	si.id_cc_destino,\n"
            + "	get_periodo_idioma(?1,si.periodo_destino) periodo_destino,\n"
            + "	get_periodo_idioma(?1,si.periodo_origen) periodo_origen,\n"
            + "	si.fecha_necesidad,\n"
            + "	si.razon,\n"
            + "	si.usuario_creacion,\n"
            + "	get_nombre_usuario(si.usuario_creacion) nombre_usuario,\n"
            + "	si.fecha_creacion,\n"
            + "	(select nombre_uen\n"
            + "		from nvc_tbl_organizaciones_h uens\n"
            + "		where uens.id_uen = si.id_uen\n"
            + "	) nombre_uen,\n"
            + "	cc.codigo_cc,\n"
            + "	cc.nombre_cc,\n"
            + "	cc2.codigo_cc as codigo_cc_origen,\n"
            + "	cc2.nombre_cc as nombre_cc_origen,\n"
            + "	si.tipo\n"
            + " from\n"
            + "	nvc_tbl_solicitud_presupuesto si\n"
            + "	inner join nvc_vm_oa_cc cc \n"
            + "		on (si.id_uen = cc.id_uen \n"
            + "			and cc.id_cc = si.id_cc_destino \n"
            + "			and cc.lenguaje= ?1\n"
            + "		)\n"
            + "	left join nvc_vm_oa_cc cc2 \n"
            + "		on (si.id_uen = cc2.id_uen \n"
            + "			and cc2.id_cc = si.id_cc_origen \n"
            + "			and cc2.lenguaje= ?1\n"
            + "		)\n"
            + "	inner join nvc_tbl_proceso_uen_cc puc \n"
            + "		on puc.id_cc = si.id_cc_destino\n"
            + "	inner join nvc_tbl_proceso_uen pu\n"
            + "		on pu.id_proceso_uen	= puc.id_proceso_uen\n"
            + "		and pu.id_uen			= si.id_uen		\n"
            + "WHERE\n"
            + "	pu.responsable = ?2\n"
            + "	and si.tipo = 'INCREASE'\n"
            + "	and exists (\n"
            + "		select cp.id \n"
            + "		from NVC_TBL_CATEGORIA_PRESUPUESTO cp\n"
            + "		where cp.id_estatus = 19	-- POR APROBADR\n"
            + "		and cp.ID_SOLICITUD_PRESUPUESTO = si.ID_SOLICITUD_PRESUPUESTO\n"
            + "	)\n"
            + "UNION ALL\n"
            + "select \n"
            + "    si.id_solicitud_presupuesto,\n"
            + "    si.id_uen,\n"
            + "    si.id_cc_origen,\n"
            + "    si.id_cc_destino,\n"
            + "    get_periodo_idioma(?1,si.periodo_destino) periodo_destino,\n"
            + "    get_periodo_idioma(?1,si.periodo_origen) periodo_origen,\n"
            + "    si.fecha_necesidad,\n"
            + "    si.razon,\n"
            + "    si.usuario_creacion,\n"
            + "    get_nombre_usuario(si.usuario_creacion) nombre_usuario,\n"
            + "    si.fecha_creacion,\n"
            + "    (select nombre_uen\n"
            + "        from nvc_tbl_organizaciones_h uens\n"
            + "        where uens.id_uen = si.id_uen\n"
            + "    ) nombre_uen,\n"
            + "    cc.codigo_cc,\n"
            + "    cc.nombre_cc,\n"
            + "    cc2.codigo_cc as codigo_cc_origen,\n"
            + "    cc2.nombre_cc as nombre_cc_origen,\n"
            + "    si.tipo\n"
            + "from\n"
            + "    nvc_tbl_solicitud_presupuesto si\n"
            + "    inner join nvc_vm_oa_cc cc \n"
            + "        on (si.id_uen = cc.id_uen \n"
            + "            and cc.id_cc = si.id_cc_destino \n"
            + "            and cc.lenguaje= ?1\n"
            + "        )\n"
            + "    left join nvc_vm_oa_cc cc2 \n"
            + "        on (si.id_uen = cc2.id_uen \n"
            + "            and cc2.id_cc = si.id_cc_origen \n"
            + "            and cc2.lenguaje= ?1\n"
            + "        )\n"
            + "    inner join cc_por_usuario ccu \n"
            + "    on (ccu.tipo_de_relacion  in ('Resp','Del')   \n"
            + "        and ccu.id_cc = cc2.id_cc \n"
            + "        and ccu.id_uen = cc2.id_uen \n"
            + "        and ccu.id_proceso_uen is null)       \n"
            + "WHERE ccu.id_usuario = ?2\n"
            + "    and si.tipo = 'TRANSFER'\n"
            + "    and exists (\n"
            + "        SELECT\n"
            + "            cp.id\n"
            + "        FROM\n"
            + "            nvc_tbl_categoria_presupuesto cp\n"
            + "        WHERE\n"
            + "            cp.id_estatus = 19	-- POR APROBADR\n"
            + "            and cp.id_solicitud_presupuesto = si.id_solicitud_presupuesto)\n"
            + ")\n"
            + "ORDER BY id_solicitud_presupuesto asc", //</RDM54075>
    resultClass = SolicitudIncremento.class
    ),//<MDA_CONTRALOR>
    @NamedNativeQuery(//<R41223> --50=PENDIENTE
            name = "SolicitudIncremento.getSolicitudesFinanzas",
                query = "SELECT\n" +
                "	si.ID_SOLICITUD_PRESUPUESTO, \n" +
                "	si.id_uen,\n" +
                "	si.id_cc_origen,\n" +
                "	si.id_cc_destino,\n" +
                "	GET_PERIODO_IDIOMA(?1,si.periodo_destino) periodo_destino,\n" +
                "	GET_PERIODO_IDIOMA(?1,si.periodo_origen) periodo_origen,\n" +
                "	si.fecha_necesidad,\n" +
                "	si.razon,\n" +
                "	si.usuario_creacion,\n" +
                "	get_nombre_usuario(si.usuario_creacion) nombre_usuario,\n" +
                "	si.fecha_creacion,\n" +
                "	(	select uens.nombre_uen \n" +
                "		from nvc_tbl_organizaciones_h  uens\n" +
                "		where uens.id_uen = si.id_uen\n" +
                "	)nombre_uen,\n" +
                "	cc.codigo_cc,\n" +
                "	cc.nombre_cc,\n" +
                "	cc2.codigo_cc as codigo_cc_origen,\n" +
                "	cc2.nombre_cc as nombre_cc_origen,\n" +
                "	si.tipo\n" +
                "FROM\n" +
                "	NVC_TBL_SOLICITUD_PRESUPUESTO si\n" +
                "	INNER JOIN nvc_vm_oa_cc cc \n" +
                "		on si.id_uen = cc.id_uen \n" +
                "		and cc.id_cc = si.id_cc_destino \n" +
                "		and cc.lenguaje= ?1\n" +
                "	LEFT JOIN nvc_vm_oa_cc cc2 \n" +
                "		on si.id_uen = cc2.id_uen \n" +
                "		and cc2.id_cc = si.id_cc_origen \n" +
                "		and cc2.lenguaje= ?1\n" +
                "	INNER JOIN (\n" +
                "		select distinct\n" +
                "			pu.id_uen,\n" +
                "			pv.parameter_value id_usuario\n" +
                "		from nvc_tbl_parameter_uen pu\n" +
                "		inner join nvc_tbl_parameter_value pv\n" +
                "			on pv.id_parameter_uen = pu.id_parameter_uen\n" +
                "			and pu.id_parameter = (\n" +
                "				select id_parameter				\n" +
                "				from nvc_tbl_parameter\n" +
                "				where parameter_name = 'APROBADORES_PPTO_FINANZAS'\n" +
                "			)\n" +
                "			and pv.parameter_value = ?2 -- ID_USUARIO		\n" +
                "	) aprobador\n" +
                "		on aprobador.id_uen = si.id_uen\n" +
                "	WHERE 1 = 1\n" +
                " 	and exists ( -- verificar  que exista almenos una linea pendiente de aprobar\n" +
                "		select cp.id \n" +
                "		from NVC_TBL_CATEGORIA_PRESUPUESTO cp\n" +
                "		where cp.id_estatus = 50 --50=PENDIENTE\n" +
                "		and cp.ID_SOLICITUD_PRESUPUESTO = si.ID_SOLICITUD_PRESUPUESTO\n" +
                "	)\n" +
                "ORDER BY si.id_solicitud_presupuesto ASC",
            resultClass = SolicitudIncremento.class
    ),//<R41223>
    @NamedNativeQuery(
            name = "SolicitudIncremento.getHistoricoByUser",
                query = "SELECT\n" +
                    "	si.ID_SOLICITUD_PRESUPUESTO,\n" +
                    "	si.id_uen,\n" +
                    "	si.id_cc_origen,\n" +
                    "	si.id_cc_destino,\n" +
                    "	GET_PERIODO_IDIOMA(?1, si.periodo_destino) periodo_destino,\n" +
                    "	GET_PERIODO_IDIOMA(?1, si.periodo_origen) periodo_origen,\n" +
                    "	si.fecha_necesidad,\n" +
                    "	si.razon,\n" +
                    "	si.usuario_creacion,\n" +
                    "	usr.nombre_usuario,\n" +
                    "	si.fecha_creacion,\n" +
                    "	uens.nombre_uen,\n" +
                    "	cc.codigo_cc,\n" +
                    "	cc.nombre_cc,\n" +
                    "	cc2.codigo_cc as codigo_cc_origen,\n" +
                    "	cc2.nombre_cc nombre_cc_origen,\n" +
                    "	si.tipo\n" +
                    "FROM NVC_TBL_SOLICITUD_PRESUPUESTO si\n" +
                    "INNER JOIN nvc_tbl_organizaciones_h uens on (uens.id_uen = si.id_uen)\n" +
                    "INNER JOIN nvc_vm_oa_cc cc on (si.id_uen = cc.id_uen and cc.id_cc = si.id_cc_destino and cc.lenguaje=?1)\n" +
                    "LEFT JOIN nvc_vm_oa_cc cc2 on (si.id_uen = cc2.id_uen and cc2.id_cc = si.id_cc_origen and cc2.lenguaje= ?1)\n" +
                    //"-- INNER JOIN NVC_TBL_PROCESO_BY_CC pro on (pro.id_uen = si.id_uen and pro.id_cc = si.id_cc_destino)\n" +
                    "INNER JOIN USUARIO usr ON (usr.ID_USUARIO = si.usuario_creacion)\n" +
                    "WHERE si.usuario_creacion = ?2\n" +
                    "ORDER BY si.id_solicitud_presupuesto ASC",
            resultClass = SolicitudIncremento.class
    ),
    //</R16788>
    @NamedNativeQuery(
            name = "SolicitudIncremento.findByIdSolicitudPresupuesto",
                query = "SELECT si.ID_SOLICITUD_PRESUPUESTO,  \n" +
                        "  si.ID_UEN                   , \n" +
                        "  si.ID_CC_DESTINO             ,\n" +
                        "  si.ID_CC_ORIGEN              ,\n" +
                        "  si.PERIODO_DESTINO           ,\n" +
                        "  si.PERIODO_ORIGEN            ,\n" +
                        "  si.FECHA_NECESIDAD           ,\n" +
                        "  si.RAZON                     ,\n" +
                        "  si.USUARIO_CREACION          ,\n" +
                        "  '' nombre_usuario            ,\n" +
                        "  si.FECHA_CREACION            ,\n" +
                        "  '' nombre_uen                ,\n" +
                        "  '' codigo_cc                 ,\n" +
                        "  '' nombre_cc                 ,\n" +
                        "  '' codigo_cc_origen          ,\n" +
                        "  '' nombre_cc_origen          ,\n" +
                        "  si.TIPO                       \n" +
                        " FROM NVC_TBL_SOLICITUD_PRESUPUESTO si \n" +
                        " WHERE si.ID_SOLICITUD_PRESUPUESTO = ?1",
            resultClass = SolicitudIncremento.class
    )
    //</R16788>
        
})
public class SolicitudIncremento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column (name = "ID_SOLICITUD_PRESUPUESTO")
    private Long idSolicitudPresupuesto;

    @Column (name = "id_uen")
    private Long idUen;

    @Column (name = "ID_CC_DESTINO")
    private Long idCcDestino;

    @Column (name = "ID_CC_ORIGEN")
    private Long idCcOrigen;

    @Column (name = "PERIODO_DESTINO")
    private String periodoDestino;

    @Column (name = "PERIODO_ORIGEN")
    private String periodoOrigen;

    @Temporal (javax.persistence.TemporalType.DATE)
    @Column (name = "fecha_necesidad")
    private Date fechaNecesidad;

    private String razon;

    @Column (name = "usuario_creacion")
    private String usuarioCreacion;
    
    @Column (name = "nombre_usuario")
    private String nombreUsuario;
    
    @Temporal (javax.persistence.TemporalType.DATE)
    @Column (name = "fecha_creacion")
    private Date fechaCreacion;

    @OneToMany
    @JoinColumn (name = "ID_SOLICITUD_PRESUPUESTO")
    @OrderBy("id ASC")
    @Filter(name="estatusPartidaFilter", condition="ID_ESTATUS=:idEstatus")
    private Set<CategoriaSolicitud> categorias;

    @OneToMany
    @JoinColumn (name = "ID_SOLICITUD_PRESUPUESTO")
    private Set<AdjuntoSolicitud> adjuntos;

    @Column (name = "nombre_uen")
    private String nombreUen;

    @Column (name = "codigo_cc")
    private String codigoCC;
    
    @Column (name = "codigo_cc_origen")
    @JsonProperty("codigo_cc_origen")
    private String codigoCCOrigen;

    @Column (name = "nombre_cc")
    private String nombreCC;
    
    
    @Column (name = "nombre_cc_origen")
    @JsonProperty("nombre_cc_origen")
    private String nombreCCOrigen;
    
    @Column (name = "tipo")
    private String tipo;

    @Transient
    private String rowKey;
    
    @Transient
    private String sFechaNecesidad;

    public Date getFechaNecesidad() {
        return fechaNecesidad == null ? null : (Date) fechaNecesidad.clone();
    }

    public void setFechaNecesidad(Date fechaNecesidad) {
        if (fechaNecesidad == null) {
            this.fechaNecesidad = null;
        } else {
            this.fechaNecesidad = (Date) fechaNecesidad.clone();
        }
    }

    public Date getFechaCreacion() {
        return fechaCreacion == null ? null : (Date) fechaCreacion.clone();
    }

    public void setFechaCreacion(Date fechaCreacion) {
        if (fechaCreacion == null) {
            this.fechaCreacion = null;
        } else {
            this.fechaCreacion = (Date) fechaCreacion.clone();
        }
    }

    public String getRowKey() {
        this.setRowKey(this.getIdUen() + "_" + this.idCcDestino);
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }
    
    /**
     * @param sFechaNecesidad the sFechaNecesidad to set
     */
    public void setsFechaNecesidad(String sFechaNecesidad) {
        this.sFechaNecesidad = sFechaNecesidad;
    }

    /**
     * @return the sFechaNecesidad
     */
    public String getsFechaNecesidad() {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        this.setsFechaNecesidad(df.format(this.getFechaNecesidad()));
        return  sFechaNecesidad;
    
    }

}
