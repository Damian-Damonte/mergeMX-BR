package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.UenWithDefault;

import java.util.List;

/**
 * Created by ruben.bresler on 05/07/2017.
 */
public interface OrganizationProcedures {

    List<UenWithDefault> findUenByUser(String user);
}
