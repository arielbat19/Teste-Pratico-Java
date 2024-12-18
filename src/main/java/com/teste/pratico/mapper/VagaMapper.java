package com.teste.pratico.mapper;

import com.teste.pratico.entity.Vaga;
import com.teste.pratico.models.request.CriaNovaVagaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy= IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface VagaMapper {

    Vaga fromRequest(CriaNovaVagaRequest criaNovaVagaRequest);
}
