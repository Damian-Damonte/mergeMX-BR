package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * mapeo de requisicion
 */
@Data
@Entity
public class Requisicion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisicion_sequence")
    @SequenceGenerator(name = "requisicion_sequence", sequenceName = "REQUISICION_S", allocationSize = 1)
    @Column(name = "id_requisicion")
    private Long idRequisicion;

    @Column(name="fecha_requisicion")
    private Date fecha;

    @Column(name = "id_usuario")
    private String usuario;

    @Column(name = "tipo_requisicion")
    private String tipoRequisicion;

    @Column(name = "id_proveedor")
    private Long proveedor;

    private String estatus;

    @Column(name = "id_uen")
    private Long uen;

    @Column(name = "id_centro_de_costos")
    private String centroCostos;

    @Column(name = "id_almacen")
    private Long almacen;

    private String estatusAlmacen;

    @Column(name = "id_tipo")
    private Long tipo;

    @Column(name = "app_origen")
    private String appOrigen;
    
    @OneToMany(mappedBy = "requisicion")
    private Set<DetalleRequisicion> detalles;

    public Date getFecha() {
        return fecha == null ? null : (Date) fecha.clone();
    }

    public void setFecha(Date fecha) {
        if (fecha == null)
            this.fecha = null;
        else
            this.fecha = (Date) fecha.clone();
    }
}
