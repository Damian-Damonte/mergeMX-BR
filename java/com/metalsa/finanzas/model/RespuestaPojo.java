package com.metalsa.finanzas.model;

//<MDA_CONTRALOR>
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author JL
 */
@Getter
@Setter
public class RespuestaPojo {
    
    private String mensaje;
    private Integer valor;
    private List listResult;	//<MDA_CONTRALOR>

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the valor
     */
    public Integer getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "RespuestaPojo{" + "mensaje=" + mensaje + ", valor=" + valor + '}';
    }
    
    
    
    
}
