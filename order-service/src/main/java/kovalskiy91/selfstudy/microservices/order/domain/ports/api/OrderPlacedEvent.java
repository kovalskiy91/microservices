package kovalskiy91.selfstudy.microservices.order.domain.ports.api;

import kovalskiy91.selfstudy.microservices.order.domain.model.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class OrderPlacedEvent {
    private final Order order;
}
