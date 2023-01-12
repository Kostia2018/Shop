package com.home.springshope.Service;

import com.home.springshope.Model.Order;

public interface OrderService {

    void saveOrder(Order order);

    Order getOrder(Long id);


}
