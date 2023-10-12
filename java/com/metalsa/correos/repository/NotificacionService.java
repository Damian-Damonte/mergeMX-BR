package com.metalsa.correos.repository;

import com.metalsa.correos.pojo.NotificacionRequest;

/**
 *
 * @author miguel.rdz
 */
public interface NotificacionService {

    boolean insert(NotificacionRequest req);
    
    boolean update(NotificacionRequest req);
    
    boolean delete(NotificacionRequest req);
}
