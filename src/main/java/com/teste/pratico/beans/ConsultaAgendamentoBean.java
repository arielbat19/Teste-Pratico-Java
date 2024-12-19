package com.teste.pratico.beans;

import com.teste.pratico.controller.ConsultaAgendamentoController;
import com.teste.pratico.entity.Solicitante;
import com.teste.pratico.models.request.ConsultaAgendamentosRequest;
import com.teste.pratico.models.response.ConsultaAgendamentoResponse;
import com.teste.pratico.models.response.ConsultaAgendamentos;
import com.teste.pratico.service.SolicitanteService;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ManagedBean
@ViewScoped
@Component
public class ConsultaAgendamentoBean {

    private List<Solicitante> listaSolicitantes;
    private Long solicitanteId;
    private Date dataInicio;
    private Date dataFim;
    private List<ConsultaAgendamentoResponse> agendamentos;
    private List<ConsultaAgendamentos> consultaAgendamentos = new ArrayList<>();

    @Autowired
    private ConsultaAgendamentoController agendamentoControllerConsulta;
    @Autowired
    private SolicitanteService solicitanteService;

    private static final Logger logger = LogManager.getLogger(ConsultaAgendamentoBean.class);

    @PostConstruct
    public void init() {
        listaSolicitantes = solicitanteService.buscarTodos();
        listaSolicitantes.forEach(solicitante -> solicitante.setNome(solicitante.getNome().toUpperCase()));
    }

    public void consultarAgendamentos() {
        consultaAgendamentos.clear();
        Solicitante solicitanteSelecionado = new Solicitante(solicitanteId, null);
        LocalDate dataInicioLocalDate = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFimLocalDate = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        ConsultaAgendamentosRequest request = new ConsultaAgendamentosRequest(dataInicioLocalDate, dataFimLocalDate, solicitanteSelecionado);

        try {
            ResponseEntity<List<ConsultaAgendamentoResponse>> response = agendamentoControllerConsulta.getAgendamentos(request);
            List<ConsultaAgendamentoResponse> agendamentos = response.getBody();

            if (agendamentos == null || agendamentos.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foram encontrados registros para os dados informados.", ""));
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("msgs");
            } else {
                processarAgendamentos(agendamentos);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta realizada com sucesso!", ""));
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("msgs");
            }
        } catch (Exception e) {
            logger.error("Erro ao cadastrar vaga", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao consultar agendamentos!", e.getMessage()));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("msgs");
        }

        limparCampos();
    }

    private void processarAgendamentos(List<ConsultaAgendamentoResponse> novosAgendamentos) {
        if (novosAgendamentos != null) {
            novosAgendamentos.forEach(response -> {
                ConsultaAgendamentos agendamento = converterResponseParaAgendamento(response);
                consultaAgendamentos.add(agendamento);
            });
        }
    }

    private ConsultaAgendamentos converterResponseParaAgendamento(ConsultaAgendamentoResponse response) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = response.data().format(formatter);

        ConsultaAgendamentos agendamento = new ConsultaAgendamentos();
        Solicitante solicitante = new Solicitante();
        solicitante.setId(response.solicitante().getId());
        solicitante.setNome(response.solicitante().getNome().toUpperCase());
        agendamento.setSolicitante(solicitante);
        agendamento.setMotivo(response.motivo().toUpperCase());
        agendamento.setNumero(response.numero());
        agendamento.setData(dataFormatada);
        return agendamento;
    }

    public void limparCampos() {
        solicitanteId = null;
        dataInicio = null;
        dataFim = null;
    }
}
