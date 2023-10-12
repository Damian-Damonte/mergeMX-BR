/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.matriz.pagos.dto;

import com.metalsa.core.model.Periodos;
import com.metalsa.matriz.pagos.model.MatrizPagos;
import java.util.List;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Data
public class ResponseMatrizDTO {

    List<MatrizPagos> matrizPagos;
    List<Periodos> periodos;

}
