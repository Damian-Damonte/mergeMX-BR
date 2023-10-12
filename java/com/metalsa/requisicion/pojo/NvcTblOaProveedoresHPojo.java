package com.metalsa.requisicion.pojo;

import com.metalsa.core.model.NvcTblOaProveedoresH;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class NvcTblOaProveedoresHPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String attributeCategory;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private Integer createdBy;
    private Date creationDate;
    private String customerNum;
    private Integer employeeId;
    private String enabledFlag;
    private Date endDateActive;
    private String globalAttribute1;
    private String globalAttribute2;
    private String globalAttribute3;
    private String globalAttribute4;
    private String globalAttribute5;
    private Integer idProveedor;
    private String inspectionRequiredFlag;
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer lastUpdatedBy;
    private String matchOption;
    private String nombreProveedor;
    private String receiptRequiredFlag;
    private String rfc;
    private String segment2;
    private String segment3;
    private String segment4;
    private String segment5;
    private Integer setOfBooksId;
    private Date startDateActive;
    private String summaryFlag;
    private String vendorNameAlt;
    private Integer numCatalogos;
    private Integer numCatalogosActivos;
    private Integer idProvPunchout;

    public NvcTblOaProveedoresHPojo() {
    }

    public NvcTblOaProveedoresHPojo(String attributeCategory,
            String attribute1, String attribute2, String attribute3, String attribute4, String attribute5,
            BigInteger createdBy,
            Date creationDate,
            String customerNum,
            BigInteger employeeId,
            String enabledFlag,
            Date endDateActive,
            String globalAttribute1, String globalAttribute2, String globalAttribute3, String globalAttribute4, String globalAttribute5,
            BigDecimal idProveedor,
            String inspectionRequiredFlag,
            Date lastUpdateDate,
            BigInteger lastUpdateLogin,
            BigInteger lastUpdatedBy,
            String matchOption,
            String nombreProveedor,
            String receiptRequiredFlag,
            String rfc, String segment2, String segment3, String segment4, String segment5,
            BigInteger setOfBooksId,
            Date startDateActive,
            String summaryFlag,
            String vendorNameAlt) {
        this.attributeCategory = attributeCategory;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.createdBy = createdBy == null ? 0 : createdBy.intValue();
        this.creationDate = creationDate;
        this.customerNum = customerNum;
        this.employeeId = employeeId == null ? 0 : employeeId.intValue();
        this.enabledFlag = enabledFlag;
        this.endDateActive = endDateActive;
        this.globalAttribute1 = globalAttribute1;
        this.globalAttribute2 = globalAttribute2;
        this.globalAttribute3 = globalAttribute3;
        this.globalAttribute4 = globalAttribute4;
        this.globalAttribute5 = globalAttribute5;
        this.idProveedor = idProveedor == null ? 0 : idProveedor.intValue();
        this.inspectionRequiredFlag = inspectionRequiredFlag;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateLogin = lastUpdateLogin == null ? 0 : lastUpdateLogin.intValue();
        this.lastUpdatedBy = lastUpdatedBy == null ? 0 : lastUpdatedBy.intValue();
        this.matchOption = matchOption;
        this.nombreProveedor = nombreProveedor;
        this.receiptRequiredFlag = receiptRequiredFlag;
        this.rfc = rfc;
        this.segment2 = segment2;
        this.segment3 = segment3;
        this.segment4 = segment4;
        this.segment5 = segment5;
        this.setOfBooksId = setOfBooksId == null ? 0 : setOfBooksId.intValue();
        this.startDateActive = startDateActive;
        this.summaryFlag = summaryFlag;
        this.vendorNameAlt = vendorNameAlt;
    }
    
    public NvcTblOaProveedoresHPojo(String attributeCategory,
            String attribute1, String attribute2, String attribute3, String attribute4, String attribute5,
            BigInteger createdBy,
            Date creationDate,
            String customerNum,
            BigInteger employeeId,
            String enabledFlag,
            Date endDateActive,
            String globalAttribute1, String globalAttribute2, String globalAttribute3, String globalAttribute4, String globalAttribute5,
            BigDecimal idProveedor,
            String inspectionRequiredFlag,
            Date lastUpdateDate,
            BigInteger lastUpdateLogin,
            BigInteger lastUpdatedBy,
            String matchOption,
            String nombreProveedor,
            String receiptRequiredFlag,
            String rfc, String segment2, String segment3, String segment4, String segment5,
            BigInteger setOfBooksId,
            Date startDateActive,
            String summaryFlag,
            String vendorNameAlt,
            BigDecimal idProvPunchout) {
        this.attributeCategory = attributeCategory;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.createdBy = createdBy == null ? 0 : createdBy.intValue();
        this.creationDate = creationDate;
        this.customerNum = customerNum;
        this.employeeId = employeeId == null ? 0 : employeeId.intValue();
        this.enabledFlag = enabledFlag;
        this.endDateActive = endDateActive;
        this.globalAttribute1 = globalAttribute1;
        this.globalAttribute2 = globalAttribute2;
        this.globalAttribute3 = globalAttribute3;
        this.globalAttribute4 = globalAttribute4;
        this.globalAttribute5 = globalAttribute5;
        this.idProveedor = idProveedor == null ? 0 : idProveedor.intValue();
        this.inspectionRequiredFlag = inspectionRequiredFlag;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateLogin = lastUpdateLogin == null ? 0 : lastUpdateLogin.intValue();
        this.lastUpdatedBy = lastUpdatedBy == null ? 0 : lastUpdatedBy.intValue();
        this.matchOption = matchOption;
        this.nombreProveedor = nombreProveedor;
        this.receiptRequiredFlag = receiptRequiredFlag;
        this.rfc = rfc;
        this.segment2 = segment2;
        this.segment3 = segment3;
        this.segment4 = segment4;
        this.segment5 = segment5;
        this.setOfBooksId = setOfBooksId == null ? 0 : setOfBooksId.intValue();
        this.startDateActive = startDateActive;
        this.summaryFlag = summaryFlag;
        this.vendorNameAlt = vendorNameAlt;
        this.idProvPunchout = idProvPunchout == null ? 0 : idProvPunchout.intValue();
    }

    private void setValues(String attributeCategory,
            String attribute1, String attribute2, String attribute3, String attribute4, String attribute5,
            BigInteger createdBy,
            Date creationDate,
            String customerNum,
            BigInteger employeeId,
            String enabledFlag,
            Date endDateActive,
            String globalAttribute1, String globalAttribute2, String globalAttribute3, String globalAttribute4, String globalAttribute5,
            BigDecimal idProveedor,
            String inspectionRequiredFlag,
            Date lastUpdateDate,
            BigInteger lastUpdateLogin,
            BigInteger lastUpdatedBy,
            String matchOption,
            String nombreProveedor,
            String receiptRequiredFlag,
            String rfc, String segment2, String segment3, String segment4, String segment5,
            BigInteger setOfBooksId,
            Date startDateActive,
            String summaryFlag,
            String vendorNameAlt) {
        this.attributeCategory = attributeCategory;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.createdBy = createdBy == null ? 0 : createdBy.intValue();
        this.creationDate = creationDate;
        this.customerNum = customerNum;
        this.employeeId = employeeId == null ? 0 : employeeId.intValue();
        this.enabledFlag = enabledFlag;
        this.endDateActive = endDateActive;
        this.globalAttribute1 = globalAttribute1;
        this.globalAttribute2 = globalAttribute2;
        this.globalAttribute3 = globalAttribute3;
        this.globalAttribute4 = globalAttribute4;
        this.globalAttribute5 = globalAttribute5;
        this.idProveedor = idProveedor == null ? 0 : idProveedor.intValue();
        this.inspectionRequiredFlag = inspectionRequiredFlag;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateLogin = lastUpdateLogin == null ? 0 : lastUpdateLogin.intValue();
        this.lastUpdatedBy = lastUpdatedBy == null ? 0 : lastUpdatedBy.intValue();
        this.matchOption = matchOption;
        this.nombreProveedor = nombreProveedor;
        this.receiptRequiredFlag = receiptRequiredFlag;
        this.rfc = rfc;
        this.segment2 = segment2;
        this.segment3 = segment3;
        this.segment4 = segment4;
        this.segment5 = segment5;
        this.setOfBooksId = setOfBooksId == null ? 0 : setOfBooksId.intValue();
        this.startDateActive = startDateActive;
        this.summaryFlag = summaryFlag;
        this.vendorNameAlt = vendorNameAlt;
    }

    public void setValFromEntity(NvcTblOaProveedoresH entity) {
        if (entity != null) {
            setValues(entity.getAttributeCategory(),
                    entity.getAttribute1(),
                    entity.getAttribute2(),
                    entity.getAttribute3(),
                    entity.getAttribute4(),
                    entity.getAttribute5(),
                    entity.getCreatedBy(),
                    entity.getCreationDate(),
                    entity.getCustomerNum(),
                    entity.getEmployeeId(), entity.getEnabledFlag(), entity.getEndDateActive(), entity.getGlobalAttribute1(),
                    entity.getGlobalAttribute2(), entity.getGlobalAttribute3(), entity.getGlobalAttribute4(), entity.getGlobalAttribute5(), entity.getIdProveedor(), entity.getInspectionRequiredFlag(),
                    entity.getLastUpdateDate(), entity.getLastUpdateLogin(), entity.getLastUpdatedBy(), entity.getMatchOption(), entity.getNombreProveedor(), entity.getReceiptRequiredFlag(), entity.getRfc(),
                    entity.getSegment2(), entity.getSegment3(), entity.getSegment4(), entity.getSegment5(), entity.getSetOfBooksId(), entity.getStartDateActive(), entity.getSummaryFlag(), entity.getVendorNameAlt());
        }
    }

    public NvcTblOaProveedoresHPojo(String nombreProveedor, java.math.BigDecimal idProveedor) {
        this.nombreProveedor = nombreProveedor;
        this.idProveedor = idProveedor == null ? null : idProveedor.intValue();
    }
    
    

    public String getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    public String getGlobalAttribute1() {
        return globalAttribute1;
    }

    public void setGlobalAttribute1(String globalAttribute1) {
        this.globalAttribute1 = globalAttribute1;
    }

    public String getGlobalAttribute2() {
        return globalAttribute2;
    }

    public void setGlobalAttribute2(String globalAttribute2) {
        this.globalAttribute2 = globalAttribute2;
    }

    public String getGlobalAttribute3() {
        return globalAttribute3;
    }

    public void setGlobalAttribute3(String globalAttribute3) {
        this.globalAttribute3 = globalAttribute3;
    }

    public String getGlobalAttribute4() {
        return globalAttribute4;
    }

    public void setGlobalAttribute4(String globalAttribute4) {
        this.globalAttribute4 = globalAttribute4;
    }

    public String getGlobalAttribute5() {
        return globalAttribute5;
    }

    public void setGlobalAttribute5(String globalAttribute5) {
        this.globalAttribute5 = globalAttribute5;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getInspectionRequiredFlag() {
        return inspectionRequiredFlag;
    }

    public void setInspectionRequiredFlag(String inspectionRequiredFlag) {
        this.inspectionRequiredFlag = inspectionRequiredFlag;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getMatchOption() {
        return matchOption;
    }

    public void setMatchOption(String matchOption) {
        this.matchOption = matchOption;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getReceiptRequiredFlag() {
        return receiptRequiredFlag;
    }

    public void setReceiptRequiredFlag(String receiptRequiredFlag) {
        this.receiptRequiredFlag = receiptRequiredFlag;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getSegment2() {
        return segment2;
    }

    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }

    public String getSegment3() {
        return segment3;
    }

    public void setSegment3(String segment3) {
        this.segment3 = segment3;
    }

    public String getSegment4() {
        return segment4;
    }

    public void setSegment4(String segment4) {
        this.segment4 = segment4;
    }

    public String getSegment5() {
        return segment5;
    }

    public void setSegment5(String segment5) {
        this.segment5 = segment5;
    }

    public Integer getSetOfBooksId() {
        return setOfBooksId;
    }

    public void setSetOfBooksId(Integer setOfBooksId) {
        this.setOfBooksId = setOfBooksId;
    }

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public String getSummaryFlag() {
        return summaryFlag;
    }

    public void setSummaryFlag(String summaryFlag) {
        this.summaryFlag = summaryFlag;
    }

    public String getVendorNameAlt() {
        return vendorNameAlt;
    }

    public void setVendorNameAlt(String vendorNameAlt) {
        this.vendorNameAlt = vendorNameAlt;
    }

    private java.util.List<NvcTblCatalogoPojo> catalogoPojo;

    public List<NvcTblCatalogoPojo> getCatalogoPojo() {
        return catalogoPojo;
    }

    public void setCatalogoPojo(List<NvcTblCatalogoPojo> catalogoPojo) {
        this.catalogoPojo = catalogoPojo;
    }

    /**
     * @return the numCatalogos
     */
    public Integer getNumCatalogos() {
        return numCatalogos;
    }

    /**
     * @param numCatalogos the numCatalogos to set
     */
    public void setNumCatalogos(Integer numCatalogos) {
        this.numCatalogos = numCatalogos;
    }

    /**
     * @return the numCatalogosActivos
     */
    public Integer getNumCatalogosActivos() {
        return numCatalogosActivos;
    }

    /**
     * @param numCatalogosActivos the numCatalogosActivos to set
     */
    public void setNumCatalogosActivos(Integer numCatalogosActivos) {
        this.numCatalogosActivos = numCatalogosActivos;
    }
    
    public Integer getIdProvPunchout() {
        return idProvPunchout;
    }

    public void setIdProvPunchout(Integer idProvPunchout) {
        this.idProvPunchout = idProvPunchout;
    }

}
