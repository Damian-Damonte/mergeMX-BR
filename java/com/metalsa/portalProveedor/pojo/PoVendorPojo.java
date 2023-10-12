package com.metalsa.portalProveedor.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author APOJM20015
 * @date 10/Jun/2020
 */
@Data
@Entity
public class PoVendorPojo implements Serializable {
    @Id
    private Integer vendorId;
    private String vendorName;
}
