package kovalskiy91.selfstudy.microservices.order.domain.usecase;

import kovalskiy91.selfstudy.microservices.order.domain.model.Order;
import kovalskiy91.selfstudy.microservices.order.domain.ports.api.OrderPlacedEvent;
import kovalskiy91.selfstudy.microservices.order.domain.ports.api.PlaceOrderAPI;
import kovalskiy91.selfstudy.microservices.order.domain.ports.api.PlaceOrderCommand;
import kovalskiy91.selfstudy.microservices.order.domain.ports.spi.OrderSPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlaceOrderUseCase implements PlaceOrderAPI {
    private final OrderSPI orderSPI;

    @Override
    public OrderPlacedEvent placeOrder(PlaceOrderCommand command) {
        Order order = command.getOrder();
        orderSPI.save(order);
        return new OrderPlacedEvent(order);
    }
}
