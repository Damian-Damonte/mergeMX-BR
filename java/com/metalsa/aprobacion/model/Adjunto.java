package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Modelo de archivos adjuntos
 */
@Data
@Entity(name = "documentos_por_requisicion")
public class Adjunto {

    @Id
    @Column(name = "consecutivo")
    private Long id;

    @Column(name = "id_requisicion")
    private Long requisicion;

    @Column(name = "id_partida")
    private Long linea;

    private String descripcion;
    private String nombre;
    private String usuario;

    private Date fecha;

    private String mime;
    private String ruta;

    @Column(name = "file_name_original")
    private String nombreOriginal;

    @Column(name = "ruta_raiz")
    private String raiz;

    private String idCarroCompra;

    private String tamano;

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
