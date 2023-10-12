package com.metalsa.analytic.performance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Component
public class PerformanceService {
    private PerformanceMeasureRepository perf;

    @Autowired
    public PerformanceService(PerformanceMeasureRepository perf) {
        this.perf = perf;
    }

    @Async
    public void capture(PerformanceMeasure measure) {
        perf.save(measure);
    }
}
