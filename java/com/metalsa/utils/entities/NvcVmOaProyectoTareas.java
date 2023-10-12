/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utils.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 *
 * @author APOJA5585
 */
@Entity
@Table(name = "NVC_VM_OA_PROYECTO_TAREAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcVmOaProyectoTareas.findAll", query = "SELECT n FROM NvcVmOaProyectoTareas n")
    ,
    @NamedQuery(name = "NvcVmOaProyectoTareas.findByIdTarea", query = "SELECT n FROM NvcVmOaProyectoTareas n WHERE n.nvcVmOaProyectoTareasPK.idTarea = :idTarea")
    ,
    @NamedQuery(name = "NvcVmOaProyectoTareas.findByIdProyecto", query = "SELECT n FROM NvcVmOaProyectoTareas n WHERE n.nvcVmOaProyectoTareasPK.idProyecto = :idProyecto")
    ,   
    @NamedQuery(name = "NvcVmOaProyectoTareas.findByNonLaborDiscReasonCode", query = "SELECT n FROM NvcVmOaProyectoTareas n WHERE n.nonLaborDiscReasonCode = :nonLaborDiscReasonCode")})
@Data
public class NvcVmOaProyectoTareas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NvcVmOaProyectoTareasPK nvcVmOaProyectoTareasPK;

    public Long getIdTarea(){
        return nvcVmOaProyectoTareasPK.getIdTarea();
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "COD_TAREA")
    private String codTarea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE_TAREA")
    private String nombreTarea;
    @Size(max = 120)
    @Column(name = "DESC_TAREA")
    private String descTarea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY")
    private long createdBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private long lastUpdatedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_LOGIN")
    private long lastUpdateLogin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOP_TASK_ID")
    private long topTaskId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WBS_LEVEL")
    private short wbsLevel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "READY_TO_BILL_FLAG")
    private String readyToBillFlag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "READY_TO_DISTRIBUTE_FLAG")
    private String readyToDistributeFlag;
    @Column(name = "PARENT_TASK_ID")
    private Long parentTaskId;
    @Size(max = 90)
    @Column(name = "SERVICE_TYPE_CODE")
    private String serviceTypeCode;
    @Column(name = "TASK_MANAGER_PERSON_ID")
    private Integer taskManagerPersonId;
    @Size(max = 3)
    @Column(name = "CHARGEABLE_FLAG")
    private String chargeableFlag;
    @Size(max = 3)
    @Column(name = "BILLABLE_FLAG")
    private String billableFlag;
    @Size(max = 3)
    @Column(name = "LIMIT_TO_TXN_CONTROLS_FLAG")
    private String limitToTxnControlsFlag;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "COMPLETION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completionDate;
    @Column(name = "ADDRESS_ID")
    private Long addressId;
    @Column(name = "LABOR_BILL_RATE_ORG_ID")
    private Long laborBillRateOrgId;
    @Size(max = 60)
    @Column(name = "LABOR_STD_BILL_RATE_SCHDL")
    private String laborStdBillRateSchdl;
    @Column(name = "LABOR_SCHEDULE_FIXED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date laborScheduleFixedDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LABOR_SCHEDULE_DISCOUNT")
    private BigDecimal laborScheduleDiscount;
    @Column(name = "NON_LABOR_BILL_RATE_ORG_ID")
    private Long nonLaborBillRateOrgId;
    @Size(max = 90)
    @Column(name = "NON_LABOR_STD_BILL_RATE_SCHDL")
    private String nonLaborStdBillRateSchdl;
    @Column(name = "NON_LABOR_SCHEDULE_FIXED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nonLaborScheduleFixedDate;
    @Column(name = "NON_LABOR_SCHEDULE_DISCOUNT")
    private BigDecimal nonLaborScheduleDiscount;
    @Size(max = 60)
    @Column(name = "LABOR_COST_MULTIPLIER_NAME")
    private String laborCostMultiplierName;
    @Column(name = "REQUEST_ID")
    private Long requestId;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private Long programApplicationId;
    @Column(name = "PROGRAM_ID")
    private Long programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
    @Size(max = 90)
    @Column(name = "ATTRIBUTE_CATEGORY")
    private String attributeCategory;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE1")
    private String attribute1;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE2")
    private String attribute2;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE3")
    private String attribute3;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE4")
    private String attribute4;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE5")
    private String attribute5;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE6")
    private String attribute6;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE7")
    private String attribute7;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE8")
    private String attribute8;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE9")
    private String attribute9;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE10")
    private String attribute10;
    @Column(name = "COST_IND_RATE_SCH_ID")
    private Long costIndRateSchId;
    @Column(name = "REV_IND_RATE_SCH_ID")
    private Long revIndRateSchId;
    @Column(name = "INV_IND_RATE_SCH_ID")
    private Long invIndRateSchId;
    @Column(name = "COST_IND_SCH_FIXED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date costIndSchFixedDate;
    @Column(name = "REV_IND_SCH_FIXED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date revIndSchFixedDate;
    @Column(name = "INV_IND_SCH_FIXED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invIndSchFixedDate;
    @Size(max = 3)
    @Column(name = "LABOR_SCH_TYPE")
    private String laborSchType;
    @Size(max = 3)
    @Column(name = "NON_LABOR_SCH_TYPE")
    private String nonLaborSchType;
    @Column(name = "OVR_COST_IND_RATE_SCH_ID")
    private Long ovrCostIndRateSchId;
    @Column(name = "OVR_INV_IND_RATE_SCH_ID")
    private Long ovrInvIndRateSchId;
    @Column(name = "OVR_REV_IND_RATE_SCH_ID")
    private Long ovrRevIndRateSchId;
    @Size(max = 90)
    @Column(name = "PM_PRODUCT_CODE")
    private String pmProductCode;
    @Size(max = 75)
    @Column(name = "PM_TASK_REFERENCE")
    private String pmTaskReference;
    @Column(name = "ACTUAL_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualStartDate;
    @Column(name = "ACTUAL_FINISH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualFinishDate;
    @Column(name = "EARLY_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date earlyStartDate;
    @Column(name = "EARLY_FINISH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date earlyFinishDate;
    @Column(name = "LATE_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lateStartDate;
    @Column(name = "LATE_FINISH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lateFinishDate;
    @Column(name = "SCHEDULED_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledStartDate;
    @Column(name = "SCHEDULED_FINISH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledFinishDate;
    @Size(max = 3)
    @Column(name = "ADW_NOTIFY_FLAG")
    private String adwNotifyFlag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "ALLOW_CROSS_CHARGE_FLAG")
    private String allowCrossChargeFlag;
    @Column(name = "PROJECT_RATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date projectRateDate;
    @Size(max = 90)
    @Column(name = "PROJECT_RATE_TYPE")
    private String projectRateType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CC_PROCESS_LABOR_FLAG")
    private String ccProcessLaborFlag;
    @Column(name = "LABOR_TP_SCHEDULE_ID")
    private BigInteger laborTpScheduleId;
    @Column(name = "LABOR_TP_FIXED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date laborTpFixedDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CC_PROCESS_NL_FLAG")
    private String ccProcessNlFlag;
    @Column(name = "NL_TP_SCHEDULE_ID")
    private BigInteger nlTpScheduleId;
    @Column(name = "NL_TP_FIXED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nlTpFixedDate;
    @Size(max = 3)
    @Column(name = "RECEIVE_PROJECT_INVOICE_FLAG")
    private String receiveProjectInvoiceFlag;
    @Column(name = "WORK_TYPE_ID")
    private Long workTypeId;
    @Column(name = "RECORD_VERSION_NUMBER")
    private Long recordVersionNumber;
    @Column(name = "JOB_BILL_RATE_SCHEDULE_ID")
    private BigInteger jobBillRateScheduleId;
    @Column(name = "EMP_BILL_RATE_SCHEDULE_ID")
    private BigInteger empBillRateScheduleId;
    @Size(max = 90)
    @Column(name = "TASKFUNC_COST_RATE_TYPE")
    private String taskfuncCostRateType;
    @Column(name = "TASKFUNC_COST_RATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taskfuncCostRateDate;
    @Column(name = "NON_LAB_STD_BILL_RT_SCH_ID")
    private Long nonLabStdBillRtSchId;
    @Size(max = 90)
    @Column(name = "LABOR_DISC_REASON_CODE")
    private String laborDiscReasonCode;
    @Size(max = 90)
    @Column(name = "NON_LABOR_DISC_REASON_CODE")
    private String nonLaborDiscReasonCode;

    public NvcVmOaProyectoTareas() {
    }

    public NvcVmOaProyectoTareas(NvcVmOaProyectoTareasPK nvcVmOaProyectoTareasPK) {
        this.nvcVmOaProyectoTareasPK = nvcVmOaProyectoTareasPK;
    }

    public NvcVmOaProyectoTareas(NvcVmOaProyectoTareasPK nvcVmOaProyectoTareasPK, String codTarea, String nombreTarea, Date creationDate, long createdBy, Date lastUpdateDate, long lastUpdatedBy, long lastUpdateLogin, long topTaskId, short wbsLevel, String readyToBillFlag, String readyToDistributeFlag, String allowCrossChargeFlag, String ccProcessLaborFlag, String ccProcessNlFlag) {
        this.nvcVmOaProyectoTareasPK = nvcVmOaProyectoTareasPK;
        this.codTarea = codTarea;
        this.nombreTarea = nombreTarea;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdateLogin = lastUpdateLogin;
        this.topTaskId = topTaskId;
        this.wbsLevel = wbsLevel;
        this.readyToBillFlag = readyToBillFlag;
        this.readyToDistributeFlag = readyToDistributeFlag;
        this.allowCrossChargeFlag = allowCrossChargeFlag;
        this.ccProcessLaborFlag = ccProcessLaborFlag;
        this.ccProcessNlFlag = ccProcessNlFlag;
    }

    public NvcVmOaProyectoTareas(long idTarea, long idProyecto, long idUen) {
        this.nvcVmOaProyectoTareasPK = new NvcVmOaProyectoTareasPK(idTarea, idProyecto, idUen);
    }

    public NvcVmOaProyectoTareasPK getNvcVmOaProyectoTareasPK() {
        return nvcVmOaProyectoTareasPK;
    }

    public void setNvcVmOaProyectoTareasPK(NvcVmOaProyectoTareasPK nvcVmOaProyectoTareasPK) {
        this.nvcVmOaProyectoTareasPK = nvcVmOaProyectoTareasPK;
    }

    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nvcVmOaProyectoTareasPK != null ? nvcVmOaProyectoTareasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcVmOaProyectoTareas)) {
            return false;
        }
        NvcVmOaProyectoTareas other = (NvcVmOaProyectoTareas) object;
        if ((this.nvcVmOaProyectoTareasPK == null && other.nvcVmOaProyectoTareasPK != null) || (this.nvcVmOaProyectoTareasPK != null && !this.nvcVmOaProyectoTareasPK.equals(other.nvcVmOaProyectoTareasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.suite.NvcVmOaProyectoTareas[ nvcVmOaProyectoTareasPK=" + nvcVmOaProyectoTareasPK + " ]";
    }

}
