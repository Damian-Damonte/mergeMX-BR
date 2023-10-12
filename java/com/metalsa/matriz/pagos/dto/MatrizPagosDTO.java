/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.matriz.pagos.dto;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Data
public class MatrizPagosDTO implements Serializable {

    private Long id;
    @SerializedName(value = "project_number")
    private String projectNumber;
    @SerializedName(value = "project_name")
    private String projectName;
    @SerializedName(value = "project_type")
    private String projectType;
    @SerializedName(value = "project_manager")
    private String projectManager;
    @SerializedName(value = "task_name")
    private String taskName;
    @SerializedName(value = "invoice_num")
    private String invoiceNum;
    private String po;
    @SerializedName(value = "receipt_num")
    private String receiptNum;
    @SerializedName(value = "vendor_name")
    private String vendorName;
    @SerializedName(value = "invoice_line_date")
    private String invoiceLineDate;
    @SerializedName(value = "digitalized_doc")
    private String digitalizedDoc;
    @SerializedName(value = "percent_paid")
    private String percentPaid;
    @SerializedName(value = "line_amount")
    private String lineAmount;
    @SerializedName(value = "invoice_currency_code")
    private String invoiceCurrencyCode;
    private String payments;
    @SerializedName(value = "prepayments_payments")
    private String prepaymentsPayments;
    @SerializedName(value = "line_amount_paid")
    private String lineAmountPaid;
    @SerializedName(value = "functional_line_amount")
    private String functionalLineAmount;
    @SerializedName(value = "functional_line_amount_paid")
    private String functionalLineAmountPaid;
    @SerializedName(value = "pay_group")
    private String payGroup;
    @SerializedName(value = "org_id")
    private String orgId;
    private String program;
    @SerializedName(value = "account_type")
    private String accountType;
    @SerializedName(value = "functional_currency_code")
    private String functionalCurrencyCode;
    @SerializedName(value = "usd_line_amount_paid")
    private String usdLineAmountPaid;
    @SerializedName(value = "invoice_date")
    private String invoiceDate;
    @SerializedName(value = "po_order_date")
    private String poOrderDate;
    @SerializedName(value = "vendor_id")
    private String vendorId;
    @SerializedName(value = "invoice_id")
    private String invoiceId;
    @SerializedName(value = "invoice_amount")
    private String invoiceAmount;
    private String status;
    @SerializedName(value = "terms_description")
    private String termsDescription;
    @SerializedName(value = "requistion_num")
    private String requistionNum;
    @SerializedName(value = "linea_req")
    private String lineaReq;
    @SerializedName(value = "nombre_site")
    private String nombreSite;
    @SerializedName(value = "nombre_uen")
    private String nombreUen;
    @SerializedName(value = "nombre_comprador")
    private String nombreComprador;
    @SerializedName(value = "fecha_vencimiento")
    private String fechaVencimiento;
    @SerializedName(value = "fecha_programada")
    private String fechaProgramada;
    @SerializedName(value = "fecha_pago_real")
    private String fechaPagoReal;
    private String warning;

}
