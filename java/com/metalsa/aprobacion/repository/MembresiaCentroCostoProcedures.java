package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CcPorUsuario;
import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.core.model.CentroCostoUsuario;
import java.util.List;

/**
 *
 * @author APOOD9272
 */
public interface MembresiaCentroCostoProcedures {

    List<Usuario> getAprobadoresPorRango(String idUen, String idioma, String codigo, String requisicion);

    //MDA_REPORTES_FINANZAS
    CcPorUsuario getRespccOrDel(Long idUen, Long idCentroCosto, String usuario, String tipoRelacion);

    List<CentroCostoUsuario> getByUserUen(String lang, String user, Integer uen);

    List<CentroCostoUsuario> getByUserUenCC(String lang, String user, Integer uen, Long cc);

    List<CentroCostoUsuario> getByUserUenRelation(String lang, String user, String relation, Integer uen);

    List<CentroCostoUsuario> getByUserUenRelations(String lang, String user, String relations, Integer uen);

}
