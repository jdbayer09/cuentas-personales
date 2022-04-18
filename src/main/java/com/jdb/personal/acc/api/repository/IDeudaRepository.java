package com.jdb.personal.acc.api.repository;

import com.jdb.personal.acc.api.entity.Deuda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeudaRepository extends JpaRepository<Deuda, Long> {

    @Query("SELECT d FROM Deuda d WHERE d.category.user.id = ?1 ORDER BY d.created DESC")
    List<Deuda> fetchAllByUserId(Long userId);
}