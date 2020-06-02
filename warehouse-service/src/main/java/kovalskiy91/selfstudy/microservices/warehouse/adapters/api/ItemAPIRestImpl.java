package kovalskiy91.selfstudy.microservices.warehouse.adapters.api;

import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMapAdapter;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ItemAPIRestImpl {
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
    public ItemBookedEvent bookItem(@RequestBody BookItemCommand command, @RequestHeader HttpHeaders httpHeaders) {
        Map<String, String> headers = new HashMap<String, String>();
        for (String key : httpHeaders.keySet()) {
            headers.put(key, httpHeaders.get(key).get(0));
        }
        SpanContext parentSpan = tracer.extract(Format.Builtin.HTTP_HEADERS, new TextMapAdapter(headers));

        Span span = tracer.buildSpan("book-item").asChildOf(parentSpan).start();
        ItemBookedEvent event = bookItemAPI.bookItem(command);
        span.finish();
        return event;
    }
}
