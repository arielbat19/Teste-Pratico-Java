package com.teste.pratico.beans;

import com.teste.pratico.controller.AgendamentoController;
import com.teste.pratico.entity.Solicitante;
import com.teste.pratico.models.request.CriarNovoAgendamentoRequest;
import com.teste.pratico.service.SolicitanteService;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Data
@ManagedBean
@ViewScoped
@Component
public class AgendamentoBean {

    private List<Solicitante> listaSolicitantes;
    private Long solicitanteId;
    private String motivoAgendamento;
    private String numeroAgendamento;
    private Date dataAgendamento;

    @Autowired
    private AgendamentoController agendamentoController;
    @Autowired
    private SolicitanteService solicitanteService;

    private static final Logger logger = LogManager.getLogger(AgendamentoBean.class);

    @PostConstruct
    public void init() {
        listaSolicitantes = solicitanteService.buscarTodos();
        listaSolicitantes.stream().forEach(solicitante -> solicitante.setNome(solicitante.getNome().toUpperCase()));
    }

    public void realizarAgendamento() {
        Solicitante solicitanteSelecionado = new Solicitante(solicitanteId, null);
        LocalDate dataInicioLocalDate = dataAgendamento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        CriarNovoAgendamentoRequest request = new CriarNovoAgendamentoRequest(solicitanteSelecionado,
                motivoAgendamento, numeroAgendamento, dataInicioLocalDate);

        try {
            agendamentoController.save(request);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Agendamento realizado com sucesso!", ""));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("msgs");
        } catch (Exception e) {
            logger.error("Erro ao cadastrar vaga", e);  // Log do erro
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao realizar agendamento!", e.getMessage()));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("msgs");
        }

        limparCampos();
    }

    public void limparCampos() {
        dataAgendamento = null;
        motivoAgendamento = null;
        numeroAgendamento = null;
        solicitanteId = null;
    }
}
