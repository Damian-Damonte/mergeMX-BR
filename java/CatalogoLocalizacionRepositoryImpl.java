
import com.metalsa.catalogo.model.NvcTblCatalogoLocalizacion;
import com.metalsa.catalogo.model.NvcTblCatalogoUenSite;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.metalsa.requisicion.repository.CatalogoRepository;
import java.util.List;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Repository
public class CatalogoLocalizacionRepositoryImpl implements CatalogoRepository {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public NvcTblCatalogoLocalizacion findByCatUenLocalizacion(Integer idUen, Integer idLocalizacion) {
        return (NvcTblCatalogoLocalizacion) em.createQuery(
                "SELECT n FROM NvcTblCatalogoLocalizacion n " +
                "Where n.idCatalogoUen.idCatalogoUen = :idCatalogoUen " +
                "AND n.idLocalizacion = :idLocalizacion")
                .setParameter("idCatalogoUen", idUen)
                .setParameter("idLocalizacion", idLocalizacion)
                .getSingleResult();
    }

    @Override
    public List<NvcTblCatalogoUenSite> findSitesByLocalizacion(Integer idLocalizacion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
