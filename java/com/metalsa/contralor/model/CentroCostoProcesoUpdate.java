package com.metalsa.contralor.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */

@Getter
@Setter
public class CentroCostoProcesoUpdate {
    private Integer uen;
    private String user;
    private List<CentroCostoProceso> lines;
    private String token;
}
