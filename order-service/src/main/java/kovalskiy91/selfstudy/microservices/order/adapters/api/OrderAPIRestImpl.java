package kovalskiy91.selfstudy.microservices.order.adapters.api;

import kovalskiy91.selfstudy.microservices.order.domain.ports.api.OrderPlacedEvent;
import kovalskiy91.selfstudy.microservices.order.domain.ports.api.PlaceOrderAPI;
import kovalskiy91.selfstudy.microservices.order.domain.ports.api.PlaceOrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderAPIRestImpl {
    private final PlaceOrderAPI placeOrderAPI;

    @PostMapping(path = "/order")
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderPlacedEvent placeOrder(@RequestBody PlaceOrderCommand command) {
        return placeOrderAPI.placeOrder(command);
    }
}
