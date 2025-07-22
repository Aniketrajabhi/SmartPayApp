package config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public abstract class RateLimitFilter
        extends AbstractGatewayFilterFactory<RateLimitFilter.Config> implements GatewayFilter {

    public static class Config {}

    private final ReactiveRedisTemplate<String, String> redis;

    public RateLimitFilter(ReactiveRedisTemplate<String, String> redis) {
        super(Config.class);
        this.redis = redis;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String key = exchange.getRequest().getRemoteAddress()
                    .getAddress()
                    .getHostAddress();
            String redisKey = "rate:" + key;

            return redis.opsForValue()
                    .increment(redisKey)
                    .flatMap(count -> {
                        if (count == 1) {
                            return redis.expire(redisKey, Duration.ofSeconds(1))
                                    .thenReturn(count);
                        }
                        return Mono.just(count);
                    })
                    .flatMap(count -> {
                        if (count > 10) {
                            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                            return exchange.getResponse().setComplete();
                        }
                        return chain.filter(exchange);
                    });
        };
    }
}