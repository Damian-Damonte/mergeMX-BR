/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utilerias.pojo;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Data
public class MyHDRowPojo {

    private String country;
    private String empNo;
    private String objectiveId;
    private String rawStatus;
    private String rawScore;
    private Date inDate;
    private Long id;
}
