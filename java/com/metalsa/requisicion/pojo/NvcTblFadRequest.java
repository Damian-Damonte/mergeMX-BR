package com.metalsa.requisicion.pojo;

import java.util.List;

/**
 *
 * @author jose.davila03
 */
public class NvcTblFadRequest extends RequestWs {

    private NvcTblFadPojo entity;
    private List<NvcTblFadPojo> entityList;

    public NvcTblFadPojo getEntity() {
        return entity;
    }

    public void setEntity(NvcTblFadPojo entity) {
        this.entity = entity;
    }

    public List<NvcTblFadPojo> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<NvcTblFadPojo> entityList) {
        this.entityList = entityList;
    }

}
