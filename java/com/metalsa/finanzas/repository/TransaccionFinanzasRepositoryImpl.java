package com.metalsa.finanzas.repository;

import com.metalsa.aprobacion.model.CategoryBudget;//MDA_REPORTES_FINANZAS
import com.metalsa.finanzas.model.RespuestaPojo;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;

/**
 *
 * @author JL
 */
@Log4j
public class TransaccionFinanzasRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public RespuestaPojo modificarPresupuesto(Long idUen, String idCcs, String idCategorias,
            String periodo, String tipoTransaccion, Double porcentaje,
            Double monto, String moneda, String solicitante, String aprobador,
            String razon, String usuario, Long idTransaccion) {

        log.debug(idUen + "-" + idCcs + "-" + idCategorias + "-" + periodo + "-" + tipoTransaccion + "-" + porcentaje + "-" + monto + "-" + moneda + "-" + solicitante + "-" + aprobador + "-" + razon + "-" + usuario + "-" + idTransaccion);

        RespuestaPojo resp = new RespuestaPojo();
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("modificar_presupuesto");
        proc.setParameter("p_id_uen", idUen);
        proc.setParameter("p_str_ids_ccs", idCcs);
        proc.setParameter("p_str_ids_categorias", idCategorias);
        proc.setParameter("p_periodo", periodo);
        proc.setParameter("p_tipo_transaccion", tipoTransaccion);
        proc.setParameter("p_cantidad_pct", porcentaje);
        proc.setParameter("p_cantidad_pesos", monto);
        proc.setParameter("p_moneda", moneda);
        proc.setParameter("p_requester", solicitante);
        proc.setParameter("p_aprobador", aprobador);
        proc.setParameter("p_razon", razon);
        proc.setParameter("p_id_usuario", usuario);
        proc.setParameter("p_id_transaccion", idTransaccion);

        try {
            proc.execute();
            resp.setMensaje((String) proc.getOutputParameterValue("out_message"));
            resp.setValor((Integer) proc.getOutputParameterValue("out_value"));

        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            throw new RuntimeException("Error ejecutando procedimiento: modificar_presupuesto", e);
        }

        return resp;
    }

	//MDA_REPORTES_FINANZAS
    public int transferenciaPresupuestoFinanzas(CategoryBudget origen, CategoryBudget destino, Double ammount, String usuario, 
            String modulo, String aprobador, String solicitante, String razon) {

        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("CategoryBudget.transferenciaPresupuestoFinanzas")
                .setParameter("p_uen_o", origen.getIdUen())
                .setParameter("p_period_o", origen.getPeriodo())
                .setParameter("p_cc_o", origen.getIdCentroCosto())
                .setParameter("p_category_o", origen.getCategoria())
                .setParameter("p_uen_d", destino.getIdUen())
                .setParameter("p_period_d", destino.getPeriodo())
                .setParameter("p_cc_d", destino.getIdCentroCosto())
                .setParameter("p_category_d", destino.getCategoria())
                .setParameter("p_transaction_type", "TRANSFER")
                .setParameter("p_amount", ammount)
                .setParameter("p_currency_code", origen.getMoneda())
                .setParameter("p_requested_by", solicitante)
                .setParameter("p_approved_by", aprobador)
                .setParameter("p_reason", razon)
                .setParameter("p_user_id", usuario)
                .setParameter("p_modulo", modulo);

        proc.execute();
        int p_resultout = (int) proc.getOutputParameterValue("p_resultout");
        log.debug("p_resultout:"+p_resultout);
        log.debug("p_ibt_trxid_origen:"+proc.getOutputParameterValue("p_ibt_trxid_origen"));
        log.debug("p_ibt_trxid_destino: "+proc.getOutputParameterValue("p_ibt_trxid_destino"));
        log.debug("p_ibt_saldo_origen: "+proc.getOutputParameterValue("p_ibt_saldo_origen"));
        log.debug("p_ibt_saldo_destino: "+proc.getOutputParameterValue("p_ibt_saldo_destino"));
        return p_resultout;
    }

}
