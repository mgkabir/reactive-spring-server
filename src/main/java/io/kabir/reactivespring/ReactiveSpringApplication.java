package io.kabir.reactivespring;

import io.kabir.reactivespring.data.Movie;
import io.kabir.reactivespring.data.MovieRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveSpringApplication.class, args);
    }

}

@Component
class SampleDataInitializer implements ApplicationRunner {


    private MovieRepository movieRepository;

    public SampleDataInitializer(MovieRepository mRepo) {
        this.movieRepository = mRepo;

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Flux<Movie> movieFlux = Flux
                .just("Contact", "Predator", "Forest Gump", "Inception", "Inter-Steller",
                        "Journey to the Center of the Earth", "Terminator", "Rambo", "Home Alone",
                        "A Beautiful Mind", "Martian")
                .map(m -> new Movie(m))
                .flatMap(movieRepository::save);

        movieRepository.deleteAll()
                .thenMany(movieFlux)
                .thenMany(movieRepository.findAll())
                .subscribe(System.out::println);
    }

}
