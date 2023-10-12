/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.contralor.service;

import com.metalsa.contralor.model.CentroCostoProceso;
import com.metalsa.contralor.model.RequestBodyContralor;
import com.metalsa.finanzas.model.AdjuntoPojo;
import java.io.ByteArrayInputStream;
import java.util.List;

/**
 *
 * @author juanangelmunozolvera
 */
public interface ConfiguracionContralorService {
    public ByteArrayInputStream getExcelConfiguracionResponsableDelegado(RequestBodyContralor request);
    public ByteArrayInputStream creaPlantillaCc(RequestBodyContralor request);
    public List<CentroCostoProceso> uploadPlantilla(AdjuntoPojo data) throws Throwable;
}
