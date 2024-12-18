package com.teste.pratico.service;

import com.teste.pratico.entity.Solicitante;
import com.teste.pratico.models.request.CriarNovoSolicitanteRequest;

import java.util.List;

public interface SolicitanteService {

    void save(final CriarNovoSolicitanteRequest criarNovoSolicitanteRequest);

    List<Solicitante> buscarTodos();

    Solicitante buscarPorId(Long solicitanteId);
}
