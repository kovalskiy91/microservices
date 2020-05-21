package kovalskiy91.selfstudy.microservices.order.domain.ports.api;

import kovalskiy91.selfstudy.microservices.order.domain.model.Order;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaceOrderCommand {
    private Order order;
}
