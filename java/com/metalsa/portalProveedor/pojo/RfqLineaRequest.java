/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.pojo;

import java.math.BigInteger;
import lombok.Data;

/**
 *
 * @author mlopez
 */
@Data
public class RfqLineaRequest {
    private Long idProveedor;
    private int idRfq;
    private Long idSucProveedor;
}
