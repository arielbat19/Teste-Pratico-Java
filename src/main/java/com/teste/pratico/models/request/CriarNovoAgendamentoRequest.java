package com.teste.pratico.models.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.pratico.entity.Solicitante;

import java.time.LocalDate;

public record CriarNovoAgendamentoRequest(
    Solicitante solicitante,
    String motivo,
    String numero,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate data
) {
}
