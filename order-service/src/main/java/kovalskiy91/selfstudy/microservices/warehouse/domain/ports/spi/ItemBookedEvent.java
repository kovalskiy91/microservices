package kovalskiy91.selfstudy.microservices.warehouse.domain.ports.spi;

import kovalskiy91.selfstudy.microservices.warehouse.domain.model.Item;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemBookedEvent {
    private Item item;
}
