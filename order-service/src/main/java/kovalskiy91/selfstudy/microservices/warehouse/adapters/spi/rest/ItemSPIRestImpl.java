package kovalskiy91.selfstudy.microservices.warehouse.adapters.spi.rest;

import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMap;
import io.opentracing.tag.Tags;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.spi.BookItemCommand;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.spi.ItemBookedEvent;
import kovalskiy91.selfstudy.microservices.warehouse.domain.ports.spi.ItemSPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ItemSPIRestImpl implements ItemSPI {
    private final Tracer tracer;
    private final RestTemplate restTemplate;

    @Override
    public ItemBookedEvent bookItem(BookItemCommand command) {
        HttpHeaders headers = new HttpHeaders();
        injectActiveSpan(headers);
        return restTemplate.exchange(
                "http://localhost:8081/item/" + command.getItem().getItemId(),
                HttpMethod.PUT,
                new HttpEntity<>(command, headers),
                ItemBookedEvent.class,
                new HashMap<>()
        ).getBody();
    }

    private void injectActiveSpan(HttpHeaders headers) {
        Tags.SPAN_KIND.set(tracer.activeSpan(), Tags.SPAN_KIND_CLIENT);
        tracer.inject(tracer.activeSpan().context(), Format.Builtin.HTTP_HEADERS, new TextMap() {
            @Override
            public Iterator<Map.Entry<String, String>> iterator() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void put(String key, String value) {
                headers.put(key, Collections.singletonList(value));
            }
        });
    }
}
