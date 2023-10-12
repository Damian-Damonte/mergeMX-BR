/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.perfil.service;

import com.metalsa.generales.model.Usuario;
import com.metalsa.perfil.pojo.CentroCosto;
import com.metalsa.perfil.pojo.DcIdioma;
import com.metalsa.perfil.pojo.Moneda;
import com.metalsa.perfil.pojo.OpcionPerfil;
import com.metalsa.perfil.pojo.PerfilRequest;
import com.metalsa.perfil.pojo.RolRequest;
import com.metalsa.perfil.pojo.UenWithCCDefault;
import com.metalsa.perfil.pojo.UnidadTiempo;
import java.util.List;

/**
 *
 * @author miguel.rdz
 */
public interface PerfilService {

    List<UenWithCCDefault> getUensPorUsuario(String idUsuario, String idIdioma);

    List<UenWithCCDefault> getDefaultUenPorUsuario(String idUsuario);

    List<DcIdioma> getIdiomas();

    List<Moneda> getMonedas();

    List<Moneda> getMonedaDefault(String idUsuario);

    List<UnidadTiempo> getUnidadesTiempo(String idIdioma);

    List<OpcionPerfil> getOpcionesPerfil(String idUsuario, String idIdioma);
    
    List<CentroCosto> getUserCcByRelacion(String idUsuario, String idIdioma, String tipoRelacion);
    
    boolean saveConfiguracion(PerfilRequest req);
    
    boolean saveNotificaciones(PerfilRequest req);
    
    boolean insertRolPerfil(String idUsuario, RolRequest rolReq);

    boolean updateGuiaPerfil(PerfilRequest req);
    
    Usuario getUsuario(String idUsuario);
}
