/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;
import com.metalsa.recibos.model.ReciboConsulta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;//<R17231>

/**
 *
 * @author edgar.leal
 */
@Log4j
public class ReciboConsultaRepositoryImpl implements ReciboConsultaProcedures {

    @PersistenceContext
    private EntityManager em;

    //<R17231>
    @Override
    public List<ReciboConsulta> PRC_SPX_REQS_POR_RECIBIR(String P_ID_REQUISICION, String P_ID_ORDEN_DE_COMPRA, String P_UEN, String P_FECHA_INICIAL, String P_FECHA_FINAL, String P_URGENTE, String P_ID_PROVEEDOR, String P_ID_CC, String P_ID_REQUISITOR, String P_ID_USUARIO) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("PRC_SPX_REQS_POR_RECIBIR");

        proc.setParameter("P_ID_REQUISICION", P_ID_REQUISICION);
        proc.setParameter("P_ID_ORDEN_DE_COMPRA", P_ID_ORDEN_DE_COMPRA);
        proc.setParameter("P_UEN", P_UEN);
        proc.setParameter("P_FECHA_INICIAL", P_FECHA_INICIAL);
        proc.setParameter("P_FECHA_FINAL", P_FECHA_FINAL);
        proc.setParameter("P_URGENTE", P_URGENTE);
        proc.setParameter("P_ID_PROVEEDOR", P_ID_PROVEEDOR);
        proc.setParameter("P_ID_CC", P_ID_CC);
        proc.setParameter("P_ID_REQUISITOR", P_ID_REQUISITOR);
        proc.setParameter("P_ID_USUARIO", P_ID_USUARIO);

        List<ReciboConsulta> list;

        try {
            proc.execute();
            list = (List<ReciboConsulta>) proc.getResultList();
        } catch (Exception e) {
            list = new ArrayList<>();
            log.error(e.getMessage(), e);
        }
        return list;
    }
    //</R17231>

    /*@Override
    public List<GastoAdicional> getGastosAdicionales(Integer idRequisicion, Integer idPartida, String idIdioma) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getGastosAdicionales");
        proc.setParameter("p_id_requisicion", idRequisicion);
        proc.setParameter("p_id_partida", idPartida);
        proc.setParameter("p_id_idioma", idIdioma);

        List<GastoAdicional> list;
        try {
            proc.execute();
            list = new ArrayList<>((List<GastoAdicional>) proc.getResultList());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            list = new ArrayList<>();
        }

        return list;
    }

    @Override
    public Integer regresarRequisiciones(String requisiciones, String razonRechazo, String idAprobador) {
        System.out.println("- - - - - - - - - - - - - - - - - - regresarRequisiciones - - - - - - - - - - - - - - - - - -");
        System.out.println("requisiciones=>"+requisiciones);
        System.out.println("razonRechazo=>"+razonRechazo);
        System.out.println("idAprobador=>"+idAprobador);
        
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("regresarRequisiciones");
        proc.setParameter("requisiciones", requisiciones);
        proc.setParameter("comentario", razonRechazo);
        proc.setParameter("aprobador", idAprobador);
        proc.execute();
        
        return (Integer)proc.getOutputParameterValue("respuesta");
    }
    
    @Override
    public Integer cancelarRequisicion(String requisiciones, String razonRechazo, String idAprobador) {
        System.out.println("- - - - - - - - - - - - - - - - - - cancelarRequisicion - - - - - - - - - - - - - - - - - -");
        System.out.println("requisiciones=>"+requisiciones);
        System.out.println("razonRechazo=>"+razonRechazo);
        System.out.println("idAprobador=>"+idAprobador);
        
        
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("cancelarRequisiciones");
        proc.setParameter("P_REQUISICIONES", requisiciones);
        proc.setParameter("P_RAZON_DE_RECHAZO", razonRechazo);
        proc.setParameter("P_ID_APROBADOR", idAprobador);
        proc.execute();
        
        return (Integer)proc.getOutputParameterValue("P_OUT_ESTATUS");
    }*/
}
