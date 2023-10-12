package com.metalsa.analytic.performance;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity(name = "nvc_tbl_performance_rest")
@Getter
public class PerformanceMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfRest")
    @SequenceGenerator(name = "perfRest", sequenceName = "seq_performance_rest")
    private Long id;

    private String endpoint;
    private Long execution;
    private LocalDateTime capturedAt;

    public PerformanceMeasure(String endpoint, Long execution) {
        this.endpoint = endpoint;
        this.execution = execution;
        this.capturedAt = LocalDateTime.now();
    }
}
