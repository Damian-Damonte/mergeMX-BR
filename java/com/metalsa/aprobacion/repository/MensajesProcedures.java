package com.metalsa.aprobacion.repository;


import com.metalsa.aprobacion.model.NotificacionHeader;

import java.util.List;

/**
 * Created by ruben.bresler on 06/07/2017.
 */
public interface MensajesProcedures {

    List<NotificacionHeader> getProcNuevoHeader(String usuario, String idioma);
}
