/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.almacen.pojo;

import com.metalsa.almacen.model.DetalleDespacho;
import java.util.List;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Data
public class CancelDespRequest {

    private List<DetalleDespacho> detalleDespacho;
    private String razonCancelacion;
}
