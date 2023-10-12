package com.metalsa.requisicion.pojo;

import java.math.BigDecimal;

/**
 *
 * @author APODA22392
 */
public class CcUsuarioPojo {

    private String idUsuario;
    private Integer idUen;
    private Integer idCc;
    private String nombreCc;
    private String codigoCc;

    public CcUsuarioPojo() {

    }

    public CcUsuarioPojo(String idUsuario, BigDecimal idUen, BigDecimal idCc, String nombreCc, String codigoCc) {
        this.idUsuario = idUsuario;
        this.idUen = idUen.intValue();
        this.idCc = idCc.intValue();
        this.nombreCc = nombreCc;
        this.codigoCc = codigoCc;

    }

    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
     * @return the idCc
     */
    public Integer getIdCc() {
        return idCc;
    }

    /**
     * @param idCc the idCc to set
     */
    public void setIdCc(Integer idCc) {
        this.idCc = idCc;
    }

    /**
     * @return the nombreCc
     */
    public String getNombreCc() {
        return nombreCc;
    }

    /**
     * @param nombreCc the nombreCc to set
     */
    public void setNombreCc(String nombreCc) {
        this.nombreCc = nombreCc;
    }

    public String getCodigoCc() {
        return codigoCc;
    }

    public void setCodigoCc(String codigoCc) {
        this.codigoCc = codigoCc;
    }

}
