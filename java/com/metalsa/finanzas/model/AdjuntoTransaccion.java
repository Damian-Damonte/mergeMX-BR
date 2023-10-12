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
@Entity(name = "NVC_TBL_ADJUNTOS_TRANSACCION")
public class AdjuntoTransaccion implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequence_adj_tr")
    @SequenceGenerator(name="sequence_adj_tr", sequenceName="SEQ_ADJUNTOS_TRANSACCION", allocationSize=1)
    private Long id;

    @Column(name = "ID_TRANSACCION_GROUP")
    private Long idTransaccionGroup;

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
