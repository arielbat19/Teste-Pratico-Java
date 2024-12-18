package com.teste.pratico.service.impl;

import com.teste.pratico.mapper.VagaMapper;
import com.teste.pratico.models.request.CriaNovaVagaRequest;
import com.teste.pratico.repository.VagaRepository;
import com.teste.pratico.service.VagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VagaServiceImpl implements VagaService {

    private final VagaRepository vagaRepository;
    private final VagaMapper mapper;

    @Override
    public void save(final CriaNovaVagaRequest criaNovaVagaRequest) {

        validateData(criaNovaVagaRequest);

        vagaRepository.save(mapper.fromRequest(criaNovaVagaRequest));

    }

    private void validateData(CriaNovaVagaRequest criaNovaVagaRequest) {
        if (criaNovaVagaRequest.inicio().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data informada não pode ser anterior à data atual.");
        }

        if (criaNovaVagaRequest.fim().isBefore(criaNovaVagaRequest.inicio())) {
            throw new IllegalArgumentException("A data fim não pode ser anterior à data início.");
        }
    }
}
