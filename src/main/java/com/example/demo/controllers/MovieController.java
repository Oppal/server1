package com.example.demo.controllers;

import com.example.demo.models.Movie;
import com.example.demo.models.MovieType;
import com.example.demo.services.CategoryService;
import com.example.demo.services.MovieService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "movies")
public class MovieController {
    private final MovieService movieService;
    private final CategoryService categoryService;

    public MovieController(MovieService movieService, CategoryService categoryService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping(value = "{id}")
    Movie findById(@PathVariable Long id) throws NotFoundException {
        return movieService.findByID(id);
    }

    @GetMapping(value = "category/{id}")
    List<Movie> findByCategoryId(@PathVariable Long id, @RequestParam(value = "type")MovieType type){
        return movieService.findByCategoryAndType(id, type);
    }




}