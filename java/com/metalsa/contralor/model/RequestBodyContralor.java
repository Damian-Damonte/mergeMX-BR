package com.metalsa.contralor.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@Getter
@Setter
public class RequestBodyContralor extends RequestBody{

    private String ccs;
    private String process;
    private String groups;
    private String delegates;
    private String responsiblescc;
    private String responsiblesgroup;
    private boolean withoutresponsible;
    private String fistUpdate;
    private String lastUpdate;
    private Integer pagestart;
    private Integer pageend;
    private Integer tipo;
    private String idioma;
    private String token;

    @Override
    public String toString() {
        return "RequestBodyContralor{" + "uen=" + uen + ", lang=" + lang + ", ccs=" + ccs + ", process=" + process + ", groups=" + groups + ", delegates=" + delegates + ", responsiblescc=" + responsiblescc + ", responsiblesgroup=" + responsiblesgroup + ", withoutresponsible=" + withoutresponsible + ", fistUpdate=" + fistUpdate + ", lastUpdate=" + lastUpdate + ", pagestart=" + pagestart + ", pageend=" + pageend + ", tipo=" + tipo + ", idioma=" + idioma + "}";
    }



}
