package com.teste.pratico.service.impl;

import com.teste.pratico.entity.Solicitante;
import com.teste.pratico.mapper.SolicitanteMapper;
import com.teste.pratico.models.request.CriarNovoSolicitanteRequest;
import com.teste.pratico.repository.SolicitanteRepository;
import com.teste.pratico.service.SolicitanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitanteServiceImpl implements SolicitanteService {

    private final SolicitanteRepository solicitanteRepository;
    private final SolicitanteMapper mapper;

    @Override
    public void save(CriarNovoSolicitanteRequest criarNovoSolicitanteRequest) {
        solicitanteRepository.save(mapper.fromRequest(criarNovoSolicitanteRequest));
    }

    @Override
    public List<Solicitante> buscarTodos() {
        return solicitanteRepository.findAll();
    }

    @Override
    public Solicitante buscarPorId(Long solicitanteId) {
        return solicitanteRepository.findById(solicitanteId).orElseThrow(() -> new RuntimeException("Solicitante não encontrado"));
    }
}
