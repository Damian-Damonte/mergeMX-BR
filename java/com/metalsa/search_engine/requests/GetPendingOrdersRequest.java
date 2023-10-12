package com.metalsa.search_engine.requests;

import lombok.Data;

@Data
public class GetPendingOrdersRequest {
    private Integer uen;
    private Integer itemId;
    private Integer almacenId;
}
