package com.metalsa.reportes.repository;

import com.metalsa.reportes.model.Balanza;
import com.metalsa.reportes.model.BalanzaLinea;
import com.metalsa.reportes.model.EstadoCuenta;
import com.metalsa.reportes.model.EstadoCuentaProject;
import com.metalsa.reportes.model.EstadoCuentaTravel;
import com.metalsa.reportes.model.GastosProject;
import com.metalsa.reportes.model.GastosTravel;
import com.metalsa.reportes.model.Incrementos;
import com.metalsa.reportes.model.Parametros;
import com.metalsa.reportes.model.PresupuestoAnual;
import com.metalsa.reportes.model.PresupuestoAnualLinea;
import com.metalsa.reportes.model.PresupuestoUen;
import com.metalsa.reportes.model.PresupuestoUenLinea;
import com.metalsa.reportes.model.Transferencias;
import com.metalsa.reportes.model.Usuarios;
import com.metalsa.reportes.model.Variacion;
import com.metalsa.reportes.model.VariacionLinea;
import java.util.List;

/**
 *
 * @author Oscar del Angel
 */
public interface ReportesProcedure {
    
    List<BalanzaLinea> getReporteBalanza(Parametros filtros);
    
    List<Balanza> getReporteBalanzaAgrupado(Parametros filtros);

    List<PresupuestoAnualLinea> getReportePresupuestoAnual(Parametros filtros);

    List<PresupuestoAnual> getReportePresupuestoAnualAgrupado(Parametros filtros);
    
    List<Variacion> getReporteVariacionAgrupado(Parametros filtros);

    List<VariacionLinea> getReporteVariacion(Parametros filtros);

    List<PresupuestoUenLinea> getReportePresupuestoUen(Parametros filtros);
    
    PresupuestoUen getReportePresupuestoUenAgrupado(Parametros filtros);
    
    List<EstadoCuenta> getReporteEstadoCuenta(Parametros filtros);
    
    List<GastosTravel> getReporteGastosTravel(Parametros filtros);
    
    List<GastosProject> getReporteGastosProject(Parametros filtros);
    
    List<EstadoCuentaProject> getReporteEdoCuentaProject(Parametros filtros);
        
    List<Incrementos> getReporteIncremento(Parametros filtros);
    
    List<Incrementos> getReporteIncrementoEdo(Parametros filtros);
    
    List<Transferencias> getReporteTransferencias(Parametros filtros);
    
    List<Transferencias> getReporteTransferenciasEdo(Parametros filtros);
    
    List<EstadoCuentaTravel> getReporteEdoCuentaTravel(Parametros filtros);

    /**
     * 
     * @param tipoReporte {INCREASE,TRANSFER}
     * @param tipoUsuario {CREADOR,REQUISITOR,APROBADOR_CC,APROBADOR_FINANZAS}  
     * @return 
     */
    List<Usuarios> getUsuarios(String tipoReporte, String tipoUsuario);

}
