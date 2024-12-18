package com.teste.pratico.mapper;

import com.teste.pratico.entity.Solicitante;
import com.teste.pratico.models.request.CriarNovoSolicitanteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SolicitanteMapper {

    Solicitante fromRequest(CriarNovoSolicitanteRequest criarNovoSolicitanteRequest);
}
