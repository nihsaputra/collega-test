package com.collega.testpraktikrestful.controller;

import com.collega.testpraktikrestful.dto.request.PelangganRequest;
import com.collega.testpraktikrestful.dto.response.WebResponse;
import com.collega.testpraktikrestful.entity.Pelanggan;
import com.collega.testpraktikrestful.service.PelangganService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class PelangganController {
    private final PelangganService pelangganService;

    @GetMapping(path = "/pelanggan")
    public ResponseEntity<?> getAll(){
        List<Pelanggan> pelanggans = pelangganService.getAll();

        WebResponse<List<Pelanggan>> response = WebResponse.<List<Pelanggan>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully")
                .data(pelanggans)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/pelanggan/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Pelanggan pelanggan = pelangganService.getById(id);

        WebResponse<Pelanggan> response = WebResponse.<Pelanggan>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfully")
                .data(pelanggan)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "/pelanggan")
    public ResponseEntity<?> create(@RequestBody PelangganRequest request){

        if (request.getUmur() < 0 ) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "umur tidak boleh minus");
        if (request.getPenghasilan() < 0 ) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "penghasilan tidak boleh minus");


        Pelanggan pelanggan = pelangganService.create(request);

        WebResponse<Pelanggan> response = WebResponse.<Pelanggan>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("creaate new pelanggan successfully")
                .data(pelanggan)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(path = "/pelanggan")
    public ResponseEntity<?> update(@RequestBody Pelanggan request){

        if (request.getUmur() < 0 ) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "umur tidak boleh minus");
        if (request.getPenghasilan() < 0 ) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "penghasilan tidak boleh minus");

        Pelanggan pelanggan = pelangganService.update(request);

        WebResponse<Pelanggan> response = WebResponse.<Pelanggan>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("update pelanggan successfully")
                .data(pelanggan)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/pelanggan/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        pelangganService.delete(id);

        WebResponse<Pelanggan> response = WebResponse.<Pelanggan>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("delete pelanggan successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
