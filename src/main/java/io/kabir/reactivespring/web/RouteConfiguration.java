package io.kabir.reactivespring.web;

import io.kabir.reactivespring.data.Movie;
import io.kabir.reactivespring.data.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class RouteConfiguration {


    @Bean
    RouterFunction<?> routes(MovieService service) {
        return RouterFunctions
                .route(GET("/movies"),
                        serverRequest -> ServerResponse
                                .ok()
                                .body(service.all(), Movie.class))
                .andRoute(GET("/movies/{id}"),
                        serverRequest -> ServerResponse
                                .ok()
                                .body(service.byId(serverRequest.pathVariable("id")), Movie.class))
                .andRoute(GET("/movie-stream"),
                        serverRequest -> ServerResponse
                                .ok()
                                .contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(service.allWithDelay(), Movie.class));
    }

}
