package com.amenor.openclassrooms.msgateway.filter;

import com.amenor.openclassrooms.msgateway.config.GatewayConfig;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    @Value("${auth.prefix.path}")
    private String authPrefixPath;

    private static final Logger logger = Logger.getLogger(GatewayConfig.class.getName());


    private final WebClient.Builder webClientBuilder;

    @Autowired
    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        request.getHeaders().forEach((name, values) -> values.forEach(value -> logger.info(name + ": " + value)));

        if (this.isAuthMissing(request))
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

        final String token = this.getAuthHeader(request)/*.substring(7)*/;

        return this.isValidated(token)
                .flatMap(isValid -> isValid ? chain.filter(exchange) : this.onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED));
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).getFirst();
    }

    private Mono<Boolean> isValidated(String token) {
        String uri = "lb://MS-AUTHSERV" + authPrefixPath + "/auth/validate/" + token;
        logger.info("Validating token with URI: " + uri);

        return webClientBuilder.build().get().uri(uri)
                .retrieve()
                .bodyToMono(Boolean.class)
                .doOnNext(isValid -> logger.info("Token validation result: " + isValid))
                .onErrorResume(e -> {
                    logger.severe("Error validating token: " + e.getMessage());
                    return Mono.just(false);
                });
    }
}
