package com.metalsa.home.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import static javax.persistence.ParameterMode.REF_CURSOR;

/**
 *
 * @author APOMR10051
 */
@Data
@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "GET_ITEMS_SUGERIDOS_BY_USER",
            procedureName = "NVC_PKG_HOME_SPX.GET_ITEMS_SUGERIDOS_BY_USER",
            resultClasses = ItemsSugeridos.class,
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "CURSOR_OUT", type = void.class),
                
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_USUARIO", type = String.class),
                
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_OUT", type = String.class)
            }),
    
    @NamedStoredProcedureQuery(name = "ADD_CARRO_COMPRAS",
            procedureName = "NVC_PKG_HOME_SPX.ADD_CARRO_COMPRAS",
            resultClasses = ItemsSugeridos.class,
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_USUARIO", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_ITEM", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_UEN", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_ALMACEN", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_LOCALIZACION", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_DESCRIPCION", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FUENTE", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_UDM", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_SUBFAMILIA", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IMG_URL", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_OUT", type = String.class)
            })
})
public class ItemsSugeridos implements Serializable {

    @Column(name = "cantidad")
    private Integer vecesComprado;

    @Id
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "cod_producto")
    private String codigoProducto;

    @Column(name = "id_uen")
    private Integer idUen;
    
    @Column(name = "nombre_uen")
    private String nombreUen;

    @Column(name = "id_almacen")
    private Integer idAlmacen;

    @Column(name = "fuente")
    private String fuente;
    
    @Column(name = "id_localizacion")
    private Integer idLocalizacion;
    
    @Column(name = "id_uen_surtidora")
    private Integer idUenSurtidora;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "id_moneda")
    private String idMoneda;

    @Column(name = "udm")
    private Integer udm;

    @Column(name = "url_ftp")
    private String urlFtp;
    
    @Column(name = "id_subfamilia")
    private Integer idSubfamilia;
    
    @Column(name = "publicado")
    private Integer publicado;

    @Transient
    private String urlIis;
    
    @Transient
    private boolean noImg;
    
    @Transient
    private String idUsuario;

}
