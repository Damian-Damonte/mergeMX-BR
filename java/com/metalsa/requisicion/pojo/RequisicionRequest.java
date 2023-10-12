package com.metalsa.requisicion.pojo;

import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class RequisicionRequest extends RequestWs {

    private Integer idRequisicion;    
    private Integer idPartida;
    private String appOrigen;
    
    private RequisicionPojo Requifiltro;
    private List<RequisicionPojo> requisicionList;
    private List<NvcTblCarroCompraPojo> entityList;

    public List<NvcTblCarroCompraPojo> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<NvcTblCarroCompraPojo> entityList) {
        this.entityList = entityList;
    }

//    public List<RequisicionPojo> getRequisicionList() {
//        return requisicionList;
//    }
//    public void setRequisicionList(List<RequisicionPojo> requisicionList) {
//        this.requisicionList = requisicionList;
//    }
    private List<RequisicionPreview> requisicionPreviewList;

    public List<RequisicionPreview> getRequisicionPreviewList() {
        return requisicionPreviewList;
    }

    public void setRequisicionPreviewList(List<RequisicionPreview> requisicionPreviewList) {
        this.requisicionPreviewList = requisicionPreviewList;
    }

    public void setRequifiltro(RequisicionPojo Requifiltro) {
        this.Requifiltro = Requifiltro;
    }

    public RequisicionPojo getRequifiltro() {
        return Requifiltro;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public List<RequisicionPojo> getRequisicionList() {
        return requisicionList;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public void setRequisicionList(List<RequisicionPojo> requisicionList) {
        this.requisicionList = requisicionList;
    }

    public String getAppOrigen() {
        return appOrigen;
    }

    public void setAppOrigen(String appOrigen) {
        this.appOrigen = appOrigen;
    }
}
