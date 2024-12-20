# Projeto: Sistema de Consulta e Agendamento

## Descrição do Projeto
O sistema é voltado para a gestão de agendamentos e controle de vagas, oferecendo funcionalidades para validação de disponibilidade e restrições baseadas em limites de agendamentos. O objetivo principal é fornecer um serviço eficiente e seguro para gerenciar solicitações de agendamento de vagas.

## Arquitetura Implementada
- **Backend:**
    - Desenvolvido em **Java** utilizando o framework **Spring Boot**.
    - Estruturação em camadas (Controller, Service, Repository, Entity).
    - Utilização de **JPA (Java Persistence API)** para acesso ao banco de dados.
    - Implementação de **anotações do Hibernate** para mapeamento objeto-relacional (ORM).
    - Utilização do **Lombok** para redução de boilerplate de código.

- **Frontend:**
    - Utiliza **JavaServer Faces (JSF)** para renderização das páginas e interação com o backend.

- **Banco de Dados:**
    - **HSQLDB** configurado como banco de dados relacional.
    - Estrutura de tabelas para gerenciar `Vaga`, `Agendamento` e `Solicitante`.
    - Dados inseridos manualmente para testes iniciais e validação de funcionalidades.

- **Integração e Validação:**
    - **Consulta de vagas e agendamentos:** Query personalizada para validar disponibilidade e limites.
    - **Regra de negócio:** Restrições de agendamentos por solicitante e validações de limites baseados nas vagas disponíveis.

## Configuração do Banco de Dados
1. **Banco local:**
    - Arquivo `application.properties` configurado para conexão com o HSQLDB local:
      ```properties
      server.port=9292
 
      spring.view.prefix=/pages/
      spring.mvc.view.suffix=.html
 
      spring.resources.static-locations=classpath:/META-INF/resources/
 
      logging.level.org.springframework.web=DEBUG
      logging.level.org.hibernate=DEBUG

      ##Conexao via servidor
      spring.datasource.url=jdbc:hsqldb:file:C:\\Projetos\\RepositoriosGitLocalLogOne\\Teste-Pratico-Desenvolvedor-Java\\database\\agenda\\agenda;hsqldb.lock_file=false
      ##Conexao via arquivo
      #spring.datasource.url=jdbc:hsqldb:hsql://localhost:9001/agenda;serverTimeZone=America/Sao_Paulo
      spring.datasource.username=sa
      spring.datasource.password=sa
      spring.datasource.driver-class-name=org.hsqldb.jdbc.JDBCDriver
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.HSQLDialect
      spring.datasource.type=com.zaxxer.hikari.HikariDataSource
      spring.datasource.hikari.pool-name=HkariPoolConnections
      spring.datasource.hikari.auto-commit: false
      spring.datasource.hikari.maximum-pool-size: 10
      spring.datasource.hikari.minimum-idle: 10
      spring.datasource.hikari.connection-timeout: 30000
      spring.datasource.hikari.idle-timeout: 60000
      spring.datasource.hikari.validation-timeout: 3000
      spring.datasource.hikari.initialization-fail-timeout: 5000
 
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.show_sql=true
 
      spring.datasource.schema=classpath:schema.sql
      spring.jpa.hibernate.ddl-auto=update
      ```

2. **Estrutura das tabelas:**
    - Tabela `Vaga`:
      ```sql
      CREATE TABLE vaga (
          id SERIAL PRIMARY KEY,
          inicio TIMESTAMP NOT NULL,
          fim TIMESTAMP NOT NULL,
          quantidade INT NOT NULL
      );
      ```
    - Tabela `Solicitante`:
      ```sql
      CREATE TABLE solicitante (
          id SERIAL PRIMARY KEY,
          nome VARCHAR(100) NOT NULL
      );
      ```
    - Tabela `Agendamento`:
      ```sql
      CREATE TABLE agendamento (
          id SERIAL PRIMARY KEY,
          data DATE NOT NULL,
          solicitante_id INT NOT NULL,
          FOREIGN KEY (solicitante_id) REFERENCES solicitante(id)
      );
      ```

## Funcionalidades Principais
1. **Cadastro de Vagas:**
    - Inserção manual ou via API.

2. **Validação de Disponibilidade:**
    - Verifica se há vagas disponíveis para uma data informada.
    - Aplica restrições baseadas em limites de agendamentos.

3. **Agendamentos:**
    - Registro de agendamentos.
    - Consulta de agendamentos por data ou solicitante.

4. **Regras de Negócio:**
    - Limite de 25% das vagas totais para um único solicitante.

## Como Executar o Projeto
1. Clone o repositório para sua máquina local:
   ```bash
   git clone https://github.com/arielbat19/Teste-Pratico-Java.git
