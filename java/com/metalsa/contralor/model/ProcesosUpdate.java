package com.metalsa.contralor.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@Getter
@Setter
public class ProcesosUpdate extends RequestBody {

    private List<Procesos> linesToAdd;
    private List<Procesos> linesToRemove;
    private List<Procesos> linesToUpdate;

    private List<ProcessUen> listProcessUen;

}
