package com.jdb.personal.acc.api.service;

import com.jdb.personal.acc.api.entity.Deuda;

import java.util.List;

public interface IDeudaService {

    List<Deuda> fetchNoPagadasByUserId(Long userId, boolean oculto);

    List<Deuda> fetchPagadasByUserId(Long userId, boolean oculto);

    List<Deuda> fetchAllByUserId(Long userId, boolean oculto);
}
