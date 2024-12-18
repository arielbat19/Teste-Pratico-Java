package com.teste.pratico.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.pratico.entity.Solicitante;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaAgendamentos {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String data;
    private String motivo;
    private String numero;
    private Solicitante solicitante;
}
