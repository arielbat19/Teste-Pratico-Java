package com.teste.pratico.service.impl;

import com.teste.pratico.entity.Agendamento;
import com.teste.pratico.entity.Solicitante;
import com.teste.pratico.mapper.AgendamentoMapper;
import com.teste.pratico.models.request.ConsultaAgendamentosRequest;
import com.teste.pratico.models.response.ConsultaAgendamentoResponse;
import com.teste.pratico.models.response.ConsultaTotalAgendamentosResponse;
import com.teste.pratico.repository.AgendamentoRepository;
import com.teste.pratico.repository.SolicitanteRepository;
import com.teste.pratico.service.ConsultaAgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaAgendamentoServiceImpl implements ConsultaAgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoMapper agendamentoMapper;
    private final SolicitanteRepository solicitanteRepository;


    @Override
    public List<ConsultaAgendamentoResponse> getAgendamentos(ConsultaAgendamentosRequest consultaAgendamentosRequest) {

        if (consultaAgendamentosRequest.fim().isBefore(consultaAgendamentosRequest.inicio())) {
            throw new IllegalArgumentException("A data fim não pode ser anterior à data início.");
        }

        Solicitante solicitante = solicitanteRepository.findById(consultaAgendamentosRequest.solicitante().getId())
                .orElseThrow(() -> new RuntimeException("Solicitante não encontrado"));

        List<Agendamento> agendamentos = agendamentoRepository.findByDataBetweenAndSolicitanteId(consultaAgendamentosRequest.inicio(),
                consultaAgendamentosRequest.fim(), solicitante.getId());

        return agendamentoMapper.fromEntity(agendamentos);

    }

    @Override
    public List<ConsultaTotalAgendamentosResponse> consultarTotalAgendamentos(LocalDate inicio, LocalDate fim, Solicitante solicitante) {

        if (fim.isBefore(inicio)) {
            throw new IllegalArgumentException("A data fim não pode ser anterior à data início.");
        }

        List<Agendamento> agendamentos = agendamentoRepository.findByDataBetweenAndSolicitanteId(inicio, fim, solicitante.getId());

        Map<Solicitante, Long> agendamentosPorSolicitante = agendamentos.stream()
                .collect(Collectors.groupingBy(Agendamento::getSolicitante, Collectors.counting()));

        long totalVagas = agendamentoRepository.countVagasDisponiveis(inicio, fim);

        return agendamentosPorSolicitante.entrySet().stream()
                .map(entry -> {
                    Solicitante solicitanteEntity = entry.getKey();
                    Long totalAgendamentos = entry.getValue();
                    String percentual = String.format("%.2f%%", (totalAgendamentos * 100.0) / totalVagas);

                    return new ConsultaTotalAgendamentosResponse(solicitanteEntity, totalAgendamentos, totalVagas, percentual);
                })
                .collect(Collectors.toList());
    }
}
