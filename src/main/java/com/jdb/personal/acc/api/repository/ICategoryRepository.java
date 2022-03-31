package com.jdb.personal.acc.api.repository;

import com.jdb.personal.acc.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByUser_Id(Long userId);

    Category findByUser_IdAndId(Long userId, Long id);
}