package com.metalsa.aprobacion.model;

import com.metalsa.utils.Constants;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ruben.bresler on 06/07/2017.
 */
@Data
public class ConversacionRequisicion implements Serializable {
    private Long requisicion;
    private boolean leida;
    private int mensajesTotales;
    private LocalDateTime fechaUltimo;
    private List<MensajeRequisicion> mensajes;

    public ConversacionRequisicion(Long requisicion, List<MensajeRequisicion> mensajes) {
        this.requisicion = requisicion;
        this.mensajes = mensajes;

        leida = !mensajes.stream()
                .anyMatch(m -> Constants.BOOLEAN_NO.equalsIgnoreCase(m.getLeido()));
        mensajesTotales = mensajes.size();

        fechaUltimo = mensajes.stream()
                .map(MensajeRequisicion::getFecha)
                .sorted()
                .findFirst()
                .orElse(null);
    }

//    public Date getFechaUltimo() {
//        return fechaUltimo == null ? null : (Date) fechaUltimo.clone();
//    }
//
//    public void setFechaUltimo(Date fechaUltimo) {
//        if (fechaUltimo == null)
//            this.fechaUltimo = null;
//        else
//            this.fechaUltimo = (Date) fechaUltimo.clone();
//    }
}