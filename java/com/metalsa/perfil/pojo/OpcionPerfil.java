package com.metalsa.perfil.pojo;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author APOMR10051
 */
public class OpcionPerfil {

    private Long idNotificacion;
    private String valor;
    private List<UnidadTiempo> unidadTiempo;
    private Boolean valorb;
    private Integer activo;

    public OpcionPerfil() {
    }

    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<UnidadTiempo> getUnidadTiempo() {
        return unidadTiempo;
    }

    public void setUnidadTiempo(List<UnidadTiempo> unidadTiempo) {
        this.unidadTiempo = unidadTiempo;
    }

    public Boolean isValorb() {
        if (this.valor != null) {
            switch (this.valor) {
                case "S":
                    valorb = true;
                    break;
                case "N":
                    valorb = false;
                    break;
                default:
                    if (activo != null) {
                        switch (activo) {
                            case 0:
                                valorb = false;
                                break;
                            case 1:
                                valorb = true;
                                break;
                            default:
                                valorb = null;
                                break;
                        }
                    }
                    break;
            }
        }
        return valorb;
    }

    public void setValorb(Boolean valorb) {
        this.valorb = valorb;

        if (valorb != null && !StringUtils.isNumeric(this.valor)) {
            this.valor = valorb == true ? "S" : "N";
        }

    }

    public Integer getValorActivo() {
        Integer val = null;
        if (valorb != null) {
            if (valorb) {
                if (StringUtils.isNumeric(this.valor)) {
                    val = 1;
                }
            } else {
                if (StringUtils.isNumeric(this.valor)) {
                    val = 0;
                }
            }
        }
        return val;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}
