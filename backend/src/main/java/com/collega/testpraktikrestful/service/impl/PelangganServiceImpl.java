package com.collega.testpraktikrestful.service.impl;

import com.collega.testpraktikrestful.dto.request.PelangganRequest;
import com.collega.testpraktikrestful.entity.Pelanggan;
import com.collega.testpraktikrestful.repository.PelangganRepository;
import com.collega.testpraktikrestful.service.PelangganService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PelangganServiceImpl implements PelangganService {
    private final PelangganRepository pelangganRepository;
    @Override
    public List<Pelanggan> getAll() {
        return pelangganRepository.findAll();
    }

    @Override
    public Pelanggan getById(String id) {
        Optional<Pelanggan> pelangganOptional = pelangganRepository.findById(id);

        if (pelangganOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pelanggan Not Found");

        return pelangganOptional.get();
    }

    @Override
    public Pelanggan create(PelangganRequest request) {

        Pelanggan pelanggan = Pelanggan.builder()
                .nama(request.getNama())
                .alamat(request.getAlamat())
                .jenis_kelamin(request.getJenis_kelamin())
                .umur(request.getUmur())
                .pekerjaan(request.getPekerjaan())
                .penghasilan(request.getPenghasilan())
                .hobby(request.getHobby())
                .build();


        Pelanggan pelangganSave = pelangganRepository.saveAndFlush(pelanggan);

        return pelangganSave;
    }

    @Override
    public Pelanggan update(Pelanggan request) {

        Pelanggan pelanggan = getById(request.getId());

        pelanggan.setNama(request.getNama());
        pelanggan.setAlamat(request.getAlamat());
        pelanggan.setJenis_kelamin(request.getJenis_kelamin());
        pelanggan.setUmur(request.getUmur());
        pelanggan.setPekerjaan(request.getPekerjaan());
        pelanggan.setHobby(request.getHobby());
        pelanggan.setPenghasilan(request.getPenghasilan());

        Pelanggan pelangganUpdate = pelangganRepository.saveAndFlush(pelanggan);
        return pelangganUpdate;
    }

    @Override
    public void delete(String id) {

        Pelanggan pelanggan = getById(id);

        pelangganRepository.delete(pelanggan);

    }
}
