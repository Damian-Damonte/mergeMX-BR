/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.analytic.ux.recibos;

import com.metalsa.analytic.ux.UxCapture;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author yair.nunez
 */
@Entity
@Table(name = "nvc_tbl_ux_recibos")
@Getter
@NoArgsConstructor
public class RecibosUxCapture extends UxCapture {
    private String requisiciones;
    private String uens;
    private String requisitor;
    private String tiposRequisicion;
    private String proveedores;
    private String centroCostos;
    private String fechasRequisicion;
    private boolean lineasUrgentes;
}
