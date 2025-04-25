package com.projeto.finance_manager.repository;

import com.projeto.finance_manager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//  Repositório JPA para a entidade Category. Permite operações CRUD automáticas.
public interface CategoryRepository extends JpaRepository<Category, Long> {
}