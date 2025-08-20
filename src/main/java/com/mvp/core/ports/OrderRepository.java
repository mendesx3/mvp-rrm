package com.mvp.core.ports;

import com.mvp.core.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
