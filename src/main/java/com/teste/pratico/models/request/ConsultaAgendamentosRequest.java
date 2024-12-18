package com.teste.pratico.models.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.pratico.entity.Solicitante;

import java.time.LocalDate;

public record ConsultaAgendamentosRequest(
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate inicio,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate fim,
        Solicitante solicitante
) {
}
