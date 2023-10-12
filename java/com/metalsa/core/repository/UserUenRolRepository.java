/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.metalsa.core.model.UserUenRol;


/**
 *
 * @author edgar.leal
 */
@Repository
public interface UserUenRolRepository extends PagingAndSortingRepository<UserUenRol, Long>{
    Iterable<UserUenRol> usuario(int uen, int tipo_rol);

}
