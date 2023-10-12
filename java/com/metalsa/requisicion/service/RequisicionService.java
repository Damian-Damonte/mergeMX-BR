package com.metalsa.requisicion.service;

import java.util.Map;
import java.util.List;

import com.metalsa.requisicion.model.Fuentes;
import com.metalsa.requisicion.pojo.RequisicionUens;
import com.metalsa.utils.entities.NvcVCarroCompra;
import com.metalsa.requisicion.pojo.RequisicionRequest;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public interface RequisicionService {
    Map<Integer, Map<Fuentes, List<NvcVCarroCompra>>> groupCarts(List<NvcVCarroCompra> items);
    public Object createRequisicion(RequisicionRequest request, String userId);
    RequisicionUens convertToUens(Map<Integer, Map<Fuentes, List<NvcVCarroCompra>>> items);
}
