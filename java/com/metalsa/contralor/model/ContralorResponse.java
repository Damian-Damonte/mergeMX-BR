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
public class ContralorResponse {

    private String message;
    private String status;
    private String statusCode;
    private List<String> procesosList;
    
    public ContralorResponse() {
    }

    public ContralorResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }
    
    
    
}
