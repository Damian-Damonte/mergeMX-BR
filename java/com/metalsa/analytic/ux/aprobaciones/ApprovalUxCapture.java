package com.metalsa.analytic.ux.aprobaciones;

import com.metalsa.analytic.ux.UxCapture;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity
@Table(name = "nvc_tbl_ux_aprobaciones")
@Getter
@NoArgsConstructor
public class ApprovalUxCapture extends UxCapture {

    private String mostrarPor;
    private String requisiciones;
    private String uens;
    private String centrosCosto;
    private String proyectos;
    private String tipos;
    private String requisitores;
    private String descripcion;
    private boolean soloUrgentes;
    private boolean soloCentroCosto;
    private boolean soloProyecto;
    private boolean soyResponsable;


//    public ApprovalUxCapture(String usuario, LocalDateTime capturedAt, String mostrarPor, String requisiciones,
//                             String uens, String centrosCosto, String proyectos, String tipos, String requisitores,
//                             String descripcion, boolean soloUrgentes, boolean soloCentroCosto, boolean soloProyecto,
//                             boolean soyResponsable) {
//        super(usuario, capturedAt);
//        this.mostrarPor = mostrarPor;
//        this.requisiciones = requisiciones;
//        this.uens = uens;
//        this.centrosCosto = centrosCosto;
//        this.proyectos = proyectos;
//        this.tipos = tipos;
//        this.requisitores = requisitores;
//        this.descripcion = descripcion;
//        this.soloUrgentes = soloUrgentes;
//        this.soloCentroCosto = soloCentroCosto;
//        this.soloProyecto = soloProyecto;
//        this.soyResponsable = soyResponsable;
//    }
}
