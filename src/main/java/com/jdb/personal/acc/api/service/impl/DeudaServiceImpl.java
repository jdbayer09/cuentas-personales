package com.jdb.personal.acc.api.service.impl;

import com.jdb.personal.acc.api.entity.Deuda;
import com.jdb.personal.acc.api.repository.IDeudaRepository;
import com.jdb.personal.acc.api.service.IDeudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeudaServiceImpl implements IDeudaService {

    @Autowired
    private IDeudaRepository deudaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Deuda> fetchNoPagadasByUserId(Long userId) {
        List<Deuda> data = deudaRepository.fetchAllByUserId(userId);
        if (data == null || data.isEmpty()) return data;

        List<Deuda> resp = new ArrayList<>();

        for (Deuda deuda: data) {
            if (!deuda.getPagada())
                resp.add(deuda);
        }

        return resp;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Deuda> fetchPagadasByUserId(Long userId) {
        List<Deuda> data = deudaRepository.fetchAllByUserId(userId);
        if (data == null || data.isEmpty()) return data;

        List<Deuda> resp = new ArrayList<>();

        for (Deuda deuda: data) {
            if (deuda.getPagada())
                resp.add(deuda);
        }

        return resp;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Deuda> fetchAllByUserId(Long userId) {
        return deudaRepository.fetchAllByUserId(userId);
    }
}
