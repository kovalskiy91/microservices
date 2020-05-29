package kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api;

public interface BookItemAPI {

    ItemBookedEvent bookItem(BookItemCommand command);

}
