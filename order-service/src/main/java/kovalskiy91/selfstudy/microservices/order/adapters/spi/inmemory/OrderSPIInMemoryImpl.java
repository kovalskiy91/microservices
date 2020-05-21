package kovalskiy91.selfstudy.microservices.order.adapters.spi.inmemory;

import kovalskiy91.selfstudy.microservices.order.domain.model.Order;
import kovalskiy91.selfstudy.microservices.order.domain.ports.spi.OrderSPI;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderSPIInMemoryImpl implements OrderSPI {
    private final Map<UUID, Order> orders = new ConcurrentHashMap<>();

    @Override
    public UUID save(Order order) {
        UUID id = UUID.randomUUID();
        order.setOrderId(id);
        orders.put(id, order);
        return id;
    }
}
