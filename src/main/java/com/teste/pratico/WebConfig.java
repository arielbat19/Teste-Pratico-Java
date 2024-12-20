package com.teste.pratico;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@Configuration
public class WebConfig extends SpringBootServletInitializer implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/pages/index.xhtml");
        registry.addViewController("/pages/cadastrar/cadastrar-vaga").setViewName("forward:/pages/cadastrar/cadastrar-vaga.xhtml");
        registry.addViewController("/pages/cadastrar/cadastrar-solicitante").setViewName("forward:/pages/cadastrar/cadastrar-solicitante.xhtml");
        registry.addViewController("/pages/cadastrar/cadastrar-agendamento").setViewName("forward:/pages/cadastrar/cadastrar-agendamento.xhtml");
        registry.addViewController("/pages/consultar/consultar-agendamentos").setViewName("forward:/pages/consultar/consultar-agendamentos.xhtml");
    }
}
