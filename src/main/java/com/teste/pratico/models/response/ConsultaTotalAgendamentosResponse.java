package com.teste.pratico.models.response;

import com.teste.pratico.entity.Solicitante;

public record ConsultaTotalAgendamentosResponse(
        Solicitante solicitante,
        Long totalAgendamentos,
        Long quantidadeVagas,
        String percentual

) {
}
