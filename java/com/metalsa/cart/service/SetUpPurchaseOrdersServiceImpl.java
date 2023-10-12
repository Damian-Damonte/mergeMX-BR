package com.metalsa.cart.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metalsa.aprobacion.model.CarroCompra;
import com.metalsa.aprobacion.model.OaCombinacion;
import com.metalsa.cart.pojo.UsuarioPojo;
import com.metalsa.utils.entities.NvcTblCarroCompraDetalle;
import com.metalsa.utils.entities.NvcVProyectosAll;
import com.metalsa.utils.entities.NvcVmOaCc;
import com.metalsa.utils.entities.NvcVmOaProyGastoCuenta;
import com.metalsa.utils.entities.NvcVmOaProyGastoCuentaPK;
import com.metalsa.utils.entities.NvcVmOaProyectoTareas;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SetUpPurchaseOrdersServiceImpl implements SetUpPurchaseOrdersService{
    
    @PersistenceContext
    private EntityManager em;

    @Autowired
    CartDetailService cartDetailService;

    public List<NvcVmOaCc> getCostCenter(BigInteger idUen){

        TypedQuery<NvcVmOaCc> queryCostCenters = em.createNamedQuery("NvcVmOaCc.findByIdUenLangAcc", NvcVmOaCc.class);
        queryCostCenters.setParameter("idUen", idUen);   
        queryCostCenters.setParameter("lenguaje", "ESA");           
        List<NvcVmOaCc> costCenters = queryCostCenters.getResultList();

        return costCenters;
    };

    @Transactional
    public NvcTblCarroCompraDetalle saveCostCenter(Long carroCompraId, Integer idCostCenter){        
        CarroCompra carroCompra = getCarroCompra(carroCompraId);
        NvcTblCarroCompraDetalle carroCompraDetalle = cartDetailService.findOrCreate(carroCompra);
        carroCompraDetalle.setIdCentroCosto(idCostCenter);
        carroCompraDetalle.setIdCuenta(null);
        carroCompraDetalle.setIdProyecto(null);
        carroCompraDetalle.setIdTarea(null);
        carroCompraDetalle.setTipoGasto(null);
        carroCompraDetalle.setComprador(null);
        carroCompraDetalle.setFechaActualizacion(new Date());
        carroCompraDetalle.setIdTipoAsignacion(2);
        em.persist(carroCompraDetalle);

        return carroCompraDetalle;
    };

    public List<OaCombinacion> getAccounts(Long carroCompraId){
        List<OaCombinacion> combinaciones = new ArrayList<>();
        
        try {
            CarroCompra carroCompra = getCarroCompra(carroCompraId);
            NvcTblCarroCompraDetalle detalle = carroCompra.getDetalle();

            StoredProcedureQuery procedure;

//            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_CUENTAS_NO_SEGPROD");

//            if(detalle.getSegmentoProducto().equals(1)) {
//                procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_CUENTAS_SEGPROD");
//            } else {
//                procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_CUENTAS_NO_SEGPROD");
//            }

            if(detalle.getSegmentoProducto() == null || !detalle.getSegmentoProducto().equals(1)) {
                procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_CUENTAS_NO_SEGPROD");
            } else {
                procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_CUENTAS_SEGPROD");
            }

            procedure.registerStoredProcedureParameter("p_iduen", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_idfamilia", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_segmento2", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_lenguaje", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_segmento6", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_fuente", String.class, ParameterMode.IN); // <RELEASE SPX ETOWN />
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            
            procedure.setParameter("p_iduen", carroCompra.getUen().intValue());
            procedure.setParameter("p_idfamilia", carroCompra.getSubFamilia().intValue());
            procedure.setParameter("p_segmento2", String.valueOf(carroCompra.getDetalle().getIdCentroCosto()));
            procedure.setParameter("p_lenguaje", "ESA");
            procedure.setParameter("p_segmento6", "000");
            procedure.setParameter("p_fuente", null); // <RELEASE SPX ETOWN/>
            
            boolean isOK = procedure.execute();

            if (isOK) {                
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();                
                OaCombinacion combinacion;
                for (Object[] row : cursor) {
                    combinacion = new OaCombinacion();
                    int pos = -1;
                    Integer idCuenta = (row[++pos] == null ? null : Integer.valueOf(row[pos].toString()));
                    String segmento1 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento2 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento3 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento4 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento5 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento6 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento7 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento8 = (row[++pos] == null ? null : row[pos].toString());

                    combinacion.setCuenta(Long.valueOf(idCuenta));
                    combinacion.setSegmento1(segmento1);
                    combinacion.setSegmento2(segmento2);
                    combinacion.setSegmento3(segmento3);
                    combinacion.setSegmento4(segmento4);
                    combinacion.setSegmento5(segmento5);
                    combinacion.setSegmento6(segmento6);
                    combinacion.setSegmento7(segmento7);
                    combinacion.setSegmento8(segmento8);

                    combinaciones.add(combinacion);
                }
                
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return combinaciones;
    }

    @Override
    public Long getCategoryByAccountId(Long accountId) {
        String queryGetCategory = "SELECT icom_budget_trx_pkg.GET_CATEGORY(:accountId) FROM DUAL";
        try {
            Object result = em.createNativeQuery(queryGetCategory).setParameter("accountId", accountId).getSingleResult();

            if(result instanceof Number) {
                return ((Number) result).longValue();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Transactional
    private double getAccountBalance(int accountId){
        double balance = 0;
        StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_SALDO_CUENTA");
        procedure.registerStoredProcedureParameter("p_idCuenta", Integer.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_lenguaje", String.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);

        procedure.setParameter("p_idCuenta", accountId);
        procedure.setParameter("p_lenguaje", "US");
        boolean ok = procedure.execute();        
        if (ok) {            
            List<Object[]> cursor = (List<Object[]>) procedure.getResultList();                            
            for (Object[] row : cursor) {                
                if(row[4] != null){                              
                    balance = Double.parseDouble(row[4].toString());                    
                }                
            }                        
        }

        return balance;
    }

    @Override
    @Transactional
    public NvcTblCarroCompraDetalle saveAccount(Long carroCompraId, Integer idAccount) {
        try {
            CarroCompra carroCompra = getCarroCompra(carroCompraId);
            NvcTblCarroCompraDetalle detalle = carroCompra.getDetalle();

            OaCombinacion cuenta = em.createNamedQuery("OaCombinacion.findCuenta", OaCombinacion.class)
                    .setParameter("cuenta", Long.valueOf(idAccount))
                    .setParameter("uen", carroCompra.getUen())
                    .setParameter("idioma", "ESA")
                    .getSingleResult();

            detalle.setFechaActualizacion(new Date());
            detalle.setIdCuenta(idAccount);
            detalle.setSegmento1(cuenta.getSegmento1());
            detalle.setSegmento2(cuenta.getSegmento2());
            detalle.setSegmento3(cuenta.getSegmento3());
            detalle.setSegmento4(cuenta.getSegmento4());
            detalle.setSegmento5(cuenta.getSegmento5());
            detalle.setSegmento6(cuenta.getSegmento6());
            detalle.setSegmento7(cuenta.getSegmento7());
            detalle.setSegmento8(cuenta.getSegmento8());
            detalle.setIdTipoAsignacion(2);
            em.persist(detalle);

            return detalle;
        }catch(NoResultException e){
            return null;
        }
        
    }

    @Override
    @Transactional
    public NvcTblCarroCompraDetalle saveSp(Long carroCompraId, Integer value) {
        CarroCompra carroCompra = getCarroCompra(carroCompraId);
        NvcTblCarroCompraDetalle carroCompraDetalle = cartDetailService.findOrCreate(carroCompra);
        carroCompraDetalle.setSegmentoProducto(value == 1 ? 1 : 0);
        carroCompraDetalle.setFechaActualizacion(new Date());
        carroCompraDetalle.setIdTipoAsignacion(2);
        em.persist(carroCompraDetalle);
        return carroCompraDetalle;        
    }


    public List<NvcVProyectosAll> getProjects(Long carroCompraId){
        List<NvcVProyectosAll> listaProyecto = new ArrayList<>();
        try{
            CarroCompra carroCompra = getCarroCompra(carroCompraId);

            listaProyecto = em.createNamedQuery("NvcVProyectosAll.findByUserUen", NvcVProyectosAll.class)
                    .setParameter("idUsuario", carroCompra.getUsuario())
//                    .setParameter("idUsuario", "APOAE11757")
                    .setParameter("idUen", carroCompra.getUen().intValue()).getResultList();

        }catch(Exception e){

        }
               
        return listaProyecto;
    }    

    @Override
    @Transactional
    public NvcTblCarroCompraDetalle saveProject(Long carroCompraId, Integer projectId) {
        CarroCompra carroCompra = getCarroCompra(carroCompraId);
        NvcTblCarroCompraDetalle carroCompraDetalle = cartDetailService.findOrCreate(carroCompra);
        carroCompraDetalle.setIdProyecto(projectId);
        carroCompraDetalle.setIdTarea(null);
        carroCompraDetalle.setTipoGasto(null);
        carroCompraDetalle.setIdCuenta(null);
        carroCompraDetalle.setComprador(null);
        carroCompraDetalle.setIdCentroCosto(null);
        carroCompraDetalle.setFechaActualizacion(new Date());
        carroCompraDetalle.setIdTipoAsignacion(1);
        em.persist(carroCompraDetalle);
        return carroCompraDetalle;
    }

    public List<NvcVmOaProyectoTareas> getTasks(Long carroCompraId){
        List<NvcVmOaProyectoTareas> tasks = new ArrayList<>();
        try{
            CarroCompra carroCompra = getCarroCompra(carroCompraId);

            tasks = em.createNamedQuery("NvcVmOaProyectoTareas.findByIdProyecto", NvcVmOaProyectoTareas.class)                    
                    .setParameter("idProyecto", Long.valueOf(carroCompra.getDetalle().getIdProyecto()))
                    .getResultList();

        }catch(Exception e){

        }
               
        return tasks;
    }    

    @Override
    @Transactional
    public NvcTblCarroCompraDetalle saveTask(Long carroCompraId, Integer taskId) {
        CarroCompra carroCompra = getCarroCompra(carroCompraId);
        NvcTblCarroCompraDetalle carroCompraDetalle = cartDetailService.findOrCreate(carroCompra);        
        carroCompraDetalle.setIdTarea(taskId);        
        carroCompraDetalle.setTipoGasto(null);
        carroCompraDetalle.setIdCuenta(null);
        carroCompraDetalle.setComprador(null);
        carroCompraDetalle.setFechaActualizacion(new Date());
        carroCompraDetalle.setIdTipoAsignacion(1);
        em.persist(carroCompraDetalle);
        return carroCompraDetalle;
    }

    public List<NvcVmOaProyGastoCuenta> getResources(Long carroCompraId){        
        
        List<NvcVmOaProyGastoCuenta> gastos = new ArrayList<>();

        try {    
            CarroCompra carroCompra = getCarroCompra(carroCompraId);
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_EROGACION_2");
            procedure.registerStoredProcedureParameter("p_idproyecto", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_idtarea", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_id_carro_compra", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);            

            procedure.setParameter("p_idproyecto", carroCompra.getDetalle().getIdProyecto());
            procedure.setParameter("p_idtarea", carroCompra.getDetalle().getIdTarea());
            procedure.setParameter("p_id_carro_compra", carroCompra.getId().intValue());

            boolean isOK = procedure.execute();

            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();                 
                NvcVmOaProyGastoCuenta gasto;
                NvcVmOaProyGastoCuentaPK nvcVmOaProyGastoCuentaPK;
                for (Object[] row : cursor) {
                    gasto = new NvcVmOaProyGastoCuenta();
                    int pos = -1;
                    String expType = (row[++pos] == null ? null : row[pos].toString());
                    String tipoGasto = (row[++pos] == null ? null : row[pos].toString());
                    String reqAlm = (row[++pos] == null ? null : row[pos].toString());
                    String tipo = (row[++pos] == null ? null : row[pos].toString());

                    nvcVmOaProyGastoCuentaPK = new NvcVmOaProyGastoCuentaPK();
                    nvcVmOaProyGastoCuentaPK.setTipo(tipo);

                    gasto.setExpType(expType);
                    gasto.setTipoGasto(tipoGasto);
                    gasto.setReqAlm(reqAlm);
                    gasto.setNvcVmOaProyGastoCuentaPK(nvcVmOaProyGastoCuentaPK);

                    gastos.add(gasto);
                }                
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
      
        return gastos;
    }    

    @Override
    @Transactional
    public NvcTblCarroCompraDetalle saveResource(Long carroCompraId, String resourceId) {
        CarroCompra carroCompra = getCarroCompra(carroCompraId);
        NvcTblCarroCompraDetalle carroCompraDetalle = carroCompra.getDetalle();
        carroCompraDetalle.setTipoGasto(resourceId);
        carroCompraDetalle.setIdCuenta(null);
        carroCompraDetalle.setComprador(null);
        carroCompraDetalle.setFechaActualizacion(new Date());
        carroCompraDetalle.setIdTipoAsignacion(1);
        em.persist(carroCompraDetalle);
        return carroCompraDetalle;
    }


    @Override
    public List<OaCombinacion> getCuentas(Long carroCompraId) {        
        List<OaCombinacion> combinaciones = new ArrayList<>();

        try {
            CarroCompra carroCompra = getCarroCompra(carroCompraId);
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_CUENTAS_PROYECTO");
            procedure.registerStoredProcedureParameter("p_idproyecto", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_idtarea", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_erogacion", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_lenguaje", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);

            procedure.setParameter("p_idproyecto", carroCompra.getDetalle().getIdProyecto());
            procedure.setParameter("p_idtarea", carroCompra.getDetalle().getIdTarea());
            procedure.setParameter("p_erogacion", carroCompra.getDetalle().getTipoGasto());
            procedure.setParameter("p_lenguaje", "ESA");            
            boolean isOK = procedure.execute();

            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();     
                OaCombinacion combinacion;
                for (Object[] row : cursor) {
                    combinacion = new OaCombinacion();
                    int pos = -1;
                    Integer idCuenta = (row[++pos] == null ? null : Integer.valueOf(row[pos].toString()));
                    String segmento1 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento2 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento3 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento4 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento5 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento6 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento7 = (row[++pos] == null ? null : row[pos].toString());
                    String segmento8 = (row[++pos] == null ? null : row[pos].toString());

                    combinacion.setCuenta(Long.valueOf(idCuenta));
                    combinacion.setSegmento1(segmento1);
                    combinacion.setSegmento2(segmento2);
                    combinacion.setSegmento3(segmento3);
                    combinacion.setSegmento4(segmento4);
                    combinacion.setSegmento5(segmento5);
                    combinacion.setSegmento6(segmento6);
                    combinacion.setSegmento7(segmento7);
                    combinacion.setSegmento8(segmento8);

                    combinaciones.add(combinacion);
                }

                for (OaCombinacion _combinacion : combinaciones) {
                    _combinacion.setSaldo( getAccountBalance(_combinacion.getCuenta().intValue()) );                    
                }
             
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return combinaciones;
    }

    @Override
    @Transactional
    public NvcTblCarroCompraDetalle saveCuenta(Long carroCompraId, Integer cuentaId) {
        try {
            CarroCompra carroCompra = getCarroCompra(carroCompraId);
            NvcTblCarroCompraDetalle detalle = carroCompra.getDetalle();

            OaCombinacion cuenta = em.createNamedQuery("OaCombinacion.findCuenta", OaCombinacion.class)
                    .setParameter("cuenta", Long.valueOf(cuentaId))
                    .setParameter("uen", carroCompra.getUen())
                    .setParameter("idioma", "ESA")
                    .getSingleResult();

            detalle.setFechaActualizacion(new Date());
            detalle.setIdCuenta(cuentaId);
            detalle.setSegmento1(cuenta.getSegmento1());
            detalle.setSegmento2(cuenta.getSegmento2());
            detalle.setSegmento3(cuenta.getSegmento3());
            detalle.setSegmento4(cuenta.getSegmento4());
            detalle.setSegmento5(cuenta.getSegmento5());
            detalle.setSegmento6(cuenta.getSegmento6());
            detalle.setSegmento7(cuenta.getSegmento7());
            detalle.setSegmento8(cuenta.getSegmento8());
            detalle.setIdTipoAsignacion(1);
            em.persist(detalle);

            return detalle;
        }catch(NoResultException e){
            return null;
        }         
    }

    private CarroCompra getCarroCompra(Long carroCompraId){
        TypedQuery<CarroCompra> queryCarro = em.createNamedQuery("CarroCompra.findById", CarroCompra.class);
        queryCarro.setParameter("id", carroCompraId);
        //try {
             return queryCarro.getSingleResult();
        //} catch (NoResultException ex) {
          //  return null;
        //}
    }

    @Override
    public List<UsuarioPojo> getBuyers(Long carroCompraId) {        
        List<UsuarioPojo> usuarios = new ArrayList<>();

        try {
            CarroCompra carroCompra = getCarroCompra(carroCompraId);
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.COMPRADOR_PROYECTO");
            procedure.registerStoredProcedureParameter("P_PROYECTO", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);

            procedure.setParameter("P_PROYECTO", String.valueOf(carroCompra.getDetalle().getIdProyecto()));

            boolean isOK = procedure.execute();

            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();     
                UsuarioPojo usuario;
                for (Object[] row : cursor) {
                    usuario = new UsuarioPojo();
                    int pos = -1;
                    String idUsuario = (row[++pos] == null ? null : row[pos].toString());
                    String nombreUsuario = (row[++pos] == null ? null : row[pos].toString());

                    usuario.setIdUsuario(idUsuario);
                    usuario.setNombreUsuario(nombreUsuario);

                    usuarios.add(usuario);
                }                
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return usuarios;
    }

    @Override
    @Transactional
    public NvcTblCarroCompraDetalle saveBuyer(Long carroCompraId, String buyerId) {
        try {
            CarroCompra carroCompra = getCarroCompra(carroCompraId);

            NvcTblCarroCompraDetalle detalle = carroCompra.getDetalle();
            detalle.setFechaActualizacion(new Date());
            detalle.setComprador(buyerId);
            detalle.setIdTipoAsignacion(1);
            em.persist(detalle);

            return detalle;
        }catch(NoResultException e){
            return null;
        }   
    }

    

}
