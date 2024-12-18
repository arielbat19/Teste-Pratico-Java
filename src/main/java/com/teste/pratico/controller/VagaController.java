package com.teste.pratico.controller;

import com.teste.pratico.models.request.CriaNovaVagaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/vaga")
public interface VagaController {

    @PostMapping
    ResponseEntity<Void> save(@RequestBody final CriaNovaVagaRequest criaNovaVagaRequest);
}
