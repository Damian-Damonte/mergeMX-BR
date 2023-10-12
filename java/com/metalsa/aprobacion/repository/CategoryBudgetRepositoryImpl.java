package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CategoryBudget;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Log4j
public class CategoryBudgetRepositoryImpl implements TransferenciaPresupuesto {

    @PersistenceContext
    private EntityManager em;


	//MDA_REPORTES_FINANZAS
    @Override
    public int transferenciaPresupuesto(CategoryBudget origen, CategoryBudget destino, Double ammount, String usuario, 
            String modulo, String aprobador, String solicitante, String razon,String periodoDestino) {
        log.debug(" **** transferenciaPresupuesto *** "
        + " " + origen.getIdUen()
        + " " + origen.getPeriodo()
        + " " + origen.getIdCentroCosto()
        + " " + origen.getCategoria()
        + " " + destino.getIdUen()
        + " " + periodoDestino
        + " " + destino.getIdCentroCosto()
        + " " + destino.getCategoria()
        + " TRANSFER "  
        + "  " + ammount 
        + "  " + origen.getMoneda()
        + "  " + solicitante
        + "  " + aprobador
        + "  " + razon
        + "  " + usuario
        + "  " + modulo
        );
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("CategoryBudget.transferenciaPresupuesto")
                .setParameter("p_uen_o", origen.getIdUen())
                .setParameter("p_period_o", origen.getPeriodo())
                .setParameter("p_cc_o", origen.getIdCentroCosto())
                .setParameter("p_category_o", origen.getCategoria())
                .setParameter("p_uen_d", destino.getIdUen())
                .setParameter("p_period_d", periodoDestino)    //R40102
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
        return (int) proc.getOutputParameterValue("p_resultout");

    }
    
    //<MDA_INC_DEC>
    @Override
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
    //</MDA_INC_DEC>

    @Override
    public List<CategoryBudget> getCategoriasByUenAndCcAndPeriodo(Long uen, Long cc, String periodo, String idioma) {
        em.clear();
        return em.createNamedQuery("CategoryBudget.getCategoriasByUenAndCcAndPeriodo")
                .setParameter("uen", uen)
                .setParameter("cc", cc)
                .setParameter("period", periodo)
                .setParameter("lang", idioma).getResultList();
    }
    
}
