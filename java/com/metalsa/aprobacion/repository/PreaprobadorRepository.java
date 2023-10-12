/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.Preaprobador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author APOOD9272
 */
@Repository
public interface PreaprobadorRepository extends JpaRepository<Preaprobador, String> {

    Preaprobador findByIdUsuario(String idUsuario);

    @Query (name = "Preaprobador.findPreAprobadorDefault")
    Preaprobador findPreaprobadorDefault(@Param ("id_uen") Long idUen);

    @Query (name = "Preaprobador.findPreaprobadoresUen")
    List<Preaprobador> findPreaprobadores();

    @Query (value = "select count(1) from proyectos_por_aprobador_uen where id_usuario = :id_usuario", nativeQuery = true)
    int countProyectosUsuario(@Param ("id_usuario") String usuario);

    @Procedure
    void eliminaRolNoPreaprobador();

    @Procedure
    void removerPreaprobador(@Param ("P_ID_PROYECTO") Long idProyecto, @Param ("P_ID_USUARIOS") String usuarios);

    @Procedure(procedureName = "NVC_PKG_SPX_APPROVALS.AGREGAR_ROLES_PREAPROBADORES",outputParameterName = "P_OUT_RESULT")
    Integer agregarRolPreaprobador();

    @Procedure
    Integer asignarPreaprobadores(@Param ("P_ID_PROYECTO") Long idProyecto, @Param ("P_ID_USUARIOS") String usuarios);
}
