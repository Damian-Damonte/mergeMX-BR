package com.metalsa.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.metalsa.core.model.User;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author  Edgar Leal
 * 
 * 
 * 
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	
    User findByEmailAddress(String emailAddress);
    
    User findByidUsuarioAndNombreFamilia(String idUsuario, String nombreFamilia); //select u from User u where u.ID_USUARIO = ?1 and u.lastname = ?2

    @Procedure(name = "addRolUsers")
    Integer addRolUsers(@Param("p_usuarios") String users,@Param("p_nombre_rol") String rolRol);
    
    @Procedure(name="removeRolPreaprobador")
    void removeRolPreaprobador();
    
    @Procedure(name = "getRolByNombreRolUsuario")
    Integer getRolByNombreRolUsuario(@Param("idUsuario") String idUsuario); //<rdm59310>

}
