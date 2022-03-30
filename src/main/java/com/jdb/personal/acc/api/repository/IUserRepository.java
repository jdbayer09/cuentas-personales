package com.jdb.personal.acc.api.repository;

import com.jdb.personal.acc.api.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);
}
