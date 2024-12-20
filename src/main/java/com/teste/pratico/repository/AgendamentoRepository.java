package com.teste.pratico.repository;

import com.teste.pratico.entity.Agendamento;
import com.teste.pratico.entity.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.data = :data")
    long contarAgendamentosPorData(@Param("data") LocalDate data);

    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.solicitante = :solicitante AND a.data = :data")
    int contarAgendamentosPorSolicitanteEData(@Param("solicitante") Solicitante solicitante, @Param("data") LocalDate data);

    List<Agendamento> findByDataBetweenAndSolicitanteId(LocalDate inicio, LocalDate fim, Long solicitanteId);

    @Query("SELECT COALESCE(SUM(v.quantidade), 0) FROM Vaga v WHERE " +
            "(v.inicio BETWEEN :inicio AND :fim OR v.fim BETWEEN :inicio AND :fim OR " +
            ":inicio BETWEEN v.inicio AND v.fim OR :fim BETWEEN v.inicio AND v.fim)")
    long countVagasDisponiveis(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);


}
