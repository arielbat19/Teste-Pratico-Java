package com.teste.pratico.models.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.With;

import java.time.LocalDate;

@With
public record CriaNovaVagaRequest(
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate inicio,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate fim,
        Integer quantidade
) {
}
