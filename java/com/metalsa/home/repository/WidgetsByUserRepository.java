package com.metalsa.home.repository;

import com.metalsa.home.model.WidgetPorUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author APOMR10051
 */
public interface WidgetsByUserRepository extends JpaRepository<WidgetPorUsuario, WidgetPorUsuario.Pk> {

    @Query(value = "select * from ("
            + "select distinct w.id_widget, w.widget, wu.id_usuario, wu.pos_x, wu.pos_y, wu.width, wu.height, w.max_width, w.max_height, w.min_width, w.min_height, w.predeterminado, wu.fijo, w.activo "
            + "from nvc_tbl_widget w, nvc_tbl_widget_por_rol wr, nvc_tbl_widget_por_usuario wu "
            + "where w.id_widget = wr.id_widget and w.id_widget = wu.id_widget and wr.id_rol in (:rolesUsuario) "
            + "union all "
            + "select distinct w.id_widget, w.widget, wu.id_usuario, wu.pos_x, wu.pos_y, wu.width, wu.height, w.max_width, w.max_height, w.min_width, w.min_height, w.predeterminado, wu.fijo, w.activo "
            + "from nvc_tbl_widget w, nvc_tbl_widget_por_usuario wu where w.id_widget = wu.id_widget and w.predeterminado = 1) w "
            + "where w.id_usuario = :idUsuario and w.activo = 1 order by w.predeterminado desc ", nativeQuery = true)
    List<WidgetPorUsuario> getWidgetsByUser(@Param("rolesUsuario") List<Integer> rolesUsuario, @Param("idUsuario") String idUsuario);
    
    @Procedure(value = "WidgetPorUsuario.creaWidgetsDefault")
    String creaWidgetsDefault(@Param("p_id_usuario") String idUsuario);

}

