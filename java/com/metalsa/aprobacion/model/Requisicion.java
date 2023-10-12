package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * mapeo de requisicion
 */
@Data
@Entity
public class Requisicion implements Serializable {

    @Id
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
