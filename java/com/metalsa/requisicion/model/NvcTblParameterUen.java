package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Entity
@Table(name = "NVC_TBL_PARAMETER_UEN", catalog = "")
public class NvcTblParameterUen implements Serializable {
    @Id
    @Column(name = "ID_PARAMETER_UEN")
    private long id;
    
    @Column(name = "ID_PARAMETER")
    private long idParameter;
    
    /*@OneToMany
    @JoinColumn(
            name = "ID_PARAMETER_UEN",
            referencedColumnName = "ID_PARAMETER_UEN",
            updatable = false,
            insertable = false)
    List<NvcTblParameterValue> values;*/
    
    public long getId() {
        return this.id;
    }
    
    public long getIdParameter() {
        return this.idParameter;
    }
    
    public void setIdParameter(long id) {
        this.idParameter = id;
    }
}
