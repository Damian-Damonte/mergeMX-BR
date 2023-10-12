/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.admonCategorias.service;

import com.metalsa.admonCategorias.pojo.ConfFamPojo;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.compradorCC.pojo.CategoriaPojo;
import com.metalsa.compradorCC.pojo.CompradorCcPojo;
import com.metalsa.compradorCC.pojo.RequestFiltros;
import java.util.List;

/**
 *
 * @author jose.jimenez07
 */
public interface AdmonCategoriasService {

    List<CategoriaPojo> getDatosByFiltros(RequestFiltros requestFiltros); // DATOS QUE SE MUESTRAN EN LA TABLA

    void saveDatosFamilia(List<ConfFamPojo> datosTabla, String personId);

    Iterable<NvcTblOrganizacionesH> getUensCc();    //COMBO LIST UEN

    void saveDatosComentarios(List<ConfFamPojo> datosGuardar);

    List<CompradorCcPojo> getAdministradorFam(Long idUen);
}
