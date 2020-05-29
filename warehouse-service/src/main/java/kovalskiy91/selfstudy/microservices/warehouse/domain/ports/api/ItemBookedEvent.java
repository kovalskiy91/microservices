package kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api;

import kovalskiy91.selfstudy.microservices.warehouse.domain.model.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class ItemBookedEvent {
    private final Item item;
}
