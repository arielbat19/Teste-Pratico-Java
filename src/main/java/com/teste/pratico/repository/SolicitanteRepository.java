package com.teste.pratico.repository;

import com.teste.pratico.entity.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {
}
