package io.kabir.reactivespring.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Movie {
    private String id;
    @NonNull
    private String title;

    public Movie(@NonNull String title) {
        this.title = title;
    }
}
