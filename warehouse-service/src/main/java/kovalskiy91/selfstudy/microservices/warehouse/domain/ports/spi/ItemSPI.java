package kovalskiy91.selfstudy.microservices.warehouse.domain.ports.spi;

import kovalskiy91.selfstudy.microservices.warehouse.domain.model.Item;

import java.util.Optional;
import java.util.UUID;

public interface ItemSPI {

    UUID save(Item item);

    Optional<Item> findById(UUID id);

}
