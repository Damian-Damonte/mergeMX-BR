package com.metalsa.correos.pojo;

import com.metalsa.aprobacion.model.PlantillaCorreo;

public class NotificacionRequest {

    private PlantillaCorreo correo;
    private String idUsuario;

    public PlantillaCorreo getCorreo() {
        return correo;
    }

    public void setCorreo(PlantillaCorreo correo) {
        this.correo = correo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

}
