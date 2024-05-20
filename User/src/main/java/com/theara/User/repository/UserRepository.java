package com.theara.User.repository;

import com.theara.User.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserId(Integer id);
    @Query(value = "SELECT * FROM user WHERE is_delete = false", nativeQuery = true)
    Iterable<User> findUserIsDeleteFalse();

}
