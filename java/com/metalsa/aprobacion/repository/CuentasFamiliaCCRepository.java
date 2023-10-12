
package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CuentasFamiliaCC;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author APOOD9272
 */
public interface CuentasFamiliaCCRepository extends PagingAndSortingRepository<CuentasFamiliaCC, Long> {

    @Query(name = "CuentasFamiliaCC.getByCuenta")
    CuentasFamiliaCC getCuentaByCuenta(Long idUen, String codigoCC, Long idFamilia, Long idCuenta,String idIdioma);
    
    @Query(name = "CuentasFamiliaCC.getCuentasFamiliaPorUenCC")
    List<CuentasFamiliaCC> getCuentasByUenCCFamilia(Long idUen, String codigoCC, Long idFamilia, String idIdioma);
  
  
}
