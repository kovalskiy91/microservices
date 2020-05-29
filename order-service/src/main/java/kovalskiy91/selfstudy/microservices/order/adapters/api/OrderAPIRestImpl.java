package kovalskiy91.selfstudy.microservices.order.adapters.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.opentracing.Span;
import io.opentracing.Tracer;
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
    private final Tracer tracer;
    private final PlaceOrderAPI placeOrderAPI;

    @PostMapping(path = "/order")
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderPlacedEvent placeOrder(@RequestBody PlaceOrderCommand command) throws JsonProcessingException {
        Span placeOrder = tracer.buildSpan("placeOrder")
                .start()
                .setBaggageItem("command", new ObjectMapper().writeValueAsString(command));
        OrderPlacedEvent orderPlacedEvent = placeOrderAPI.placeOrder(command);
        placeOrder.finish();
        return orderPlacedEvent;

    }
}
