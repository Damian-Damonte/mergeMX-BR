package com.metalsa.generales.repository;

import com.metalsa.generales.model.BitacoraTrfPresupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface BitacoraTrfPresupuestoRepository extends JpaRepository<BitacoraTrfPresupuesto, Long> {

    List<BitacoraTrfPresupuesto> findAllByOrderById();

    @Query("select p " +
            "from BitacoraTrfPresupuesto p " +
            "where p.sourceUen = :uen " +
            "   and p.sourceCc = :cc " +
            "   and p.sourcePeriod = :period " +
            "   and p.sourceCategory = :category " +
            "order by p.transferAt")
    List<BitacoraTrfPresupuesto> findAllBySource(Long uen, Long cc, String period, Long category);

    @Query("select p " +
            "from BitacoraTrfPresupuesto p " +
            "where p.targetUen = :uen " +
            "   and p.targetCc = :cc " +
            "   and p.targetPeriod = :period " +
            "   and p.targetCategory = :category " +
            "order by p.transferAt")
    List<BitacoraTrfPresupuesto> findAllByTarget(Long uen, Long cc, String period, Long category);

    List<BitacoraTrfPresupuesto> findAllByTransferByOrderByTransferAt(String user);
}
