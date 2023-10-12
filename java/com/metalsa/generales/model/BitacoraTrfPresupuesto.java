package com.metalsa.generales.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Modelo de la bitacora de transferencias de presupuestos
 */
@Entity
@Table(name = "bitacora_trf_ppto_extendida")
@Data
public class BitacoraTrfPresupuesto implements Serializable {

    @Id
    private Long id;
    private Long sourceUen;
    private String sourceUenName;
    private Long sourceCc;
    private String sourceCcName;
    private String sourcePeriod;
    private Long sourceCategory;
    private String sourceCategoryName;
    private String sourceCategoryNameUs;
    private Long targetUen;
    private String targetUenName;
    private Long targetCc;
    private String targetCcName;
    private String targetPeriod;
    private Long targetCategory;
    private String targetCategoryName;
    private String targetCategoryNameUs;
    private Double transferAmmount;
    private String currency;
    private String transferBy;
    private Date transferAt;


    public Date getTransferAt() {
        return transferAt == null ? null : (Date) transferAt.clone();
    }

    public void setTransferAt(Date transferAt) {
        if (transferAt == null)
            this.transferAt = null;
        else
            this.transferAt = (Date) transferAt.clone();
    }
}
