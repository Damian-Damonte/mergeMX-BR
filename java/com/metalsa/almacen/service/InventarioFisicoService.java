/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.almacen.service;

import com.metalsa.almacen.pojo.Almacen;
import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.inventarioFisico.AlmacenInvFisRequest;
import com.metalsa.inventarioFisico.InventarioFisicoRequest;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author hector.gutierrez02
 */
public interface InventarioFisicoService {
    
    Iterable<Almacen> getAlmacenesPorUen(@Param("uens") int uens);    
    
    Iterable<AlmacenInvFisRequest> getAlmacenes();
    
    Iterable<Usuario> geUsuariosActivos();
    
    String insertBloqueoInventario(InventarioFisicoRequest request);
    
    String EliminaBloqueoInventario(InventarioFisicoRequest request);
    
    List<InventarioFisicoRequest> getAllBlockInventarioFisico();
    
    String insertConfigBanner(String valor);
    
    String getConfigBanner();
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.almacen.service;

import com.metalsa.almacen.pojo.Almacen;
import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.inventarioFisico.InventarioFisicoRequest;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author hector.gutierrez02
 */
public interface InventarioFisicoService {
    
    Iterable<Almacen> getAlmacenesPorUen(@Param("uens") int uens);    
    
    Iterable<Usuario> geUsuariosActivos();
    
    String insertBloqueoInventario(InventarioFisicoRequest request);
    
    String EliminaBloqueoInventario(InventarioFisicoRequest request);
    
    List<InventarioFisicoRequest> getAllBlockInventarioFisico();
}
