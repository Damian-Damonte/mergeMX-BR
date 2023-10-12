package com.metalsa.home.repository;

import com.metalsa.home.model.ItemsSugeridos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author APOMR10051
 */
public interface ItemsSugeridosRepository extends JpaRepository<ItemsSugeridos, Long>, ItemsSugeridosProcedures {

    @Override
    @Procedure(name = "GET_ITEMS_SUGERIDOS_BY_USER")
    List<ItemsSugeridos> GET_ITEMS_SUGERIDOS_BY_USER(@Param("P_ID_USUARIO") String P_ID_USUARIO);

    @Procedure(name = "ADD_CARRO_COMPRAS")
    String ADD_CARRO_COMPRAS(@Param("P_ID_USUARIO") String P_ID_USUARIO,
            @Param("P_ID_ITEM") Integer P_ID_ITEM,
            @Param("P_ID_UEN") Integer P_ID_UEN,
            @Param("P_ID_ALMACEN") Integer P_ID_ALMACEN,
            @Param("P_ID_LOCALIZACION") Integer P_ID_LOCALIZACION,
            @Param("P_DESCRIPCION") String P_DESCRIPCION,
            @Param("P_FUENTE") String P_FUENTE,
            @Param("P_UDM") Integer P_UDM,
            @Param("P_ID_SUBFAMILIA") Integer P_ID_SUBFAMILIA,
            @Param("P_IMG_URL") String P_IMG_URL);
}
