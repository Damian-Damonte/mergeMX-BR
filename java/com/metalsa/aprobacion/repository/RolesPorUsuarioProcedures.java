//MDA_CONTRALOR
package com.metalsa.aprobacion.repository;

import com.metalsa.recibos.model.Requisitor;
import java.util.List;

/**
 * 
 * @author Oscar del Angel
 */
public interface RolesPorUsuarioProcedures {
    
    List<Requisitor> getUsersByRol(Integer idUen, String clave);
}
