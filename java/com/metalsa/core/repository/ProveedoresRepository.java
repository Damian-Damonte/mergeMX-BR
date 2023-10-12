/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;
import com.metalsa.core.model.NvcTblOaProveedoresH;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author edgar.leal
 */
@Repository
public interface ProveedoresRepository extends PagingAndSortingRepository<NvcTblOaProveedoresH, Long>{

    /**
    * devuelve los proveedores de las UEN's pero que tengan elementos de catalogos activos
    */
    @Query(value = "select distinct prov.* from nvc_tbl_oa_proveedores_h prov \n" +
                "join nvc_tbl_prov_sites_h puen on prov.id_proveedor = puen.id_proveedor \n" +
                "join nvc_tbl_catalogo c on prov.id_proveedor = c.id_proveedor and c.activo = 1\n" +
                "join nvc_tbl_catalogo_uen cuen on c.id_catalogo = cuen.id_catalogo and cuen.id_uen = puen.id_uen and cuen.activo = 1 \n" +
                "join nvc_tbl_catalogo_item item on item.id_catalogo_uen = cuen.id_catalogo_uen and item.item_publicado = 45 and item.activo = 1\n" +
                "where puen.id_uen in (?1) and lower(puen.vendor_site_code) <> 'office'\n" +
                "order by prov.nombre_proveedor",
            nativeQuery = true
    )
    List<NvcTblOaProveedoresH> findProveedoresByUens(List<Long> ids);

    Iterable<NvcTblOaProveedoresH> findPorUen(int uen);
    Iterable<NvcTblOaProveedoresH> findDispDirigidosPorUen(@Param("idUen") int idUen, @Param("query") String query);
    Iterable<NvcTblOaProveedoresH> findLikeByUenWithFad(@Param("uens") String uens, @Param("query") String query);
    Iterable<NvcTblOaProveedoresH> findLikeWithFad(@Param("query") String query);
    Iterable<NvcTblOaProveedoresH> proveedoresPorUenRequisicion(String idUsuario, int uen);
    Iterable<NvcTblOaProveedoresH> proveedoresPorRequisicion(String idUsuario);
    List<NvcTblOaProveedoresH> findByIdProveedor(Integer idProveedor);
    
    @Query(name = "NvcTblOaProveedoresH.findLikeNombreByUen_")
    List<NvcTblOaProveedoresH> buscarLikeNombreByUen_(String nombre, BigDecimal idUen);
}
