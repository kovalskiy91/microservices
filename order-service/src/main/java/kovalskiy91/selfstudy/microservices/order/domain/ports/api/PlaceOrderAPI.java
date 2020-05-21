package kovalskiy91.selfstudy.microservices.order.domain.ports.api;

public interface PlaceOrderAPI {

    OrderPlacedEvent placeOrder(PlaceOrderCommand command);

}
