package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //find all
    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    //find by id
    @GetMapping(value = "{id}")
    Category findById(@PathVariable Long id) throws NotFoundException {
        return categoryService.findByID(id);
    }

    //create
    @PostMapping
    Category create(@Valid @RequestBody Category category){
        return categoryService.create(category);
    }

    //delete
    @DeleteMapping(value = "{id}")
    void delete(@PathVariable Long id){
        categoryService.delete(id);
    }

    //update
    @PatchMapping(value = "{id}")
    Category update(@PathVariable Long id, @RequestBody Category category) throws NotFoundException {
        return categoryService.update(id, category);
    }
}