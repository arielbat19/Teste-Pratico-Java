package com.teste.pratico.controller;

import com.teste.pratico.models.request.CriarNovoAgendamentoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/agendamentos")
public interface AgendamentoController {

    @PostMapping
    ResponseEntity<Void> save(@RequestBody final CriarNovoAgendamentoRequest criarNovoAgendamentoRequest);
}
