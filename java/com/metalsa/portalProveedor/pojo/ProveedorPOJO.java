/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mlopez
 */
/*
public class ProveedorPOJO {
    private BigDecimal idProveedor;
    private String nombreProveedor;
    private String vendorNameAlt;
    private NvcTblCategoria categoria;
    private NvcTblProvContact contacto;
    private String emailAltContacto;
    private NvcTblOaProveedoresH proveedor;
    private String comentarios;

    private NvcTblProvSitesH provSite;

    private NvcTblProvTemporal provTemporal;

    private List<NvcTblReqLineaProv> lineas;
    private List<NvcTblReqLineaProv> lineasSeleccionadas;
    private boolean notificarPorEmail;

    private boolean enviadoProv;
    
    
    private String idPojo;//<R21195>

    public ProveedorPOJO() {
        this.idProveedor = BigDecimal.ZERO;
        this.nombreProveedor = "";
        this.vendorNameAlt = "";
        this.categoria = new NvcTblCategoria();
        this.provSite = new NvcTblProvSitesH();
        this.contacto = new NvcTblProvContact();
        this.emailAltContacto = "";
    }

    public ProveedorPOJO(BigDecimal idProveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = "";
        this.vendorNameAlt = "";
        this.categoria = new NvcTblCategoria();
        this.provSite = new NvcTblProvSitesH();
        this.contacto = new NvcTblProvContact();
        this.emailAltContacto = "";
    }

    /**
     *
     * @param idProveedor
     * @param nombreProveedor
     * @param vendorNameAlt
     * @param categoria
     * @param provSite
     * @param contacto
     * @param proveedor
     */
/*
    public ProveedorPOJO(BigDecimal idProveedor, String nombreProveedor,
            String vendorNameAlt, NvcTblCategoria categoria,
            NvcTblProvSitesH provSite, NvcTblProvContact contacto,
            NvcTblOaProveedoresH proveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.vendorNameAlt = vendorNameAlt;
        this.categoria = categoria;
        this.provSite = provSite;
        this.contacto = contacto;
        this.proveedor = proveedor;
    }

    public ProveedorPOJO(BigDecimal idProveedor, String nombreProveedor,
            String vendorNameAlt, NvcTblCategoria categoria,
            NvcTblProvSitesH provSite, NvcTblProvContact contacto,
            NvcTblOaProveedoresH proveedor, String emailAltContacto) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.vendorNameAlt = vendorNameAlt;
        this.categoria = categoria;
        this.provSite = provSite;
        this.contacto = contacto;
        this.proveedor = proveedor;
        this.emailAltContacto = emailAltContacto;
    }

    /**
     *
     * @param idProveedor
     * @param nombreProveedor
     * @param vendorNameAlt
     * @param provSite
     * @param contacto
     * @param proveedor
     */

/*
    public ProveedorPOJO(BigDecimal idProveedor, String nombreProveedor,
            String vendorNameAlt, NvcTblProvSitesH provSite,
            NvcTblProvContact contacto, NvcTblOaProveedoresH proveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.vendorNameAlt = vendorNameAlt;
        this.provSite = provSite;
        this.contacto = contacto;
        this.proveedor = proveedor;
    }

    /**
     *
     * @param idProveedor
     * @param provTemporal
     */

/*
    public ProveedorPOJO(int idProveedor, NvcTblProvTemporal provTemporal) {
        this.idProveedor = new BigDecimal(idProveedor);
        this.provTemporal = provTemporal;
        this.nombreProveedor = this.provTemporal.getNombre();
        this.vendorNameAlt = this.nombreProveedor;
        this.provSite = new NvcTblProvSitesH();
        this.provSite.setVendorSiteCode(this.nombreProveedor);
        this.contacto = new NvcTblProvContact();
        this.contacto.setFirstName(this.nombreProveedor);
        this.contacto.setEmailAddress(this.provTemporal.getEmail());
        this.emailAltContacto = this.provTemporal.getEmailAdicional();
    }

    public ProveedorPOJO(NvcTblProvSitesH provSite, NvcTblProvContact contacto) {
        this.idProveedor = new BigDecimal(provSite.getNvcTblProvSitesHPK().getIdProveedor().longValue());
        this.nombreProveedor = provSite.getVendorName();
        this.vendorNameAlt = provSite.getVendorSiteCodeAlt();
        this.provSite = provSite;
        this.contacto = contacto;
//        this.lineas = provSite.getNvcTblReqLineaProvList();
        this.setAttributes();
    }

    public ProveedorPOJO(NvcTblProvTemporal provTemporal) {
        this.idProveedor = new BigDecimal(provTemporal.getIdProveedor());
        this.nombreProveedor = provTemporal.getNombre();
        this.provTemporal = provTemporal;
//        this.lineas = (List<NvcTblReqLineaProv>) provTemporal.getNvcTblReqLineaProvCollection();
        this.setAttributes();
    }

    public void setAttributes() {
        if (this.lineas != null && this.lineas.size() > 0) {
            this.comentarios = lineas.get(0).getIdRfqProv().getComentario();
            this.emailAltContacto = lineas.get(0).getIdRfqProv().getAdicionalContact();
        }
        for1:
        for (NvcTblReqLineaProv li : lineas) {
            li.setProvPOJO(this);
            if (li.getEnviadoProv() == null || li.getEnviadoProv().compareTo('N') == 0) {
                this.enviadoProv = false;
                continue for1;
            } else {
                this.enviadoProv = true;
            }
        }
    }


}
*/