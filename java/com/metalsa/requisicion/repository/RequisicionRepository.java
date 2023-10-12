package com.metalsa.requisicion.repository;

import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.requisicion.model.Requisicion;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public interface RequisicionRepository {
    public boolean validarAutoaprobacion(Integer idRequisicion);
    public Integer autoAprueba(Integer idRequisicion, Integer idPartida);
    public Requisicion findById(Long idRequisicion);
    public boolean exist(Long idRequisicion);
}
