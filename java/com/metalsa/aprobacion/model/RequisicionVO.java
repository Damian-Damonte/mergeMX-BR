package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 *
 * @author APOOD9272
 */
@Data
public class RequisicionVO {

    @JsonProperty("id_requisicion")
    private Long idRequisicion;

    @JsonProperty("nombre_uen")
    private String nombreUen;

    @JsonProperty("id_requisitor")
    private String idRequisitor;

    @JsonProperty("fuente")
    private String tipoRequisicion;
    
    @JsonProperty("aprobacion_especial")
    private String aprobacionEspecial;

    private List<LineaVO> lineas;
    
    private NvcTblFad fad;
}
