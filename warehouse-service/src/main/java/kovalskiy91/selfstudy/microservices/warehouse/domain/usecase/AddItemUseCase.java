package kovalskiy91.selfstudy.microservices.warehouse.domain.usecase;

import kovalskiy91.selfstudy.microservices.warehouse.domain.model.Item;
import kovalskiy91.selfstudy.microservices.warehouse.domain.model.ItemStatus;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api.AddItemAPI;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api.AddItemCommand;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api.ItemAddedEvent;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.spi.ItemSPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddItemUseCase implements AddItemAPI {
    private final ItemSPI itemSPI;

    @Override
    public ItemAddedEvent addItem(AddItemCommand command) {
        Item item = command.getItem();
        UUID itemId = item.getItemId();
        itemSPI.findById(itemId)
                .ifPresent(existingItem -> {
                    throw new IllegalArgumentException("Item with id: " + itemId + " already exists");
                });
        item.setStatus(ItemStatus.AVAILABLE);
        itemSPI.save(item);
        return new ItemAddedEvent(item);
    }
}
