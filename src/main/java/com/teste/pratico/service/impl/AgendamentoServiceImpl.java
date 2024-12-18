package com.teste.pratico.service.impl;

import com.teste.pratico.entity.Agendamento;
import com.teste.pratico.entity.Solicitante;
import com.teste.pratico.entity.Vaga;
import com.teste.pratico.models.request.CriarNovoAgendamentoRequest;
import com.teste.pratico.repository.AgendamentoRepository;
import com.teste.pratico.repository.SolicitanteRepository;
import com.teste.pratico.repository.VagaRepository;
import com.teste.pratico.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final VagaRepository vagaRepository;
    private final SolicitanteRepository solicitanteRepository;

    @Override
    public void save(CriarNovoAgendamentoRequest criarNovoAgendamentoRequest) {

        if (criarNovoAgendamentoRequest.data().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data informada não pode ser anterior à data atual.");
        }

        Solicitante solicitante = solicitanteRepository.findById(criarNovoAgendamentoRequest.solicitante().getId())
                .orElseThrow(() -> new RuntimeException("Solicitante não encontrado"));

        validarDisponibilidadeDeVagas(criarNovoAgendamentoRequest.data(), solicitante);

        Agendamento request = new Agendamento();
        request.setMotivo(criarNovoAgendamentoRequest.motivo());
        request.setData(criarNovoAgendamentoRequest.data());
        request.setNumero(criarNovoAgendamentoRequest.numero());
        request.setSolicitante(solicitante);

        agendamentoRepository.save(request);

    }

    private void validarDisponibilidadeDeVagas(LocalDate dataAgendamento, Solicitante solicitante) {
        // Obter a soma da quantidade de vagas disponíveis para a data informada
        long vagasDisponiveis = vagaRepository.somarVagasPorData(dataAgendamento);

        if (vagasDisponiveis == 0) {
            throw new RuntimeException("Nenhuma vaga disponível para o período informado.");
        }

        // Contar os agendamentos realizados para a data informada
        long agendamentosRealizados = agendamentoRepository.contarAgendamentosPorData(dataAgendamento);

        if (agendamentosRealizados >= vagasDisponiveis) {
            throw new IllegalArgumentException("Não há vagas disponíveis para o período informado.");
        }

        // Contar o número de agendamentos realizados para o solicitante na data informada
        int agendamentosPorSolicitante = agendamentoRepository.contarAgendamentosPorSolicitanteEData(solicitante, dataAgendamento);

        // Calcular o limite máximo de agendamentos permitido por solicitante (25% do total de vagas disponíveis)
        int limitePorSolicitante = (int) Math.ceil(vagasDisponiveis * 0.25);

        if (agendamentosPorSolicitante >= limitePorSolicitante) {
            throw new IllegalArgumentException("O limite de agendamentos para este solicitante foi atingido.");
        }
    }
}
