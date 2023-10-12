package com.metalsa.requisicion.pojo;

import com.metalsa.aprobacion.model.Usuario;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author APOMS7376
 */
public class NvcTblCatalogoUenPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fechaActualizacion;
    private Date fechaCreacion;
    private Integer idCatalogo;
    private Integer idCatalogoUen;
    private Integer idUen;
    private Integer activo;
    private String nombreUen;
    private String usuarioActualizacion;
    private String usuarioCreacion;
    private List<NvcTblCatalogoItemPojo> items;
    private Integer tipoRecibo;
    private Integer idFacturacion;
    private String idioma;
    //-------------- j. Alfred -------------------------------------    R16693 
    private String idCompradorUen;
    private String comprador;
    //-------------- j. Alfred -------------------------------------    R16693        ;
    private List<NvcTblCatalogoLocalizacionPojo> localizaciones;
//-------------- j. Alfred -------------------------------------    
    private Integer tipoPrecio;
    private Integer idTemplate;
//-------------- j. Alfred -------------------------------------    
//    private NvcTblCatalogoLocalizacionPojo localizacionFacturacion;
    private Integer idProvPunchout;
    private Integer idIva;
    
    public NvcTblCatalogoUenPojo() {
    }

    public NvcTblCatalogoUenPojo(Date fechaActualizacion, Date fechaCreacion, Integer idCatalogo,
            Integer idCatalogoUen, Long idUen, String usuarioActualizacion,
            String usuarioCreacion, BigInteger activo, String nombreUen, Integer tipoRecibo, Integer idFacturacion, 
            String ocManual,String idCompradorUen, Usuario comprador, Integer tipoPrecio, Integer idProvPunchout, Integer idIva) { // <R17736 />   // J. Alfred <R16693 />
        this.fechaActualizacion = fechaActualizacion;
        this.fechaCreacion = fechaCreacion;
        this.idCatalogo = idCatalogo == null ? 0 : idCatalogo;
        this.idCatalogoUen = idCatalogoUen == null ? 0 : idCatalogoUen;
        this.idUen = idUen == null ? 0 : idUen.intValue();
        this.usuarioActualizacion = usuarioActualizacion;
        this.usuarioCreacion = usuarioCreacion;
        this.activo = activo == null ? 0 : activo.intValue();
        this.nombreUen = nombreUen;
        this.tipoRecibo = tipoRecibo;
        this.idFacturacion = idFacturacion;
        this.ocManual = ocManual == null || ocManual.equals("N") ? "false" : 
                ocManual.equals("Y") ? "true" : "false" ; // <R17736 />
        this.idCompradorUen= idCompradorUen!=null?idCompradorUen:null; // J. Alfred <R16693
        this.comprador= comprador!=null?comprador.getNombreUsuario():null; // J. Alfred <R16693
        this.tipoPrecio = tipoPrecio;     //cv ==TipoPrecio
        this.idProvPunchout = idProvPunchout;
        this.idIva = idIva;
    }
	
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public Integer getIdCatalogoUen() {
        return idCatalogoUen;
    }

    public void setIdCatalogoUen(Integer idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public List<NvcTblCatalogoItemPojo> getItems() {
        if (items == null) {
            return new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<NvcTblCatalogoItemPojo> items) {
        this.items = items;
    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    public Integer getTipoRecibo() {
        return tipoRecibo;
    }

    public void setTipoRecibo(Integer tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }

    public List<NvcTblCatalogoLocalizacionPojo> getLocalizaciones() {
        return localizaciones;
    }

    public void setLocalizaciones(List<NvcTblCatalogoLocalizacionPojo> localizaciones) {
        this.localizaciones = localizaciones;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("fechaActualizacion=").append(this.fechaActualizacion == null ? "null" : fechaActualizacion).append(" ")
                .append("fechaCreacion=").append(fechaCreacion == null ? "null" : fechaCreacion).append(" ")
                .append("idCatalogo=").append(idCatalogo == null ? "null" : idCatalogo).append(" ")
                .append("idCatalogoUen=").append(idCatalogoUen == null ? "null" : idCatalogoUen).append(" ")
                .append("idUen=").append(idUen == null ? "null" : idUen).append(" n")
                .append("usuarioActualizacion=").append(usuarioActualizacion == null ? "null" : usuarioActualizacion).append(" ")
                .append("usuarioCreacion =").append(usuarioCreacion == null ? "null" : usuarioCreacion).append(" ");
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NvcTblCatalogoUenPojo) {
            NvcTblCatalogoUenPojo nvcTblCatalogoUen = (NvcTblCatalogoUenPojo) obj;
            boolean equals = (nvcTblCatalogoUen.getIdCatalogo() == null ? "null" : nvcTblCatalogoUen.getIdCatalogo())
                    .equals(this.idCatalogo == null ? "null" : this.idCatalogo)
                    && (nvcTblCatalogoUen.getIdUen() == null ? "null" : nvcTblCatalogoUen.getIdUen())
                            .equals(this.idUen == null ? "null" : this.idUen);
            return equals;
        } else {
            boolean equals = super.equals(obj);
            return equals;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idCatalogo);
        hash = 59 * hash + Objects.hashCode(this.idCatalogoUen);
        hash = 59 * hash + Objects.hashCode(this.idUen);
        return hash;
    }

    public Integer getIdFacturacion() {
        return idFacturacion;
    }

    public void setIdFacturacion(Integer idFacturacion) {
        this.idFacturacion = idFacturacion;
    }
    
    // OC MANUAL FUENTE P  <R17736>
    private String ocManual;

    public String getOcManual() {
        return ocManual;
    }

    public void setOcManual(String ocManual) {
        this.ocManual = ocManual;
    }
    // <R17736 />
    // J. Alfred R16693 
    public String getIdCompradorUen() {
        return idCompradorUen;
    }

    public void setIdCompradorUen(String idComprador) {
        this.idCompradorUen = idComprador;
    }
    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }
    // J. Alfred R16693 
    
	//-------------- j. Alfred -------------------------------------    

    public Integer getTipoPrecio() {
        return tipoPrecio;
    }

    public void setTipoPrecio(Integer tipoPrecio) {
        this.tipoPrecio = tipoPrecio;
    }

    public Integer getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Integer idTemplate) {
        this.idTemplate = idTemplate;
    }

//-------------- j. Alfred -------------------------------------  

    public Integer getIdProvPunchout() {
        return idProvPunchout;
    }

    public void setIdProvPunchout(Integer idProvPunchout) {
        this.idProvPunchout = idProvPunchout;
    }
    
    public Integer getIdIva() {
        return idIva;
    }

    public void setIdIva(Integer idIva) {
        this.idIva = idIva;
    }
	
	public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
