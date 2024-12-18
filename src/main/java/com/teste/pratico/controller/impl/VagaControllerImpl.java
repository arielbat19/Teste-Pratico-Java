package com.teste.pratico.controller.impl;

import com.teste.pratico.controller.VagaController;
import com.teste.pratico.models.request.CriaNovaVagaRequest;
import com.teste.pratico.service.VagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class VagaControllerImpl implements VagaController {

    private final VagaService vagaService;
    @Override
    public ResponseEntity<Void> save(final CriaNovaVagaRequest criaNovaVagaRequest) {
        vagaService.save(criaNovaVagaRequest);
        return ResponseEntity.status(CREATED.value()).build();
    }
}
