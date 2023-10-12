package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.RolesPorUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Oscar del Angel
 */
public interface RolesPorUsuarioRepository extends JpaRepository<RolesPorUsuario, RolesPorUsuario.Pk>,RolesPorUsuarioProcedures {

    int countByRolAndUsuario(Long rol, String usuario);

    List<RolesPorUsuario> findByUsuario(String usuario);
    
}
