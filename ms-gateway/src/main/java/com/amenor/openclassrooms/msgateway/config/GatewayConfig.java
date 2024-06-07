package com.amenor.openclassrooms.msgateway.config;

import com.amenor.openclassrooms.msgateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthenticationFilter authenticationFilter;
    @Value("${patients.prefix.path}")
    private String patientsPrefix;
    @Value("${patientNotes.prefix.path}")
    private String patientNotesPrefix;

    @Autowired
    public GatewayConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ms-patients", r -> r.path("/patients/**")
                        .filters(f -> f.filter(authenticationFilter)
                                .prefixPath(patientsPrefix))
                        .uri("lb://MS-PATIENTS"))
                .route("ms-patientNotes", r -> r.path("/patientNotes/**")
                        .filters(f -> f.filter(authenticationFilter)
                                .prefixPath(patientNotesPrefix))
                        .uri("lb://MS-PATIENTNOTES"))
                .route("ms-authserv", r -> r.path("/auth/**")
                        .filters(f -> f.prefixPath("/api-auth/v1"))
                        .uri("lb://MS-AUTHSERV"))
                .route("ms-frontend", r -> r.path("/ms-frontend/**")
                        .uri("lb://MS-FRONTEND"))
                .build();
    }
}
