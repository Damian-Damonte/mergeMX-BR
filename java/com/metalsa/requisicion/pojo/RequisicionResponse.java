package com.metalsa.requisicion.pojo;

import com.metalsa.requisicion.responses.ResponseWs;
import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class RequisicionResponse extends ResponseWs {
    
//    private List<RequisicionPojo> requisicionPojoList;
//
//    public List<RequisicionPojo> getRequisicionPojoList() {
//        return requisicionPojoList;
//    }
//
//    public void setRequisicionPojoList(List<RequisicionPojo> requisicionPojoList) {
//        this.requisicionPojoList = requisicionPojoList;
//    }
    
    private List<RequisicionPreview> requisicionPreviewList;

    public List<RequisicionPreview> getRequisicionPreviewList() {
        return requisicionPreviewList;
    }

    public void setRequisicionPreviewList(List<RequisicionPreview> requisicionPreviewList) {
        this.requisicionPreviewList = requisicionPreviewList;
    }
}
