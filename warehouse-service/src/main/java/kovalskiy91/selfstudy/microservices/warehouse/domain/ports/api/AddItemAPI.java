package kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api;

public interface AddItemAPI {

    ItemAddedEvent addItem(AddItemCommand command);

}
