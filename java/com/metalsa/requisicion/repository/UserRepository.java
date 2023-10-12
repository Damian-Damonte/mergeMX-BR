package com.metalsa.requisicion.repository;

import com.metalsa.aprobacion.model.Usuario;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public interface UserRepository {
    Usuario getOneById(String id);
}
