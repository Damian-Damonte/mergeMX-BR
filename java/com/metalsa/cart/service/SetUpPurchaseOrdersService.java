package com.metalsa.cart.service;

import java.math.BigInteger;
import java.util.List;

import com.metalsa.aprobacion.model.OaCombinacion;
import com.metalsa.cart.pojo.UsuarioPojo;
import com.metalsa.utils.entities.NvcTblCarroCompraDetalle;
import com.metalsa.utils.entities.NvcVProyectosAll;
import com.metalsa.utils.entities.NvcVmOaCc;
import com.metalsa.utils.entities.NvcVmOaProyGastoCuenta;
import com.metalsa.utils.entities.NvcVmOaProyectoTareas;

public interface SetUpPurchaseOrdersService {
    
    public List<NvcVmOaCc> getCostCenter(BigInteger idUen);
    public NvcTblCarroCompraDetalle saveCostCenter(Long carroCompraId, Integer idCostCenter);
    public List<OaCombinacion> getAccounts(Long carroCompraId);
    public Long getCategoryByAccountId(Long accourId);
    public NvcTblCarroCompraDetalle saveAccount(Long carroCompraId, Integer idAccount);
    public NvcTblCarroCompraDetalle saveSp(Long carroCompraId, Integer value);


    public List<NvcVProyectosAll> getProjects(Long carroCompraId);
    public NvcTblCarroCompraDetalle saveProject(Long carroCompraId, Integer projectId);

          
    public List<NvcVmOaProyectoTareas> getTasks(Long carroCompraId);
    public NvcTblCarroCompraDetalle saveTask(Long carroCompraId, Integer taskId);
    
    public List<NvcVmOaProyGastoCuenta> getResources(Long carroCompraId);
    public NvcTblCarroCompraDetalle saveResource(Long carroCompraId, String resourceId);

    public List<OaCombinacion> getCuentas(Long carroCompraId);
    public NvcTblCarroCompraDetalle saveCuenta(Long carroCompraId, Integer cuentaId);

    public List<UsuarioPojo> getBuyers(Long carroCompraId);
    public NvcTblCarroCompraDetalle saveBuyer(Long carroCompraId, String buyerId);
    
}
