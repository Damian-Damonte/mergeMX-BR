package com.metalsa.aprobacion.model;

import com.metalsa.core.model.CentroCostoUsuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UenWithDefault extends NvcTblOrganizacionesH implements Serializable {
    private boolean uenDefault;

    private String descripcion;

    private boolean interuen;
    
    private ResponseMobile respuesta = new ResponseMobile();

    public UenWithDefault(Long id, String nombre, String currency, boolean uenDefault, String descripcion, boolean interuen) {
        super(id, nombre, currency);
        this.uenDefault = uenDefault;
        this.descripcion = descripcion;
        this.interuen = interuen;
    }
}
