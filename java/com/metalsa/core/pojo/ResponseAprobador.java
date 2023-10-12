/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.pojo;

import com.metalsa.core.model.RespDelRequisicion;
import java.util.List;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Data
public class ResponseAprobador {

    String codigoCc;
    List<RespDelRequisicion> aprobadores;

    public ResponseAprobador() {
    }
    public ResponseAprobador(String codigoCc,List<RespDelRequisicion> aprobadores) {
        this.codigoCc= codigoCc;
        this.aprobadores = aprobadores;
    }

}
