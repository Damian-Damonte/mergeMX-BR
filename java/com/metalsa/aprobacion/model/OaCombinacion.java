package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity(name = "NVC_TBL_OA_COMBINACIONES")
@IdClass(OaCombinacion.OaCombinacionPk.class)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt")),
        @SqlResultSetMapping(
                name = "Presupuesto",
                classes = {
                        @ConstructorResult(
                                targetClass = CategoryCentroCosto.class,
                                columns = {
                                        @ColumnResult(name = "period_onh", type = DoubleType.class),
                                        @ColumnResult(name = "period_bud", type = DoubleType.class),
                                        @ColumnResult(name = "period_act", type = DoubleType.class),
                                        @ColumnResult(name = "period_enc", type = DoubleType.class),
                                        @ColumnResult(name = "id_cuenta", type = LongType.class),
                                        @ColumnResult(name = "id_uen", type = LongType.class),
                                        @ColumnResult(name = "id_cc", type = LongType.class),
                                        @ColumnResult(name = "codigo_cc"),
                                        @ColumnResult(name = "segmento_3"),
                                        @ColumnResult(name = "category_id", type = LongType.class),
                                        @ColumnResult(name = "category_name"),

                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = "PresupuestoProyecto",
                classes = {
                        @ConstructorResult(
                                targetClass = PresupuestoProyecto.class,
                                columns = {
                                        @ColumnResult(name = "period_onh", type = DoubleType.class),
                                        @ColumnResult(name = "period_bud", type = DoubleType.class),
                                        @ColumnResult(name = "period_act", type = DoubleType.class),
                                        @ColumnResult(name = "period_enc", type = DoubleType.class),
                                        @ColumnResult(name = "id_proyecto", type = LongType.class),
                                        @ColumnResult(name = "proyecto"),
                                        @ColumnResult(name = "id_tarea", type = LongType.class),
                                        @ColumnResult(name = "tarea"),
                                        @ColumnResult(name = "id_tipo_gasto", type = LongType.class),
                                        @ColumnResult(name = "tipo_gasto")
                                }
                        )
                }
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "OaCombinacion.findPresupuestoCcByCuentaAndIdioma",
                resultSetMapping = "Presupuesto",
                query = "select a.id_cuenta, cat.id_uen, cc.id_cc, cc.codigo_cc, a.segmento_3, " +
                        "  decode(a.language, 'US', cat.category_name_english, " +
                        "         cat.category_name) category_name, " +
                        "  cat.period_bud, cat.period_act, cat.period_enc, " +
                        "  cat.period_onh, cat.category_id " +
                        "from nvc_tbl_oa_combinaciones a, icom_category_budget_v cat, " +
                        "   nvc_vm_oa_cc cc, nvc_vm_gl_periods t " +
                        "where a.id_cuenta = :cuenta  " +
                        "  and a.language = :idioma  " +
                        "  and cc.id_uen = a.id_uen " +
                        "  and cc.codigo_cc = a.segmento_2 " +
                        "  and cat.id_uen = a.id_uen " +
                        "  and cat.cost_center_id = cc.id_cc " +
                        "  and cat.period_name = t.period_name " +
                        "  and t.period_set_name = 'Contable' " +
                        "  and trunc(sysdate) between trunc(t.start_date) and trunc(t.end_date) " +
                        "  and icom_budget_trx_pkg.get_category(a.id_cuenta) = cat.category_id " +
                        "  and cc.lenguaje = a.language " +
                        "order by cat.category_id asc"
        ),
        @NamedNativeQuery(
                name = "OaCombinacion.findPresupuestoByCuentaInAndIdioma",
                resultClass = Presupuesto.class,
                resultSetMapping = "Presupuesto",
                query = "select a.id_cuenta, cat.id_uen, cc.id_cc, cc.codigo_cc, a.segmento_3, " +
                        "  decode(a.language, 'US', cat.category_name_english, " +
                        "         cat.category_name) category_name, " +
                        "  cat.period_bud, cat.period_act, cat.period_enc, " +
                        "  cat.period_onh, cat.category_id " +
                        "from nvc_tbl_oa_combinaciones a, icom_category_budget_v cat, " +
                        "   nvc_vm_oa_cc cc, nvc_vm_gl_periods t " +
                        "where a.id_cuenta in :cuentas  " +
                        "  and a.language = :idioma  " +
                        "  and cc.id_uen = a.id_uen " +
                        "  and cc.codigo_cc = a.segmento_2 " +
                        "  and cat.id_uen = a.id_uen " +
                        "  and cat.cost_center_id = cc.id_cc " +
                        "  and cat.period_name = t.period_name " +
                        "  and t.period_set_name = 'Contable' " +
                        "  and trunc(sysdate) between trunc(t.start_date) and trunc(t.end_date) " +
                        "  and icom_budget_trx_pkg.get_category(a.id_cuenta) = cat.category_id " +
                        "  and cc.lenguaje = a.language " +
                        "order by cat.category_id asc"
        ),
        @NamedNativeQuery(
                name = "OaCombinacion.budgetByUenAndCentroCostoAndIdioma",
                resultClass = Presupuesto.class,
                resultSetMapping = "Presupuesto",
                query = "select a.id_cuenta, cat.id_uen, cc.id_cc, cc.codigo_cc, a.segmento_3, " +
                        "  decode(a.language, 'US', cat.category_name_english, " +
                        "         cat.category_name) as category_name, " +
                        "  cat.period_bud, cat.period_act, cat.period_enc, " +
                        "  cat.period_onh, cat.category_id " +
                        "from nvc_tbl_oa_combinaciones a " +
                        "  join nvc_vm_oa_cc cc on cc.id_uen = a.id_uen " +
                        "    and cc.lenguaje = a.language " +
                        "    and cc.codigo_cc = a.segmento_2 " +
                        "  join nvc_vm_gl_periods t on t.period_set_name = 'Contable' " +
                        "    and trunc(sysdate) between trunc(t.start_date) and trunc(t.end_date) " +
                        "  join icom_category_budget_v cat on cat.id_uen = a.id_uen " +
                        "    and cat.cost_center_id = cc.id_cc " +
                        "    and cat.period_name = t.period_name " +
                        "    and cat.category_id = icom_budget_trx_pkg.get_category(a.id_cuenta) " +
                        "where a.id_uen = :uen  " +
                        "  and a.language = :idioma  " +
                        "  and cc.id_cc = :cc  " +
                        "order by cat.category_id asc /*#pageable*/"
        ),
        @NamedNativeQuery(
                name = "OaCombinacion.budgetByUenAndCentroCostoAndIdioma.count",
                resultSetMapping = "SqlResultSetMapping.count",
                query = "select count(1) as cnt " +
                        "from nvc_tbl_oa_combinaciones a " +
                        "  join nvc_vm_oa_cc cc on cc.id_uen = a.id_uen " +
                        "    and cc.lenguaje = a.language " +
                        "    and cc.codigo_cc = a.segmento_2 " +
                        "  join nvc_vm_gl_periods t on t.period_set_name = 'Contable' " +
                        "    and trunc(sysdate) between trunc(t.start_date) and trunc(t.end_date) " +
                        "  join icom_category_budget_v cat on cat.id_uen = a.id_uen " +
                        "    and cat.cost_center_id = cc.id_cc " +
                        "    and cat.period_name = t.period_name " +
                        "    and cat.category_id = icom_budget_trx_pkg.get_category(a.id_cuenta) " +
                        "where a.id_uen = :uen  " +
                        "  and a.language = :idioma  " +
                        "  and cc.id_cc = :cc "
        )
})
@NamedQuery(
        name = "OaCombinacion.findCuenta",
        query = "SELECT c FROM NVC_TBL_OA_COMBINACIONES c " +
                "WHERE c.cuenta = :cuenta AND c.uen = :uen AND c.idioma = :idioma"
)
public class OaCombinacion implements Serializable {
    @Id
    @Column(name = "id_cuenta")
    private Long cuenta;

    @Column(name = "segmento_1")
    private String segmento1;

    @Column(name = "segmento_2")
    private String segmento2;

    @Column(name = "segmento_3")
    private String segmento3;

    @Column(name = "segmento_4")
    private String segmento4;

    @Column(name = "segmento_5")
    private String segmento5;

    @Column(name = "segmento_6")
    private String segmento6;

    @Column(name = "segmento_7")
    private String segmento7;

    @Column(name = "segmento_8")
    private String segmento8;
    private String combinacion;

    @Column(name = "start_date_active")
    private Date startActive;

    @Column(name = "end_date_active")
    private Date endActive;
    
    @Column(name = "CUENTA_NATURAL")
    private String cuentaNatural;

    @Id
    @Column(name = "id_uen")
    private Long uen;

    @Id
    @Column(name = "language")
    private String idioma;

    @Column(name = "chart_of_accounts_id")
    private Long chartOfAccounts;


    @Transient
    private double saldo;
    

    /**
     * OaCombinacion primary key
     */
    @Data
    public static class OaCombinacionPk implements Serializable {
        @Id
        @Column(name = "id_cuenta")
        private Long cuenta;
        @Id
        @Column(name = "id_uen")
        private Long uen;
        @Id
        @Column(name = "language")
        private String idioma;
    }

    public Date getStartActive() {
        return startActive == null ? null : (Date) startActive.clone();
    }

    public void setStartActive(Date startActive) {
        if (startActive == null)
            this.startActive = null;
        else
            this.startActive = (Date) startActive.clone();
    }

    public Date getEndActive() {
        return endActive == null ? null : (Date) endActive.clone();
    }

    public void setEndActive(Date endActive) {
        if (endActive == null)
            this.endActive = null;
        else
            this.endActive = (Date) endActive.clone();
    }
}

