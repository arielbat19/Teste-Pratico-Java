package com.teste.pratico.controller.impl;

import com.teste.pratico.controller.AgendamentoController;
import com.teste.pratico.models.request.CriarNovoAgendamentoRequest;
import com.teste.pratico.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class AgendamentoControllerImpl implements AgendamentoController {

    private final AgendamentoService agendamentoService;

    @Override
    public ResponseEntity<Void> save(final CriarNovoAgendamentoRequest criarNovoAgendamentoRequest) {
        agendamentoService.save(criarNovoAgendamentoRequest);
        return ResponseEntity.status(CREATED.value()).build();
    }
}
