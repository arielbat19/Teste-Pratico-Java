package com.teste.pratico.models.response;

import com.teste.pratico.entity.Solicitante;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaTotalAgendamentos {

    private Solicitante solicitante;
    private Long totalAgendamentos;
    private Long quantidadeVagas;
    private String percentual;
}
