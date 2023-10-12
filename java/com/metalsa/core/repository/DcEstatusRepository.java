package com.metalsa.core.repository;

import com.metalsa.core.model.DcEstatus;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public interface DcEstatusRepository {
    DcEstatus findActiveByDesc(String desc);
}
