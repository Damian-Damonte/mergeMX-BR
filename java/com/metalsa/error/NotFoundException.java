package com.metalsa.error;

/**
 * Excepcion para identificar los errores de items de BD no encontrados
 * y que se quieran reportar por el servicio rest
 *
 * @author ruben.bresler
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
