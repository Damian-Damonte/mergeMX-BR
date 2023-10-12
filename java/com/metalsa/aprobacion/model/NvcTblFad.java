package com.metalsa.aprobacion.model;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

/**
 *
 * @author APOMR10051
 */
@Entity(name = "nvc_tbl_fad")
@Data
@NoArgsConstructor
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
        name = "getFadByIdReq",
        resultSetMappings = {"FadMapping"},
        procedureName = "NVC_PKG_SPX_FAD.GET_FAD_BY_ID_REQ",
        parameters = {
            @StoredProcedureParameter(mode = REF_CURSOR, name = "out_cursor", type = void.class),
            @StoredProcedureParameter(mode = IN, name = "P_ID_REQ", type = Integer.class)
        }
    )
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "FadMapping",
            classes = {
                @ConstructorResult(
                        targetClass = NvcTblFad.class,
                        columns = {
                            @ColumnResult(name = "ID_FORMATO_ASIGNACION_DIRECTA", type = Integer.class),
                            @ColumnResult(name = "DESCRIPCION", type = StringType.class),
                            @ColumnResult(name = "ID_PROV", type = LongType.class),
                            @ColumnResult(name = "NOMBRE_PROVEEDOR", type = StringType.class),
                            @ColumnResult(name = "VENDOR_NAME_ALT", type = StringType.class),
                            @ColumnResult(name = "ID_SITE", type = LongType.class),
                            @ColumnResult(name = "VENDOR_SITE_CODE", type = StringType.class),
                            @ColumnResult(name = "MONTO", type = Double.class),
                            @ColumnResult(name = "ID_MONEDA", type = StringType.class),
                            @ColumnResult(name = "FECHA_NECESIDAD", type = StringType.class),
                            @ColumnResult(name = "FECHA_CREACION", type = StringType.class),
                            @ColumnResult(name = "APROBACION_ESPECIAL", type = StringType.class),
                            @ColumnResult(name = "ID_RAZON_FAD", type = Integer.class),
                            @ColumnResult(name = "RAZON_FAD", type = StringType.class),
                            @ColumnResult(name = "NUM_LINEAS", type = Integer.class),
                            @ColumnResult(name = "COMPRADOR", type = StringType.class),
                            @ColumnResult(name = "COMENTARIO_COMPRADOR", type = StringType.class),
                            @ColumnResult(name = "REQUISITOR", type = StringType.class),
                            @ColumnResult(name = "COMENTARIO_REQUISITOR", type = StringType.class),
                            @ColumnResult(name = "ADMIN_PLANTA", type = StringType.class),
                            @ColumnResult(name = "COMENTARIO_ADMIN_PLANTA", type = StringType.class),
                            @ColumnResult(name = "ADMIN_PROCESO", type = StringType.class),
                            @ColumnResult(name = "COMENTARIO_ADMIN_PROCESO", type = StringType.class),
                            @ColumnResult(name = "ID_USUARIO_CREACION", type = StringType.class),
                            @ColumnResult(name = "USUARIO_CREACION", type = StringType.class)
                        }
                )
            }
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "NvcTblFad.getByIdFad",
            resultClass = NvcTblFad.class,
            resultSetMapping = "FadMapping",
            query = "SELECT DISTINCT F.ID_FORMATO_ASIGNACION_DIRECTA, F.DESCRIPCION, F.ID_PROV, P.NOMBRE_PROVEEDOR, P.VENDOR_NAME_ALT, "
            + "F.ID_SITE, S.VENDOR_SITE_CODE, F.MONTO, F.ID_MONEDA, TO_CHAR(F.FECHA_NECESIDAD, 'DD/MM/YYYY') AS FECHA_NECESIDAD, "
            + "F.FECHA_CREACION, F.ID_RAZON_FAD, F.OTRA_RAZON, "
            + "(SELECT DISTINCT U.NOMBRE_USUARIO FROM USUARIO U WHERE DR.COMPRADOR = U.ID_USUARIO) AS COMPRADOR, "
            + "F.COMENTARIO_COMPRADOR, "
            + "(SELECT DISTINCT U.NOMBRE_USUARIO FROM REQUISICION R, USUARIO U WHERE R.ID_USUARIO = U.ID_USUARIO AND R.ID_REQUISICION = C.ID_REQUISICION) AS REQUISITOR, "
            + "F.COMENTARIO_REQUISITOR, "
            + "F.COMENTARIO_ADMIN_PLANTA, "
            + "(SELECT DISTINCT U.NOMBRE_USUARIO FROM USUARIO U WHERE PU.RESPONSABLE = U.ID_USUARIO) AS ADMIN_PROCESO, "
            + "F.COMENTARIO_ADMIN_PROCESO, "
            + "F.ID_USUARIO_CREACION, "
            + "(SELECT DISTINCT U.NOMBRE_USUARIO FROM USUARIO U WHERE C.USUARIO_CREACION = U.ID_USUARIO) AS USUARIO_CREACION "
            + "FROM NVC_TBL_CARRO_COMPRA C "
            + "JOIN DETALLE_DE_REQUISICION DR ON C.ID_REQUISICION = DR.ID_REQUISICION "
            + "JOIN CC_POR_USUARIO CU ON CU.ID_UEN = DR.ID_UEN AND DR.ID_CC = CU.ID_CC AND CU.TIPO_DE_RELACION = 'Resp' "
            + "JOIN NVC_TBL_PROCESO_UEN PU ON PU.ID_PROCESO_UEN = CU.ID_PROCESO_UEN AND PU.ID_UEN = DR.ID_UEN AND PU.ID_PROCESO_PADRE IS NOT NULL "
            + "JOIN NVC_TBL_FAD F ON C.ID_FAD = F.ID_FORMATO_ASIGNACION_DIRECTA "
            + "JOIN NVC_TBL_OA_PROVEEDORES_H P ON F.ID_PROV = P.ID_PROVEEDOR "
            + "LEFT JOIN NVC_TBL_PROV_SITES_H S ON F.ID_PROV = S.ID_PROVEEDOR AND F.ID_SITE = S.ID_SUCURSAL_PROVEEDOR "
            + "WHERE F.ID_FORMATO_ASIGNACION_DIRECTA = :idFad "
    )
    ,
    @NamedNativeQuery(
            name = "NvcTblFad.getByIdRequisicion",
            resultClass = NvcTblFad.class,
            resultSetMapping = "FadMapping",
            query = "SELECT DISTINCT F.ID_FORMATO_ASIGNACION_DIRECTA, F.DESCRIPCION, F.ID_PROV, P.NOMBRE_PROVEEDOR, P.VENDOR_NAME_ALT, \n" +
"            F.ID_SITE, S.VENDOR_SITE_CODE, F.MONTO, F.ID_MONEDA, TO_CHAR(F.FECHA_NECESIDAD, 'DD-Mon-YYYY') AS FECHA_NECESIDAD, \n" +
"            F.FECHA_CREACION, DR.APROBACION_ESPECIAL, F.ID_RAZON_FAD,\n" +
"            (SELECT CASE WHEN F.ID_RAZON_FAD IN(6, 12) THEN F.OTRA_RAZON ELSE (SELECT R.DESCRIPCION FROM NVC_TBL_RAZON_FAD R WHERE R.ID_RAZON_FAD = F.ID_RAZON_FAD) END FROM DUAL) RAZON_FAD,\n" +
"            (SELECT COUNT(*) FROM DETALLE_DE_REQUISICION WHERE ID_REQUISICION = C.ID_REQUISICION) AS NUM_LINEAS,\n" +
"            F.OTRA_RAZON, \n" +
"            (SELECT DISTINCT U.NOMBRE_USUARIO FROM USUARIO U WHERE DR.COMPRADOR = U.ID_USUARIO) AS COMPRADOR, \n" +
"            F.COMENTARIO_COMPRADOR, \n" +
"            (SELECT DISTINCT U.NOMBRE_USUARIO FROM REQUISICION R, USUARIO U WHERE U.ID_USUARIO = R.ID_USUARIO AND R.ID_REQUISICION = C.ID_REQUISICION) AS REQUISITOR, \n" +
"            F.COMENTARIO_REQUISITOR,\n" +
"            (SELECT DISTINCT U.NOMBRE_USUARIO FROM USUARIO U WHERE U.ID_USUARIO = (SELECT NVC_PKG_SPX_FAD.FN_GET_CONTROLLER_BY_REQ(C.ID_REQUISICION, 1) FROM DUAL)) ADMIN_PLANTA,\n" +
"            F.COMENTARIO_ADMIN_PLANTA, \n" +
"            (SELECT DISTINCT U.NOMBRE_USUARIO FROM USUARIO U WHERE U.ID_USUARIO = (SELECT NVC_PKG_SPX_FAD.FN_GET_PROCESS_APROVAL(C.ID_REQUISICION, 1) FROM DUAL)) AS ADMIN_PROCESO, \n" +
"            F.COMENTARIO_ADMIN_PROCESO, \n" +
"            F.ID_USUARIO_CREACION, \n" +
"            (SELECT DISTINCT U.NOMBRE_USUARIO FROM USUARIO U WHERE C.USUARIO_CREACION = U.ID_USUARIO) AS USUARIO_CREACION\n" +
"            FROM NVC_TBL_CARRO_COMPRA C \n" +
"            JOIN DETALLE_DE_REQUISICION DR ON C.ID_REQUISICION = DR.ID_REQUISICION\n" +
"            JOIN NVC_TBL_FAD F ON C.ID_FAD = F.ID_FORMATO_ASIGNACION_DIRECTA \n" +
"            JOIN NVC_TBL_OA_PROVEEDORES_H P ON F.ID_PROV = P.ID_PROVEEDOR \n" +
"            LEFT JOIN NVC_TBL_PROV_SITES_H S ON F.ID_PROV = S.ID_PROVEEDOR AND F.ID_SITE = S.ID_SUCURSAL_PROVEEDOR \n" +
"            WHERE C.ID_REQUISICION = :idRequisicion "
    )
})
public class NvcTblFad implements Serializable {

    @Id
    @Column(name = "ID_FORMATO_ASIGNACION_DIRECTA")
    private Integer idFad;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ID_PROV")
    private Long idProv;
    @Transient
    private String nombreProveedor;
    @Transient
    private String vendorNameAlt;
    @Column(name = "ID_SITE")
    private Long idSite;
    @Column(name = "VENDOR_SITE_CODE")
    private String vendorSiteCode;
    @Column(name = "MONTO")
    private Double monto;
    @Column(name = "ID_MONEDA")
    private String idMoneda;
    @Column(name = "FECHA_NECESIDAD")
    private String fechaNecesidad;
    @Column(name = "FECHA_NECESIDAD", insertable = false, updatable = false)
    private Date fechaNecesidadAsDate;
    @Column(name = "FECHA_CREACION")
    private String fechaCreacion;
    @Column(name = "FECHA_CREACION", insertable = false, updatable = false)
    private Date fechaCreacionAsDate;
    @Transient
    private String aprobacionEspecial;
    @Column(name = "ID_RAZON_FAD")
    private Integer idRazonFad;
    @Transient
    private String razonFad;
    @Transient
    private Integer numLineas;
    @Transient
    private String comprador;
    @Column(name = "COMENTARIO_COMPRADOR")
    private String comentarioComprador;
    @Transient
    private String requisitor;
    @Column(name = "COMENTARIO_REQUISITOR")
    private String comentarioRequisitor;
    @Transient
    private String adminPlanta;
    @Column(name = "COMENTARIO_ADMIN_PLANTA")
    private String comentarioAdminPlanta;
    @Transient
    private String adminProceso;
    @Column(name = "COMENTARIO_ADMIN_PROCESO")
    private String comentarioAdminProceso;
    @Column(name = "ID_USUARIO_CREACION")
    private String idUsuarioCreacion;
    @Transient
    private String usuarioCreacion;
    @Column(name = "OTRA_RAZON")
    private String otraRazon;
    @Column(name = "SERVICIO_REALIZADO")
    private Integer servicioRealizado;

    public NvcTblFad(Integer idFad, String descripcion, Long idProv, String nombreProveedor, String vendorNameAlt, Long idSite, String vendorSiteCode, Double monto, String idMoneda, String fechaNecesidad, String fechaCreacion, String aprobacionEspecial, Integer idRazonFad, String razonFad, Integer numLineas, String comprador, String comentarioComprador, String requisitor, String comentarioRequisitor, String adminPlanta, String comentarioAdminPlanta, String adminProceso, String comentarioAdminProceso, String idUsuarioCreacion, String usuarioCreacion) {
        this.idFad = idFad;
        this.descripcion = descripcion;
        this.idProv = idProv;
        this.nombreProveedor = nombreProveedor;
        this.vendorNameAlt = vendorNameAlt;
        this.idSite = idSite;
        this.vendorSiteCode = vendorSiteCode;
        this.monto = monto;
        this.idMoneda = idMoneda;
        this.fechaNecesidad = fechaNecesidad;
        this.fechaCreacion = fechaCreacion;
        this.aprobacionEspecial = aprobacionEspecial;
        this.idRazonFad = idRazonFad;
        this.razonFad = razonFad;
        this.numLineas = numLineas;
        this.comprador = comprador;
        this.comentarioComprador = comentarioComprador;
        this.requisitor = requisitor;
        this.comentarioRequisitor = comentarioRequisitor;
        this.adminPlanta = adminPlanta;
        this.comentarioAdminPlanta = comentarioAdminPlanta;
        this.adminProceso = adminProceso;
        this.comentarioAdminProceso = comentarioAdminProceso;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.usuarioCreacion = usuarioCreacion;
    }

    public Integer getIdFad() {
        return idFad;
    }

    public void setIdFad(Integer idFad) {
        this.idFad = idFad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdProv() {
        return idProv;
    }

    public void setIdProv(Long idProv) {
        this.idProv = idProv;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getVendorNameAlt() {
        return vendorNameAlt;
    }

    public void setVendorNameAlt(String vendorNameAlt) {
        this.vendorNameAlt = vendorNameAlt;
    }

    public Long getIdSite() {
        return idSite;
    }

    public void setIdSite(Long idSite) {
        this.idSite = idSite;
    }

    public String getVendorSiteCode() {
        return vendorSiteCode;
    }

    public void setVendorSiteCode(String vendorSiteCode) {
        this.vendorSiteCode = vendorSiteCode;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getFechaNecesidad() {
        return fechaNecesidad;
    }

    public void setFechaNecesidad(String fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public String getAprobacionEspecial() {
        return aprobacionEspecial;
    }

    public void setAprobacionEspecial(String aprobacionEspecial) {
        this.aprobacionEspecial = aprobacionEspecial;
    }

    public Integer getIdRazonFad() {
        return idRazonFad;
    }

    public void setIdRazonFad(Integer idRazonFad) {
        this.idRazonFad = idRazonFad;
    }

    public String getRazonFad() {
        return razonFad;
    }

    public void setRazonFad(String razonFad) {
        this.razonFad = razonFad;
    }
    
    public Integer getNumLineas() {
        return numLineas;
    }

    public void setNumLineas(Integer numLineas) {
        this.numLineas = numLineas;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getComentarioComprador() {
        return comentarioComprador;
    }

    public void setComentarioComprador(String comentarioComprador) {
        this.comentarioComprador = comentarioComprador;
    }

    public String getRequisitor() {
        return requisitor;
    }

    public void setRequisitor(String requisitor) {
        this.requisitor = requisitor;
    }

    public String getComentarioRequisitor() {
        return comentarioRequisitor;
    }

    public void setComentarioRequisitor(String comentarioRequisitor) {
        this.comentarioRequisitor = comentarioRequisitor;
    }
    
    public String getAdminPlanta() {
        return adminPlanta;
    }

    public void setAdminPlanta(String adminPlanta) {
        this.adminPlanta = adminPlanta;
    }

    public String getComentarioAdminPlanta() {
        return comentarioAdminPlanta;
    }

    public void setComentarioAdminPlanta(String comentarioAdminPlanta) {
        this.comentarioAdminPlanta = comentarioAdminPlanta;
    }

    public String getAdminProceso() {
        return adminProceso;
    }

    public void setAdminProceso(String adminProceso) {
        this.adminProceso = adminProceso;
    }

    public String getComentarioAdminProceso() {
        return comentarioAdminProceso;
    }

    public void setComentarioAdminProceso(String comentarioAdminProceso) {
        this.comentarioAdminProceso = comentarioAdminProceso;
    }

    public String getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(String idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }
    
    public String getOtraRazon() {
        return this.otraRazon;
    }
    
    public void setOtraRazon(String razon) {
        this.otraRazon = razon;
    }
    
    public Integer getServicioRealizado() {
        return this.servicioRealizado;
    }
    
    public void setServicioRealizado(Integer servicio) {
        this.servicioRealizado = servicio;
    }
    
    public Date getFechaCreacionAsDate() {
        return this.fechaCreacionAsDate;
    }
    
    public Date fechaNecesidadAsDate() {
        return this.fechaNecesidadAsDate;
    }
    
    
    
}
