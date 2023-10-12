package com.metalsa.cart.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import com.metalsa.cart.pojo.SaveAccount;
import com.metalsa.cart.pojo.SaveBuyer;
import com.metalsa.cart.pojo.SaveCostCenter;
import com.metalsa.cart.pojo.SaveProject;
import com.metalsa.cart.pojo.SaveResource;
import com.metalsa.cart.pojo.SaveTask;
import com.metalsa.cart.pojo.UpdateSp;
import com.metalsa.cart.service.SetUpPurchaseOrdersService;
import com.metalsa.utils.Constants;

@RestController
@Api(value = "SetUpPurchaseOrdersController", tags = {"SetUpPurchaseOrdersController Service API"})
@RequestMapping(Constants.URI_API_SET_UP_PURCHASE_ORDERS)
public class SetUpPurchaseOrdersController{

    @Autowired
    private SetUpPurchaseOrdersService setUpPurchaseOrdersService;

    @GetMapping("/cost-centers")
    public Object costCenters(@RequestParam BigInteger idUen){    
        return setUpPurchaseOrdersService.getCostCenter(idUen);        
    }

    @PostMapping("/cost-centers/{carroCompraId}")
    public Object costCenters(@PathVariable Long carroCompraId, @RequestBody SaveCostCenter request){    
        return setUpPurchaseOrdersService.saveCostCenter(carroCompraId, request.getCostCenterId());                
    }

    
    @GetMapping("/accounts/{carroCompraId}")
    public Object getAccounts(@PathVariable Long carroCompraId){    
        return setUpPurchaseOrdersService.getAccounts(carroCompraId);        
    }

    @PostMapping("/accounts/{carroCompraId}")
    public Object saveAccount(@PathVariable Long carroCompraId, @RequestBody SaveAccount request){    
        return setUpPurchaseOrdersService.saveAccount(carroCompraId, request.getAccount());        
    }

    @PostMapping("/sp/{carroCompraId}")
    public Object saveSp(@PathVariable Long carroCompraId, @RequestBody UpdateSp request){
        return setUpPurchaseOrdersService.saveSp(carroCompraId, request.getSp());                
    }

    @GetMapping("/projects/{carroCompraId}")
    public Object getProjects(@PathVariable Long carroCompraId){    
        return setUpPurchaseOrdersService.getProjects(carroCompraId);        
    }

    @PostMapping("/projects/{carroCompraId}")
    public Object saveProject(@PathVariable Long carroCompraId, @RequestBody SaveProject request){    
        return setUpPurchaseOrdersService.saveProject(carroCompraId, request.getProjectId());        
    }

    @GetMapping("/tasks/{carroCompraId}")
    public Object getTasks(@PathVariable Long carroCompraId){    
        return setUpPurchaseOrdersService.getTasks(carroCompraId);        
    }

    @PostMapping("/tasks/{carroCompraId}")
    public Object saveTasks(@PathVariable Long carroCompraId, @RequestBody SaveTask request){    
        return setUpPurchaseOrdersService.saveTask(carroCompraId, request.getTaskId());        
    }

    @GetMapping("/resources/{carroCompraId}")
    public Object getResources(@PathVariable Long carroCompraId){    
        return setUpPurchaseOrdersService.getResources(carroCompraId);        
    }

    @PostMapping("/resources/{carroCompraId}")
    public Object saveResource(@PathVariable Long carroCompraId, @RequestBody SaveResource request){    
        return setUpPurchaseOrdersService.saveResource(carroCompraId, request.getResource());        
    }

    @GetMapping("/cuentas/{carroCompraId}")
    public Object getCuentas(@PathVariable Long carroCompraId){    
        return setUpPurchaseOrdersService.getCuentas(carroCompraId);        
    }

    @GetMapping("/cuentas/{accountId}/category")
    public Long getCategoryByAccountId(@PathVariable("accountId") Long accountId) {
        return setUpPurchaseOrdersService.getCategoryByAccountId(accountId);
    }

    @PostMapping("/cuentas/{carroCompraId}")
    public Object saveCuenta(@PathVariable Long carroCompraId, @RequestBody SaveAccount request){    
        return setUpPurchaseOrdersService.saveCuenta(carroCompraId, request.getAccount());        
    }

    @GetMapping("/buyers/{carroCompraId}")
    public Object getBuyers(@PathVariable Long carroCompraId){    
        return setUpPurchaseOrdersService.getBuyers(carroCompraId);        
    }

    @PostMapping("/buyers/{carroCompraId}")
    public Object saveBuyer(@PathVariable Long carroCompraId, @RequestBody SaveBuyer request){    
        return setUpPurchaseOrdersService.saveBuyer(carroCompraId, request.getUserId());        
    }

}