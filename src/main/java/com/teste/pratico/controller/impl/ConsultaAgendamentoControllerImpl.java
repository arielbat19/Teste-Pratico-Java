package com.teste.pratico.controller.impl;

import com.teste.pratico.controller.ConsultaAgendamentoController;
import com.teste.pratico.models.request.ConsultaAgendamentosRequest;
import com.teste.pratico.models.response.ConsultaAgendamentoResponse;
import com.teste.pratico.models.response.ConsultaTotalAgendamentosResponse;
import com.teste.pratico.service.ConsultaAgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConsultaAgendamentoControllerImpl implements ConsultaAgendamentoController {

    private final ConsultaAgendamentoService consultaAgendamentoService;

    @Override
    public ResponseEntity<List<ConsultaAgendamentoResponse>> getAgendamentos(final ConsultaAgendamentosRequest consultaAgendamentoRequest) {
        return ResponseEntity.ok().body(consultaAgendamentoService.getAgendamentos(consultaAgendamentoRequest));
    }

    @Override
    public ResponseEntity<List<ConsultaTotalAgendamentosResponse>> consultarTotalAgendamentos(final ConsultaAgendamentosRequest consultaAgendamentoRequest) {
        return ResponseEntity.ok().body(consultaAgendamentoService.consultarTotalAgendamentos(
                consultaAgendamentoRequest.inicio(),
                consultaAgendamentoRequest.fim(),
                consultaAgendamentoRequest.solicitante()
        ));
    }

}
