package com.ecommerce.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

	List<Category> findByIsactiveTrue();
}
