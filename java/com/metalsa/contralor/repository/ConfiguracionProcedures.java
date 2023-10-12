/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.contralor.repository;

import com.metalsa.contralor.controller.UenContralor;
import com.metalsa.contralor.model.Configuracion;
import com.metalsa.contralor.model.RequestBodyConfiguration;
import com.metalsa.contralor.model.Valores;
import com.metalsa.finanzas.model.RespuestaPojo;
import java.util.List;

/**
 *
 * @author Oscar del Angel
 */
public interface ConfiguracionProcedures {

    RespuestaPojo updateConfiguration(RequestBodyConfiguration request);

    List<UenContralor> getUensByApprovalProcess(String user, String approvProcess);

    List<UenContralor> getUensByParamValue(String paramName, String paramValue);

    Configuracion findByUenName(Integer uen, String parameterName);

    Valores findSingleValue(Integer uen, String parameterName);

}
