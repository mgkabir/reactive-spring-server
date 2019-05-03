package io.kabir.reactivespring.data;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Mono<Movie> byId(String movieId) {
        return this.movieRepository.findById(movieId);
    }

    public Flux<Movie> all() {
        return this.movieRepository.findAll();
    }

    public Flux<Movie> allWithDelay(){
        return this
                .movieRepository
                .findAll()
                .delayElements(Duration.ofSeconds(1));
    }
}
