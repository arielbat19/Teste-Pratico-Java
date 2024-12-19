package com.teste.pratico.repository;

import com.teste.pratico.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    @Query("SELECT COALESCE(SUM(v.quantidade), 0) FROM Vaga v WHERE :data BETWEEN v.inicio AND v.fim")
    long somarVagasPorData(@Param("data") LocalDate data);






}
