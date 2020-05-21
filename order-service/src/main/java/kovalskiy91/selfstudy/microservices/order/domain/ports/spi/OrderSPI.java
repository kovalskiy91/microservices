package kovalskiy91.selfstudy.microservices.order.domain.ports.spi;

import kovalskiy91.selfstudy.microservices.order.domain.model.Order;

import java.util.UUID;

public interface OrderSPI {

    UUID save(Order order);

}
