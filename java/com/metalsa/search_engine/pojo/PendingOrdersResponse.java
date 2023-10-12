package com.metalsa.search_engine.pojo;

import java.util.List;

import com.metalsa.search_engine.model.PendingOrder;

import lombok.Data;

@Data
public class PendingOrdersResponse {
    private List<PendingOrder> pendingOrders;
}
