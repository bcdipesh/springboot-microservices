package com.dipeshbc.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseTraceFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (serverWebExchange, gatewayFilterChain) ->
                gatewayFilterChain.filter(serverWebExchange).then(Mono.fromRunnable(() -> {
                    HttpHeaders requestHeaders = serverWebExchange.getRequest().getHeaders();
                    String correlationId = filterUtility.getCorrelationId(requestHeaders);

                    if (!(serverWebExchange.getResponse().getHeaders().containsKey(FilterUtility.CORRELATION_ID))) {
                        logger.debug("Updated the correlation id to the outbound headers: {}", correlationId);
                        serverWebExchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
                    }
                }));
    }
}
