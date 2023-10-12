package com.metalsa.requisicion.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Entity
@Table(name = "NVC_TBL_PARAMETER_VALUE", catalog = "")
public class NvcTblParameterValue implements Serializable {
    @Id
    @Column(name = "ID_PARAMETER_VALUE")
    private long id;
    
    public long getId() {
        return this.id;
    }
}
