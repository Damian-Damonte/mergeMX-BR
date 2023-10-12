package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.NvcTblFad;
import java.util.List;

/**
 *
 * @author miguel.rdz
 */
public interface NvcTblFadProcedures {    
    List<NvcTblFad> getFadByIdReq(Integer idReq);
}
