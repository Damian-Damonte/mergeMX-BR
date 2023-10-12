package com.metalsa.perfil.pojo;

import com.metalsa.generales.model.NvcTblRolPerfil;
import java.util.List;

/**
 *
 * @author APOMR10051
 */
public class RolRequest {

    private List<NvcTblRolPerfil> rolSolicitado;
    private String opcionMenu;
    private String razon;

    public List<NvcTblRolPerfil> getRolSolicitado() {
        return rolSolicitado;
    }

    public void setRolSolicitado(List<NvcTblRolPerfil> rolSolicitado) {
        this.rolSolicitado = rolSolicitado;
    }

    public String getOpcionMenu() {
        return opcionMenu;
    }

    public void setOpcionMenu(String opcionMenu) {
        this.opcionMenu = opcionMenu;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
}
