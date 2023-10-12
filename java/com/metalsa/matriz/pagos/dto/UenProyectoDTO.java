/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.matriz.pagos.dto;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.aprobacion.model.OaProyectos;
import java.util.List;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Data
public class UenProyectoDTO {
    private NvcTblOrganizacionesH uen;
    private List<OaProyectos> listProyectos;
}
