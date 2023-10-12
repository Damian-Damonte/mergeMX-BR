package com.metalsa.catalogo.repository;

import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author APOMR10051
 */
public interface CatalogoItemRepository extends JpaRepository<NvcTblCatalogoItem, Integer> {

    Page<NvcTblCatalogoItem> getByIdCatalogoUen(@Param("idCatalogoUen") String idCatalogoUen, @Param("idioma") String idioma, Pageable page);

    Page<NvcTblCatalogoItem> getByIdCatalogoUenFiltroItem(String idCatalogoUen, String idioma, List<String> items, List<String> estatus, List<String> publicado, Pageable page);

    List<NvcTblCatalogoItem> getAllByIdCatalogoUenFiltro(String idCatalogoUen, String idioma, List<String> items, List<String> estatus, List<String> publicado);

    List<NvcTblCatalogoItem> getAllByIdCatalogoUen(@Param("idCatalogoUen") Integer idCatalogoUen);

    @Modifying
    @Query(name = "NvcTblCatalogoItem.activaItemsAprobados")
    int activaItemsAprobados(Integer itemPublicado, String usuarioActualizacion, Integer idCatalogoUen, Integer itemPublicadoAnterior);

    @Modifying
    @Query(name = "NvcTblCatalogoItem.activaItemsEnEdicion")
    int activaItemsEnEdicion(Integer idEstatus, String usuarioActualizacion, Integer idCatalogoUen);

    NvcTblCatalogoItem findByIdItem(@Param("idItem") Integer idItem);

    List<NvcTblCatalogoItem> findByIdCatUen(@Param("idCatUen") Integer idCatalogoUen);

    List<NvcTblCatalogoItem> findByIdCatUenQuery(@Param("idCatUen") Integer idCatalogoUen, @Param("query") String query);
}
