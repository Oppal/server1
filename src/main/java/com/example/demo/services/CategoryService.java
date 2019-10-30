package com.example.demo.services;

import com.example.demo.models.Category;
import com.example.demo.models.Movie;
import com.example.demo.repositories.CategoryRepository;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryRepository categoryRepository = null;

    List<Category> findAll();

    public Category create(Category Category);

    Category findByID(Long id) throws NotFoundException;

    Optional<Movie> findMovieinCategories(Long id);

    Optional<Movie> findMovieinCategories(Long id, String type);

    Optional<Category> findByCategoryName(String name);

    public Category update(Long id, Category Category) throws NotFoundException;

    Optional<Category> findCategoryIds(List<Category> category);

    public void delete(Long id);

}