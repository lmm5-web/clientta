# **📄 PRD — Documento de Requisitos do Produto**

> Documento de requisitos do aplicativo Android **Clientta**, elaborado com base no Canvas do projeto.

|  |  |
| ----- | ----- |
| **App** | Clientta |
| **Autores** | Ana Clara, Sofia, Letícia e Maria Eduarda |
| **Versão do documento** | 1.0 |
| **Última atualização** | 06/10/2026 |
| **Status** | (X) Rascunho    ( ) Em revisão    ( ) Aprovado |

---

# **1\. Visão do produto**

**Pitch:**

> O Clientta ajuda profissionais de clínicas de estética e outras clínicas a organizar clientes, atendimentos e horários, permitindo que o cliente consulte tratamentos, realize seu cadastro, agende um atendimento e preencha o pré-atendimento de forma digital, reduzindo o uso de papel e processos manuais.

**Problema:**

Atualmente, clientes precisam entrar em contato diretamente com a clínica para consultar tratamentos, preços e horários, tornando o agendamento mais demorado. Além disso, questionários, termos e informações de clientes podem ser preenchidos e armazenados em papel, dificultando a organização dos profissionais.

**Por que vale a pena fazer isso:**

O Clientta centraliza cadastro, tratamentos, agendamentos e pré-atendimento em um único aplicativo. Para o cliente, isso reduz a necessidade de troca de mensagens para obter informações e facilita o agendamento. Para o profissional, facilita a consulta das informações e a organização da agenda e dos atendimentos.

---

# **2\. Público e cenário de uso**

**Usuário-alvo:**

* **Cliente:** adulto que deseja utilizar os serviços de uma clínica de estética ou outra clínica com atendimento agendado.

* **Profissional:** pessoa responsável pelo atendimento e pela organização da agenda e das informações dos clientes.

O aplicativo deverá ser desenvolvido pensando principalmente em usuários adultos que utilizam o celular para realizar agendamentos.

**História de uso:**

> "São 19h e uma cliente quer marcar uma limpeza de pele, mas não quer precisar esperar a clínica responder no WhatsApp. Ela abre o Clientta, cria seu cadastro, consulta os tratamentos disponíveis, escolhe a limpeza de pele, seleciona uma data e horário disponível e confirma o agendamento. Depois, acessa o pré-atendimento e responde às perguntas necessárias. Ao finalizar, ela consegue consultar o agendamento e verificar se o pré-atendimento foi concluído."

---

# **3\. Objetivos e não-objetivos**

## **Objetivos desta versão (v1.0)**

1. Permitir que clientes criem e gerenciem seus próprios cadastros, com validação dos dados obrigatórios.

2. Permitir que clientes consultem tratamentos, preços e horários e realizem agendamentos.

3. Permitir que clientes preencham o pré-atendimento e que o profissional consulte as informações necessárias para organizar os atendimentos.

## **Não-objetivos**

* ❌ Realizar pagamentos pelo aplicativo.

* ❌ Possuir chat ou sistema de mensagens interno.

* ❌ Enviar notificações push.

* ❌ Integrar automaticamente com WhatsApp.

* ❌ Integrar com Google Calendar.

* ❌ Possuir login com Google ou redes sociais.

* ❌ Recuperar senha por e-mail.

* ❌ Utilizar assinatura digital juridicamente certificada.

* ❌ Sincronizar dados com um servidor ou serviço de nuvem.

* ❌ Integrar com sistemas externos de clínicas.

* ❌ Administrar múltiplas clínicas em uma única conta.

---

# **4\. Requisitos funcionais**

## **Cadastro e autenticação**

| ID | História de usuário | Critério de aceite | Prioridade |
| ----- | ----- | ----- | ----- |
| **RF01** | Como cliente, quero criar uma conta para utilizar o aplicativo. | Ao preencher os campos obrigatórios e confirmar o cadastro, os dados são salvos e o usuário recebe uma mensagem de cadastro realizado com sucesso. | **Must** |
| **RF02** | Como cliente, quero informar meus dados pessoais para que a clínica tenha informações básicas sobre mim. | O formulário permite informar nome completo, CPF, data de nascimento, telefone, e-mail e senha. | **Must** |
| **RF03** | Como cliente, quero saber quais campos preciso preencher. | Campos obrigatórios são identificados visualmente e o cadastro não é concluído enquanto algum campo obrigatório estiver vazio. | **Must** |
| **RF04** | Como cliente, quero receber um aviso quando preencher um dado incorretamente. | Ao inserir um e-mail inválido, campo obrigatório vazio ou outro dado inválido, o aplicativo informa o problema e não conclui o cadastro. | **Must** |
| **RF05** | Como cliente, quero evitar a criação de contas duplicadas. | Se o CPF ou e-mail já estiver cadastrado, o aplicativo não cria uma nova conta e informa que o dado já está sendo utilizado. | **Must** |
| **RF06** | Como cliente, quero entrar na minha conta. | Ao informar e-mail e senha cadastrados corretamente, o aplicativo permite o acesso à área do cliente. | **Must** |
| **RF07** | Como cliente, quero saber quando minha senha ou e-mail estão incorretos. | Quando os dados de login não correspondem ao cadastro, o aplicativo mostra uma mensagem de erro sem fechar a aplicação. | **Must** |
| **RF08** | Como cliente, quero visualizar meus dados cadastrados. | A tela de perfil mostra os dados principais associados à conta do cliente. | **Must** |
| **RF09** | Como cliente, quero atualizar meus dados de contato. | O cliente consegue editar informações permitidas, como telefone e e-mail, e salvar as alterações. | **Should** |
| **RF10** | Como cliente, quero visualizar meu CPF de maneira protegida. | O CPF apresentado no perfil deve possuir parte dos caracteres ocultados. | **Should** |

## **Tratamentos**

| ID | História de usuário | Critério de aceite | Prioridade |
| ----- | ----- | ----- | ----- |
| **RF11** | Como cliente, quero visualizar os tratamentos oferecidos pela clínica. | A tela de tratamentos apresenta os tratamentos cadastrados e ativos. | **Must** |
| **RF12** | Como cliente, quero conhecer as informações de um tratamento antes de agendar. | Ao selecionar um tratamento, são mostrados nome, descrição, preço e duração aproximada. | **Must** |
| **RF13** | Como profissional, quero cadastrar um tratamento para disponibilizá-lo aos clientes. | O profissional consegue informar nome, descrição, preço e duração e salvar o tratamento. | **Should** |
| **RF14** | Como profissional, quero editar as informações de um tratamento. | O profissional consegue alterar dados de um tratamento já cadastrado e salvar as alterações. | **Should** |

## **Agendamento**

| ID | História de usuário | Critério de aceite | Prioridade |
| ----- | ----- | ----- | ----- |
| **RF15** | Como cliente, quero escolher um tratamento para agendar. | O cliente consegue iniciar um agendamento a partir de um tratamento disponível. | **Must** |
| **RF16** | Como cliente, quero escolher uma data disponível. | O aplicativo apresenta datas disponíveis para o tratamento selecionado. | **Must** |
| **RF17** | Como cliente, quero escolher um horário disponível. | O aplicativo apresenta horários disponíveis para a data selecionada. | **Must** |
| **RF18** | Como cliente, quero confirmar meu agendamento. | Ao confirmar, o sistema salva o agendamento contendo cliente, tratamento, data, horário e status. | **Must** |
| **RF19** | Como cliente, quero consultar meus agendamentos. | A tela de agendamentos apresenta os agendamentos do cliente, incluindo tratamento, data, horário e status. | **Must** |
| **RF20** | Como cliente, quero saber se meu agendamento foi confirmado. | Após a confirmação, o aplicativo mostra uma tela ou mensagem com tratamento, data, horário e status do agendamento. | **Must** |
| **RF21** | Como cliente, quero cancelar um agendamento. | O cliente consegue cancelar um agendamento permitido e seu status passa para "Cancelado". | **Should** |

## **Pré-atendimento**

| ID | História de usuário | Critério de aceite | Prioridade |
| ----- | ----- | ----- | ----- |
| **RF22** | Como cliente, quero preencher meu pré-atendimento antes da consulta. | Após possuir um agendamento, o cliente consegue acessar o questionário correspondente. | **Must** |
| **RF23** | Como cliente, quero responder perguntas sobre meu atendimento. | O aplicativo permite responder às perguntas definidas para o pré-atendimento. | **Must** |
| **RF24** | Como cliente, quero confirmar que li o termo apresentado. | O aplicativo permite marcar a opção de confirmação do termo antes de concluir o pré-atendimento. | **Must** |
| **RF25** | Como cliente, quero saber se finalizei meu pré-atendimento. | Após salvar as respostas, o aplicativo mostra o status "Pré-atendimento concluído". | **Must** |
| **RF26** | Como profissional, quero consultar o pré-atendimento de um cliente. | Ao acessar um agendamento, o profissional consegue visualizar as respostas registradas pelo cliente. | **Must** |

## **Área profissional**

| ID | História de usuário | Critério de aceite | Prioridade |
| ----- | ----- | ----- | ----- |
| **RF27** | Como profissional, quero visualizar minha agenda. | A área profissional apresenta os agendamentos organizados por data e horário. | **Must** |
| **RF28** | Como profissional, quero consultar os clientes cadastrados. | O aplicativo apresenta uma lista de clientes cadastrados para o profissional. | **Must** |
| **RF29** | Como profissional, quero visualizar informações básicas de um cliente. | Ao selecionar um cliente, o profissional consegue visualizar seus dados permitidos. | **Must** |
| **RF30** | Como profissional, quero visualizar o status dos agendamentos. | Cada agendamento apresenta um status: "Agendado", "Concluído" ou "Cancelado". | **Must** |
| **RF31** | Como profissional, quero alterar o status de um atendimento. | O profissional consegue alterar um agendamento para "Concluído" após o atendimento. | **Should** |

---

# **5\. Requisitos não funcionais**

| ID | Requisito | Como será verificado |
| ----- | ----- | ----- |
| **RNF01** | O app não pode fechar sozinho durante o uso normal. | 5 minutos de uso contínuo em pelo menos 2 celulares diferentes. |
| **RNF02** | Operações de leitura e gravação que possam gerar exceções deverão ser tratadas. | Revisão do código e testes de operações do Room. |
| **RNF03** | Uma falha não pode apresentar tela branca ou encerrar o aplicativo. | Realização dos testes de falha definidos na seção 9\. |
| **RNF04** | O aplicativo deverá funcionar sem conexão com a internet durante o uso do MVP. | Ativar modo avião e testar cadastro, consulta e armazenamento dos dados locais. |
| **RNF05** | Os dados principais deverão permanecer disponíveis após fechar e abrir novamente o aplicativo. | Criar dados, fechar o app, abrir novamente e verificar os dados. |
| **RNF06** | O app deverá utilizar Room para persistência local. | Revisão da implementação e do banco de dados. |
| **RNF07** | O aplicativo deverá possuir interface legível e consistente. | Teste em pelo menos dois tamanhos de tela. |
| **RNF08** | Textos visíveis deverão ficar em recursos de strings, evitando textos fixos diretamente nas telas. | Revisão do código. |
| **RNF09** | As cores principais da identidade visual deverão ser centralizadas nos recursos de tema. | Revisão do código. |
| **RNF10** | O aplicativo deverá utilizar mensagens compreensíveis para erros. | Execução dos testes de erro com usuários. |
| **RNF11** | Dados como senha não deverão ser exibidos diretamente na interface. | Teste visual das telas de cadastro e login. |
| **RNF12** | O CPF deverá ser parcialmente ocultado na tela de perfil. | Teste da tela de perfil. |
| **RNF13** | Todo arquivo do pacote do aplicativo deverá possuir o comentário de fronteira definido pelo grupo. | Revisão do projeto antes da entrega. |
| **RNF14** | Qualquer integrante deverá conseguir realizar uma alteração simples no projeto. | Teste prático com todas as integrantes. |

---

# **6\. Telas e navegação**

## **Mapa de navegação**

                        \[Abertura\]  
                             │  
                 ┌───────────┴───────────┐  
                 │                       │  
             \[Login\]                \[Cadastro\]  
                 │                       │  
                 └───────────┬───────────┘  
                             │  
                       \[Tela Inicial\]  
                             │  
          ┌──────────────────┼──────────────────┐  
          │                  │                  │  
   \[Tratamentos\]       \[Agendamentos\]        \[Perfil\]  
          │                  │  
 \[Detalhe do tratamento\]     │  
          │                  │  
      \[Agendar\]        \[Detalhe do agendamento\]  
          │                  │  
 \[Data e horário\]            │  
          │                  │  
     \[Confirmação\]           │  
          │                  │  
 \[Pré-atendimento\]           │  
          │                  │  
 \[Questionário \+ Termo\]      │  
          │                  │  
    \[Concluído\]──────────────┘

                  \[Área Profissional\]  
                         │  
        ┌────────────────┼────────────────┐  
        │                │                │  
     \[Agenda\]         \[Clientes\]     \[Tratamentos\]  
        │                │                │  
 \[Detalhe do         \[Perfil do       \[Cadastrar/  
 agendamento\]         cliente\]          editar\]  
        │  
 \[Pré-atendimento\]

---

## **Telas do cliente**

| Tela | O que mostra | Ações disponíveis |
| ----- | ----- | ----- |
| **01 — Login** | E-mail, senha e opções de acesso | Entrar, ir para cadastro |
| **02 — Cadastro** | Dados pessoais e campos obrigatórios | Preencher e salvar cadastro |
| **03 — Início** | Tratamentos, agendamentos e acesso ao perfil | Consultar tratamento, agendamento e perfil |
| **04 — Tratamentos** | Lista de tratamentos, preços e informações | Selecionar tratamento |
| **05 — Detalhe do tratamento** | Nome, descrição, preço e duração | Iniciar agendamento |
| **06 — Data e horário** | Datas e horários disponíveis | Escolher data e horário |
| **07 — Confirmação** | Resumo do agendamento | Confirmar agendamento |
| **08 — Agendamentos** | Agendamentos futuros e anteriores | Visualizar detalhes/cancelar |
| **09 — Detalhe do agendamento** | Tratamento, data, horário e status | Acessar pré-atendimento |
| **10 — Pré-atendimento** | Perguntas e termo | Responder e confirmar |
| **11 — Perfil** | Dados do cliente | Visualizar/editar dados |

## **Telas do profissional**

| Tela | O que mostra | Ações disponíveis |
| ----- | ----- | ----- |
| **12 — Login profissional** | E-mail e senha | Entrar |
| **13 — Dashboard/Agenda** | Agendamentos organizados por data e horário | Consultar atendimento |
| **14 — Clientes** | Lista de clientes cadastrados | Selecionar cliente |
| **15 — Perfil do cliente** | Dados básicos do cliente | Consultar informações |
| **16 — Pré-atendimento** | Respostas do questionário e termo | Consultar informações |
| **17 — Tratamentos** | Tratamentos cadastrados | Adicionar/editar |
| **18 — Cadastro de tratamento** | Nome, descrição, preço e duração | Salvar tratamento |

---

## **Estados vazios**

As telas deverão apresentar mensagens quando ainda não existirem dados.

### **Nenhum agendamento**

> "Você ainda não possui agendamentos."

### **Nenhum tratamento**

> "Nenhum tratamento disponível no momento."

### **Nenhum cliente**

> "Ainda não existem clientes cadastrados."

### **Nenhum pré-atendimento**

> "O pré-atendimento ainda não foi preenchido."

---

## **Rascunhos das telas**

Os desenhos/protótipos deverão ser armazenados em:

docs/telas/

Arquivos previstos:

* `01-login.png`

* `02-cadastro.png`

* `03-inicio.png`

* `04-tratamentos.png`

* `05-detalhe-tratamento.png`

* `06-agendamento.png`

* `07-confirmacao.png`

* `08-agendamentos.png`

* `09-pre-atendimento.png`

* `10-perfil.png`

* `11-area-profissional.png`

---

# **7\. Dados**

## **Persistência**

O aplicativo utilizará **Room** para armazenar os dados localmente.

### **Entidade: `Cliente`**

| Campo | Tipo | Obrigatório | Observação |
| ----- | ----- | ----- | ----- |
| `id` | Long | Sim | Chave primária, autogerada |
| `nome` | String | Sim | Nome completo |
| `cpf` | String | Sim | Identificação do cliente |
| `dataNascimento` | String | Sim | Data de nascimento |
| `telefone` | String | Sim | Telefone de contato |
| `email` | String | Sim | E-mail utilizado no login |
| `senha` | String | Sim | Senha para acesso ao MVP |
| `endereco` | String | Não | Informação opcional |
| `observacoes` | String | Não | Informação opcional |

### **Operações**

* (X) inserir

* (X) listar

* (X) atualizar

* (X) excluir

---

## **Entidade: `Profissional`**

| Campo | Tipo | Obrigatório | Observação |
| ----- | ----- | ----- | ----- |
| `id` | Long | Sim | Chave primária |
| `nome` | String | Sim | Nome do profissional |
| `email` | String | Sim | E-mail |
| `telefone` | String | Sim | Telefone |
| `especialidade` | String | Sim | Área de atuação |
| `senha` | String | Sim | Senha de acesso |

---

## **Entidade: `Tratamento`**

| Campo | Tipo | Obrigatório | Observação |
| ----- | ----- | ----- | ----- |
| `id` | Long | Sim | Chave primária |
| `nome` | String | Sim | Nome do tratamento |
| `descricao` | String | Sim | Descrição |
| `preco` | Double | Sim | Preço do tratamento |
| `duracao` | Int | Sim | Duração aproximada em minutos |
| `ativo` | Boolean | Sim | Define se aparece para o cliente |

### **Operações**

* (X) inserir

* (X) listar

* (X) atualizar

* (X) excluir

---

## **Entidade: `Agendamento`**

| Campo | Tipo | Obrigatório | Observação |
| ----- | ----- | ----- | ----- |
| `id` | Long | Sim | Chave primária |
| `clienteId` | Long | Sim | Cliente relacionado |
| `tratamentoId` | Long | Sim | Tratamento escolhido |
| `data` | String | Sim | Data do atendimento |
| `horario` | String | Sim | Horário |
| `status` | String | Sim | Agendado, Concluído ou Cancelado |

### **Operações**

* (X) inserir

* (X) listar

* (X) atualizar

* (X) excluir

---

## **Entidade: `PreAtendimento`**

| Campo | Tipo | Obrigatório | Observação |
| ----- | ----- | ----- | ----- |
| `id` | Long | Sim | Chave primária |
| `agendamentoId` | Long | Sim | Agendamento relacionado |
| `respostas` | String | Sim | Respostas do questionário |
| `termoAceito` | Boolean | Sim | Indica confirmação do termo |
| `dataPreenchimento` | String | Sim | Data do preenchimento |

### **Operações**

* (X) inserir

* (X) listar

* (X) atualizar

* ( ) excluir

---

## **Relacionamento dos dados**

CLIENTE  
   │  
   └──────\< AGENDAMENTO \>────── TRATAMENTO  
                │  
                │  
                └────── PRE-ATENDIMENTO

Um cliente poderá possuir vários agendamentos.

Um tratamento poderá estar associado a vários agendamentos.

Um agendamento poderá possuir um pré-atendimento.

---

# **8\. Arquitetura e tecnologias**

| Item | Escolha |
| ----- | ----- |
| **Linguagem** | Kotlin |
| **Interface** | Jetpack Compose |
| **Persistência** | Room |
| **Rede** | Não utilizada na v1.0 |
| **Arquitetura** | MVVM |
| **Outras bibliotecas** | Android Jetpack, Material 3 |
| **minSdk** | A definir pelo grupo |
| **targetSdk** | Versão estável utilizada no projeto |

## **Organização de pastas**

app/src/main/java/br/edu/ifpe/clientta/  
├── data/  
│   ├── database/  
│   │   ├── ClienttaDatabase.kt  
│   │   └── Converters.kt  
│   │  
│   ├── dao/  
│   │   ├── ClienteDao.kt  
│   │   ├── ProfissionalDao.kt  
│   │   ├── TratamentoDao.kt  
│   │   ├── AgendamentoDao.kt  
│   │   └── PreAtendimentoDao.kt  
│   │  
│   ├── entity/  
│   │   ├── Cliente.kt  
│   │   ├── Profissional.kt  
│   │   ├── Tratamento.kt  
│   │   ├── Agendamento.kt  
│   │   └── PreAtendimento.kt  
│   │  
│   └── repository/  
│  
├── ui/  
│   ├── screens/  
│   │   ├── login/  
│   │   ├── cadastro/  
│   │   ├── inicio/  
│   │   ├── tratamentos/  
│   │   ├── agendamentos/  
│   │   ├── preatendimento/  
│   │   ├── perfil/  
│   │   └── profissional/  
│   │  
│   ├── components/  
│   └── theme/  
│  
├── viewmodel/  
│  
└── MainActivity.kt

---

# **9\. Tratamento de erros**

| Situação de falha | O que o app faz | Mensagem para o usuário |
| ----- | ----- | ----- |
| **Campo obrigatório vazio** | Impede o envio do formulário e destaca o campo que precisa ser preenchido. | "Preencha todos os campos obrigatórios." |
| **E-mail inválido** | Impede o cadastro até que o formato seja corrigido. | "Digite um e-mail válido." |
| **CPF já cadastrado** | Impede a criação de uma conta duplicada. | "Este CPF já está cadastrado." |
| **E-mail já cadastrado** | Impede a criação de uma conta duplicada. | "Este e-mail já está cadastrado." |
| **Senha incorreta** | Impede o login. | "E-mail ou senha incorretos." |
| **Usuário não encontrado** | Impede o login. | "E-mail ou senha incorretos." |
| **Erro ao salvar cadastro** | Mantém o usuário na tela e permite tentar novamente. | "Não foi possível salvar o cadastro. Tente novamente." |
| **Erro ao carregar dados** | Mantém a tela aberta e oferece nova tentativa. | "Não foi possível carregar as informações." |
| **Erro ao salvar agendamento** | Não confirma o agendamento e permite nova tentativa. | "Não foi possível realizar o agendamento." |
| **Horário indisponível** | Impede o registro daquele horário. | "Esse horário não está disponível." |
| **Erro ao salvar pré-atendimento** | Mantém as respostas na tela sempre que possível. | "Não foi possível salvar o pré-atendimento." |
| **Termo não confirmado** | Impede a conclusão do pré-atendimento. | "Confirme que você leu o termo para continuar." |
| **Nenhum tratamento cadastrado** | Apresenta estado vazio. | "Nenhum tratamento disponível no momento." |
| **Nenhum agendamento** | Apresenta estado vazio. | "Você ainda não possui agendamentos." |
| **Nenhum cliente cadastrado** | Apresenta estado vazio ao profissional. | "Ainda não existem clientes cadastrados." |

> As operações de banco que possam lançar exceções deverão ser tratadas adequadamente para impedir o encerramento inesperado do aplicativo.

---

# **10\. Identidade visual e publicação**

| Item | Definição | Onde fica |
| ----- | ----- | ----- |
| **Nome do app** | Clientta | `strings.xml` |
| **Cor principal** | `#8F7AAE` | `Color.kt` |
| **Cor de fundo** | `#F7F3F8` | `Color.kt` |
| **Cor complementar** | `#F5EEDB` | `Color.kt` |
| **Cor complementar 2** | `#E8DFF0` | `Color.kt` |
| **Estilo** | Minimalista, acolhedor e profissional | Tema |
| **Ícone 512×512** | Símbolo relacionado a conexão, cuidado e organização | `loja/icone-512.png` |
| **applicationId** | `br.edu.ifpe.clientta` | `build.gradle.kts` |
| **versionName** | `1.0` | `build.gradle.kts` |
| **versionCode** | `1` | `build.gradle.kts` |

## **Material da loja**

| Artefato | Limite | Conteúdo |
| ----- | ----- | ----- |
| **Título** | 30 caracteres | Clientta |
| **Descrição curta** | 80 caracteres | Organização de atendimentos e agendamentos de clínicas. |
| **Descrição completa** | — | `loja/descricao.md` |
| **Imagem de destaque** | 1024×500 | `loja/destaque-1024x500.png` |
| **Screenshots** | Mínimo 2 | `loja/screenshots/` |
| **Esboço de privacidade** | — | `loja/privacidade.md` |
| **Arquivo `.aab`** | — | `loja/app-release.aab` |

## **Descrição inicial da loja**

O Clientta é um aplicativo desenvolvido para facilitar a organização de atendimentos em clínicas.

Com o aplicativo, clientes podem criar seu cadastro, consultar tratamentos, visualizar preços, realizar agendamentos e preencher informações de pré-atendimento.

Profissionais podem consultar clientes, organizar a agenda, visualizar agendamentos e acessar informações do pré-atendimento.

---

# **11\. Plano de testes**

| \# | O que testar | Passos | Resultado esperado | OK? |
| ----- | ----- | ----- | ----- | ----- |
| **T1** | Abrir o app pela primeira vez | Instalar e abrir | Tela inicial/login aparece corretamente |  |
| **T2** | Cadastro válido | Preencher todos os dados corretamente e salvar | Cadastro realizado e dados armazenados |  |
| **T3** | Cadastro incompleto | Deixar um campo obrigatório vazio | Cadastro não é salvo e mensagem aparece |  |
| **T4** | E-mail inválido | Informar um e-mail inválido | Aplicativo informa o erro |  |
| **T5** | CPF duplicado | Tentar cadastrar CPF já existente | Cadastro é bloqueado |  |
| **T6** | E-mail duplicado | Tentar cadastrar e-mail já existente | Cadastro é bloqueado |  |
| **T7** | Login válido | Informar e-mail e senha corretos | Usuário entra na conta |  |
| **T8** | Login inválido | Informar senha incorreta | Mensagem de erro aparece sem fechar o app |  |
| **T9** | Perfil | Entrar e acessar perfil | Dados cadastrados aparecem corretamente |  |
| **T10** | Editar perfil | Alterar telefone/e-mail e salvar | Alteração permanece após reabrir o perfil |  |
| **T11** | Tratamentos | Abrir lista de tratamentos | Tratamentos cadastrados aparecem |  |
| **T12** | Detalhe do tratamento | Selecionar tratamento | Nome, descrição, preço e duração aparecem |  |
| **T13** | Agendamento | Selecionar tratamento, data e horário | Agendamento é salvo |  |
| **T14** | Consulta de agendamento | Abrir "Meus agendamentos" | Agendamento aparece com dados corretos |  |
| **T15** | Horário indisponível | Tentar reservar horário já ocupado | Sistema impede duplicidade |  |
| **T16** | Pré-atendimento | Abrir pré-atendimento do agendamento | Questionário aparece |  |
| **T17** | Termo | Não confirmar termo e tentar concluir | Sistema impede conclusão |  |
| **T18** | Pré-atendimento concluído | Responder perguntas e confirmar termo | Status passa para concluído |  |
| **T19** | Área profissional | Entrar como profissional | Agenda e opções administrativas aparecem |  |
| **T20** | Clientes | Profissional acessar lista de clientes | Clientes cadastrados aparecem |  |
| **T21** | Pré-atendimento profissional | Abrir cliente/agendamento | Respostas do pré-atendimento aparecem |  |
| **T22** | Tratamento profissional | Criar/editar tratamento | Alteração aparece para o cliente |  |
| **T23** | Persistência | Fechar e abrir o aplicativo | Dados permanecem salvos |  |
| **T24** | Erro no banco | Simular erro durante operação | Mensagem aparece e o app não fecha |  |
| **T25** | Usuário externo | Entregar APK para pessoa de fora | Pessoa consegue realizar cadastro e agendamento sem explicação |  |

**Testado em:**

* Aparelho 1: \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_

* Android: \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_

* Aparelho 2: \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_

* Android: \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_

---

# **12\. Cronograma**

| Marco | Prazo | Responsável | Status |
| ----- | ----- | ----- | ----- |
| **M1 — Canvas \+ repositório** | 16/09 | Grupo | Concluído |
| **M2 — PRD aprovado \+ telas** | 30/09 | Grupo | Em andamento |
| **M3 — Cadastro \+ funcionalidade base** | 21/10 | Ana Clara \+ Sofia |  |
| **M4 — Dados e erros tratados** | 11/11 | Sofia \+ grupo |  |
| **M5 — Identidade visual \+ APK testado** | 25/11 | Letícia \+ grupo |  |
| **M6 — AAB \+ loja \+ README** | 02/12 | Maria Eduarda \+ grupo |  |
| **Entrega e apresentação** | **10/12** | Grupo |  |

## **Ordem de implementação recomendada**

Para evitar que o projeto avance para funcionalidades dependentes de dados que ainda não existem:

1. Estrutura inicial do projeto;

2. Tema e identidade visual básica;

3. Entidade `Cliente`;

4. Cadastro;

5. Validação do cadastro;

6. Login;

7. Perfil;

8. Entidade `Tratamento`;

9. Lista de tratamentos;

10. Entidade `Agendamento`;

11. Fluxo de agendamento;

12. Consulta de agendamentos;

13. Pré-atendimento;

14. Área profissional;

15. Testes;

16. Identidade visual final;

17. APK/AAB e documentação.

---

# **13\. Riscos**

| Risco | Impacto | Plano B |
| ----- | ----- | ----- |
| **Banco de dados ficar complexo** | Alto | Simplificar relacionamentos e implementar uma entidade por vez. |
| **Cadastro possuir campos demais** | Médio | Manter somente dados essenciais como obrigatórios. |
| **Problemas na autenticação local** | Médio | Simplificar o login para e-mail e senha armazenados localmente. |
| **Equipe ter dificuldade com Room** | Alto | Implementar primeiro apenas Cliente e depois adicionar as demais entidades. |
| **Interface ficar complexa** | Médio | Reduzir elementos e priorizar as quatro funcionalidades do MVP. |
| **Falta de tempo** | Alto | Priorizar cadastro, tratamentos, agendamento e pré-atendimento. |
| **Integrante ficar sem computador** | Médio | Revezar tarefas e utilizar o repositório GitHub para manter o projeto sincronizado. |
| **Código gerado por IA apresentar erro** | Médio | Revisar, testar e entender toda alteração antes de aceitar. |
| **Dados inconsistentes entre entidades** | Alto | Testar cada operação do Room individualmente antes de integrar as funcionalidades. |
| **APK apresentar problemas em outro aparelho** | Médio | Testar antecipadamente em pelo menos dois dispositivos reais. |

---

# **14\. Como vamos orientar a implementação com IA**

A implementação utilizará o **Gemini no Android Studio** como ferramenta de apoio ao desenvolvimento.

O PRD será utilizado como fonte principal para definir o comportamento esperado do aplicativo.

A IA não deverá criar funcionalidades fora deste documento sem autorização do grupo.

## **Recursos que vamos usar**

* (X) Chat

* (X) Agent Mode

* (X) Explain Code

* (X) Ask Gemini no Logcat

* (X) Generate Unit Tests

* (X) Transform UI

## **Regras do `AGENTS.md`**

1. A IA deve seguir os requisitos definidos no `PRD.md` e não adicionar funcionalidades que estejam fora do escopo sem autorização do grupo.

2. O código gerado ou alterado pela IA deve ser revisado, testado e compreendido por pelo menos uma integrante antes de ser aceito.

3. A IA deve priorizar soluções simples, compatíveis com Kotlin, Android Jetpack, Material 3 e Room, evitando complexidade desnecessária.

4. A IA deve manter a identidade visual do Clientta e não substituir as cores, componentes ou padrões definidos pelo grupo sem autorização.

5. A IA não deve receber senhas, chaves de API ou informações pessoais reais de clientes.

## **Divisão do perímetro explicável**

| Parte do código | Responsável principal |
| ----- | ----- |
| **Telas e navegação (`ui/`)** | Ana Clara |
| **Room e dados (`data/`)** | Sofia |
| **Identidade visual e recursos** | Letícia |
| **Build, documentação e artefatos** | Maria Eduarda |

Todas as integrantes deverão conhecer o funcionamento geral do projeto e conseguir realizar pequenas alterações fora de sua área principal.

## **Decisões do grupo contra sugestões da IA**

A preencher durante o desenvolvimento.

Exemplos de decisões que poderão ser registradas:

* O grupo optou por utilizar Room em vez de uma API.

* O grupo decidiu não implementar pagamentos.

* O grupo decidiu não implementar notificações push.

* O grupo decidiu manter o cadastro com poucos campos obrigatórios para facilitar o uso.

* O grupo decidiu não implementar autenticação externa.

---

# **15\. Histórico de versões deste documento**

| Versão | Data | Autor | O que mudou |
| ----- | ----- | ----- | ----- |
| **1.0** | 06/10/2026 | Grupo Clientta | Criação inicial do PRD com base no Canvas |
|  |  |  |  |
|  |  |  |  |

