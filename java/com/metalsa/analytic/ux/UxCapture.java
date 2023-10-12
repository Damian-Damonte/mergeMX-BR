package com.metalsa.analytic.ux;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "nvc_tbl_ux_principal")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class UxCapture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uxGen")
    @SequenceGenerator(name = "uxGen", sequenceName = "seq_ux")
    private Long id;

    @NotNull
    private String usuario;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date capturedAt;


    public Date getCapturedAt() {
        return capturedAt == null ? null : (Date)capturedAt.clone();
    }
}
