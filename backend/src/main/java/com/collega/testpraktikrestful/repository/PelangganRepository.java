package com.collega.testpraktikrestful.repository;

import com.collega.testpraktikrestful.entity.Pelanggan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PelangganRepository extends JpaRepository<Pelanggan, String> {
}
