/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.ApprovalResponse;
import com.metalsa.aprobacion.model.CategoryConfiguration;
import com.metalsa.aprobacion.model.DetalleRequisicion;
import com.metalsa.aprobacion.repository.CategoryConfigurationRepository;
import com.metalsa.aprobacion.repository.DetalleRequisicionRepository;
import com.metalsa.aprobacion.repository.ReqPorAprobarRepository;
import com.metalsa.aprobacion.service.ApprovalService.ApprovalItemRequest;
import com.metalsa.core.model.NvcTblProvSitesH;
import com.metalsa.core.model.OaUens;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author hector.gutierrez02
 */
@Service
public class ApprovalValidation {

    @Autowired
    private ReqPorAprobarRepository db;

    @Autowired
    private DetalleRequisicionRepository detalles;

    @Autowired
    private CategoryConfigurationRepository configCategorias;

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    public Object[] EvalMessageApproval(int errorCode, ApprovalItemRequest item) {
        Object[] oResult = new Object[2];
        switch (errorCode) {
            case 0:
            case 4:
            case 200:
            case 204:
                oResult[0] = messages.getMessage("approvals.code." + errorCode, null, LocaleContextHolder.getLocale());
                oResult[1] = true;
                break;
            case -200:
                //<T403785>
                oResult[0] = messages.getMessage("approvals.valid.project", null, LocaleContextHolder.getLocale())
                        + messages.getMessage("approvals.valid.contact", null, LocaleContextHolder.getLocale());
                oResult[1] = false;
                break;
            case -300:
                oResult[0] = messages.getMessage("approvals.valid.project_period", null, LocaleContextHolder.getLocale())
                        + messages.getMessage("approvals.valid.contact", null, LocaleContextHolder.getLocale());
                oResult[1] = false;
                break;
            case -400:
                oResult[0] = messages.getMessage("approvals.valid.task", null, LocaleContextHolder.getLocale())
                        + messages.getMessage("approvals.valid.contact", null, LocaleContextHolder.getLocale());
                oResult[1] = false;
                break;
            case -500:
                oResult[0] = messages.getMessage("approvals.valid.task.charges", null, LocaleContextHolder.getLocale())
                        + messages.getMessage("approvals.valid.contact", null, LocaleContextHolder.getLocale());
                oResult[1] = false;
                break;
            //</T403785>
            case -600:
                oResult[0] = creaMensajeErrorAprobar(messages.getMessage("approvals.valid.project_budget_update", null, LocaleContextHolder.getLocale()), item.getRequisicion(), item.getLinea())
                        + messages.getMessage("approvals.valid.contact", null, LocaleContextHolder.getLocale());
                oResult[1] = false;
                break;
            //</T428940>
            case -700:
                oResult[0] = creaMensajeErrorAprobar(messages.getMessage("approvals.valid.project_budget_in_process", null, LocaleContextHolder.getLocale()), item.getRequisicion(), item.getLinea());
                oResult[1] = false;
                break;
            //</T475293>
            case 701:
                //<T475293>
                oResult[0] = messages.getMessage("msj_valida_seleccion_cotizacion", null, LocaleContextHolder.getLocale());
                oResult[1] = false;
                break;
            //</T479818>
            case 702:
                //<T479818>
                oResult[0] = messages.getMessage("msj_valida_cuenta", null, LocaleContextHolder.getLocale());
                oResult[1] = false;
                break;
            //</T428940>
            case 703:
                //<T472517>
                oResult[0] = messages.getMessage("approvals.valid.user.requisitor", null, LocaleContextHolder.getLocale());
                oResult[1] = false;
                break;
            case 704:
                //<T472517>
                NvcTblProvSitesH site = db.getProveedorSite(item.getRequisicion());
                if (site != null) {
                    oResult[0] = messages.getMessage("msj_proveedor_site_invalido", null, LocaleContextHolder.getLocale()).replace("[site]", site.getVendorSiteCode()).replace("[prov]", site.getVendorName());
                    oResult[1] = false;
                } else {
                    oResult[0] = messages.getMessage("msj_proveedor_site_invalido", null, LocaleContextHolder.getLocale()).replace("[site]", "").replace("[prov]", "");
                    oResult[1] = false;
                }
                break;
            //<T567485>
            case 705:
                oResult[0] = messages.getMessage("approvals.item.proveedor_valido", null, LocaleContextHolder.getLocale());
                oResult[1] = false;
                break;
            case 706:
                oResult[0] = messages.getMessage("approvals.item.no_reservable", null, LocaleContextHolder.getLocale());
                oResult[1] = false;
                break;
            //</T567485>
            default:
                if (errorCode == 100 || errorCode == 3) {
                    Optional<DetalleRequisicion> detail = detalles.getByRequisicionAndLinea(item.getRequisicion(), item.getLinea());
                    if (detail.isPresent()) {
                        CategoryConfiguration conf = configCategorias.findOne(
                                new CategoryConfiguration.Pk(detail.get().getUen(), detail.get().getCategoryId()));
                        if (conf != null && conf.isAprobacionExceso()) {
                            errorCode = 101;
                        }
                    }
                }
                oResult[0] = messages.getMessage("approvals.code." + errorCode, null, LocaleContextHolder.getLocale());
                oResult[1] = false;
                break;
        }
        return oResult;
    }

    public String creaMensajeErrorAprobar(String msj, Long requi, Long linea) {//<T426293>

        Optional<DetalleRequisicion> detail = detalles.getByRequisicionAndLinea(requi, linea);
        OaUens uenEntity = db.findMainValuesById(detail.get().getUen());

        return messages.getMessage(msj, null, LocaleContextHolder.getLocale()).
                replace("[proyecto]", detail.get().getCodigoProyecto()).
                replace("[uen]", uenEntity.getOrganizationName());

    }

}
