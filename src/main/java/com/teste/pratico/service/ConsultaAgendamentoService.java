package com.teste.pratico.service;

import com.teste.pratico.entity.Solicitante;
import com.teste.pratico.models.request.ConsultaAgendamentosRequest;
import com.teste.pratico.models.response.ConsultaAgendamentoResponse;
import com.teste.pratico.models.response.ConsultaTotalAgendamentosResponse;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaAgendamentoService {

    List<ConsultaAgendamentoResponse> getAgendamentos(ConsultaAgendamentosRequest consultaAgendamentosRequest);

    List<ConsultaTotalAgendamentosResponse> consultarTotalAgendamentos(LocalDate inicio, LocalDate fim, Solicitante solicitante);

}
