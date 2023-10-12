package com.metalsa.aprobacion.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.EqualsAndHashCode;

/**
 * Modelo de archivos adjuntos
 */
@Data
@Entity(name = "nvc_v_adjuntos_por_requisicion")
@IdClass(AdjuntoAprobacion.Pk.class)
public class AdjuntoAprobacion {

    @Id
    @Column(name = "id_requisicion")
    private Long requisicion;

    @Id
    @Column(name = "id_partida")
    private Long linea;

    @Id
    @Column(name = "id_adjunto")
    private Long adjunto;

    @Column(name = "id_carro_compra")
    private Long carroCompras;

    @Column(name = "descripcion_producto")
    private String descripcionLinea;

    @Column(name = "id_unidad_de_medida")
    private String uom;
    private String nombreArchivo;

    @Column(name = "tamanio_archivo")
    private Long longitud;
    private Date fechaCreacion;

    @Column(name = "desc_rfq")
    private String descRfq;
    private String archivoFtp;
    private String ubicacion;
    
    private Integer  isAdjuntoFad;
    
    private String idFad;

    @Transient
    private String iisUbicacion;

    /**
     * primary key
     */
    @Getter
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        @Id
        @Column(name = "id_requisicion")
        private Long requisicion;

        @Id
        @Column(name = "id_partida")
        private Long linea;

        @Id
        @Column(name = "id_adjunto")
        private Long adjunto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion == null ? null : (Date) fechaCreacion.clone();
    }

    public void setFechaCreacion(Date fechaCreacion) {
        if (fechaCreacion == null)
            this.fechaCreacion = null;
        else
            this.fechaCreacion = (Date) fechaCreacion.clone();
    }
}
