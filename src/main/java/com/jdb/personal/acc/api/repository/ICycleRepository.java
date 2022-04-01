package com.jdb.personal.acc.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jdb.personal.acc.api.entity.Cycle;

@Repository
public interface ICycleRepository extends CrudRepository<Cycle, Long> {

}
