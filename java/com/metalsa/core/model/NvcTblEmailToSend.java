package com.metalsa.core.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author apomr
 */
@Entity
@Table(name = "NVC_TBL_EMAIL_TO_SEND")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblEmailToSend.findAll", query = "SELECT r FROM NvcTblEmailToSend r")
    , @NamedQuery(name = "NvcTblEmailToSend.findByStatus", query = "SELECT r FROM NvcTblEmailToSend r WHERE r.status = :status")})
public class NvcTblEmailToSend implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_EMAIL_TO_SEND")
    private Integer idEmailToSend;
    @Column(name = "SENDERS")
    private String senders;
    @Column(name = "RECEIPT")
    private String receipt;
    @Column(name = "RECEIPTS_CC")
    private String receiptsCc;
    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "EMAIL_BODY")
    private String emailBody;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "STATUS")
    private String status;

    public NvcTblEmailToSend() {

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdEmailToSend() != null ? getIdEmailToSend().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NvcTblEmailToSend)) {
            return false;
        }
        NvcTblEmailToSend other = (NvcTblEmailToSend) object;
        return this.getIdEmailToSend().equals(other.getIdEmailToSend());
    }

    @Override
    public String toString() {
        return "com.metalsa.core.model.NvcTblEmailToSend[ idEmailToSend=" + getIdEmailToSend() + " ]";
    }

    public Integer getIdEmailToSend() {
        return idEmailToSend;
    }

    public void setIdEmailToSend(Integer idEmailToSend) {
        this.idEmailToSend = idEmailToSend;
    }

    public String getSenders() {
        return senders;
    }

    public void setSenders(String senders) {
        this.senders = senders;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getReceiptsCc() {
        return receiptsCc;
    }

    public void setReceiptsCc(String receiptsCc) {
        this.receiptsCc = receiptsCc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
