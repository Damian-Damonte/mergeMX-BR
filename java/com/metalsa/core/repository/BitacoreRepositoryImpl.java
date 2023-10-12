package com.metalsa.core.repository;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Repository
public class BitacoreRepositoryImpl implements BitacoreRepository {
    private final EntityManager em;
    
    private static final String REGISTER_BITACORE_SP = "SPX_COMPRAS_RETURN_REQ.inserta_bitacora";
    
    public BitacoreRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public boolean registerSafe(
            Integer idRequisicion,
            Integer idPartida,
            Integer idComentarioRegresado,
            String idRequisitor,
            Integer idAccion,
            Integer idEstatus,
            String idComprador,
            Integer idRol
    ) {
        try {
            StoredProcedureQuery sp = em.createStoredProcedureQuery(REGISTER_BITACORE_SP);
        
            sp.registerStoredProcedureParameter("P_ID_REQUISICION", Integer.class, ParameterMode.IN);
            sp.registerStoredProcedureParameter("P_ID_PARTIDA", Integer.class, ParameterMode.IN);
            sp.registerStoredProcedureParameter("P_ID_COMEN_REGRESO_REQ", Integer.class, ParameterMode.IN);
            sp.registerStoredProcedureParameter("P_REQUISITOR", String.class, ParameterMode.IN);
            sp.registerStoredProcedureParameter("P_ACCION", Integer.class, ParameterMode.IN);
            sp.registerStoredProcedureParameter("P_ID_ESTATUS", Integer.class, ParameterMode.IN);
            sp.registerStoredProcedureParameter("P_ID_COMPRADOR", String.class, ParameterMode.IN);
            sp.registerStoredProcedureParameter("ID_ROL", Integer.class, ParameterMode.IN);

            sp.setParameter("P_ID_REQUISICION", idRequisicion);
            sp.setParameter("P_ID_PARTIDA", idPartida);
            sp.setParameter("P_ID_COMEN_REGRESO_REQ", idComentarioRegresado);
            sp.setParameter("P_REQUISITOR", idRequisitor);
            sp.setParameter("P_ACCION", idAccion);
            sp.setParameter("P_ID_ESTATUS", idEstatus);
            sp.setParameter("P_ID_COMPRADOR", idComprador);
            sp.setParameter("ID_ROL", idRol);

            boolean isOK = sp.execute();

            if (isOK) {
                return true;
            }
        } catch (Exception error) {
            System.out.println("(BitacoraRepositoryImpl.registerSafe): " + error.getMessage());
        }
        
        return false;
    }
}
