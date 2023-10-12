package com.metalsa.core.repository;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;

import com.metalsa.core.pojo.UenParameter;
import com.metalsa.core.pojo.UenParameterValue;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Repository
public class ParameterRepositoryImpl implements ParameterRepository {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public boolean checkUenAndFuente(String param, Integer idUen, String fuente) {
        UenParameter parameterUen = findParameterUenByUenName(idUen, param);
        return parameterUen != null
                && parameterUen.isCondition("Y")
                && parameterUen.getValues().stream().anyMatch((value) -> {
                    return  value.getValue() != null
                            && value.isCondition("Y")
                            && value.getProperty() != null
                            && value.getProperty().equals(fuente);
                });
    }
    
    @Override
    public UenParameter findParameterUenByUenName(Integer idUen, String paramName) {
        UenParameter response = new UenParameter();
        Query query = em.createNativeQuery(
            "SELECT " +
                "pu.parameter_condition  parameter_condition_uen, " +
                "pv.id_parameter_uen, " +
                "pv.id_parameter_value, " +
                "pv.parameter_value, " +
                "pv.parameter_property, " +
                "pv.parameter_condition " +
            "FROM  NVC_TBL_PARAMETER_UEN pu "  +
            "inner join NVC_TBL_PARAMETER_VALUE pv " +
                "on pv.id_parameter_uen = pu.id_parameter_uen " +
                "and pu.id_uen   = :id_uen " +
            "where id_parameter = (" +
                "select id_parameter " +
                "from nvc_tbl_parameter " +
                "where parameter_name  = :name " +
            ")"
        );
        query.setParameter("name", paramName)
                 .setParameter("id_uen", idUen);

        List<Object[]> results = query.getResultList();
        if (results != null && !results.isEmpty()) {
            response.setValues(new ArrayList<>());
            results.forEach((object) -> {
                int pos = -1;
                String parameterConditionUen = (String) object[++pos];
                Integer idParameterUen = Integer.valueOf(object[++pos].toString());
                Integer idParameterValue = Integer.valueOf(object[++pos].toString());
                String parameterValue = (String) object[++pos];
                String parameterProperty = (String) object[++pos];
                String parameterCondition = (String) object[++pos];

                response.setIdUen(idUen);
                response.setIdParameterUen(idParameterUen);
                response.setCondition(parameterConditionUen);

                UenParameterValue value = new UenParameterValue();
                value.setIdValue(idParameterValue);
                value.setIdUen(idParameterUen);
                value.setValue(parameterValue);
                value.setProperty(parameterProperty);
                value.setParameterCondition(parameterCondition);
                response.getValues().add(value);
            });
        }

        return response;
    }
}
