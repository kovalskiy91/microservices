package kovalskiy91.selfstudy.microservices.warehouse.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Item {
    private UUID itemId;
}
