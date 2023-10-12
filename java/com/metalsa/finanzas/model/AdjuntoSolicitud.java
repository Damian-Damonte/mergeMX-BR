package com.metalsa.finanzas.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import lombok.Data;

/**
 *
 * @author JL
 */
@Data
@Entity(name = "NVC_TBL_ADJUNTOS_PRESUPUESTO")
public class AdjuntoSolicitud implements Serializable{
    private static final long serialVersionUID = 1L; 
                
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequence_adj")
    @SequenceGenerator(name="sequence_adj", sequenceName="SEQ_ADJUNTOS_INCREMENTO", allocationSize=1)
    private Long id;

    @Column(name = "ID_SOLICITUD_PRESUPUESTO")
    private Long idSolicitudPresupuesto;

    private String nombreArchivo;

    @Column(name = "tamano")
    private Long peso;

    private String mime;
    
    @Column(name = "path")
    private String ruta;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fecha_creacion", insertable=false)
    private Date fechaCreacion  = new Date();
    
    
               
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
