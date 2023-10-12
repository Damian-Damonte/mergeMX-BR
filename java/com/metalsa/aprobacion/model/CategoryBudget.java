package com.metalsa.aprobacion.model;

//<MDA_INC_DEC>
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
//<MDA_INC_DEC>
import lombok.Data;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.OUT;


/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity(name = "icom_category_budget_v")
@Data
//<MDA_INC_DEC>
@NamedStoredProcedureQueries({
//</MDA_INC_DEC>
        @NamedStoredProcedureQuery(
                name = "CategoryBudget.transferenciaPresupuesto",
                procedureName = "nvc_pkg_spx_budget.transfer_budget_spx",
                parameters = {
                        @StoredProcedureParameter(mode=IN, name="p_uen_o", type=Long.class),
                        @StoredProcedureParameter(mode=IN, name="p_period_o", type=String.class),
                        @StoredProcedureParameter(mode=IN, name="p_cc_o", type=Long.class),
                        @StoredProcedureParameter(mode=IN, name="p_category_o", type=Long.class),
                        @StoredProcedureParameter(mode=IN, name="p_uen_d", type=Long.class),
                        @StoredProcedureParameter(mode=IN, name="p_period_d", type=String.class),
                        @StoredProcedureParameter(mode=IN, name="p_cc_d", type=Long.class),
                        @StoredProcedureParameter(mode=IN, name="p_category_d", type=Long.class),
                        @StoredProcedureParameter(mode=IN, name="p_transaction_type", type=String.class),
                        @StoredProcedureParameter(mode=IN, name="p_amount", type=Double.class),
                        @StoredProcedureParameter(mode=IN, name="p_currency_code", type=String.class),
                        @StoredProcedureParameter(mode=IN, name="p_requested_by", type=String.class),
                        @StoredProcedureParameter(mode=IN, name="p_approved_by", type=String.class),
                        @StoredProcedureParameter(mode=IN, name="p_reason", type=String.class),
                        @StoredProcedureParameter(mode=IN, name="p_user_id", type=String.class),
                        @StoredProcedureParameter(mode=IN, name="p_modulo", type=String.class),
                        @StoredProcedureParameter(mode=OUT,name="p_resultout", type=Integer.class),
                        @StoredProcedureParameter(mode=OUT,name="p_ibt_trxid_origen", type=Integer.class),//mda_reportes
                        @StoredProcedureParameter(mode=OUT,name="p_ibt_trxid_destino", type=Integer.class),//mda_reportes
                        @StoredProcedureParameter(mode=OUT,name="p_ibt_saldo_origen", type=Integer.class),//mda_reportes
                        @StoredProcedureParameter(mode=OUT,name="p_ibt_saldo_destino", type=Integer.class)//mda_reportes
                }
        )
        //<MDA_INC_DEC>
        ,
        //MDA_CONTRALOR
        @NamedStoredProcedureQuery(
            name = "CategoryBudget.transferenciaPresupuestoFinanzas",
            procedureName = "nvc_pkg_spx_budget.transfer_budget_finanza",
            parameters = {
                @StoredProcedureParameter(mode=IN, name="p_uen_o", type=Long.class),
                @StoredProcedureParameter(mode=IN, name="p_period_o", type=String.class),
                @StoredProcedureParameter(mode=IN, name="p_cc_o", type=Long.class),
                @StoredProcedureParameter(mode=IN, name="p_category_o", type=Long.class),
                @StoredProcedureParameter(mode=IN, name="p_uen_d", type=Long.class),
                @StoredProcedureParameter(mode=IN, name="p_period_d", type=String.class),
                @StoredProcedureParameter(mode=IN, name="p_cc_d", type=Long.class),
                @StoredProcedureParameter(mode=IN, name="p_category_d", type=Long.class),
                @StoredProcedureParameter(mode=IN, name="p_transaction_type", type=String.class),
                @StoredProcedureParameter(mode=IN, name="p_amount", type=Double.class),
                @StoredProcedureParameter(mode=IN, name="p_currency_code", type=String.class),
                @StoredProcedureParameter(mode=IN, name="p_requested_by", type=String.class),
                @StoredProcedureParameter(mode=IN, name="p_approved_by", type=String.class),
                @StoredProcedureParameter(mode=IN, name="p_reason", type=String.class),
                @StoredProcedureParameter(mode=IN, name="p_user_id", type=String.class),
                @StoredProcedureParameter(mode=IN, name="p_modulo", type=String.class),
                @StoredProcedureParameter(mode=OUT,name="p_resultout", type=Integer.class),
                @StoredProcedureParameter(mode=OUT,name="p_ibt_trxid_origen", type=Long.class),//mda_reportes
                @StoredProcedureParameter(mode=OUT,name="p_ibt_trxid_destino", type=Long.class),//mda_reportes
                @StoredProcedureParameter(mode=OUT,name="p_ibt_saldo_origen", type=Double.class),//mda_reportes
                @StoredProcedureParameter(mode=OUT,name="p_ibt_saldo_destino", type=Double.class)//mda_reportes
            }
        )
        //</MDA_INC_DEC>
})
//R41223
@NamedNativeQueries({
        //</MDA_INC_DEC>
        @NamedNativeQuery(
                name = "CategoryBudget.getCategoriasByUenAndCcAndPeriodo",
                query = "SELECT " +
                        "  cat.budget_balance_id, " +
                        "  cat.category_id, " +
                        "  cat.cost_center, " +
                        "  cat.cost_center_id, " +
                        "  cat.id_uen, " +
                        "  cat.currency_code, " +
                        "  decode(:lang, " +
                        "         'US', " +
                        "         cat.category_name_english, " +
                        "         cat.category_name) AS category_name, " +
                        "  cat.period_name, " +
                        "  cat.period_act, " +
                        "  cat.period_enc, " +
                        "  cat.period_onh, " +
                        "  cat.period_bud, " +
                        "  cat.uen " +
                        "FROM " +
                        "  icom_category_budget_v cat " +
                        "WHERE " +
                        "  cat.id_uen = :uen " +
                        "  AND cat.cost_center_id = :cc " +
                        "  AND cat.period_name = :period " +
                        "ORDER BY " +
                        "  cat.category_id ASC",
                resultClass = CategoryBudget.class
        ),
        //<MDA_INC_DEC>
        @NamedNativeQuery(
                name = "CategoryBudget.getCategoriasByUenAndCcAndPeriodoAndId",
                query = "SELECT " +
                        "  cat.budget_balance_id, " +
                        "  cat.category_id, " +
                        "  cat.cost_center, " +
                        "  cat.cost_center_id, " +
                        "  cat.id_uen, " +
                        "  cat.currency_code, " +
                        "  decode(:lang, " +
                        "         'US', " +
                        "         cat.category_name_english, " +
                        "         cat.category_name) AS category_name, " +
                        "  cat.period_name, " +
                        "  cat.period_act, " +
                        "  cat.period_enc, " +
                        "  cat.period_onh, " +
                        "  cat.period_bud, " +
                        "  cat.uen " +
                        "FROM " +
                        "  icom_category_budget_v cat " +
                        "WHERE " +
                        "  cat.id_uen = :uen " +
                        "  AND cat.cost_center_id = :cc " +
                        "  AND cat.period_name = :period " +
                        "  AND cat.category_id = :categoria " +
                        "ORDER BY " +
                        "  cat.category_id ASC",
                resultClass = CategoryBudget.class
        ),
        //</MDA_INC_DEC>
        @NamedNativeQuery(
                name = "CategoryBudget.getLastBudgetHistory",
                query = "select t.period_name periodo, " +
                        "       t.period_num numero_periodo, " +
                        "       sum (t.period_bud) inicial, " +
                        "       sum (t.period_act) actual, " +
                        "       sum (t.period_enc) comprometido, " +
                        "       sum (t.period_onh) disponible " +
                        "from icom_category_budget_v t " +
                        "WHERE t.id_uen = :uen " +
                        "  AND t.cost_center_id = :cc " +
                        "  AND t.period_name IN (select t.period_name " +
                        "                        from nvc_vm_gl_periods t " +
                        "                        where t.period_set_name = 'Contable' " +
                        "                              and trunc(t.start_date) between trunc(add_months(sysdate,:months)) and trunc(sysdate)) " +
                        "GROUP BY T.PERIOD_NAME, period_num " +
                        "ORDER BY t.period_num DESC",
                resultClass = BudgetHistory.class
        ),
        @NamedNativeQuery(
                name = "CategoryBudget.getBudgetStatusByUser",
                query = "select a.*, nvl((select nvl(b.requi_mayor, 0) " +
                        "   FROM (select sum (d.cantidad_requerida * d.precio * tipo_cambio_f(d.id_moneda, d.id_uen)) requi_mayor, " +
                        "           d.siguiente_aprobador, " +
                        "           d.id_requisicion " +
                        "         from    requisicion r " +
                        "           join detalle_de_requisicion d " +
                        "             on d.id_requisicion = r.id_requisicion " +
                        "                and d.estatus = 'APROBADA' " +
                        "                and d.siguiente_aprobador = :username " +
                        "           join nvc_vm_gl_periods p " +
                        "             on p.period_set_name = 'Contable' " +
                        "                and p.period_name = :period " +
                        "                and d.fecha_de_aprobacion between p.start_date and p.end_date " +
                        "         group by d.siguiente_aprobador, d.id_requisicion " +
                        "         order by 1 desc) b " +
                        "   where rownum = 1), 0) monto_maximo " +
                        "from (  select sum (t.period_bud) inicial, " +
                        "               sum (t.period_act) actual, " +
                        "               sum (t.period_enc) comprometido, " +
                        "               sum (t.period_onh) disponible, " +
                        "               t.period_name nombre_periodo " +
                        "        from icom_category_budget_v t " +
                        "        where t.id_uen = :uen " +
                        "          and t.cost_center_id = :cc " +
                        "          and t.period_name = :period " +
                        "        group by t.period_name) a",
                resultClass = BudgetStatusUser.class
        ),
        @NamedNativeQuery(
                name = "CategoryBudget.getCategoriasByUenAndCcAndPeriodoAndIdAll",
                query = "SELECT " +
                        "  cat.budget_balance_id, " +
                        "  cat.category_id, " +
                        "  cat.cost_center, " +
                        "  cat.cost_center_id, " +
                        "  cat.id_uen, " +
                        "  cat.currency_code, " +
                        "  decode(:lang, " +
                        "         'US', " +
                        "         cat.category_name_english, " +
                        "         cat.category_name) AS category_name, " +
                        "  cat.period_name, " +
                        "  cat.period_act, " +
                        "  cat.period_enc, " +
                        "  cat.period_onh, " +
                        "  cat.period_bud, " +
                        "  cat.uen " +
                        "FROM " +
                        "  icom_category_budget_v cat " +
                        "WHERE " +
                        "  cat.id_uen = :uen " +
                        "  AND cat.cost_center_id in :cc " +
                        "  AND cat.period_name = :period " +
                        "  AND cat.category_id in :categoria " +
                        "ORDER BY " +
                        "  cat.category_id ASC",
                resultClass = CategoryBudget.class
        )
})
public class CategoryBudget extends Budget {
    @Id
    @Column(name = "budget_balance_id")
    private Long id;

    @Column(name = "category_id")
    private Long categoria;

    @Column(name = "category_name")
    private String nombreCategoria;

    private Long idUen;

    private String uen;

    @Column(name = "cost_center_id")
    private Long idCentroCosto;

    @Column(name = "cost_center")
    private String centroCosto;

    @Column(name = "currency_code")
    private String moneda;

    @Column(name = "period_name")
    private String periodo;

    @Column(name = "period_onh")
    private Double saldoDisponible;

    @Column(name = "period_bud")
    private Double saldoInicial;

    @Column(name = "period_act")
    private Double saldoActual;

    @Column(name = "period_enc")
    private Double saldoComprometido;

    @Override
    public String toString() {
        return "CategoryBudget{" + "id=" + id + ", categoria=" + categoria + ", nombreCategoria=" + nombreCategoria + ", idUen=" + idUen + ", uen=" + uen + ", idCentroCosto=" + idCentroCosto + ", centroCosto=" + centroCosto + ", periodo=" + periodo + '}';
    }
    
}
