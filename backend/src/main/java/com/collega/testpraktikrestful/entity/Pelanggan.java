package com.collega.testpraktikrestful.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "m_pelanggan")
public class Pelanggan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nama;

    private String alamat;

    private String jenis_kelamin;

    private int umur;

    private String pekerjaan;

    private int penghasilan;

    private String hobby;
}
