package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.BuyerReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface BuyerReasonRepository extends JpaRepository<BuyerReason, BuyerReason.Pk> {

    BuyerReason getBuyerReason(@Param("req") Long requisicion,
                               @Param("line") Long linea);
}
