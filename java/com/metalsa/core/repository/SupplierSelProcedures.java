//<R43143>
package com.metalsa.core.repository;

import java.util.List;

/**
 *
 * @author Oscar del Angel
 */
public interface SupplierSelProcedures {

    List getAprobadoresCC(Integer uen, String cc, String idioma);
    
    boolean isMultiCc(String idRequisicion);
    List<String> getAllDist(String idRequisicion);
}
