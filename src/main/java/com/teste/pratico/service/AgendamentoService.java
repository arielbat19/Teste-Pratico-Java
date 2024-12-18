package com.teste.pratico.service;

import com.teste.pratico.models.request.CriarNovoAgendamentoRequest;

public interface AgendamentoService {

    void save(final CriarNovoAgendamentoRequest criarNovoAgendamentoRequest);
}
