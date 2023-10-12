/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import static javax.persistence.ParameterMode.REF_CURSOR;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.StoredProcedureParameter;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edgar.leal
 */
@Entity
@XmlRootElement
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "imprimeRecibo",
            procedureName = "NVC_RECIBOS_SPX_PKG.IMPRIME_RECIBO",
            resultSetMappings = {"ImprimeReciboConstructorResultSetMapping"},
            //resultClasses = ImprimeRecibo.class,
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class),
                
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USUARIO", type = String.class)
                ,
                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FORMAT_DATE", type = String.class)
                ,
                  @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_REQUICISION", type = String.class)
                ,
                   @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ORDEN_COMPRA", type = String.class)
                ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FINI", type = String.class)
                ,
                   @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FFIN", type = String.class)
                ,
                  @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FOLIO", type = String.class)   
                ,
                  @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IDUENS", type = String.class)      
                ,
                  @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_MOSTRARRECIBOS", type = String.class)
                    //<R17486>
                ,
                  @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGINA", type = Integer.class)
                ,
                  @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGESIZE", type = Integer.class)
                    //</R17486>
    }),
    //<R17486>
    @NamedStoredProcedureQuery(name = "cuentaRecibos",
            procedureName = "NVC_RECIBOS_SPX_PKG.CUENTA_RECIBOS",
            parameters = {
               
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USUARIO", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FORMAT_DATE", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "P_REQUICISION", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ORDEN_COMPRA", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FINI", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FFIN", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FOLIO", type = String.class)   
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IDUENS", type = String.class)      
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "P_MOSTRARRECIBOS", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.OUT, name = "TOTAL", type = Integer.class)
                    
                                   
    }),
    //</R17486>
    @NamedStoredProcedureQuery(name = "detalleRecibo",
            procedureName = "NVC_RECIBOS_SPX_PKG.DETALLE_RECIBO",
            //resultClasses = ImprimeRecibo.class,
            resultSetMappings = {"DetalleReciboConstructorResultSetMapping"},
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class),
                
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_REQUICISION", type = String.class)
                ,
                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FOLIO", type = String.class)
                ,
                  @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FORMAT_DATE", type = String.class)    
    })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
            name = "ImprimeReciboConstructorResultSetMapping",
            classes = { 
                @ConstructorResult(
                    targetClass = ImprimeRecibo.class,
                    columns = {
                        @ColumnResult(name="FOLIO", type=Integer.class),
                        @ColumnResult(name="ID_REQUISICION", type=Integer.class),
                        @ColumnResult(name="FECHA_RECEPCION", type=String.class),
                        @ColumnResult(name="NOMBRE_PROVEEDOR", type=String.class),
                        @ColumnResult(name="ID_ORDEN_DE_COMPRA", type=Integer.class),
                        @ColumnResult(name="ID_PARTIDA", type=Integer.class),
                        @ColumnResult(name="DESCRIPCION_PRODUCTO", type=String.class),
                        @ColumnResult(name="CANTIDAD_RECIBIDA", type=Double.class),
                        @ColumnResult(name="COMPRADOR", type=String.class),
                        @ColumnResult(name="NOMBRE_USUARIO", type=String.class),
                        @ColumnResult(name="USUARIO_RECEPTOR", type=String.class),
                        @ColumnResult(name="ID_UNIDAD_DE_MEDIDA", type=String.class),
                        @ColumnResult(name="NOMBRE_UEN", type=String.class),
                        @ColumnResult(name="TRANSACTION_TYPE", type=String.class)
                    }
                )
            }
        ),
        @SqlResultSetMapping(
            name = "DetalleReciboConstructorResultSetMapping",
            classes = { 
                @ConstructorResult(
                    targetClass = ImprimeRecibo.class,
                    columns = {
                        @ColumnResult(name="FOLIO", type=Integer.class),
                        @ColumnResult(name="ID_REQUISICION", type=Integer.class),
                        @ColumnResult(name="ID_PARTIDA", type=Integer.class),
                        @ColumnResult(name="DESCRIPCION_PRODUCTO", type=String.class),
                        @ColumnResult(name="ID_ORDEN_DE_COMPRA", type=Integer.class),
                        @ColumnResult(name="FECHA_RECEPCION", type=String.class),
                        @ColumnResult(name="CANTIDAD_REQUERIDA", type=Double.class),
                        @ColumnResult(name="CANTIDAD_RECIBIDA", type=Double.class),
                        @ColumnResult(name="NOMBRE_PROVEEDOR", type=String.class),
                        @ColumnResult(name="NOMBRE_USUARIO", type=String.class),
                    }
                )
            }
        )
        
})

@IdClass(ImprimeReciboPK.class)
public class ImprimeRecibo implements Serializable{
    @Id
    @Column(name = "FOLIO")
    private Integer folio;
    @Id
    @Column(name = "ID_REQUISICION")
    private Integer idRequisicion;
    @Id
    @Column(name = "ID_PARTIDA")
    private Integer idPartida;
    @Column(name = "FECHA_RECEPCION")
    private String fechaRecepcion;
    @Column(name = "NOMBRE_PROVEEDOR")
    private String nombreProveedor;
    @Column(name = "ID_ORDEN_DE_COMPRA")
    private Integer idOrdenCompra;
    @Column(name = "DESCRIPCION_PRODUCTO")
    private String descripcionProducto;
    @Column(name = "CANTIDAD_REQUERIDA")
    private Double cantidadRequerida;
    @Column(name = "CANTIDAD_RECIBIDA")
    private Double cantidadRecibida;
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Column(name = "COMPRADOR")
    private String comprador;
    @Column(name = "USUARIO_RECEPTOR")
    private String usuarioReceptor;
    @Column(name = "ID_UNIDAD_DE_MEDIDA")
    private String idUnidadDeMedida;
    @Column(name = "NOMBRE_UEN")
    private String nombreUen;
    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;
    
    
    public ImprimeRecibo() {
    }

    /*public ImprimeRecibo(Integer folio, Integer idRequisicion, String fechaRecepcion, String nombreProveedor, Integer idOrdenCompra) {
        this.folio = folio;
        this.idRequisicion = idRequisicion;
        this.fechaRecepcion = fechaRecepcion;
        this.nombreProveedor = nombreProveedor;
        this.idOrdenCompra = idOrdenCompra;
    }*/

    public ImprimeRecibo(Integer folio, Integer idRequisicion, Integer idPartida, String descripcionProducto, Integer idOrdenCompra, String fechaRecepcion, Double cantidadRequerida, Double cantidadRecida, String nombreProveedor,  String nombreUsuario) {
        this.folio = folio;
        this.idRequisicion = idRequisicion;
        this.idPartida = idPartida;
        this.descripcionProducto = descripcionProducto;
        this.idOrdenCompra = idOrdenCompra;
        this.fechaRecepcion = fechaRecepcion;
        this.cantidadRequerida = cantidadRequerida;
        this.cantidadRecibida = cantidadRecida;
        this.nombreProveedor = nombreProveedor;
        this.nombreUsuario = nombreUsuario;
    }

    public ImprimeRecibo(Integer folio, Integer idRequisicion, String fechaRecepcion, String nombreProveedor, Integer idOrdenCompra, Integer idPartida, String descripcionProducto,  Double cantidadRecibida, String comprador , String nombreUsuario, String usuarioReceptor, String idUnidadDeMedida,String nombreUen, String transactionType) {
        this.folio = folio;
        this.idRequisicion = idRequisicion;
        this.fechaRecepcion = fechaRecepcion;
        this.nombreProveedor = nombreProveedor;
        this.idOrdenCompra = idOrdenCompra;
        this.idPartida = idPartida;
        this.descripcionProducto = descripcionProducto;
        //this.cantidadRequerida = cantidadRequerida;
        this.cantidadRecibida = cantidadRecibida;
        this.comprador = comprador;
        this.nombreUsuario = nombreUsuario;
        this.usuarioReceptor = usuarioReceptor;
        this.idUnidadDeMedida = idUnidadDeMedida;
        this.nombreUen = nombreUen;
        this.transactionType = transactionType;
    }

    public String getIdUnidadDeMedida() {
        return idUnidadDeMedida;
    }

    public void setIdUnidadDeMedida(String idUnidadDeMedida) {
        this.idUnidadDeMedida = idUnidadDeMedida;
    }
    
    
    
    
    
    

    public String getUsuarioReceptor() {
        return usuarioReceptor;
    }

    public void setUsuarioReceptor(String usuarioReceptor) {
        this.usuarioReceptor = usuarioReceptor;
    }
    
    

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public Double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Double cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Double getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(Double cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public Double getCantidadReciba() {
        return cantidadRecibida;
    }

    public void setCantidadReciba(Double cantidadReciba) {
        this.cantidadRecibida = cantidadReciba;
    }
    
    
    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    
}
