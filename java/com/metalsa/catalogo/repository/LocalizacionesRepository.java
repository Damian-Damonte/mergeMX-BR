package com.metalsa.catalogo.repository;

import com.metalsa.catalogo.model.NvcTblOaLocalizacionesH;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;

/**
 *
 * @author APOMR10051
 */
public interface LocalizacionesRepository extends JpaRepository<NvcTblOaLocalizacionesH, BigDecimal> {

    List<NvcTblOaLocalizacionesH> getShipToByIdUen(@Param("idUen") Integer idUen);

    List<NvcTblOaLocalizacionesH> getBillToByIdUen(@Param("idUen") Integer idUen);
}
