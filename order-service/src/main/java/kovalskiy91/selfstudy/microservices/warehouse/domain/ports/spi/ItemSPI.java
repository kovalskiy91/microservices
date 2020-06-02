package kovalskiy91.selfstudy.microservices.warehouse.domain.ports.spi;

public interface ItemSPI {

    ItemBookedEvent bookItem(BookItemCommand command);

}
