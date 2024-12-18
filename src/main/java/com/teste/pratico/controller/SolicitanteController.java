package com.teste.pratico.controller;

import com.teste.pratico.models.request.CriarNovoSolicitanteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/solicitante")
public interface SolicitanteController {

    @PostMapping
    ResponseEntity<Void> save(@RequestBody final CriarNovoSolicitanteRequest criarNovoSolicitanteRequest);
}
