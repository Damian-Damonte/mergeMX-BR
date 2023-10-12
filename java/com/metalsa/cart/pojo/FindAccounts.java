package com.metalsa.cart.pojo;

import java.math.BigInteger;

import lombok.Data;

@Data
public class FindAccounts {
    private BigInteger idUen;
    private int idFamilia;
    private String segmento2;
    private String lenguaje;
    private String segmento6;
    private String fuente;


    public String getSegmento6(){
        return "000";
    }

    public String getFuente(){
        return null;
    }



}
