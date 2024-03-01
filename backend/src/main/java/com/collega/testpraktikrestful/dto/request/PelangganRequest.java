package com.collega.testpraktikrestful.dto.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PelangganRequest {
    private String nama;
    private String alamat;
    private String jenis_kelamin;
    private int umur;
    private String pekerjaan;
    private int penghasilan;
    private String hobby;
}
