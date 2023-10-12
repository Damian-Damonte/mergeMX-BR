package com.metalsa.contralor.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */

@Setter
@Getter
public class RequestBodyConfiguration extends RequestBody{
    private List<Configuracion> configurations;
}
