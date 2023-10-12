package com.metalsa.requisicion.repository;

import com.metalsa.core.model.NvcTblOaProveedoresH;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public interface ProveedorRepository {
    NvcTblOaProveedoresH findOneById(Integer id);
}
