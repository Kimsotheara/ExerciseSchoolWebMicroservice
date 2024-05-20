package com.theara.Promotion.repository;

import com.theara.Promotion.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion,Integer> {
    Optional<Promotion> findByPromId(Integer id);
    @Query(value = "SELECT * FROM promotion WHERE is_delete = false", nativeQuery = true)
    Iterable<Promotion> findPromotionIsDeleteFalse();

}
