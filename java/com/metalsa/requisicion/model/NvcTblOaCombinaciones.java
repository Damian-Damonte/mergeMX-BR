package com.metalsa.requisicion.model;

/**
 *
 * @author jose.rivera02
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "NVC_TBL_OA_COMBINACIONES")
public class NvcTblOaCombinaciones implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_CUENTA")
    private Integer idCuenta;

    @Column(name = "SEGMENTO_1")
    private String segmento1;

    @Column(name = "SEGMENTO_2")
    private String segmento2;

    @Column(name = "SEGMENTO_3")
    private String segmento3;

    @Column(name = "SEGMENTO_4")
    private String segmento4;

    @Column(name = "SEGMENTO_5")
    private String segmento5;

    @Column(name = "SEGMENTO_6")
    private String segmento6;

    @Column(name = "SEGMENTO_7")
    private String segmento7;

    @Column(name = "SEGMENTO_8")
    private String segmento8;

    @Column(name = "COMBINACION")
    private String combinacion;

    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.DATE)
    private Date startDateActive;

    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.DATE)
    private Date endDateActive;

    @Column(name = "CUENTA_NATURAL")
    private String cuentaNatural;

    @Column(name = "ID_UEN")
    private Integer idUen;

    @Column(name = "LANGUAGE")
    private String lenguaje;

    @Column(name = "CHART_OF_ACCOUNTS_ID")
    private long chartOfAccountsId;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdCuenta() != null ? getIdCuenta().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblOaCombinaciones)) {
            return false;
        }
        NvcTblOaCombinaciones other = (NvcTblOaCombinaciones) object;
        return !((this.getIdCuenta() == null && other.getIdCuenta() != null) || (this.getIdCuenta() != null && !this.idCuenta.equals(other.idCuenta)));
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblOaCombinaciones[ id=" + getIdCuenta() + " ]";
    }

    /**
     * @return the idCuenta
     */
    public Integer getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param idCuenta the idCuenta to set
     */
    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * @return the segmento1
     */
    public String getSegmento1() {
        return segmento1;
    }

    /**
     * @param segmento1 the segmento1 to set
     */
    public void setSegmento1(String segmento1) {
        this.segmento1 = segmento1;
    }

    /**
     * @return the segmento2
     */
    public String getSegmento2() {
        return segmento2;
    }

    /**
     * @param segmento2 the segmento2 to set
     */
    public void setSegmento2(String segmento2) {
        this.segmento2 = segmento2;
    }

    /**
     * @return the segmento3
     */
    public String getSegmento3() {
        return segmento3;
    }

    /**
     * @param segmento3 the segmento3 to set
     */
    public void setSegmento3(String segmento3) {
        this.segmento3 = segmento3;
    }

    /**
     * @return the segmento4
     */
    public String getSegmento4() {
        return segmento4;
    }

    /**
     * @param segmento4 the segmento4 to set
     */
    public void setSegmento4(String segmento4) {
        this.segmento4 = segmento4;
    }

    /**
     * @return the segmento5
     */
    public String getSegmento5() {
        return segmento5;
    }

    /**
     * @param segmento5 the segmento5 to set
     */
    public void setSegmento5(String segmento5) {
        this.segmento5 = segmento5;
    }

    /**
     * @return the segmento6
     */
    public String getSegmento6() {
        return segmento6;
    }

    /**
     * @param segmento6 the segmento6 to set
     */
    public void setSegmento6(String segmento6) {
        this.segmento6 = segmento6;
    }

    /**
     * @return the segmento7
     */
    public String getSegmento7() {
        return segmento7;
    }

    /**
     * @param segmento7 the segmento7 to set
     */
    public void setSegmento7(String segmento7) {
        this.segmento7 = segmento7;
    }

    /**
     * @return the segmento8
     */
    public String getSegmento8() {
        return segmento8;
    }

    /**
     * @param segmento8 the segmento8 to set
     */
    public void setSegmento8(String segmento8) {
        this.segmento8 = segmento8;
    }

    /**
     * @return the combinacion
     */
    public String getCombinacion() {
        return combinacion;
    }

    /**
     * @param combinacion the combinacion to set
     */
    public void setCombinacion(String combinacion) {
        this.combinacion = combinacion;
    }

    /**
     * @return the startDateActive
     */
    public Date getStartDateActive() {
        return startDateActive;
    }

    /**
     * @param startDateActive the startDateActive to set
     */
    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    /**
     * @return the endDateActive
     */
    public Date getEndDateActive() {
        return endDateActive;
    }

    /**
     * @param endDateActive the endDateActive to set
     */
    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    /**
     * @return the cuentaNatural
     */
    public String getCuentaNatural() {
        return cuentaNatural;
    }

    /**
     * @param cuentaNatural the cuentaNatural to set
     */
    public void setCuentaNatural(String cuentaNatural) {
        this.cuentaNatural = cuentaNatural;
    }

    /**
     * @return the idUen
     */
    public Integer getIdUen() {
        return idUen;
    }

    /**
     * @param idUen the idUen to set
     */
    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    /**
     * @return the chartOfAccountsId
     */
    public long getChartOfAccountsId() {
        return chartOfAccountsId;
    }

    /**
     * @param chartOfAccountsId the chartOfAccountsId to set
     */
    public void setChartOfAccountsId(long chartOfAccountsId) {
        this.chartOfAccountsId = chartOfAccountsId;
    }

    /**
     * @return the lenguaje
     */
    public String getLenguaje() {
        return lenguaje;
    }

    /**
     * @param lenguaje the lenguaje to set
     */
    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

}
