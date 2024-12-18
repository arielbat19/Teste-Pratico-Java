package com.teste.pratico.beans;

import com.teste.pratico.controller.VagaController;
import com.teste.pratico.models.request.CriaNovaVagaRequest;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
@ManagedBean
@ViewScoped
@Component
public class VagaBean {

    private Date dataInicio;
    private Date dataFim;
    private Integer quantidadeVaga;

    @Autowired
    private VagaController vagaController;

    private static final Logger logger = LogManager.getLogger(VagaBean.class);

    public void cadastrarVaga() {
        LocalDate dataInicioLocalDate = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFimLocalDate = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        CriaNovaVagaRequest request = new CriaNovaVagaRequest(dataInicioLocalDate, dataFimLocalDate, quantidadeVaga);

        try {
            vagaController.save(request);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Vaga incluída com sucesso!", ""));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("msgs");
        } catch (Exception e) {
            logger.error("Erro ao cadastrar vaga", e);  // Log do erro
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar vaga!", e.getMessage()));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("msgs");
        }

        limparCampos();
    }

    public void limparCampos() {
        dataInicio = null;
        dataFim = null;
        quantidadeVaga = null;
    }

}
