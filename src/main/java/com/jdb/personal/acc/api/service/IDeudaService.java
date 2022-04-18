package com.jdb.personal.acc.api.service;

import com.jdb.personal.acc.api.entity.Deuda;

import java.util.List;

public interface IDeudaService {

    List<Deuda> fetchNoPagadasByUserId(Long userId);

    List<Deuda> fetchPagadasByUserId(Long userId);

    List<Deuda> fetchAllByUserId(Long userId);
}
