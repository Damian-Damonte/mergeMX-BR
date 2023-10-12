package com.metalsa.requisicion.responses;

import com.metalsa.requisicion.pojo.NvcTblOaProveedoresHPojo;
import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class NvcTblOaProveedoresHResponse extends ResponseWs {

    private NvcTblOaProveedoresHPojo entity;
    private List<NvcTblOaProveedoresHPojo> entityList;

    public NvcTblOaProveedoresHPojo getEntity() {
        return this.entity; 
    }

    public void setEntity(NvcTblOaProveedoresHPojo entity) {
        this.entity = entity;
    }

    public List<NvcTblOaProveedoresHPojo> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<NvcTblOaProveedoresHPojo> entityList) {
        this.entityList = entityList;
    }
}
