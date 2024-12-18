package com.teste.pratico.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "AGENDAMENTO")
@With
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "numero", length = 20)
    private String numero;

    @Column(name = "motivo", length = 255)
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Solicitante solicitante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }
}
