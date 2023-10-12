package com.metalsa.core.repository;

import com.metalsa.cart.model.NvcTblCarroCompra;
import com.metalsa.requisicion.model.LineaControlada;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Repository
public class LineaControladaRepositoryImpl implements LineaControladaRepository {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void register(NvcTblCarroCompra carro, Integer idRequisicion, Integer idPartida) {
        LineaControlada lc = new LineaControlada();
        lc.setIdCarroCompra(carro.getIdCarroCompra());
        lc.setIdRequisicion(idRequisicion);
        lc.setIdLinea(idPartida);
        lc.setIdMotivoSol(carro.getIdMotivoSol());
        lc.setIdPerson(carro.getPerIdUsuarioSol());
        em.persist(lc);
    }
}
