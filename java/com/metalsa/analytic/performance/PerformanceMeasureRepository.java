package com.metalsa.analytic.performance;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface PerformanceMeasureRepository extends JpaRepository<PerformanceMeasure, Long> {

}
