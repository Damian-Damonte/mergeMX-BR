/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.generales.service;

import com.metalsa.generales.model.NvcTblRolPerfil;
import com.metalsa.generales.model.PerAllPeopleF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author miguel.rdz
 * @param <T>
 * @param <ID>
 */
@Log4j
@Service
public class HeaderServiceImpl<T, ID extends Serializable> implements HeaderService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public PerAllPeopleF getEmpInfo(Long personId) {
        PerAllPeopleF res = null;
        try {
            List<Object[]> results = em.createNativeQuery("select pf.person_id, pf.first_name, substr(p.position, - instr(reverse(p.position), '.') + 1) position, "
                    + "p.location_code, pf.email_address, dt.extension from nvc_vm_per_all_people_f pf "
                    + "left join tbl_tw_persona p on pf.person_id = p.person_id "
                    + "left join directorio_telefonico@tecweb_tecweb dt on pf.person_id = dt.person_id "
                    + "where pf.person_id = :personId and effective_end_date = "
                    + "(select max(effective_end_date) from nvc_vm_per_all_people_f where person_id = :personId) ")
                    .setParameter("personId", personId)
                    .getResultList();
            for (Object[] result : results) {
                res = new PerAllPeopleF();
                res.setIdEmpleado(result[0] != null ? result[0].toString() : null);
                res.setPrimerNombre(result[1] != null ? result[1].toString() : null);
                res.setPuesto(result[2] != null ? result[2].toString() : null);
                res.setLocalizacion(result[3] != null ? result[3].toString() : null);
                res.setEmail(result[4] != null ? result[4].toString() : null);
                res.setExtension(result[5] != null ? result[5].toString() : null);
            }
        } catch (Exception e) {
            log.error("error en : getCacheComprador :" + e.getMessage());
        }
        return res;
    }

    @Override
    public Iterable<NvcTblRolPerfil> getRolesPerfil(String idUsuario) {
        List<NvcTblRolPerfil> res = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select rp.id_rol, rp.id_usuario, rp.orden, rp.longitud, rp.color, rp.icono, r.clave "
                    + "from NVC_TBL_ROL_PERFIL rp, ROLES_POR_USUARIO ru, ROL r  "
                    + "where r.id_rol = ru.id_rol and ru.id_rol = rp.id_rol and ru.id_usuario = rp.id_usuario "
                    + "and rp.id_usuario = :idUsuario and rp.activo = 1 order by rp.orden")
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
            results.stream().map((result) -> {
                NvcTblRolPerfil rolPerfil = new NvcTblRolPerfil();
                rolPerfil.setIdRol(Integer.parseInt(result[0].toString()));
                rolPerfil.setIdUsuario(result[1].toString());
                rolPerfil.setOrden(Integer.parseInt(result[2].toString()));
                rolPerfil.setLongitud(Integer.parseInt(result[3].toString()));
                rolPerfil.setColor(result[4].toString());
                rolPerfil.setIcono(result[5].toString());
                rolPerfil.setClave(result[6].toString());
                return rolPerfil;
            }).forEachOrdered((rolPerfil) -> {
                res.add(rolPerfil);
            });
        } catch (NumberFormatException e) {
            log.error("error en : getRolesPerfil :" + e.getMessage());
        }
        return res;
    }

    @Override
    public Iterable<NvcTblRolPerfil> getRolesDisponibles(String idUsuario) {
        List<NvcTblRolPerfil> res = new ArrayList();
        try {
//            List<Object[]> results = em.createNativeQuery("select r.id_rol, r.clave from rol r "
//                    + "where r.id_rol in(select distinct rp.id_rol from nvc_tbl_rol_perfil rp "
//                    + "where rp.id_rol not in (select rp2.id_rol from nvc_tbl_rol_perfil rp2 "
//                    + "where rp2.id_usuario = :idUsuario))")
            List<Object[]> results = em.createNativeQuery("select r.id_rol, r.clave from rol r "
                    + "where r.id_rol in(select distinct rp.id_rol from nvc_tbl_rol_perfil rp "
                    + "where rp.id_rol not in (select ru.id_rol from roles_por_usuario ru where ru.id_usuario = :idUsuario)) ")
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
            results.stream().map((result) -> {
                NvcTblRolPerfil rolPerfil = new NvcTblRolPerfil();
                rolPerfil.setIdRol(Integer.parseInt(result[0].toString()));
                rolPerfil.setClave(result[1].toString());
                return rolPerfil;
            }).forEachOrdered((rolPerfil) -> {
                res.add(rolPerfil);
            });
        } catch (NumberFormatException e) {
            log.error("error en : getRolesPerfilDisponibles :" + e.getMessage());
        }
        return res;
    }

    @Override
    public String canShowGuide(String idUsuario) {
        try {
            List<Object[]> results = em.createNativeQuery("select fecha_actualizacion from nvc_tbl_rol_perfil "
                    + "where id_usuario = :idUsuario and fecha_actualizacion is not null ")
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
            return results.isEmpty() ? "Y" : "N";
        } catch (NumberFormatException e) {
            log.error("error en : canShowGuide :" + e.getMessage());
        }
        return "N";
    }
}
