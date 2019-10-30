package com.example.demo.repositories;

import com.example.demo.models.Category;
import com.example.demo.models.Movie;
import com.example.demo.models.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByCategoryAndMovieType(Category categoryId, MovieType type);
    Optional<Movie> findByMovieNameAndReleaseYear(String movieName, int year);

}
