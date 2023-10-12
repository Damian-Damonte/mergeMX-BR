package com.metalsa.contralor.repository;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.contralor.model.Configuracion;
import com.metalsa.contralor.model.Mes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar del Angel
 */
@Repository
public interface ConfiguracionRepository extends JpaRepository<Configuracion, Long>,ConfiguracionProcedures{
 
    @Query(name="Configuracion.findByUen")
    List<Configuracion> findAllByUen(Integer uen);
        
    @Query("SELECT m FROM Mes m WHERE  m.idioma = ?1")
    List<Mes> findMonthsByLanguage(String idioma);
    
    @Query(name="Configuracion.getAuthorizedUensByName")
    List<NvcTblOrganizacionesH> getAuthorizedUensByName(String parameterName, String idUsuario);
    
}
