package com.metalsa.requisicion.pojo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class DetalleDeRequisicionPojo {

    private Integer idRow;
    private Integer idRequisicion;
    private Integer idPartida;
    private Integer idEstatus;
    private String descripcion;
    private Integer numeroLinea;
    private Double cantidad;
    private Double precio;
    private Double montoExtendido;
    private String uom;
    private String urgente;
    private String razonUrgencia;
    private Date fechaNecesidad;
    private String strFechaNecesidad;
    private String estatus;
    private Integer concatenado;
    private String fabricante;
    private String articuloFabricante;
    private Integer proceso;
    private Integer centroCostos;
    private Integer idProyecto;
    private Integer idCentroCosto;
    private String centroCosto;
    private String proyecto;
    private String codProyecto;
    private Integer idTarea;
    private String tarea;
    private String tipoGasto;
    private String comprador;
    private String notasComprador;
    private Integer idRazonUrgencia;
    private Integer idCuenta;
    private String segmento1;
    private String segmento2;
    private String segmento3;
    private String segmento4;
    private String segmento5;
    private String segmento6;
    private String segmento7;
    private String segmento8;
    private String datosDeAuditoria;
    private Integer idSubfamilia;
    private String siguienteAprobador;
    private String aprobacionEspecial;
    private Integer idProducto;
    private String codProducto;
    private Integer tiempoEntrega;
    private String fuente;
    private String iva;
    private Integer idIva;
    private Integer idCarroCompra;
    private String razonRegreso;
    private String comentarioBitacora;
    private Integer idLocalizacion;
    private Integer idUen;
    private Integer idContrato;
    private Integer idUenSurtidora;
    private String nombreAprobador;
    private String nombreComprador;
    private String tieneRFQ;
    private Integer tipoRecibo;
    private String idMoneda;
    private String nombreUen;
    private String idUsuario;
    private String NombreUsuario;
    private String fechaCreacion;
    private String vendorPartNumber;
    private String appOrigen;//<T402990>
    private Integer idFad;
	private String typeDocumentoMaximo;
    private String documentoValueMaximo;
    private String siteIdMaximo;
    private Integer idTipoCargo;
    private List<NvcTblCarroCompraDetallePojo> detalleMultiCc;

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(Integer numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getUrgente() {
        return urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }

    public String getRazonUrgencia() {
        return razonUrgencia;
    }

    public void setRazonUrgencia(String razonUrgencia) {
        this.razonUrgencia = razonUrgencia;
    }

    public Date getFechaNecesidad() {
        return fechaNecesidad;
    }

    public void setFechaNecesidad(Date fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getConcatenado() {
        return concatenado;
    }

    public void setConcatenado(Integer concatenado) {
        this.concatenado = concatenado;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getArticuloFabricante() {
        return articuloFabricante;
    }

    public void setArticuloFabricante(String articuloFabricante) {
        this.articuloFabricante = articuloFabricante;
    }

    public Integer getProceso() {
        return proceso;
    }

    public void setProceso(Integer proceso) {
        this.proceso = proceso;
    }

    public Integer getCentroCostos() {
        return centroCostos;
    }

    public void setCentroCostos(Integer centroCostos) {
        this.centroCostos = centroCostos;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public String getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(String codProyecto) {
        this.codProyecto = codProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public String getNotasComprador() {
        return notasComprador;
    }

    public void setNotasComprador(String notasComprador) {
        this.notasComprador = notasComprador;
    }

    public Integer getIdRazonUrgencia() {
        return idRazonUrgencia;
    }

    public void setIdRazonUrgencia(Integer idRazonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getSegmento1() {
        return segmento1;
    }

    public void setSegmento1(String segmento1) {
        this.segmento1 = segmento1;
    }

    public String getSegmento2() {
        return segmento2;
    }

    public void setSegmento2(String segmento2) {
        this.segmento2 = segmento2;
    }

    public String getSegmento3() {
        return segmento3;
    }

    public void setSegmento3(String segmento3) {
        this.segmento3 = segmento3;
    }

    public String getSegmento4() {
        return segmento4;
    }

    public void setSegmento4(String segmento4) {
        this.segmento4 = segmento4;
    }

    public String getSegmento5() {
        return segmento5;
    }

    public void setSegmento5(String segmento5) {
        this.segmento5 = segmento5;
    }

    public String getSegmento6() {
        return segmento6;
    }

    public void setSegmento6(String segmento6) {
        this.segmento6 = segmento6;
    }

    public String getSegmento7() {
        return segmento7;
    }

    public void setSegmento7(String segmento7) {
        this.segmento7 = segmento7;
    }

    public String getSegmento8() {
        return segmento8;
    }

    public void setSegmento8(String segmento8) {
        this.segmento8 = segmento8;
    }

    public Integer getIdSubfamilia() {
        return idSubfamilia;
    }

    public void setIdSubfamilia(Integer idSubfamilia) {
        this.idSubfamilia = idSubfamilia;
    }

    public String getSiguienteAprobador() {
        return siguienteAprobador;
    }

    public void setSiguienteAprobador(String siguienteAprobador) {
        this.siguienteAprobador = siguienteAprobador;
    }

    public String getAprobacionEspecial() {
        return aprobacionEspecial;
    }

    public void setAprobacionEspecial(String aprobacionEspecial) {
        this.aprobacionEspecial = aprobacionEspecial;
    }

    public Integer getIdCentroCosto() {
        return idCentroCosto;
    }

    public void setIdCentroCosto(Integer idCentroCosto) {
        this.idCentroCosto = idCentroCosto;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getMontoExtendido() {
        return montoExtendido;
    }

    public void setMontoExtendido(Double montoExtendido) {
        this.montoExtendido = montoExtendido;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Integer getIdIva() {
        return idIva;
    }

    public void setIdIva(Integer idIva) {
        this.idIva = idIva;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public String getStrFechaNecesidad() {
        return strFechaNecesidad;
    }

    public void setStrFechaNecesidad(String strFechaNecesidad) {
        this.strFechaNecesidad = strFechaNecesidad;
    }

    public Integer getIdCarroCompra() {
        return idCarroCompra;
    }

    public void setIdCarroCompra(Integer idCarroCompra) {
        this.idCarroCompra = idCarroCompra;
    }

    public String getNombreAprobador() {
        return nombreAprobador;
    }

    public void setNombreAprobador(String nombreAprobador) {
        this.nombreAprobador = nombreAprobador;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public String getDatosDeAuditoria() {
        return datosDeAuditoria;
    }

    public void setDatosDeAuditoria(String datosDeAuditoria) {
        this.datosDeAuditoria = datosDeAuditoria;
    }

    public Integer getIdUenSurtidora() {
        return idUenSurtidora;
    }

    public void setIdUenSurtidora(Integer idUenSurtidora) {
        this.idUenSurtidora = idUenSurtidora;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getRazonRegreso() {
        return razonRegreso;
    }

    public void setRazonRegreso(String razonRegreso) {
        this.razonRegreso = razonRegreso;
    }

    public String getComentarioBitacora() {
        return comentarioBitacora;
    }

    public void setComentarioBitacora(String comentarioBitacora) {
        this.comentarioBitacora = comentarioBitacora;
    }

    public String getTieneRFQ() {
        return tieneRFQ;
    }

    public void setTieneRFQ(String tieneRFQ) {
        this.tieneRFQ = tieneRFQ;
    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public Integer getIdRow() {
        return idRow;
    }

    public void setIdRow(Integer idRow) {
        this.idRow = idRow;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getVendorPartNumber() {
        return vendorPartNumber;
    }

    public void setVendorPartNumber(String vendorPartNumber) {
        this.vendorPartNumber = vendorPartNumber;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getTipoRecibo() {
        return tipoRecibo;
    }

    public void setTipoRecibo(Integer tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }
    
    // <R17639>
    private String mensajeAprobacion;

    public String getMensajeAprobacion() {
        return mensajeAprobacion;
    }
    
    public void setMensajeAprobacion(String mensajeAprobacion) {
        this.mensajeAprobacion = mensajeAprobacion;
    }
    
    // <R17639/>
	//<T402990>
    public String getAppOrigen() {
        return appOrigen;
    }

    public void setAppOrigen(String appOrigen) {
        this.appOrigen = appOrigen;
    }
	//</T402990>    
    
    public Integer getIdFad() {
        return idFad;
    }

    public void setIdFad(Integer idFad) {
        this.idFad = idFad;
    }

	public String getTypeDocumentoMaximo() {
        return typeDocumentoMaximo;
    }

    public void setTypeDocumentoMaximo(String typeDocumentoMaximo) {
        this.typeDocumentoMaximo = typeDocumentoMaximo;
    }

    public String getDocumentoValueMaximo() {
        return documentoValueMaximo;
    }

    public void setDocumentoValueMaximo(String documentoValueMaximo) {
        this.documentoValueMaximo = documentoValueMaximo;
    }

    public String getSiteIdMaximo() {
        return siteIdMaximo;
    }

    public void setSiteIdMaximo(String siteIdMaximo) {
        this.siteIdMaximo = siteIdMaximo;
    }
	
    public List<NvcTblCarroCompraDetallePojo> getDetalleMultiCc() {
        return detalleMultiCc;
    }

    public void setDetalleMultiCc(List<NvcTblCarroCompraDetallePojo> detalleMultiCc) {
        this.detalleMultiCc = detalleMultiCc;
    }

    public Integer getIdTipoCargo() {
        return idTipoCargo;
    }

    public void setIdTipoCargo(Integer idTipoCargo) {
        this.idTipoCargo = idTipoCargo;
    }
    
    public boolean isFuente(String fuente) {
        return this.fuente != null && this.fuente.equals(fuente);
    }
    
    public boolean isAnyFuente(String... types) {
        for (String oneType : types) {
            if (fuente.equals(oneType)) {
                return true;
            }
        }
        return false;
    }
    
    public String trimmedNotasComprador() {
        return (
            this.notasComprador != null
                && this.notasComprador.length() > 960
        ) ? (
                this.notasComprador.substring(0, 960)
        ) : (
                this.notasComprador
        );
    }
    
    public boolean hasProduct() {
        return this.idProducto != null && this.idProducto != 0;
    }
    
    public boolean hasMultiCc() {
        return this.detalleMultiCc != null && !this.detalleMultiCc.isEmpty();
    }
    
    public boolean areBothUensTheSame() {
        return this.idUen != null && this.idUenSurtidora != null
                && this.idUen.equals(this.idUenSurtidora);
    }
    
    public boolean hasTypeDocumentoMaximo() {
       return this.typeDocumentoMaximo != null && !this.typeDocumentoMaximo.isEmpty();
    }
    
    public boolean hasDocumentoValueMaximo() {
        return this.documentoValueMaximo != null && !this.documentoValueMaximo.isEmpty();
    }

    @Override
    public String toString() {
        return "DetalleDeRequisicionPojo{" +
                "idRow=" + idRow +
                ", idRequisicion=" + idRequisicion +
                ", idPartida=" + idPartida +
                ", idEstatus=" + idEstatus +
                ", descripcion='" + descripcion + '\'' +
                ", numeroLinea=" + numeroLinea +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", estatus='" + estatus + '\'' +
                ", centroCostos=" + centroCostos +
                ", idProyecto=" + idProyecto +
                ", idCentroCosto=" + idCentroCosto +
                ", centroCosto='" + centroCosto + '\'' +
                ", proyecto='" + proyecto + '\'' +
                ", codProyecto='" + codProyecto + '\'' +
                ", idTarea=" + idTarea +
                ", tarea='" + tarea + '\'' +
                ", tipoGasto='" + tipoGasto + '\'' +
                ", idCuenta=" + idCuenta +
                ", segmento1='" + segmento1 + '\'' +
                ", segmento2='" + segmento2 + '\'' +
                ", segmento3='" + segmento3 + '\'' +
                ", segmento4='" + segmento4 + '\'' +
                ", segmento5='" + segmento5 + '\'' +
                ", segmento6='" + segmento6 + '\'' +
                ", segmento7='" + segmento7 + '\'' +
                ", segmento8='" + segmento8 + '\'' +
                ", siguienteAprobador='" + siguienteAprobador + '\'' +
                ", idProducto=" + idProducto +
                ", codProducto='" + codProducto + '\'' +
                ", fuente='" + fuente + '\'' +
                ", idUen=" + idUen +
                ", nombreUen='" + nombreUen + '\'' +
                ", NombreUsuario='" + NombreUsuario + '\'' +
                ", IdTipoCargo='" + idTipoCargo + '\'' +
                '}';
    }
}