package com.teste.pratico.service.impl;

import com.teste.pratico.entity.Agendamento;
import com.teste.pratico.entity.Solicitante;
import com.teste.pratico.models.request.CriarNovoAgendamentoRequest;
import com.teste.pratico.repository.AgendamentoRepository;
import com.teste.pratico.repository.SolicitanteRepository;
import com.teste.pratico.repository.VagaRepository;
import com.teste.pratico.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
        long vagasDisponiveis = vagaRepository.somarVagasPorData(dataAgendamento);

        if (vagasDisponiveis == 0) {
            throw new RuntimeException("Nenhuma vaga disponível para o período informado.");
        }

        long agendamentosRealizados = agendamentoRepository.contarAgendamentosPorData(dataAgendamento);

        if (agendamentosRealizados >= vagasDisponiveis) {
            throw new IllegalArgumentException("Não há vagas disponíveis para o período informado.");
        }

        int agendamentosPorSolicitante = agendamentoRepository.contarAgendamentosPorSolicitanteEData(solicitante, dataAgendamento);

        int limitePorSolicitante = (int) Math.ceil(vagasDisponiveis * 0.25);

        if (agendamentosPorSolicitante >= limitePorSolicitante) {
            throw new IllegalArgumentException("O limite de agendamentos para este solicitante foi atingido.");
        }
    }
}
