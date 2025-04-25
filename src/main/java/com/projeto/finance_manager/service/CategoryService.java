package com.projeto.finance_manager.service;

import com.projeto.finance_manager.model.Category;
import com.projeto.finance_manager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Serviço responsável pela lógica de negócio da entidade Category.
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> listAll() {
        return repository.findAll();
    }

    public Category create(Category category) {
        return repository.save(category);
    }

    public Category update(Long id, Category updatedCategory) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        category.setName(updatedCategory.getName());
        return repository.save(category);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }
}