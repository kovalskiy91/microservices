package kovalskiy91.selfstudy.microservices.order.domain.usecase;

import kovalskiy91.selfstudy.microservices.order.domain.model.Order;
import kovalskiy91.selfstudy.microservices.order.domain.ports.api.OrderPlacedEvent;
import kovalskiy91.selfstudy.microservices.order.domain.ports.api.PlaceOrderAPI;
import kovalskiy91.selfstudy.microservices.order.domain.ports.api.PlaceOrderCommand;
import kovalskiy91.selfstudy.microservices.order.domain.ports.spi.OrderSPI;
import kovalskiy91.selfstudy.microservices.warehouse.domain.model.Item;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.spi.BookItemCommand;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.spi.ItemSPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlaceOrderUseCase implements PlaceOrderAPI {
    private final OrderSPI orderSPI;
    private final ItemSPI itemSPI;

    @Override
    public OrderPlacedEvent placeOrder(PlaceOrderCommand command) {
        Order order = command.getOrder();
        bookItem(order.getItemId());
        orderSPI.save(order);
        return new OrderPlacedEvent(order);
    }

    private void bookItem(UUID itemId) {
        BookItemCommand bookItemCommand = new BookItemCommand();
        Item item = new Item();
        item.setItemId(itemId);
        bookItemCommand.setItem(item);
        itemSPI.bookItem(bookItemCommand);
    }
}
