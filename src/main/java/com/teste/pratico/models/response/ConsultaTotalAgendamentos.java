package com.teste.pratico.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.pratico.entity.Solicitante;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ConsultaTotalAgendamentos {

    private Solicitante solicitante;
    private Long totalAgendamentos;
    private Long quantidadeVagas;
    private String percentual;
}
