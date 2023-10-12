package com.metalsa.reportes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author diego.juarez
 */
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "getGastosTravel",
            procedureName = "NVC_PKG_REPORTES_SPX.GET_GASTOS_TRAVEL",
            resultClasses = GastosTravel.class,
            parameters = {
                @StoredProcedureParameter(type = Integer.class, name = "p_uen", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "p_periodo_inicial", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "p_periodo_final", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_id_cc", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_idioma", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Void.class, name = "p_ref_cursor", mode = ParameterMode.REF_CURSOR)
            }
    )
})
@Entity
@Getter
@Setter
public class GastosTravel implements Serializable{
    
    @Id
    private Long id;
    
    @Column(name="cuenta_z")
    private String cuentaZ;
    
    @Column(name="cuenta_contable")
    private String cuentaContable;
    
    @Column(name="fecha_creation")
    private String fechaCreation;
    
    @Column(name="fecha_aproved")
    private String fechaAproved;
    
    @Column(name="numero_factura")
    private String numeroFactura;
    
    @Column(name="proveedor")
    private String proveedor;
    
    @Column(name="status")
    private String status;
    
    @Column(name="numero_oc")
    private Long numeroOc;
    
    @Column(name="description")
    private String description;
    
    @Column(name="cantidad")
    private Double cantidad;
    
    @Column(name="precio_unitario")
    private Double precioUnitario;
    
    @Column(name="monto")
    private Double monto;
    
    @Column(name="moneda")
    private String moneda;
    
    @Column(name="requisitor")
    private String requisitor;
    
    @Column(name="aprobador")
    private String aprobador;
    
    @Column(name = "categoria")
    private String categoria;
    
    @Column(name = "familia")
    private String familia;
    
    @Column(name = "subfamilia")
    private String subfamilia;

    @Column(name = "comprometido")
    private Double comprometido;

    @Column(name = "erogado")
    private Double erogado;
    
    @Transient
    private Double total;
    
     public Double getTotal(){
        total = this.getComprometido() + this.getErogado();
        return this.total;
    }
   
}
