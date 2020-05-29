package kovalskiy91.selfstudy.microservices.warehouse.domain.usecase;

import kovalskiy91.selfstudy.microservices.warehouse.domain.model.Item;
import kovalskiy91.selfstudy.microservices.warehouse.domain.model.ItemStatus;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api.BookItemAPI;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api.BookItemCommand;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api.ItemBookedEvent;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.spi.ItemSPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookItemUseCase implements BookItemAPI {
    private final ItemSPI itemSPI;

    @Override
    public ItemBookedEvent bookItem(BookItemCommand command) {
        UUID itemId = command.getItem().getItemId();
        Item item = itemSPI.findById(itemId)
                .filter(existingItem -> existingItem.getStatus() == ItemStatus.AVAILABLE)
                .orElseThrow(() -> new IllegalArgumentException("There is no available item with id: " + itemId));
        item.setStatus(ItemStatus.BOOKED);
        itemSPI.save(item);
        return new ItemBookedEvent(item);
    }
}
