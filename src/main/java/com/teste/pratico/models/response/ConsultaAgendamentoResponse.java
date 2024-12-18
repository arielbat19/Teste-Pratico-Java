package com.teste.pratico.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.pratico.entity.Solicitante;

import java.time.LocalDate;

public record ConsultaAgendamentoResponse(
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data,
        String motivo,
        String numero,
        Solicitante solicitante
) {
}
