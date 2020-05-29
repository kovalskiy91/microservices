package kovalskiy91.selfstudy.microservices.warehouse.adapters.api;

import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderAPIRestImpl {
    private final AddItemAPI addItemAPI;
    private final BookItemAPI bookItemAPI;

    @PostMapping(path = "/item")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ItemAddedEvent addItem(@RequestBody AddItemCommand command) {
        return addItemAPI.addItem(command);
    }

    @PutMapping(path = "/item/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ItemBookedEvent bookItem(@RequestBody BookItemCommand command) {
        return bookItemAPI.bookItem(command);
    }
}
