package com.metalsa.requisicion.pojo;

import com.metalsa.requisicion.utils.ConstantsUtils;
import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class TipoRequisicion {
    
    private String tipoRequisicionName;
    private Integer tipoRequisicionValue;
    private String  tipoRequisicionDescripcion;
    private String tipoRequisicionDescription;
    private List<RequisicionPojo> requisicionPojoList;

    public TipoRequisicion() {
    }
    
    public TipoRequisicion(ConstantsUtils.FUENTES tipoRequisicion, List<RequisicionPojo> requisicionPojoList) {
//        this.tipoRequisicion = tipoRequisicion;
        this.tipoRequisicionName = tipoRequisicion.getFuente();
        this.tipoRequisicionValue = tipoRequisicion.getCodigo();
        this.tipoRequisicionDescripcion = tipoRequisicion.getDescripcion();
        this.tipoRequisicionDescription = tipoRequisicion.getDescription();
        this.requisicionPojoList = requisicionPojoList;
    }

    public List<RequisicionPojo> getRequisicionPojoList() {
        return requisicionPojoList;
    }

    public void setRequisicionPojoList(List<RequisicionPojo> requisicionPojoList) {
        this.requisicionPojoList = requisicionPojoList;
    }

    public String getTipoRequisicionName() {
        return tipoRequisicionName;
    }

    public void setTipoRequisicionName(String tipoRequisicionName) {
        this.tipoRequisicionName = tipoRequisicionName;
    }

    public Integer getTipoRequisicionValue() {
        return tipoRequisicionValue;
    }

    public void setTipoRequisicionValue(Integer tipoRequisicionValue) {
        this.tipoRequisicionValue = tipoRequisicionValue;
    }

    public String getTipoRequisicionDescripcion() {
        return tipoRequisicionDescripcion;
    }

    public void setTipoRequisicionDescripcion(String tipoRequisicionDescripcion) {
        this.tipoRequisicionDescripcion = tipoRequisicionDescripcion;
    }
    
    public String getTipoRequisicionDescription() {
        return tipoRequisicionDescription;
    }

    public void setTipoRequisicionDescription(String tipoRequisicionDescription) {
        this.tipoRequisicionDescription = tipoRequisicionDescription;
    }
    
    public boolean isAnyTipo(Integer... tipo) {
        for (Integer id : tipo) {
            if (this.tipoRequisicionValue.equals(id)) {
                return true;
            }
        }
        return false;
    }
}
