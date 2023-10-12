package com.metalsa.requisicion.pojo;

import com.metalsa.requisicion.model.NvcTblRazonFad;
import com.metalsa.requisicion.utils.ConstantsUtils;
import com.metalsa.aprobacion.model.NvcTblFad;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jose.davila03
 */
public class NvcTblFadPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idRequisicion;
    private Integer idFormatoAsignacionDirecta;
    private String descripcion;
    private Integer idProveedor;
    private Integer idSite;
    private Double monto;
    private String idMoneda;
    private String fechaNecesidad;
    private List<NvcTblDocumentoPojo> adjuntos;
    private List<NvcTblDocumentoPojo> edDocsAgregar;
    private List<NvcTblDocumentoPojo> edDocsEliminar;
    private NvcTblRazonFad nvcTblrazonFad;
    private String otraRazon;
    private String aprobacionEspecial;
    private Integer numLineas;
    private String comentarioComprador;
    private Integer idUen;
    private String fechaCreacion;
    private String idUsuarioCreacion;
    private String nombreUsuarioCreacion;
    private String nombreRequisitor;
    private String nombreComprador;
    private String nombreAdminPlanta;
    private String nombreAdminProceso;
    
    private String comentarioRequisitor;
    private String comentarioAdminProceso;
    private String comentarioAdminPlanta;
    private String nombreAltProveedor;
    private String nombreSite;
    private Integer idRazon;
    
    private String nombreProveedor;
    private String moneda;
    
    private Integer idCarroCompra;

    private List<NvcTblCarroCompraPojo> lineas;
    
    private Integer servicioRealizado;
    private boolean editable; //<RDM54512>//<RDM54805>
    public NvcTblFadPojo() {
    }

    public NvcTblFadPojo(NvcTblFad fad) {
        this.idFormatoAsignacionDirecta = fad.getIdFad();
        this.descripcion = fad.getDescripcion();
        this.comentarioComprador = fad.getComentarioComprador();
        this.fechaNecesidad = fad.getFechaNecesidad() == null
                ? "" : ConstantsUtils.formattDate_DDD_MM_YYYY(fad.getFechaNecesidadAsDate()) ;
        this.idMoneda = fad.getIdMoneda();
        this.idProveedor = fad.getIdProv() != null ? fad.getIdProv().intValue() : null;
        this.idSite = fad.getIdSite() != null ? fad.getIdSite().intValue() : null;
        this.monto = fad.getMonto();
        this.otraRazon = fad.getOtraRazon();
        this.nvcTblrazonFad = new NvcTblRazonFad();
        this.nvcTblrazonFad.setIdRazonFad(fad.getIdRazonFad());
        this.idUsuarioCreacion = fad.getIdUsuarioCreacion();
        this.fechaCreacion = fad.getFechaCreacion() == null
                ? "" : ConstantsUtils.formattDate_DDD_MM_YYYY(fad.getFechaCreacionAsDate());
        this.comentarioRequisitor = fad.getComentarioRequisitor();
        this.comentarioAdminPlanta = fad.getComentarioAdminPlanta();
        this.comentarioAdminProceso = fad.getComentarioAdminProceso();
        this.servicioRealizado = fad.getServicioRealizado();
    }

//    public NvcTblFadPojo(NvcTblFad fad, List<NvcTblDocumento> documentos) {
//        this.idFormatoAsignacionDirecta = fad.getIdFormatoAsignacionDirecta();
//        this.descripcion = fad.getDescripcion();
//        this.comentarioComprador = fad.getComentarioComprador();
//        this.fechaNecesidad = fad.getFechaNecesidad();
//        this.idMoneda = fad.getIdMoneda();
//        this.idProveedor = fad.getIdProveedor();
//        this.idSite = fad.getIdSite();
//        this.monto = fad.getMonto();
//        this.otraRazon = fad.getOtraRazon();
//        this.nvcTblrazonFad = new NvcTblRazonFad();
//        this.nvcTblrazonFad.setIdRazonFad(fad.getIdRazonFad());
//        if (documentos != null && !documentos.isEmpty()) {
//            this.adjuntos = new ArrayList(documentos);
//        }
//    }
    
    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public Integer getIdFormatoAsignacionDirecta() {
        return idFormatoAsignacionDirecta;
    }

    public void setIdFormatoAsignacionDirecta(Integer idFormatoAsignacionDirecta) {
        this.idFormatoAsignacionDirecta = idFormatoAsignacionDirecta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdSite() {
        return idSite;
    }

    public void setIdSite(Integer idSite) {
        this.idSite = idSite;
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

    public List<NvcTblDocumentoPojo> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<NvcTblDocumentoPojo> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public List<NvcTblDocumentoPojo> getEdDocsAgregar() {
        return edDocsAgregar;
    }

    public void setEdDocsAgregar(List<NvcTblDocumentoPojo> edDocsAgregar) {
        this.edDocsAgregar = edDocsAgregar;
    }

    public List<NvcTblDocumentoPojo> getEdDocsEliminar() {
        return edDocsEliminar;
    }

    public void setEdDocsEliminar(List<NvcTblDocumentoPojo> edDocsEliminar) {
        this.edDocsEliminar = edDocsEliminar;
    }

    public NvcTblRazonFad getNvcTblrazonFad() {
        return nvcTblrazonFad;
    }

    public void setNvcTblrazonFad(NvcTblRazonFad nvcTblrazonFad) {
        this.nvcTblrazonFad = nvcTblrazonFad;
    }

    public String getOtraRazon() {
        return otraRazon;
    }

    public void setOtraRazon(String otraRazon) {
        this.otraRazon = otraRazon;
    }

    public String getAprobacionEspecial() {
        return aprobacionEspecial;
    }

    public void setAprobacionEspecial(String aprobacionEspecial) {
        this.aprobacionEspecial = aprobacionEspecial;
    }

    public Integer getNumLineas() {
        return numLineas;
    }

    public void setNumLineas(Integer numLineas) {
        this.numLineas = numLineas;
    }

    public String getComentarioComprador() {
        return comentarioComprador;
    }

    public void setComentarioComprador(String comentarioComprador) {
        this.comentarioComprador = comentarioComprador;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(String idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public String getNombreUsuarioCreacion() {
        return nombreUsuarioCreacion;
    }

    public void setNombreUsuarioCreacion(String nombreUsuarioCreacion) {
        this.nombreUsuarioCreacion = nombreUsuarioCreacion;
    }

    public String getNombreRequisitor() {
        return nombreRequisitor;
    }

    public void setNombreRequisitor(String nombreRequisitor) {
        this.nombreRequisitor = nombreRequisitor;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getNombreAdminPlanta() {
        return nombreAdminPlanta;
    }

    public void setNombreAdminPlanta(String nombreAdminPlanta) {
        this.nombreAdminPlanta = nombreAdminPlanta;
    }

    public String getNombreAdminProceso() {
        return nombreAdminProceso;
    }

    public void setNombreAdminProceso(String nombreAdminProceso) {
        this.nombreAdminProceso = nombreAdminProceso;
    }

    public String getComentarioRequisitor() {
        return comentarioRequisitor;
    }

    public void setComentarioRequisitor(String comentarioRequisitor) {
        this.comentarioRequisitor = comentarioRequisitor;
    }

    public String getComentarioAdminProceso() {
        return comentarioAdminProceso;
    }

    public void setComentarioAdminProceso(String comentarioAdminProceso) {
        this.comentarioAdminProceso = comentarioAdminProceso;
    }

    public String getComentarioAdminPlanta() {
        return comentarioAdminPlanta;
    }

    public void setComentarioAdminPlanta(String comentarioAdminPlanta) {
        this.comentarioAdminPlanta = comentarioAdminPlanta;
    }

    public String getNombreAltProveedor() {
        return nombreAltProveedor;
    }

    public void setNombreAltProveedor(String nombreAltProveedor) {
        this.nombreAltProveedor = nombreAltProveedor;
    }

    public String getNombreSite() {
        return nombreSite;
    }

    public void setNombreSite(String nombreSite) {
        this.nombreSite = nombreSite;
    }

    public Integer getIdRazon() {
        return idRazon;
    }

    public void setIdRazon(Integer idRazon) {
        this.idRazon = idRazon;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Integer getIdCarroCompra() {
        return idCarroCompra;
    }

    public void setIdCarroCompra(Integer idCarroCompra) {
        this.idCarroCompra = idCarroCompra;
    }

    public List<NvcTblCarroCompraPojo> getLineas() {
        return lineas;
    }

    public void setLineas(List<NvcTblCarroCompraPojo> lineas) {
        this.lineas = lineas;
    }
    
    public Integer getServicioRealizado() {
        return servicioRealizado;
    }

    public void setServicioRealizado(Integer servicioRealizado) {
        this.servicioRealizado = servicioRealizado;
    }
    //<RDM54512>//<RDM54805>
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    //<RDM54512>//<RDM54805>
    
}
