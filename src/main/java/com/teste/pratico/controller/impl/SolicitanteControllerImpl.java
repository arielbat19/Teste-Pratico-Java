package com.teste.pratico.controller.impl;

import com.teste.pratico.controller.SolicitanteController;
import com.teste.pratico.models.request.CriarNovoSolicitanteRequest;
import com.teste.pratico.service.SolicitanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class SolicitanteControllerImpl implements SolicitanteController {

    private final SolicitanteService solicitanteService;

    @Override
    public ResponseEntity<Void> save(final CriarNovoSolicitanteRequest criarNovoSolicitanteRequest) {
        solicitanteService.save(criarNovoSolicitanteRequest);
        return ResponseEntity.status(CREATED.value()).build();
    }
}
