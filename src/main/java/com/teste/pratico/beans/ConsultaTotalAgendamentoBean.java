package com.teste.pratico.beans;

import com.teste.pratico.controller.ConsultaAgendamentoController;
import com.teste.pratico.entity.Solicitante;
import com.teste.pratico.models.request.ConsultaAgendamentosRequest;
import com.teste.pratico.models.response.ConsultaTotalAgendamentos;
import com.teste.pratico.models.response.ConsultaTotalAgendamentosResponse;
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ManagedBean
@ViewScoped
@Component
public class ConsultaTotalAgendamentoBean {

    private List<Solicitante> listaSolicitantes;
    private Long solicitanteId;
    private Date dataInicio;
    private Date dataFim;
    private List<ConsultaTotalAgendamentosResponse> agendamentos;
    private List<ConsultaTotalAgendamentos> consultaAgendamentos = new ArrayList<>();

    @Autowired
    private ConsultaAgendamentoController agendamentoControllerConsulta;
    @Autowired
    private SolicitanteService solicitanteService;

    private static final Logger logger = LogManager.getLogger(ConsultaTotalAgendamentos.class);

    @PostConstruct
    public void init() {
        listaSolicitantes = solicitanteService.buscarTodos();
        listaSolicitantes.forEach(solicitante -> solicitante.setNome(solicitante.getNome().toUpperCase()));
    }

    public void consultarTotalAgendamentos() {
        Solicitante solicitanteSelecionado = new Solicitante(solicitanteId, null);
        LocalDate dataInicioLocalDate = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFimLocalDate = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        ConsultaAgendamentosRequest request = new ConsultaAgendamentosRequest(dataInicioLocalDate, dataFimLocalDate, solicitanteSelecionado);

        try {
            ResponseEntity<List<ConsultaTotalAgendamentosResponse>> response = agendamentoControllerConsulta.consultarTotalAgendamentos(request);
            List<ConsultaTotalAgendamentosResponse> agendamentos = response.getBody();

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

    private void processarAgendamentos(List<ConsultaTotalAgendamentosResponse> novosAgendamentos) {
        if (novosAgendamentos != null) {
            novosAgendamentos.forEach(response -> {
                ConsultaTotalAgendamentos agendamento = converterResponseParaAgendamento(response);
                consultaAgendamentos.add(agendamento);
            });
        }
    }

    private ConsultaTotalAgendamentos converterResponseParaAgendamento(ConsultaTotalAgendamentosResponse response) {
        ConsultaTotalAgendamentos agendamento = new ConsultaTotalAgendamentos();
        Solicitante solicitante = new Solicitante();
        solicitante.setId(response.solicitante().getId());
        solicitante.setNome(response.solicitante().getNome().toUpperCase());
        agendamento.setSolicitante(solicitante);
        agendamento.setTotalAgendamentos(response.totalAgendamentos());
        agendamento.setPercentual(response.percentual());
        agendamento.setQuantidadeVagas(response.quantidadeVagas());
        return agendamento;
    }

    public void limparCampos() {
        solicitanteId = null;
        dataInicio = null;
        dataFim = null;
    }
}
