package com.jdb.personal.acc.api.repository;

import com.jdb.personal.acc.api.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findUserByEmail(String email);

    @Query("SELECT u FROM AppUser u WHERE u.id = ?1")
    AppUser fetchById(Long id);
}
