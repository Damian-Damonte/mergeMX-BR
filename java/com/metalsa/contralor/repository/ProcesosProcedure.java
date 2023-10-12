/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.contralor.repository;

import com.metalsa.contralor.model.CentroCostoProceso;
import com.metalsa.contralor.model.CentroCostoProcesoDetalle;
import com.metalsa.contralor.model.CentroCostoProcesoUpdate;
import com.metalsa.contralor.model.ContralorResponse;
import com.metalsa.contralor.model.Procesos;
import com.metalsa.contralor.model.ProcesosUpdate;
import com.metalsa.contralor.model.ProcessUenCC;
import com.metalsa.contralor.model.RequestBodyContralor;
import com.metalsa.contralor.model.ResponsableProceso;
import java.util.List;

/**
 *
 * @author Oscar del Angel
 */
public interface ProcesosProcedure {

    Long findCostsCenterProcessCount(RequestBodyContralor request);

    List<CentroCostoProceso> findCostsCenterProcess(RequestBodyContralor request);

    List<CentroCostoProcesoDetalle> findDetailCC(Integer uen, Long cc);
    
    List<CentroCostoProcesoDetalle> findDetailCC_UEN(Integer uen);

    ContralorResponse updateUsersCCS(CentroCostoProcesoUpdate ccs);
	
    List<Procesos> findAllProcess();//<R39943>
    
    List<Procesos> findAllProcessUen(Integer uen,Integer parent);

    List<Procesos> findAllProcessUenLang(Integer uen,Integer level);

    List<ProcessUenCC> findAllProcessUenCC(Integer uen, String lang);

    ContralorResponse UpdateProcess(ProcesosUpdate request) ;

    ContralorResponse addUpdateProcessUenCC(ProcesosUpdate procesosUpdate);

    List<ResponsableProceso> getResponsiblesByLevel(Integer uen, Integer level);
    
    List<Procesos> findProcessByName(String nombre);
    
}
