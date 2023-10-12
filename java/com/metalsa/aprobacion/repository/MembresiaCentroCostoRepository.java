package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CcPorUsuario;
import com.metalsa.aprobacion.model.UsuarioUen;	//<MDA_CONTRALOR>
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
 //<RELEASE ARG/>
public interface MembresiaCentroCostoRepository extends JpaRepository<CcPorUsuario, CcPorUsuario.Pk>,MembresiaCentroCostoProcedures {
    
    List<CcPorUsuario> getAllByCcOrderByUenAscRelacionDescUsuarioDesc(Long cc);

    List<CcPorUsuario> getAllByCcAndUenOrderByRelacionDescUsuarioDesc(Long cc, Long uen);

    Optional<CcPorUsuario> getUsuarioExtByCcAndUenAndUsuario(
            @Param("cc") Long cc,
            @Param("uen") Long uen,
            @Param("usuario") String usuario);
    
    List<CcPorUsuario> getnombreCcByUsuarioAndUenAndUsuario(
            @Param("uen") Long uen,
            @Param("usuario") String usuario,
            @Param("lang") String lang,
            @Param("tipoRelacion") List<String> tipoRelacion); //MDACONTRALOR
    
    //<MDA_INC_DEC>
    List<CcPorUsuario> getnombreCcByUenAndLangAndUsuario(
            @Param("uen") Long uen,
            @Param("usuario") String usuario,
            @Param("lang") String lang);

    List<CcPorUsuario> getCCSDefault(
            @Param("usuario") String usuario,
            @Param("lang") String lang);
    //</MDA_INC_DEC>

    //<MDA_CONTRALOR>
    List<CcPorUsuario> getCCSPorTipoRelacion(
            @Param("uen") Long uen,
            @Param("usuario") String usuario,
            @Param("relacion") String relacion,
            @Param("lang") String lang
    );

    List<UsuarioUen> getUsuariosPorTipoRelacion(
            @Param("uen") Long uen, 
            @Param("tipo") String relacion
    );
    
    //</MDA_CONTRALOR>
}
