/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.analytic.ux.seleccion;

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
@Table(name = "nvc_tbl_ux_seleccion")
@Getter
@NoArgsConstructor
public class SeleccionUxCapture extends UxCapture {
    private String requisiciones;
    private String uens;
    private String proveedores;
    private String lineas;
    private String compradores;
    private String fechasNecesidad;
    private String fechasCreacion;
    private boolean soloRegresadasAprobador;
}
