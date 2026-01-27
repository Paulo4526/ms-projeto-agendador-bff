<h1 align="center">🧩 ms-projeto-agendador-bff</h1>

<p align="center">
  <strong>Backend for Frontend (BFF) do Projeto Agendador</strong><br>
  Camada de orquestração e exposição das APIs
</p>

<p align="center">
  Java • Spring Boot • Maven • Feign • Swagger • CORS • JWT • Microsserviços • Docker • CI/CD
</p>

<hr>

<h2>📌 Sobre o Microserviço</h2>

<p>
  O <strong>ms-projeto-agendador-bff</strong> é o
  <strong>quarto microsserviço</strong> do <strong>Projeto Agendador</strong>,
  atuando como um <strong>Backend for Frontend (BFF)</strong>.
</p>

<p>
  Sua principal responsabilidade é <strong>centralizar, orquestrar e expor</strong>
  as APIs dos microsserviços internos, fornecendo um
  <strong>ponto único de acesso</strong> para aplicações frontend.
</p>

<p>
  O BFF abstrai a complexidade da comunicação entre microsserviços,
  padroniza respostas, trata exceções e aplica regras de segurança
  antes de encaminhar as requisições.
</p>

<hr>

<h2>🧩 Arquitetura do Projeto Agendador</h2>

<p>
  O <strong>Projeto Agendador</strong> é composto por <strong>4 microsserviços</strong>,
  que trabalham de forma integrada e devem ser iniciados em uma
  <strong>ordem específica</strong> para garantir o funcionamento correto do sistema.
</p>

<h3>📌 Ordem de Execução dos Microsserviços</h3>

<ol>
  <li>
    <strong>ms-projeto-agendador-usuario</strong><br>
    Microsserviço <strong>principal</strong> e <strong>obrigatório</strong>, responsável
    por autenticação, autorização e gestão de usuários.<br>
    <em>Deve estar em execução antes de todos os outros serviços.</em>
    🔗 Repositório:
    <a href="https://github.com/Paulo4526/ms-projeto-agendador-usuario" target="_blank">
      https://github.com/Paulo4526/ms-projeto-agendador-bff
    </a>
  </li>
  <br>

  <li>
    <strong>ms-projeto-agendador-tarefas</strong><br>
    Responsável pelo agendamento e gerenciamento de tarefas, consumindo
    autenticação do microsserviço de usuários.<br>
    🔗 Repositório:
    <a href="https://github.com/Paulo4526/ms-projeto-agendador-tarefas" target="_blank">
      https://github.com/Paulo4526/ms-projeto-agendador-tarefas
    </a>
  </li>
  <br>

  <li>
    <strong>ms-projeto-agendador-notificacao</strong><br>
    Microsserviço responsável pelo envio de notificações (e-mails),
    acionado a partir dos eventos de tarefas.<br>
    🔗 Repositório:
    <a href="https://github.com/Paulo4526/ms-projeto-agendador-notificacao" target="_blank">
      https://github.com/Paulo4526/ms-projeto-agendador-notificacao
    </a>
  </li>
  <br>

  <li>
    <strong>ms-projeto-agendador-bff</strong><br>
    Backend for Frontend responsável por centralizar, orquestrar e expor
    as APIs para o frontend, consumindo os demais microsserviços.<br>
  </li>
</ol>

<p>
  ⚠️ <strong>Importante:</strong> A aplicação deve ser executada exatamente
  na ordem acima, pois cada microsserviço depende dos anteriores
  para autenticação, comunicação e processamento correto.
</p>

<p>
  ⚠️ Este microsserviço deve ser iniciado <strong>por último</strong>,
  após todos os demais microsserviços estarem em execução.
</p>

<hr>

<h2>🚀 Tecnologias Utilizadas</h2>

<h3>🧠 Linguagem & Framework</h3>
<ul>
  <li><strong>Java 21</strong></li>
  <li><strong>Spring Boot</strong></li>
  <li>Spring Web</li>
</ul>

<h3>🔗 Comunicação entre Microsserviços</h3>
<ul>
  <li>Spring Cloud OpenFeign</li>
  <li>Comunicação HTTP síncrona</li>
</ul>

<h3>🔐 Segurança</h3>
<ul>
  <li>JWT (JSON Web Token)</li>
  <li>Validação de token entre microsserviços</li>
  <li>Configuração de CORS</li>
</ul>

<h3>📑 Documentação</h3>
<ul>
  <li>Swagger / OpenAPI</li>
  <li>Endpoints documentados e testáveis</li>
</ul>

<h3>🛠 Build & Infraestrutura</h3>
<ul>
  <li><strong>Maven</strong></li>
  <li>Docker</li>
  <li>Docker Compose</li>
  <li>GitHub Actions (CI)</li>
</ul>

<hr>

<h2>🐳 Execução com Docker</h2>

<h3>📦 Criar a imagem Docker</h3>

<pre><code>docker build -t ms-projeto-agendador-bff .</code></pre>

<h3>🚀 Subir a aplicação com Docker Compose</h3>

<pre><code>docker compose build ms-agendador-bff</code></pre>
<pre><code>docker compose up -d</code></pre>

<p>
  ⚠️ Certifique-se de que os microsserviços
  <strong>ms-projeto-agendador-usuario</strong>,
  <strong>ms-projeto-agendador-tarefas</strong> e
  <strong>ms-projeto-agendador-notificacao</strong>
  já estejam em execução antes de iniciar o BFF.
</p>

<hr>

<h2>🏗️ Conceitos Arquiteturais</h2>

<ul>
  <li>Arquitetura de <strong>Microsserviços</strong></li>
  <li>Padrão <strong>BFF (Backend for Frontend)</strong></li>
  <li>Orquestração de chamadas entre serviços</li>
  <li>Tratamento de exceções personalizadas</li>
  <li>Configuração centralizada de CORS</li>
  <li>Segurança stateless com JWT</li>
  <li>Configuração por variáveis de ambiente</li>
  <li>Containerização com Docker</li>
</ul>

<hr>

<p align="center">
  <strong>Projeto Agendador</strong><br>
  Microsserviços • Java • Spring Boot • BFF
</p>

<p align="center">
  Desenvolvido por <strong>Paulo Bueno</strong>
</p>
