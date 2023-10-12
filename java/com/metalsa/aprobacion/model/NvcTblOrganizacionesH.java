package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import javax.persistence.EntityManager;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;

/**
 * Created by ruben.bresler on 05/07/2017.
 */
@Data
@Entity(name = "nvc_tbl_organizaciones_h")
@AllArgsConstructor
@NoArgsConstructor
@NamedStoredProcedureQuery(
        name = "NvcTblOrganizacionesH.findUenByUser",
        resultSetMappings = {"withUenDefault"},
        procedureName = "nvc_pkg_aprobacion_spx.get_uens_by_user_rest_friendly",
        parameters = {
            @StoredProcedureParameter(mode = REF_CURSOR, name = "cursor_out", type = void.class),            
            @StoredProcedureParameter(mode = IN, name = "p_id_user", type = String.class)
        }
)
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "withUenDefault",
            classes = {
                @ConstructorResult(
                    targetClass = UenWithDefault.class,
                    columns = {
                        @ColumnResult(name = "id_uen", type = Long.class),
                        @ColumnResult(name = "nombre_uen"),
                        @ColumnResult(name = "currency_code"),
                        @ColumnResult(name = "uen_default", type = Boolean.class),
                        @ColumnResult(name = "desc_uen"),
                        @ColumnResult(name = "interuen", type = Boolean.class)
                    }
                )
            }
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "NvcTblOrganizacionesH.getUensByUser",
            query = "select decode(tbl1.uen_default, 'S', 1, 'N', 0) as uen_default, tbl1.id_uen, "
            + "   tbl1.nombre_uen, tbl1.nombre_uen || tbl1.descinteruen as desc_uen, "
            + "   tbl1.currency_code, tbl1.interuen\n"
            + "from (\n"
            + "   select  uo.uen_default, o.id_uen, o.nombre_uen, o.currency_code, 0 as interuen, '' as descinteruen\n"
            + "   from nvc_tbl_organizaciones_h o\n"
            + "       inner join uen_por_usuario uo on (o.id_uen = uo.id_uen)\n"
            + "       inner join usuario u on (u.id_usuario = uo.id_usuario)and upper(u.id_usuario) = upper(?1)\n"
            + "   union\n"
            + "   select 'N' as uen_default, o2.id_uen, o2.nombre_uen, "
            + "       o2.currency_code, 1 as interuen, ' (inter uen)' as descinteruen\n"
            + "   from nvc_tbl_organizaciones_h o2 "
            + "   where (select count (id_rol) "
            + "           from roles_por_usuario\n"
            + "           where id_usuario = ?1 "
            + "               and id_rol in (select id_rol from rol where upper(nombre_rol) like '%INTERUEN%')) > 0\n"
            + "               and o2.active = 1\n"
            + "               and o2.id_uen not in (select n.id_uen "
            + "                       from nvc_tbl_organizaciones_h n\n"
            + "                           inner join uen_por_usuario nuo on (n.id_uen = nuo.id_uen)\n"
            + "                           inner join usuario nu on (nu.id_usuario = nuo.id_usuario) and "
            + "                               upper(nu.id_usuario) = upper(?1))\n"
            + ") tbl1\n"
            + "order by tbl1.uen_default desc, tbl1.interuen asc, tbl1.id_uen asc",
            resultSetMapping = "withUenDefault"
    )
    ,
    //<R17643>
    @NamedNativeQuery(
            name = "NvcTblOrganizacionesH.getUensActivasByUser",
            query = "select\n"
            + "    decode(uens.uen_default, 'S', 1, 0) as uen_default,\n"
            + "    uens.id_uen,\n"
            + "    uens.nombre_uen,\n"
            + "    uens.nombre_uen || uens.descinteruen as desc_uen,\n"
            + "    uens.currency_code,\n"
            + "    uens.interuen\n"
            + "from (\n"
            + "    select\n"
            + "        uo.uen_default,\n"
            + "        o.id_uen,\n"
            + "        o.nombre_uen,\n"
            + "        o.currency_code,\n"
            + "        0 as interuen,\n"
            + "        '' as descinteruen\n"
            + "    from nvc_tbl_organizaciones_h o\n"
            + "    inner join uen_por_usuario uo\n"
            + "    on (o.id_uen = uo.id_uen)\n"
            + "    and uo.id_usuario = :idUsuario\n"
            + "    union all\n"
            + "    select\n"
            + "        'N' as uen_default,\n"
            + "        uen.id_uen,\n"
            + "        uen.nombre_uen,\n"
            + "        uen.currency_code,\n"
            + "        1 as interuen,\n"
            + "        ' (inter uen)' as descinteruen\n"
            + "   from nvc_tbl_organizaciones_h uen\n"
            + "   left join uen_por_usuario uus\n"
            + "		on uus.id_uen = uen.id_uen\n"
            + "        and uus.id_usuario = :idUsuario\n"
            + "    where 1 = 1     \n"
            + "    and uus.id_uen is null\n"
            + "    and uen.active = 1\n"
            + "    and EXISTS(\n"
            + "        select 1 from roles_por_usuario ru\n"
            + "        where id_usuario = :idUsuario\n"
            + "        and id_rol = (\n"
            + "            select id_rol from rol where clave = 'INTER_UEN'\n"
            + "        )\n"
            + "    )\n"
            + ")uens\n"
            + "order by uens.uen_default desc, uens.interuen asc, uens.id_uen asc",
            resultSetMapping = "withUenDefault"
    ) //</R17643>
    ,
    @NamedNativeQuery(
            name = "NvcTblOrganizacionesH.getUensActivasByUserRol",
            query = "SELECT DECODE (uens.uen_default, 'S', 1, 0) AS uen_default,\n"
            + "         uens.id_uen,\n"
            + "         uens.nombre_uen,\n"
            + "         uens.nombre_uen || uens.descinteruen AS desc_uen,\n"
            + "         uens.currency_code,\n"
            + "         uens.interuen\n"
            + "    FROM (SELECT uo.uen_default,\n"
            + "                 o.id_uen,\n"
            + "                 o.nombre_uen,\n"
            + "                 o.currency_code,\n"
            + "                 0 AS interuen,\n"
            + "                 '' AS descinteruen\n"
            + "            FROM nvc_tbl_uen_usu_aprobacion ua\n"
            + "                 LEFT JOIN nvc_tbl_organizaciones_h o ON ua.id_uen = o.id_uen\n"
            + "                 INNER JOIN uen_por_usuario uo\n"
            + "                    ON (o.id_uen = uo.id_uen) AND uo.id_usuario = ua.id_usuario\n"
            + "           WHERE ua.id_usuario = :idUsuario AND ua.id_proceso_aprobacion = :idProceso\n"
            + "          UNION ALL\n"
            + "          SELECT 'N' AS uen_default,\n"
            + "                 uen.id_uen,\n"
            + "                 uen.nombre_uen,\n"
            + "                 uen.currency_code,\n"
            + "                 1 AS interuen,\n"
            + "                 ' (inter uen)' AS descinteruen\n"
            + "            FROM nvc_tbl_uen_usu_aprobacion usua\n"
            + "                 LEFT JOIN nvc_tbl_organizaciones_h uen\n"
            + "                    ON usua.id_uen = uen.id_uen\n"
            + "                 LEFT JOIN uen_por_usuario uus\n"
            + "                    ON uus.id_uen = uen.id_uen AND uus.id_usuario = usua.id_usuario\n"
            + "           WHERE     1 = 1\n"
            + "                 AND uus.id_uen IS NULL\n"
            + "                 AND uen.active = 1\n"
            + "                 AND EXISTS\n"
            + "                        (SELECT 1\n"
            + "                           FROM roles_por_usuario ru\n"
            + "                          WHERE id_usuario = :idUsuario\n"
            + "                                AND id_rol = (SELECT id_rol\n"
            + "                                                FROM rol\n"
            + "                                               WHERE clave = 'INTER_UEN'))\n"
            + "                 AND usua.id_usuario = :idUsuario\n"
            + "                 AND usua.id_proceso_aprobacion = :idProceso) uens\n"
            + "ORDER BY uens.uen_default DESC, uens.interuen ASC, uens.id_uen ASC",
            resultSetMapping = "withUenDefault"
    )
    ,
    //<PERFIL>
    @NamedNativeQuery(
            name = "NvcTblOrganizacionesH.getUensActivasDispByUser",
            resultClass = NvcTblOrganizacionesH.class,
            query = "select o.id_uen, o.nombre_uen, o.currency_code "
            + "from nvc_tbl_organizaciones_h o where o.active = 1 "
            + "and id_uen not in (select uu.id_uen from uen_por_usuario uu where uu.id_usuario = :usuario)"
    ) //</PERFIL>
    ,
    @NamedNativeQuery(
            name = "NvcTblOrganizacionesH.getUensPreaprobacion",
            resultClass = NvcTblOrganizacionesH.class,
            query = "SELECT o.id_uen, o.nombre_uen, o.currency_code "
            + "FROM nvc_tbl_organizaciones_h o "
            + "  JOIN parametros_por_uen_cia p1 "
            + "    ON o.id_uen = p1.id_uen "
            + "      AND p1.id_parametro = (SELECT id_parametro "
            + "                            FROM parametros_configuracion "
            + "                            WHERE nombre_parametro = 'CORREO ALMACEN') "
            + "  JOIN parametros_por_uen_cia p2 "
            + "    ON o.id_uen = p2.id_uen "
            + "       AND p2.id_parametro = (SELECT id_parametro "
            + "                             FROM parametros_configuracion "
            + "                             WHERE nombre_parametro = 'PRE_APPROVAL_ADMIN') "
            + "WHERE p1.valor = 'true' "
            + "  AND p2.valor = :usuario"
    )
    //<jl>
    ,
    @NamedNativeQuery(
            name = "NvcTblOrganizacionesH.getAllUensActivas",
            resultClass = NvcTblOrganizacionesH.class,
            query = "SELECT o.id_uen, o.nombre_uen, o.currency_code "
            + "FROM nvc_tbl_organizaciones_h o "
            + "WHERE o.active = 1 "
    )
    //</jl>
    ,
    @NamedNativeQuery(
            name = "NvcTblOrganizacionesH.findByCompany",
            resultClass = NvcTblOrganizacionesH.class,
            query = "SELECT o.id_uen, o.nombre_uen, o.currency_code "
            + " FROM nvc_tbl_organizaciones_h o "
            + " WHERE o.company = :company "
    ),//MDA_CONTRALOR
    @NamedNativeQuery(
            name = "NvcTblOrganizacionesH.getUenCc",
            resultClass = NvcTblOrganizacionesH.class,
            query = "SELECT o.id_uen, o.nombre_uen, o.currency_code "
            + "from nvc_tbl_uen_usu_aprobacion ua, nvc_tbl_organizaciones_h o, "
            + "nvc_tbl_procesos_aprobacion pa, parametros_por_uen_cia pu "
            + "where ua.id_usuario = :idUsuario and ua.id_uen = o.id_uen  "
            + "and ua.id_proceso_aprobacion = pa.id_proceso_aprobacion "
            + "and ua.id_uen = pu.id_uen and pu.id_parametro = 3 and pu.valor = 'CC' "
            + "and pa.descripcion = 'SPX_ADMIN_COMPRADORES' "
    )
    ,
        @NamedNativeQuery(
            name = "NvcTblOrganizacionesH.getUenCcAdmon",
            resultClass = NvcTblOrganizacionesH.class,
            query = "SELECT o.id_uen, o.nombre_uen, o.currency_code \n"
            + "from nvc_tbl_organizaciones_h o\n"
            + "where o.active =1"
    )
})
/*@NamedQueries({
    @NamedQuery(name = "NvcTblOrganizacionesH.findByIdUen", query = "SELECT n FROM NvcTblOrganizacionesH n WHERE n.idUen = ?1")
})*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NvcTblOrganizacionesH implements Serializable {

    @Column(name = "id_uen")
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre_uen")
    private String nombre;

    @Column(name = "currency_code")
    private String currency;
    
    /*public static NvcTblOrganizacionesH getOneByUEN(Integer uen, EntityManager em) {
        return em
                .createNamedQuery("NvcTblOrganizacionesH.findByIdUen", NvcTblOrganizacionesH.class)
                .setParameter("1", uen)
                .getSingleResult();
    }*/
}
