package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.aprobacion.model.UenWithDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ruben.bresler on 05/07/2017.
 */
public interface OrganizacionesRepository extends PagingAndSortingRepository<NvcTblOrganizacionesH, Long>,
        OrganizationProcedures {

    List<UenWithDefault> findUenByUser(@Param("p_id_user") String user);

    @Query(name = "NvcTblOrganizacionesH.getUensByUser")
    List<UenWithDefault> getUensByUser(String user);

    @Query(name = "NvcTblOrganizacionesH.getUensActivasByUser")
    List<UenWithDefault> getUensActivasByUser(@Param("idUsuario") String user);
    
    @Query(name = "NvcTblOrganizacionesH.getUensActivasByUserRol")
    List<UenWithDefault> getUensActivasByUserRol(@Param("idUsuario") String user, @Param("idProceso") Integer idProceso);

    List<NvcTblOrganizacionesH> getUensActivasDispByUser(@Param("usuario") String usuario);
    List<NvcTblOrganizacionesH> getUensActivasByUser2(String userId);
    
    List<NvcTblOrganizacionesH> getUensPreaprobacion(@Param("usuario") String usuario);

    Page<NvcTblOrganizacionesH> findAll(Pageable page);

    //<MDA_INC_DEC>
    @Query(name = "NvcTblOrganizacionesH.getAllUensActivas")
    List<NvcTblOrganizacionesH> getAllUensActivas();

    NvcTblOrganizacionesH findByCompany(@Param("company") String company);
    //</MDA_INC_DEC>
}
