package com.teste.pratico.controller;

import com.teste.pratico.models.request.ConsultaAgendamentosRequest;
import com.teste.pratico.models.response.ConsultaAgendamentoResponse;
import com.teste.pratico.models.response.ConsultaTotalAgendamentosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/consulta_agendamentos")
public interface ConsultaAgendamentoController {


    @GetMapping
    ResponseEntity<List<ConsultaAgendamentoResponse>> getAgendamentos(@RequestBody final ConsultaAgendamentosRequest consultaAgendamentoRequest);

    @GetMapping("/total-por-solicitante")
    ResponseEntity<List<ConsultaTotalAgendamentosResponse>> consultarTotalAgendamentos(@RequestBody final ConsultaAgendamentosRequest consultaAgendamentoRequest);
}
