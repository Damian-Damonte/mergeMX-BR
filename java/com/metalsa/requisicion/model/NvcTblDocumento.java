package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.metalsa.aprobacion.model.NvcTblFad;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "NVC_TBL_DOCUMENTO")
@NamedQueries({
    @NamedQuery(name = "NvcTblDocumento.findByIdCarroCom", query = "SELECT n FROM NvcTblDocumento n WHERE n.idCarroCompra = :idCarroCompra")
    ,
    @NamedQuery(name = "NvcTblDocumento.findByIdDocumento", query = "SELECT n FROM NvcTblDocumento n WHERE n.idDocumento = :idDocumento")
    ,
    @NamedQuery(name = "NvcTblDocumento.findByIdFad", query = "SELECT n FROM NvcTblDocumento n WHERE n.idFormatoAsignacionDirecta = :idFad")
})

@NamedNativeQueries({
    @NamedNativeQuery(name = "NvcTblDocumento.findByIdProveedor", query = "SELECT do.*\n"
            + "  FROM proveedor_dirigido pd\n"
            + "       JOIN NVC_TBL_DOCUMENTO do\n"
            + "          ON do.proveedor_dirigido_id = pd.id_prov_dirigido\n"
            + "       JOIN REQUISICION r ON r.id_uen = pd.id_uen\n"
            + " WHERE     pd.id_proveedor = ?1\n"
            + "       AND do.activo = 1\n"
            + "       AND r.id_requisicion = ?2", resultClass = NvcTblDocumento.class)

})
public class NvcTblDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ACTIVO")
    private Character activo;
    @JoinColumn(name = "ID_CARRO_COMPRA", referencedColumnName = "ID_CARRO_COMPRA")
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    private NvcTblCarroCompra idCarroCompra;

    @Id
    @SequenceGenerator(name = "SEQ_NVC_TBL_DOCUMENTO_GEN", sequenceName = "SEQ_NVC_TBL_DOCUMENTO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_NVC_TBL_DOCUMENTO_GEN")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DOCUMENTO")
    private Integer idDocumento;
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @Column(name = "NOMBRE_ARCHIVO_FTP")
    private String nombreArchivoFtp;
    @Column(name = "EXTENSION_ARCHIVO")
    private String extensionArchivo;
    @Column(name = "TAMANIO_ARCHIVO")
    private Integer tamanioArchivo;
    @Column(name = "UBICACION_FTP")
    private String ubicacionFtp;

    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    
    @Column(name="PROVEEDOR_DIRIGIDO_ID")
    private Long provDirId;
    
    @Column(name = "ID_UEN")
    private Integer idUen;

    @PreUpdate
    public void preUpdate() {
        System.out.println("com.metalsa.spx.entities.NvcTblDocumento.preUpdate() " + this.idDocumento);
    }

    @PreRemove
    public void preRemove() {
        System.out.println("com.metalsa.spx.entities.NvcTblDocumento.preRemove() " + this.idDocumento);
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivoFtp() {
        return nombreArchivoFtp;
    }

    public void setNombreArchivoFtp(String nombreArchivoFtp) {
        this.nombreArchivoFtp = nombreArchivoFtp;
    }

    public String getExtensionArchivo() {
        return extensionArchivo;
    }

    public void setExtensionArchivo(String extensionArchivo) {
        this.extensionArchivo = extensionArchivo;
    }

    public Integer getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setTamanioArchivo(Integer tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    public String getUbicacionFtp() {
        return ubicacionFtp;
    }

    public void setUbicacionFtp(String ubicacionFtp) {
        this.ubicacionFtp = ubicacionFtp;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Character getActivo() {
        return activo;
    }

    public void setActivo(Character activo) {
        this.activo = activo;
    }

    public NvcTblCarroCompra getIdCarroCompra() {
        return idCarroCompra;
    }

    public void setIdCarroCompra(NvcTblCarroCompra idCarroCompra) {
        this.idCarroCompra = idCarroCompra;
    }
    
    // <FAD>
    @JoinColumn(name = "ID_FORMATO_ASIGNACION_DIRECTA", referencedColumnName = "ID_FORMATO_ASIGNACION_DIRECTA")
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    private NvcTblFad idFormatoAsignacionDirecta;

    public NvcTblFad getIdFormatoAsignacionDirecta() {
        return idFormatoAsignacionDirecta;
    }
    
    public void setIdFormatoAsignacionDirecta(NvcTblFad idFormatoAsignacionDirecta) {
        this.idFormatoAsignacionDirecta = idFormatoAsignacionDirecta;
    }
    
    public Long getProvDirId() {
        return this.provDirId;
    }
    
    public Integer getIdUen() {
        return this.idUen;
    }
    // </FAD>
}
