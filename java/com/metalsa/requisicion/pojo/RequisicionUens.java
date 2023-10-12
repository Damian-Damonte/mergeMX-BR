package com.metalsa.requisicion.pojo;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public class RequisicionUens {
     private final List<RequisicionUen> uens;
     
     public RequisicionUens() {
         this.uens = new ArrayList<>();
     }
     
     public List<RequisicionUen> getUens() {
         return uens;
     }
     
     public RequisicionUen uenById(String id) {
         return uenById(Integer.valueOf(id));
     }
     
     public RequisicionUen uenById(Integer id) {
         return uens.stream()
                 .filter((uen) -> Objects.equals(uen.getId(), id))
                 .findFirst()
                 .get();
     }
     
     public RequisicionUen createUen(Integer id) {
         RequisicionUen uen = new RequisicionUen();
         uens.add(uen);
         return uen;
     }
}