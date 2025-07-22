package config;

import config.RateLimitFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder,
                               RateLimitFilter rateLimitFilter) {
        return builder.routes()
                .route("user-service",
                        r -> r.path("/api/user/**")
                                .filters(f -> f.filter(rateLimitFilter))
                                .uri("lb://user-service"))
                .route("wallet-service",
                        r -> r.path("/api/wallet/**")
                                .filters(f -> f.filter(rateLimitFilter))
                                .uri("lb://wallet-service"))
                .route("merchant-service",
                        r -> r.path("/api/merchant/**")
                                .filters(f -> f.filter(rateLimitFilter))
                                .uri("lb://merchant-service"))
                .route("payment-gateway-service",
                        r -> r.path("/api/pay/**")
                                .filters(f -> f.filter(rateLimitFilter))
                                .uri("lb://payment-gateway-service"))
                .build();
    }
}