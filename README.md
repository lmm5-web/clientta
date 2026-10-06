# projeto-final-clientta-

## Introdução do site

O Clientta é uma aplicação Android desenvolvida com o objetivo de auxiliar clínicas e profissionais da área estética na organização de seus atendimentos, centralizando informações e digitalizando etapas que podem ser realizadas de forma manual. A plataforma foi pensada a partir de problemas comuns na rotina de clínicas, como dificuldade no gerenciamento da agenda, informações de clientes distribuídas em diferentes locais, coleta de dados realizada somente no momento do atendimento e excesso de tarefas administrativas. A proposta do Clientta é transformar esse processo em um fluxo mais organizado e integrado, permitindo que diferentes etapas do atendimento sejam realizadas dentro da mesma plataforma. O sistema busca atuar desde o agendamento até o pré-atendimento, permitindo que o profissional tenha acesso antecipado a informações relevantes e possa organizar melhor sua rotina. Dessa forma, o Clientta não funciona apenas como uma agenda digital. A proposta é estruturar o processo de atendimento como um todo, reduzindo tarefas repetitivas, centralizando informações e proporcionando uma experiência mais prática para profissionais e clientes.

## Objetivo

O objetivo principal do Clientta é otimizar o fluxo de atendimento de clínicas estéticas por meio da centralização de informações e da digitalização de processos administrativos.

- Organizar os horários e atendimentos da clínica;
- Facilitar o gerenciamento da agenda;
- Centralizar informações dos clientes;
- Permitir o preenchimento de informações antes do atendimento;
- Reduzir processos realizados manualmente;
- Diminuir o retrabalho dos profissionais;
- Facilitar o acesso às informações necessárias durante o atendimento;
- Otimizar o tempo dos profissionais;
- Proporcionar uma experiência mais simples aos clientes.

O gerenciamento de horários, cadastro de clientes, coleta de informações e preparação para os atendimentos pode ser realizado por meio de diferentes ferramentas ou processos manuais. Quando essas informações não estão centralizadas, aumenta a possibilidade de conflitos de agenda, perda de informações e retrabalho. Outro ponto importante é a coleta de informações no momento do atendimento. Quando o profissional precisa realizar diversas tarefas administrativas antes de iniciar o procedimento, parte do tempo que poderia ser dedicado ao cliente é utilizada para organização e preenchimento de dados. O Clientta busca solucionar esses problemas antecipando parte desse processo. Através do pré-atendimento, o cliente pode fornecer determinadas informações antes de sua chegada à clínica. Dessa forma, o profissional pode consultar esses dados previamente e iniciar o atendimento com uma visão mais organizada das informações disponíveis.



## Funcionalidades

### Agendamento

Permite organizar e gerenciar os horários dos atendimentos. A funcionalidade foi desenvolvida para facilitar a visualização da agenda e reduzir conflitos de horários, tornando o processo de marcação mais organizado.

### Gestão de clientes

Centraliza as informações dos clientes em um único ambiente. Isso permite que os profissionais tenham acesso mais rápido aos dados necessários e reduz a necessidade de consultar diferentes fontes de informação.

### Pré-atendimento

Permite que o cliente forneça informações antes do atendimento. Essa funcionalidade busca antecipar determinadas etapas do processo, permitindo que o profissional tenha acesso aos dados previamente e possa se preparar melhor para o atendimento.

### Organização dos atendimentos

Auxilia o profissional no acompanhamento de sua rotina. A centralização dos atendimentos permite visualizar os compromissos e as informações relacionadas aos clientes de forma mais estruturada.

### Agilidade no atendimento

Ao integrar agendamento, informações dos clientes e pré-atendimento, o Clientta busca reduzir tarefas repetitivas e processos manuais. O objetivo não é apenas executar as tarefas mais rapidamente, mas também reduzir a complexidade do fluxo de atendimento.

## Tecnologias utilizadas

- Kotlin: O Kotlin é a linguagem principal utilizada no desenvolvimento do aplicativo. A linguagem fornece recursos que contribuem para um código mais conciso e seguro, além de possuir integração com o ecossistema Android.
- Jetpack Compose: O Jetpack Compose é utilizado para construir a interface da aplicação. Por utilizar uma abordagem declarativa, a interface pode ser construída com base no estado atual da aplicação, facilitando a criação e manutenção dos componentes visuais.
- Material 3:	Componentes e identidade visual.
- Navigation Compose: Navegação entre telas.
- Room Database: O Room é utilizado para persistência local dos dados Ele fornece uma camada de abstração sobre o SQLite e permite trabalhar com o banco de dados utilizando estruturas diretamente integradas ao Kotlin.
- Retrofit: Comunicação com APIs externas.
- Gson: O Gson auxilia na conversão dos dados recebidos e enviados no formato JSON para objetos utilizados pela aplicação.
- Coroutines: As Coroutines permitem executar operações assíncronas sem bloquear a interface da aplicação.
- Flow: O Flow é utilizado para trabalhar com fluxos de dados observáveis, permitindo que alterações nos dados sejam acompanhadas pela aplicação.
- KSP: Processamento de código.
- Android Studio: Ambiente de desenvolvimento.
- Git: Controle de versão.
- GitHub: Hospedagem e compartilhamento do projeto.

## Estrutura do projeto

Clientta/
├── app/
│   └── src/
│       └── main/
│           └── java/
│               └── com.example.clientta/
│                   ├── data/
│                   │   ├── local/
│                   │   ├── remote/
│                   │   └── repository/
│                   ├── model/
│                   └── ui/
│                       ├── features/
│                       ├── navigation/
│                       └── theme/
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── README.md

## instalação

Clone o repositório:

- git clone URL_DO_REPOSITORIO

Acesse o diretório:
- cd projeto-final--clientta-

Abra a pasta do projeto no Android Studio. Aguarde a sincronização do Gradle e a instalação das dependências necessárias.
  
Execute o projeto:

- Inicie um emulador Android ou conecte um dispositivo físico;
- Aguarde o reconhecimento do dispositivo;
- Selecione a configuração de execução da aplicação;
- Clique em Run ▶.
- A aplicação será compilada e instalada no dispositivo selecionado.

## Benefícios

### Para clínicas e profissionais

- Maior organização da agenda;
- Centralização dos dados;
- Redução de tarefas administrativas;
- Melhor acompanhamento dos atendimentos;
- Acesso antecipado às informações;
- Redução de retrabalho;
- Melhor aproveitamento do tempo.

### Para clientes

- Processo de agendamento mais organizado;
- Possibilidade de fornecer informações antecipadamente;
- Menor quantidade de etapas no momento do atendimento;
- Experiência mais simples e prática.

## Considerações finais

O Clientta busca utilizar a tecnologia para solucionar problemas práticos encontrados na rotina de clínicas e profissionais estéticos. A plataforma centraliza informações, organiza agendamentos e permite antecipar etapas do atendimento, criando um fluxo mais estruturado entre cliente e profissional. A principal proposta do projeto é transformar um processo que pode ser fragmentado e manual em uma experiência mais integrada. Com uma arquitetura estruturada e tecnologias modernas do ecossistema Android, o projeto também estabelece uma base para futuras melhorias e expansão das funcionalidades.
