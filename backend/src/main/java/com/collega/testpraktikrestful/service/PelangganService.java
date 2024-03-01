package com.collega.testpraktikrestful.service;

import com.collega.testpraktikrestful.dto.request.PelangganRequest;
import com.collega.testpraktikrestful.entity.Pelanggan;

import java.util.List;

public interface PelangganService {
    List<Pelanggan> getAll();
    Pelanggan getById(String id);
    Pelanggan create(PelangganRequest request);
    Pelanggan update(Pelanggan request);
    void delete(String id);
}
