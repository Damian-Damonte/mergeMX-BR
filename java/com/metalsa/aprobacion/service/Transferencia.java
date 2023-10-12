package com.metalsa.aprobacion.service;

import com.fasterxml.jackson.annotation.JsonIgnore;	//<MDA_CONTRALOR>
import com.metalsa.aprobacion.model.CategoryBudget;	//<MDA_CONTRALOR>
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
@EqualsAndHashCode(of = {"uen", "codigoCentroCosto","categoria", "periodo"})
public class Transferencia {
    private Long from;
    private Long uen;
    private String codigoCentroCosto;
    private Long categoria;
    private String periodo;
    private Double ammount;
    private String usuario;
    private String modulo;
    //<MDA_INC_DEC>
    private String solicitante;
    private String aprobador;
    private String razon;
    //</MDA_INC_DEC>

	//<MDA_CONTRALOR>
    @JsonIgnore    
    private CategoryBudget origen;
    
    @JsonIgnore
    private CategoryBudget destino;
    
    
    
    @Override
    public String toString() {
        return "Transferencia{" + "from=" + from + ", uen=" + uen + ", codigoCentroCosto=" + codigoCentroCosto + ", categoria=" + categoria + ", periodo=" + periodo + ", ammount=" + ammount + ", usuario=" + usuario + ", modulo=" + modulo + ", solicitante=" + solicitante + ", aprobador=" + aprobador + ", razon=" + razon + '}';
    }
    
    
    
    
}
