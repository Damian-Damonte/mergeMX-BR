/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.matriz.pagos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Entity
@Data
public class MatrizPagos implements Serializable {

    @Id
    private Long id;
    private String projectNumber;
    private String projectName;
    private String projectType;
    private String projectManager;
    private String taskName;
    private String invoiceNum;
    private String po;
    private String receiptNum;
    private String vendorName;
    private String invoiceLineDate;
    private String digitalizedDoc;
    private String percentPaid;
    private String lineAmount;
    private String invoiceCurrencyCode;
    private String payments;
    private String prepaymentsPayments;
    private String lineAmountPaid;
    private String functionalLineAmount;
    private String functionalLineAmountPaid;
    private String payGroup;
    private String orgId;
    private String program;
    private String accountType;
    private String functionalCurrencyCode;
    private String usdLineAmountPaid;
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private Date invoiceDate;
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private Date poOrderDate;
    private String vendorId;
    private String invoiceId;
    private String invoiceAmount;
    private String termsDescription;
    private String requistionNum;
    private String lineaReq;
    private String nombreSite;
    @Column(name = "ORGANIZATION_NAME")
    private String nombreUen;
    @Column(name = "NOMBRE_USUARIO")
    private String nombreComprador;
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    private String status;
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private Date fechaVencimiento;
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private Date fechaProgramada;
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private Date fechaPagoReal;
    private String warning;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date invoiceCreationDate;

}
