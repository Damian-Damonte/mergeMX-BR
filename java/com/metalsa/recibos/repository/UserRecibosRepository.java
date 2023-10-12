/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;


import com.metalsa.recibos.model.UserRecibo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author edgar.leal
 */
@Repository
public interface UserRecibosRepository extends PagingAndSortingRepository<UserRecibo, String>{/*<M26022018>*/
    Iterable<UserRecibo> usuarioRecibo(int uen, int tipo_rol);
    Iterable<UserRecibo> usuarioPorRol(int tipo_rol);
    Iterable<UserRecibo> usuarioReceptor();
    Iterable<UserRecibo> usuarioActivo();
     UserRecibo findByIdUsuario(String id);
}