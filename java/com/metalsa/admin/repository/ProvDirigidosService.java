package com.metalsa.admin.repository;

import com.metalsa.admin.model.NvcTblDocumento;
import com.metalsa.admin.model.ProveedorDirigido;
import com.metalsa.admin.pojo.ExcelProvDirigidosPojo;
import com.metalsa.admin.pojo.ProveedorDirigidoRequest;
import java.io.ByteArrayInputStream;
import java.util.List;

/**
 *
 * @author miguel.rdz
 */
public interface ProvDirigidosService {

    boolean insertProvDirigido(ProveedorDirigidoRequest req);

    boolean updateProvDirigido(ProveedorDirigidoRequest req);

    boolean deleteProvDirigido(ProveedorDirigidoRequest req);

    List<NvcTblDocumento> getAdjuntosProvDirigidos(Long provDirId, Integer idUen);

    ByteArrayInputStream getReporteExcel(List<ProveedorDirigido> allProvs,ExcelProvDirigidosPojo excelProvDirigidosPojo);
}
