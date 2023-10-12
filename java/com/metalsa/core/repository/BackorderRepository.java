package com.metalsa.core.repository;

import com.metalsa.core.model.Backorder;
import java.util.List;

/**
 * @author APOMR10051
 */
public interface BackorderRepository {

    List<Backorder> getBackorders(Integer idRequisicion, Integer idPartida);

}
