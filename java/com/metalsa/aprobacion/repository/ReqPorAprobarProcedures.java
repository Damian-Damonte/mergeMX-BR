/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.GastoAdicional;
import com.metalsa.aprobacion.model.ReqPorAprobar;
import com.metalsa.aprobacion.service.CheckBudgetRequest;
import com.metalsa.core.model.NvcTblProvSitesH;

import java.util.List;
import java.util.Map;

/**
 *
 * @author jose.rivera02
 */
public interface ReqPorAprobarProcedures {

    List<ReqPorAprobar> getRequisPorAprobar(String user);

    List<ReqPorAprobar> getRequisPorPreAprobar(String user);

    List<GastoAdicional> getGastosAdicionales(Integer idRequisicion, Integer idPartida, String idIdioma, String aprobador);

    Integer cancelarRequisicion(Long requisicion, Long linea, String razonRechazo, String idAprobador,String modulo);

    List<ReqPorAprobar> getRequisFueraRango(String user);

    List<ReqPorAprobar> getRequisCriticas(String user, Long uen);

    //<ERM#14135>
    List<ReqPorAprobar> getRequisSuperAprobacion(String user, String idUens,String requisicion);
    //</ERM#14135>
    
    Integer preaprobarRequisicion(Long requisicion, Long linea, String aprobador,String modulo);
    	
	Integer aprobarRequisicion(Long requisicion, Long linea, String aprobador,String modulo, Integer idCuenta);
    Integer iniAprobarRequisicion(Long requisicion, Long linea, String aprobador,String modulo, Integer idCuenta);

    Integer aprobarRequisicionCritica(Long requisicion, Long linea, String aprobador, String solicitante, String razon,String modulo); //<R17424>

    Integer verificarPresupuesto(Long requisicion, Long linea, String aprobador, Integer idCuenta);

    void bloquearDetalle(Long requisicion, Long linea, Integer idCuenta, String estado);

    Long countRequisiciones(ReqPorAprobarRepository.APPROVAL_TYPE tipo, Map<String, Object> params);
    
    //<ERM#17422>
    Integer preAprobarRequisciones(String stringRequis,String modulo,String aprobador);
    //</ERM#17422>
    Integer updateExceso(Long requisicion, Long partida, String razonExceso, String solucionExceso);
    
    Integer guardaComentarioFad(Integer idFad, String aprobacionEspecial, String comentario);
    
    List<String> getSiguienteAprobador(Long idRequisicion, Long idPartida, String aprobador);
    
    String guardaMailAprobacion(String mailBody, String destino, String asunto);//<T395493>
    
    Integer verifyBudgetProyecto(Long requisicion, Long linea, String aprobador);//<T420188>
    
    List<CheckBudgetRequest> getBudgetRequiProyecto(String sWhere);//<T428940>
    
    Integer verifyBudgetProyecto(Long idUen,Long idProyecto, Double monto, Long idCuenta);//<T428940>
    
    Integer validaPresupProySpx(Long idUen,Long idProyecto, Long idTarea, String TipoGasto,Double monto);//<T428940>
    
    NvcTblProvSitesH getProveedorSite(Long requisicion);
}
