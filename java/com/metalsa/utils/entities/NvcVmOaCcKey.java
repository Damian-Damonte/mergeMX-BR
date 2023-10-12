package com.metalsa.utils.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Embeddable
@Data
public class NvcVmOaCcKey implements Serializable{
    
    @NotNull
    @Column(name = "ID_UEN", nullable = false)
    private BigInteger idUen;
    
    @NotNull
    @Column(name = "ID_CC", nullable = false)
    private BigInteger idCc;



}