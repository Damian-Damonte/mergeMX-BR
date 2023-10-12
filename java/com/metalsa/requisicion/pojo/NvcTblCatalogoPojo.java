package com.metalsa.requisicion.pojo;

import com.metalsa.catalogo.model.NvcTblCatalogo;
import com.metalsa.requisicion.utils.ConstantsUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author APODR7218
 */
public class NvcTblCatalogoPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(NvcTblCatalogoPojo.class);
    private Integer idCatalogo;
    private String nombreCatalogo;
    private Integer idProveedor;
    private String nombreProveedor;
    private String idAdministrador;
    private String nombreAdministrador;
    private String fechaInicioVigencia;
    private String fechaFinVigencia;
    private Integer idTipo;
    private String descTipo;
    private String rfcProv;
    private Integer activo;
    private boolean tieneAvisoTerminacion;
    private Integer tipoAvisoTerminacion;
    private Integer punchout;
    private Integer idProvPunchout;
    private List<NvcTblCatalogoUenPojo> catalogoUensPojo;
    //<RELEASE ARG>
    private Integer idUen;
    private String nombreUen;
    private Integer idCatalogoUen;
    //</RELEASE ARG>
    private ConstantsUtils.EVENTOS evento;
    private String estatusCatalogoUen;//<T402773>
    private int esCatalogoSP; 
   public ConstantsUtils.EVENTOS getEvento() {
        return evento;
    }

    public void setEvento(ConstantsUtils.EVENTOS evento) {
        this.evento = evento;
    }

    public NvcTblCatalogoPojo() {

    }

    public NvcTblCatalogoPojo(
            Integer idCatalogo,
            String nombreCatalogo,
            BigDecimal idProveedor,
            String nombreProveedor,
            String idAdministrador,
            String nombreAdministrador,
            Date fechaInicioVigencia,
            Date fechaFinVigencia,
            BigDecimal idTipo,
            String descTipo,
            BigInteger activo,
            Integer tipoAvisoTerminacion,
            Integer idProvPunchout,
            String rfcProv,
            String estatusCatalogoUen) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConstantsUtils.DATE_PATTERN_DD_MMM_YYYY);
            this.idCatalogo = idCatalogo == null ? 0 : idCatalogo;
            this.nombreCatalogo = nombreCatalogo;
            this.idProveedor = idProveedor == null ? 0 : idProveedor.intValue();
            this.nombreProveedor = nombreProveedor;
            this.idAdministrador = idAdministrador;
            this.nombreAdministrador = nombreAdministrador;
            this.fechaInicioVigencia = fechaInicioVigencia == null ? "" : sdf.format(fechaInicioVigencia);
            this.fechaFinVigencia = fechaFinVigencia == null ? "" : sdf.format(fechaFinVigencia);
            this.idTipo = idTipo == null ? 0 : idTipo.intValue();
            this.descTipo = descTipo;
            this.activo = activo == null ? 0 : activo.intValue();
            this.tipoAvisoTerminacion = tipoAvisoTerminacion == null ? 0 : tipoAvisoTerminacion;
            this.punchout = idProvPunchout == null ? 0 : 1;
            this.idProvPunchout = idProvPunchout;
            this.tieneAvisoTerminacion = tipoAvisoTerminacion != null;
            this.rfcProv = rfcProv;
            this.estatusCatalogoUen = estatusCatalogoUen;//<T402773>
        } catch (Exception e) {
            log.error(e);
        }
    }

    public NvcTblCatalogoPojo(
            Integer idCatalogo,
            String nombreCatalogo,
            BigDecimal idProveedor,
            String nombreProveedor,
            String idAdministrador,
            String nombreAdministrador,
            Date fechaInicioVigencia,
            Date fechaFinVigencia,
            BigDecimal idTipo,
            String descTipo,
            Integer activo,
            Integer tipoAvisoTerminacion,
            Integer idProvPunchout,
            String rfcProv) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConstantsUtils.DATE_PATTERN_DD_MMM_YYYY);
            this.idCatalogo = idCatalogo == null ? 0 : idCatalogo;
            this.nombreCatalogo = nombreCatalogo;
            this.idProveedor = idProveedor == null ? 0 : idProveedor.intValue();
            this.nombreProveedor = nombreProveedor;
            this.idAdministrador = idAdministrador;
            this.nombreAdministrador = nombreAdministrador;
            this.fechaInicioVigencia = fechaInicioVigencia == null ? "" : sdf.format(fechaInicioVigencia);
            this.fechaFinVigencia = fechaFinVigencia == null ? "" : sdf.format(fechaFinVigencia);
            this.idTipo = idTipo == null ? 0 : idTipo.intValue();
            this.descTipo = descTipo;
            this.activo = activo == null ? 0 : activo;
            this.tipoAvisoTerminacion = tipoAvisoTerminacion == null ? 0 : tipoAvisoTerminacion;
            this.punchout = idProvPunchout == null ? 0 : 1;
            this.idProvPunchout = idProvPunchout;
            this.tieneAvisoTerminacion = tipoAvisoTerminacion != null;
            this.rfcProv = rfcProv;
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void setValues(
            Integer idCatalogo,
            String nombreCatalogo,
            BigDecimal idProveedor,
            String nombreProveedor,
            String idAdministrador,
            String nombreAdministrador,
            Date fechaInicioVigencia,
            Date fechaFinVigencia,
            BigDecimal idTipo,
            String descTipo,
            Integer activo,
            Integer tipoAvisoTerminacion,
            Integer idProvPunchout) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConstantsUtils.DATE_PATTERN_DD_MMM_YYYY);
            this.idCatalogo = idCatalogo == null ? 0 : idCatalogo;
            this.nombreCatalogo = nombreCatalogo;
            this.idProveedor = idProveedor == null ? 0 : idProveedor.intValue();
            this.nombreProveedor = nombreProveedor;
            this.idAdministrador = idAdministrador;
            this.nombreAdministrador = nombreAdministrador;
            this.fechaInicioVigencia = fechaInicioVigencia == null ? "" : sdf.format(fechaInicioVigencia);
            this.fechaFinVigencia = fechaFinVigencia == null ? "" : sdf.format(fechaFinVigencia);
            this.idTipo = idTipo == null ? 0 : idTipo.intValue();
            this.descTipo = descTipo;
            this.activo = activo == null ? 0 : activo;
            this.tipoAvisoTerminacion = tipoAvisoTerminacion == null ? 0 : tipoAvisoTerminacion;
            this.punchout = idProvPunchout == null ? 0 : 1;
            this.idProvPunchout = idProvPunchout;
            this.tieneAvisoTerminacion = tipoAvisoTerminacion != null;
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void setValuesFromEntity(NvcTblCatalogo entity) {
        setValues(
                entity.getIdCatalogo(),
                entity.getNombreCatalogo(),
                BigDecimal.valueOf(entity.getIdProveedor()),
                entity.getProveedor().getNombreProveedor(),
                entity.getUsuarioActualizacion(),
                entity.getNombreUsuarioAct(),
                entity.getFechaInicioVigencia(),
                entity.getFechaFinVigencia(),
                BigDecimal.valueOf(entity.getPublicado()),
                entity.getEstatusPublicado().getDescEstatus(),
                entity.getActivo(),
                entity.getTipoAvisoTerminacion(),
                entity.getIdProvPunchout()
        );
    }

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getNombreCatalogo() {
        return nombreCatalogo;
    }

    public void setNombreCatalogo(String nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    /**
     *
     * @param fechaFinVigencia La fecha se espera en formato dd-MM-yyyy
     */
    public void setFechaFinVigencia(String fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public String getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    /**
     *
     * @param fechaInicioVigencia La fecha se espera en formato dd-MM-yyyy
     */
    public void setFechaInicioVigencia(String fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescTipo() {
        return descTipo;
    }

    public void setDescTipo(String descTipo) {
        this.descTipo = descTipo;
    }

    /**
     * @return the catalogoUensPojo
     */
    public List<NvcTblCatalogoUenPojo> getCatalogoUensPojo() {
        return catalogoUensPojo;
    }

    /**
     * @param catalogoUensPojo the catalogoUensPojo to set
     */
    public void setCatalogoUensPojo(List<NvcTblCatalogoUenPojo> catalogoUensPojo) {
        this.catalogoUensPojo = catalogoUensPojo;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("activo:").append(this.activo == null ? "" : this.activo).append(" ")
                .append("fechaFinVigencia:").append(this.fechaFinVigencia == null ? "" : this.fechaFinVigencia).append(" ")
                .append("fechaInicioVigencia:").append(this.fechaInicioVigencia == null ? "" : this.fechaInicioVigencia).append(" ")
                .append("idCatalogo:").append(this.idCatalogo == null ? 0 : this.idCatalogo).append(" ")
                .append("idProveedor:").append(this.idProveedor == null ? 0 : this.idProveedor).append(" ")
                .append("idTipo:").append(this.idTipo == null ? "" : this.idTipo).append(" ")
                .append("nombreCatalogo:").append(this.nombreCatalogo == null ? "" : this.nombreCatalogo).append(" ")
                .append("nombreProveedor:").append(this.nombreProveedor == null ? "" : this.nombreProveedor).append(" ");
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        boolean equals;
        if (object instanceof NvcTblCatalogoPojo) {
            NvcTblCatalogoPojo nvcTblCatalogo = (NvcTblCatalogoPojo) object;
            equals = (nvcTblCatalogo.getActivo() == null ? "null" : nvcTblCatalogo.getActivo()
                    .equals(this.activo == null ? "null" : this.activo)
                    && nvcTblCatalogo.getFechaFinVigencia() == null ? "null" : nvcTblCatalogo.getFechaFinVigencia())
                    .equals(this.fechaFinVigencia == null ? "null" : this.fechaFinVigencia)
                    && (nvcTblCatalogo.getFechaInicioVigencia() == null ? "null" : nvcTblCatalogo.getFechaInicioVigencia())
                            .equals(this.fechaInicioVigencia == null ? "null" : this.fechaInicioVigencia)
                    && (nvcTblCatalogo.getIdCatalogo() == null ? "null" : nvcTblCatalogo.getIdCatalogo())
                            .equals(this.idCatalogo == null ? "null" : this.idCatalogo)
                    && (nvcTblCatalogo.getIdTipo() == null ? "null" : nvcTblCatalogo.getIdTipo())
                            .equals(this.idTipo == null ? "null" : this.idTipo)
                    && (nvcTblCatalogo.getTipoAvisoTerminacion() == null ? "null" : nvcTblCatalogo.getTipoAvisoTerminacion())
                            .equals(this.tipoAvisoTerminacion == null ? "null" : this.tipoAvisoTerminacion)
                    && (nvcTblCatalogo.getIdProvPunchout() == null ? "null" : nvcTblCatalogo.getIdProvPunchout())
                            .equals(this.getIdProvPunchout() == null ? "null" : this.getIdProvPunchout())
                    && (nvcTblCatalogo.getNombreCatalogo() == null ? "null" : nvcTblCatalogo.getNombreCatalogo())
                            .equals(this.nombreCatalogo == null ? "null" : this.nombreCatalogo);
            return equals;
        } else {
            equals = super.equals(object);
            return equals;
        }
    }

    @Override
    public int hashCode() {
        int hash = 9;
        hash = 60 * hash + Objects.hashCode(this.idCatalogo);
        hash = 60 * hash + Objects.hashCode(this.nombreCatalogo);
        hash = 60 * hash + Objects.hashCode(this.fechaInicioVigencia);
        hash = 60 * hash + Objects.hashCode(this.fechaFinVigencia);
        hash = 60 * hash + Objects.hashCode(this.idTipo);
        hash = 60 * hash + Objects.hashCode(this.activo);
        hash = 60 * hash + Objects.hashCode(this.tipoAvisoTerminacion);
        return hash;
    }

    /**
     * @return the idAdministrador
     */
    public String getIdAdministrador() {
        return idAdministrador;
    }

    /**
     * @param idAdministrador the idAdministrador to set
     */
    public void setIdAdministrador(String idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Integer getTipoAvisoTerminacion() {
        return tipoAvisoTerminacion;
    }

    public void setTipoAvisoTerminacion(Integer tipoAvisoTerminacion) {
        this.tipoAvisoTerminacion = tipoAvisoTerminacion;
    }

    /**
     * @return the nombreAdministrador
     */
    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    /**
     * @param nombreAdministrador the nombreAdministrador to set
     */
    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    /**
     * @return the tieneAvisoTerminacion
     */
    public boolean isTieneAvisoTerminacion() {
        return tieneAvisoTerminacion;
    }

    /**
     * @param tieneAvisoTerminacion the tieneAvisoTerminacion to set
     */
    public void setTieneAvisoTerminacion(boolean tieneAvisoTerminacion) {
        this.tieneAvisoTerminacion = tieneAvisoTerminacion;
    }

    public String getRfcProv() {
        return rfcProv;
    }

    public void setRfcProv(String rfcProv) {
        this.rfcProv = rfcProv;
    }

    //<RELEASE ARG>
    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    public Integer getIdCatalogoUen() {
        return idCatalogoUen;
    }

    public void setIdCatalogoUen(Integer idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
    }
    //</RELEASE ARG>
    //<T402773>

    public String getEstatusCatalogoUen() {
        return estatusCatalogoUen;
    }

    public void setEstatusCatalogoUen(String estatusCatalogoUen) {
        this.estatusCatalogoUen = estatusCatalogoUen;
    }

    //</T402773>
    public Integer getPunchout() {
        return punchout;
    }

    public void setPunchout(Integer punchout) {
        this.punchout = punchout;
    }

    public Integer getIdProvPunchout() {
        return idProvPunchout;
    }

    public void setIdProvPunchout(Integer idProvPunchout) {
        this.idProvPunchout = idProvPunchout;
    }

    public int getEsCatalogoSP() {
        return esCatalogoSP;
    }

    public void setEsCatalogoSP(int esCatalogoSP) {
        this.esCatalogoSP = esCatalogoSP;
    }
}
