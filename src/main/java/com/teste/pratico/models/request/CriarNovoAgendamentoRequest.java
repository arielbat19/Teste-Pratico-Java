package com.teste.pratico.models.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.pratico.entity.Solicitante;
import lombok.With;

import java.time.LocalDate;

@With
public record CriarNovoAgendamentoRequest(
    Solicitante solicitante,
    String motivo,
    String numero,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate data
) {
}
