package kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api;

import kovalskiy91.selfstudy.microservices.warehouse.domain.model.Item;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookItemCommand {
    private Item item;
}
