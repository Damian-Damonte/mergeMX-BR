package com.metalsa.perfil.pojo;

import java.util.List;


/**
 *
 * @author APOMR10051
 */
public class PerfilRequest {

    private String idUsuario;
    private String idIdioma;
    private String perfilActivo;
    private List<OpcionPerfil> opciones;
    private DatosPerfil datos;
    private List<RolRequest> solicitudRoles;
    private List<UenRequest> solicitudUens;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getPerfilActivo() {
        return perfilActivo;
    }

    public void setPerfilActivo(String perfilActivo) {
        this.perfilActivo = perfilActivo;
    }

    public List<OpcionPerfil> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<OpcionPerfil> opciones) {
        this.opciones = opciones;
    }

    public DatosPerfil getDatos() {
        return datos;
    }

    public void setDatos(DatosPerfil datos) {
        this.datos = datos;
    }

    public List<RolRequest> getSolicitudRoles() {
        return solicitudRoles;
    }

    public void setSolicitudRoles(List<RolRequest> solicitudRoles) {
        this.solicitudRoles = solicitudRoles;
    }

    public List<UenRequest> getSolicitudUens() {
        return solicitudUens;
    }

    public void setSolicitudUens(List<UenRequest> solicitudUens) {
        this.solicitudUens = solicitudUens;
    }
}
