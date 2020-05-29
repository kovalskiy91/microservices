package kovalskiy91.selfstudy.microservices.order.configuration;

import io.opentracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.jaegertracing.Configuration.JAEGER_SERVICE_NAME;

@Configuration
public class ObservabilityConfig {

    @Bean
    Tracer tracer() {
        System.setProperty(JAEGER_SERVICE_NAME, "order-service");
        Tracer tracer = io.jaegertracing.Configuration.fromEnv().getTracer();
        return tracer;
    }

}
