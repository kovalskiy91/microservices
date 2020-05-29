package kovalskiy91.selfstudy.microservices.warehouse.adapters.spi.inmemory;

import kovalskiy91.selfstudy.microservices.warehouse.domain.model.Item;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.spi.ItemSPI;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ItemSPIInMemoryImpl implements ItemSPI {
    private final Map<UUID, Item> items = new ConcurrentHashMap<>();

    @Override
    public UUID save(Item item) {
        UUID id = item.getItemId();
        if (id == null) {
            id = UUID.randomUUID();
            item.setItemId(id);
        }
        items.put(id, item);
        return id;
    }

    @Override
    public Optional<Item> findById(UUID id) {
        return Optional.ofNullable(items.get(id));
    }
}
