package com.metalsa.core.repository;

import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import com.metalsa.catalogo.model.NvcVItemsAll;
import com.metalsa.requisicion.model.NvcVmOaExistencias;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public interface ItemsRepository {
    NvcVItemsAll findByCodigo(String codigo);
    NvcVItemsAll findById(Integer id);
    NvcVItemsAll findOneByIdAndCodigo(Integer idItem, String codigo);
    NvcVItemsAll findViewByPk(Integer idItem, Integer idUen, Integer idAlmacen);
    NvcVItemsAll findViewByItemUenFuente(Integer uen, Integer idItem, String fuente);
    NvcTblCatalogoItem findItemById(Integer idItem);
    NvcVmOaExistencias getExistencia(Integer idProducto, Integer idUen, Integer idAlmacen);
}
