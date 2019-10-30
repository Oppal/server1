package com.example.demo.services;

import com.example.demo.models.Movie;
import com.example.demo.models.MovieType;
import com.example.demo.repositories.MovieRepository;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    MovieRepository movieRepository = null;

    List<Movie> findAll();

    public Movie create(Movie Movie);

    Movie findByID(Long id) throws NotFoundException;

    List<Movie> findByCategoryAndType(Long id, MovieType type);

    public Movie update(Long id, Long userid, Movie Movie) throws NotFoundException;

    public String delete(Long id, Long userid) throws NotFoundException;

    Optional<Movie> findByMovieNameAndReleaseYear(String name, int year);

}