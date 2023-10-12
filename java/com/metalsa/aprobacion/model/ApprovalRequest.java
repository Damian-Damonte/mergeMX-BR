package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
public class ApprovalRequest {

    @JsonProperty("id_aprobador")
    private String aprobador;

    private String solicitante;//<R17424>
    private List<RequisicionVO> requisiciones;
}
