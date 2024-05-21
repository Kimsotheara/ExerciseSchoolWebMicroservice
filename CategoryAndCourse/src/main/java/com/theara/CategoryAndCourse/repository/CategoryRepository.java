package com.theara.CategoryAndCourse.repository;

import com.theara.CategoryAndCourse.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByCateId(Integer id);
    @Query(value = "SELECT * FROM category WHERE is_delete=false",nativeQuery = true)
    Iterable<Category> findAllByIsDelete();
}
