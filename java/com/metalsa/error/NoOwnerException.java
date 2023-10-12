package com.metalsa.error;

/**
 * Tipo de error cuando se quiere reportar por el servicio rest que el
 * usuario que realiza la operacion no es el dueño o no tiene permiso
 * para la misma.
 *
 * @author ruben.bresler
 */
public class NoOwnerException extends RuntimeException {

    public NoOwnerException(String message) {
        super(message);
    }
}
