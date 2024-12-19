package com.teste.pratico.beans;

import com.teste.pratico.controller.SolicitanteController;
import com.teste.pratico.models.request.CriarNovoSolicitanteRequest;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@Data
@ManagedBean
@ViewScoped
@Component
public class SolicitanteBean {

    private String nomeSolicitante;

    @Autowired
    private SolicitanteController solicitanteController;

    private static final Logger logger = LogManager.getLogger(SolicitanteBean.class);

    public void cadastrarSolicitante() {
        CriarNovoSolicitanteRequest request = new CriarNovoSolicitanteRequest(nomeSolicitante);

        try {
            solicitanteController.save(request);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitante incluído com sucesso!", ""));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("msgs");
        } catch (Exception e) {
            logger.error("Erro ao cadastrar vaga", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar solicitante!", e.getMessage()));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("msgs");
        }

        limparCampos();
    }

    public void limparCampos() {
        nomeSolicitante = null;
    }

}
