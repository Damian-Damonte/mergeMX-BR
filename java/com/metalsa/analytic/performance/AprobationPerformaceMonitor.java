package com.metalsa.analytic.performance;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Aspect
@Component
@Log4j
public class AprobationPerformaceMonitor {

    private PerformanceService profiler;

    @Autowired
    public AprobationPerformaceMonitor(PerformanceService profiler) {
        this.profiler = profiler;
    }

    @Around("@annotation(com.metalsa.analytic.performance.PerformaceMeasure)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("calling service " + joinPoint.getSignature().toShortString());
        Object result = joinPoint.proceed();

        return result;
    }

    @Around("@within(org.springframework.web.bind.annotation.RestController) && "
            + "(@annotation(org.springframework.web.bind.annotation.PostMapping)) "
            + "|| @annotation(org.springframework.web.bind.annotation.GetMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)) ")
    public Object performanceRestService(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!joinPoint.getSignature().toShortString().contains("ScheduledProcess")) {
            log.debug("ENDPOINT " + joinPoint.getSignature().toShortString());
        }
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        if (!joinPoint.getSignature().toShortString().contains("ScheduledProcess")) {
            log.debug("ENDPOINT: " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
        }

        try {
            profiler.capture(new PerformanceMeasure(joinPoint.getSignature().toShortString(), executionTime));
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
        }

        return result;
    }
}
