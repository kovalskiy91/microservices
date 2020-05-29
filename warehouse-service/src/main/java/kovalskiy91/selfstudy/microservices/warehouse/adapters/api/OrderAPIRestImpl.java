package kovalskiy91.selfstudy.microservices.warehouse.adapters.api;

import io.opentracing.Span;
import io.opentracing.Tracer;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderAPIRestImpl {
    private final Tracer tracer;
    private final AddItemAPI addItemAPI;
    private final BookItemAPI bookItemAPI;

    @PostMapping(path = "/item")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ItemAddedEvent addItem(@RequestBody AddItemCommand command) {
        Span span = tracer.buildSpan("add-item").start();
        ItemAddedEvent event = addItemAPI.addItem(command);
        span.finish();
        return event;
    }

    @PutMapping(path = "/item/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ItemBookedEvent bookItem(@RequestBody BookItemCommand command) {
        Span span = tracer.buildSpan("book-item").start();
        ItemBookedEvent event = bookItemAPI.bookItem(command);
        span.finish();
        return event;
    }
}
