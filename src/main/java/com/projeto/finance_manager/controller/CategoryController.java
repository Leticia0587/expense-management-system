package com.projeto.finance_manager.controller;

import com.projeto.finance_manager.model.Category;
import com.projeto.finance_manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller que gerencia as operações de categoria (CRUD).
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    // Retorna todas as categorias
    @GetMapping
    public List<Category> listAll() {
        return service.listAll();
    }

    // Retorna uma categoria pelo ID
    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // Cria uma nova categoria
    @PostMapping
    public Category create(@RequestBody Category category) {
        return service.create(category);
    }

    // Atualiza uma categoria existente
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        return service.update(id, category);
    }

    // Deleta uma categoria pelo ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}