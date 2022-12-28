package com.home.springshope.Service;


import com.home.springshope.Config.IntegrationOrderConfig;
import com.home.springshope.Dto.IntegrationOrderDto;
import com.home.springshope.Model.Order;
import com.home.springshope.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final IntegrationOrderConfig config;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, IntegrationOrderConfig config) {
        this.orderRepository = orderRepository;
        this.config = config;
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {

        Order saveOrder = orderRepository.save(order);
        sendIntegrationNotify(saveOrder);

    }

    private void sendIntegrationNotify(Order order) {
        IntegrationOrderDto integrDto = new IntegrationOrderDto();

        integrDto.setOrderId(order.getId());
        integrDto.setUsername(order.getUser().getName());
        integrDto.setAddress(order.getAddress());


        List<IntegrationOrderDto.OrderDetailsDto> detailsDto = order.getOrderDetailsList().stream()
                .map(d -> new IntegrationOrderDto.OrderDetailsDto(d)).collect(Collectors.toList());


        integrDto.setDetails(detailsDto);

        Message<IntegrationOrderDto> message = MessageBuilder.withPayload(integrDto)
                .setHeader("Content-type","application/json")
                .build();
        config.getOrdersChannel().send(message);


    }


    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
