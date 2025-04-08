package com.projeto.finance_manager.repository;

import com.projeto.finance_manager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}