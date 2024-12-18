package com.teste.pratico.mapper;

import com.teste.pratico.entity.Agendamento;
import com.teste.pratico.models.request.ConsultaAgendamentosRequest;
import com.teste.pratico.models.request.CriarNovoAgendamentoRequest;
import com.teste.pratico.models.response.ConsultaAgendamentoResponse;
import com.teste.pratico.models.response.ConsultaAgendamentos;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = IGNORE,
    nullValueCheckStrategy = ALWAYS
)
public interface AgendamentoMapper {

    List<ConsultaAgendamentoResponse> fromEntity(List<Agendamento> agendamento);

    Agendamento fromRequest(CriarNovoAgendamentoRequest criaNovoAgendamentoRequest);

    Agendamento fromConsultaAgendamentosRequest(ConsultaAgendamentosRequest consultaAgendamentosRequest);

}
