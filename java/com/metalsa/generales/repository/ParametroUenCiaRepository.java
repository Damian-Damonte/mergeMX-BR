package com.metalsa.generales.repository;

import com.metalsa.generales.model.ParametroUenCia;
import java.util.List;//<RELEASE ARG/>
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface ParametroUenCiaRepository extends JpaRepository<ParametroUenCia, ParametroUenCia.Pk> {

    @Query("select case when count(p) > 0 then true else false end "
            + "from ParametroUenCia p "
            + "where p.idUen = :uen "
            + "   and p.idParametro = :param")
    boolean existsByUen(@Param("uen") Long uen, @Param("param") Long param);

    Optional<ParametroUenCia> findByIdUenAndIdParametro(Long uen, Long param);

	//<RELEASE ARG>
    List<ParametroUenCia> findAllByIdParametro(Long idParametro);
    
    List<ParametroUenCia> findAllByIdParametroAndValor(Long idParametro,String Valor);
	//<RELEASE ARG/>
    
    //<RELEASE_ETOWN>
	//<MDA_CONTRALOR>
    @Query(value = "select "
            + " pv.ID_PARAMETER_UEN as id_uen, "
            + " pv.ID_PARAMETER_VALUE as id_parametro, "
            + " pv.PARAMETER_VALUE as valor "
            + "from nvc_tbl_parameter p, "
            + " nvc_tbl_parameter_uen pu, "
            + " nvc_tbl_parameter_value pv "
            + "where p.id_parameter = pu.id_parameter "
            + " and pu.id_uen = :uen "
            + " and p.parameter_name = :parametro "
            + " and pu.id_parameter_uen = pv.id_parameter_uen",
            nativeQuery = true)
    ParametroUenCia findParametroSPXByNombre(@Param("uen") Integer uen, @Param("parametro") String parametro);
    //<RELEASE_ETOWN>
}
