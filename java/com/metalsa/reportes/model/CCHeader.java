package com.metalsa.reportes.model;

import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@Getter
@Setter
public class CCHeader {

    protected String codigoCC;

    protected String nombreCC;

    protected String responsable;

    protected Meses totales;

//    protected byte chartImage[];

    protected List<byte[]> chartImages;

    public CCHeader() {
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codigoCC);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CCHeader other = (CCHeader) obj;
        return Objects.equals(this.codigoCC, other.codigoCC);
    }

}
