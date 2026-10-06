# **🎯 Canvas do Projeto Final — App Android**

> 

|  |  |
| ----- | ----- |
|  |  |
| **Integrantes** | Ana Clara, Sofia, Letícia e Maria Eduarda |
| **Turma** | 3º ano — Ensino Médio |
| **Repositório** | `https://github.com/lmm5-web/projeto-final--clientta-` |
| **Data de preenchimento** | 06/10/2026 |
| **Entrega final** | **10/12/2026** |

---

# **🧩 Bloco 1 — Nome e pitch do app**

**Nome do app:** Clientta

**Pitch em uma frase:**

> O Clientta ajuda profissionais de clínicas de estética e outras clínicas a organizar clientes, atendimentos e horários, permitindo que o cliente consulte tratamentos, realize seu cadastro, agende um atendimento e preencha o pré-atendimento de forma digital, reduzindo o uso de papel e processos manuais.

**Objetivo principal do aplicativo:**

O Clientta será uma solução digital para facilitar a comunicação inicial entre clientes e clínicas, centralizando informações básicas do cliente, tratamentos disponíveis, agendamentos e documentos de pré-atendimento em um único aplicativo.

---

# **😖 Bloco 2 — Problema**

## **Qual problema estamos resolvendo?**

Atualmente, muitos clientes precisam entrar em contato diretamente com a clínica para descobrir informações sobre tratamentos, preços e horários disponíveis. Esse processo pode ser demorado e depende da disponibilidade do profissional para responder.

Além disso, após o agendamento, o cliente pode precisar preencher questionários, termos de responsabilidade e autorizações manualmente ou em papel. Para o profissional, isso gera grande quantidade de documentos e informações que precisam ser organizados e consultados posteriormente.

Por exemplo, profissionais como Danielle Macêdo Sales Mendes, da área de estética, podem precisar administrar simultaneamente horários, informações de clientes, questionários e documentos de atendimento.

A utilização de papel pode provocar perda de documentos, dificuldade para encontrar informações e confusão na organização dos atendimentos.

Também existe uma dificuldade para clientes que preferem pesquisar informações antes de entrar em contato diretamente com a clínica, principalmente preços, tratamentos e disponibilidade.

## **Como esse problema é resolvido hoje?**

* O cliente entra em contato com a clínica pelo WhatsApp ou presencialmente.

* Pergunta quais tratamentos estão disponíveis e seus preços.

* Conversa com o profissional para encontrar um horário disponível.

* Realiza o agendamento manualmente.

* Preenche questionários e termos em papel ou por diferentes meios.

* O profissional organiza a agenda e os documentos separadamente.

* As informações ficam espalhadas entre conversas, papéis e outros registros.

## **Problema central**

> **Falta de centralização das informações de clientes, tratamentos, agendamentos e pré-atendimento, tornando o processo mais demorado e sujeito a erros.**

---

# **👥 Bloco 3 — Público-alvo**

## **Público principal**

Adultos que utilizam serviços de clínicas de estética e outras clínicas que realizam atendimentos previamente agendados.

O aplicativo deverá priorizar:

* facilidade de uso;

* linguagem simples;

* poucos passos para realizar um agendamento;

* informações claras sobre tratamentos;

* formulário de cadastro objetivo;

* visual limpo e acessível.

## **Público secundário**

Profissionais responsáveis pelo atendimento e organização da clínica.

Esses usuários utilizarão o aplicativo principalmente para:

* consultar clientes cadastrados;

* visualizar informações dos clientes;

* consultar agendamentos;

* organizar horários;

* consultar respostas do pré-atendimento.

## **Quando e onde o app será utilizado?**

O cliente poderá acessar o aplicativo pelo celular quando quiser:

* conhecer os tratamentos disponíveis;

* consultar preços;

* criar sua conta;

* atualizar seus dados;

* realizar um agendamento;

* consultar seus agendamentos;

* preencher o pré-atendimento.

O aplicativo poderá ser divulgado nas redes sociais da clínica e por meio de um link enviado pelo WhatsApp.

## **Pessoa real que testará o app**

**Danielle Macêdo Sales Mendes**, profissional da área de estética.

Ela poderá avaliar principalmente:

* facilidade de cadastro;

* organização da agenda;

* informações apresentadas ao profissional;

* praticidade do pré-atendimento;

* clareza das telas.

---

# **👤 Bloco 4 — Cadastro e perfis de usuário**

> **Esta é uma das principais funcionalidades do Clientta. O cadastro deve ser simples para o cliente, mas permitir que a clínica tenha as informações necessárias para o atendimento.**

## **4.1 Quem pode se cadastrar?**

O aplicativo terá inicialmente dois tipos de usuário:

### **Cliente**

Pessoa que deseja consultar tratamentos e realizar atendimentos na clínica.

### **Profissional**

Usuário responsável por administrar os atendimentos e visualizar as informações dos clientes.

O tipo de usuário será definido durante o cadastro ou por uma forma controlada de acesso para profissionais.

> Para o MVP escolar, o cadastro de profissional poderá ser simplificado, evitando a criação de um sistema complexo de permissões.

---

## **4.2 Cadastro do cliente**

O cliente deverá preencher um formulário com informações essenciais.

### **Dados obrigatórios**

* Nome completo;

* CPF;

* Data de nascimento;

* Telefone;

* E-mail;

* Senha.

### **Dados opcionais**

* Endereço;

* Observações pessoais;

* Nome social, caso necessário;

* Outras informações que a clínica considere relevantes.

O formulário não deverá solicitar informações desnecessárias.

---

## **4.3 Regras do cadastro**

O sistema deverá verificar:

* se o nome foi preenchido;

* se o CPF foi preenchido;

* se a data de nascimento foi informada;

* se o telefone foi preenchido;

* se o e-mail possui formato válido;

* se a senha foi preenchida;

* se os campos obrigatórios não estão vazios.

Caso algum dado esteja incorreto, o aplicativo deverá informar claramente qual campo precisa ser corrigido.

### **Exemplos**

> "Preencha seu nome completo."

> "Digite um e-mail válido."

> "Informe seu telefone."

> "Preencha todos os campos obrigatórios."

---

## **4.4 Evitar cadastros duplicados**

O sistema deverá verificar se já existe um cliente cadastrado utilizando o mesmo CPF ou e-mail.

Se existir:

> "Já existe um cadastro com este CPF."

ou:

> "Este e-mail já está cadastrado."

O aplicativo não deverá criar um segundo cadastro para a mesma pessoa.

---

## **4.5 Login**

Depois de realizar o cadastro, o cliente poderá acessar sua conta utilizando:

* e-mail;

* senha.

Após o login, o aplicativo deverá identificar o usuário e mostrar as informações relacionadas à sua conta.

---

## **4.6 Perfil do cliente**

Depois de cadastrado, o cliente poderá acessar uma tela de perfil contendo:

* nome;

* telefone;

* e-mail;

* data de nascimento;

* CPF parcialmente ocultado;

* histórico de agendamentos;

* situação do pré-atendimento.

O cliente poderá editar informações permitidas, como telefone e e-mail.

Dados que não devem ser alterados facilmente, como CPF, poderão permanecer bloqueados no MVP.

---

## **4.7 Cadastro do profissional**

O profissional terá um cadastro próprio, contendo:

* nome;

* e-mail;

* senha;

* telefone;

* especialidade ou função na clínica.

Para evitar complexidade, o MVP poderá trabalhar com **um perfil profissional administrador**, responsável por visualizar e organizar os dados.

---

# **💡 Bloco 5 — Solução em uma tela**

## **Tela inicial**

A tela principal do Clientta deverá apresentar de maneira clara:

* nome/logo da clínica ou do Clientta;

* tratamentos disponíveis;

* preços;

* botão de agendamento;

* acesso aos agendamentos;

* acesso ao perfil;

* acesso ao pré-atendimento.

## **Principal ação**

A principal ação do cliente será:

> **Escolher um tratamento e realizar um agendamento.**

O caminho esperado será:

**Tela inicial → Tratamento → Agendamento → Confirmação → Pré-atendimento**

---

# **📅 Bloco 6 — Fluxo principal do cliente**

O fluxo principal do aplicativo será:

### **1\. Acesso**

O cliente abre o aplicativo.

### **2\. Cadastro**

Caso ainda não possua conta:

**Criar conta → preencher dados → validar dados → salvar cadastro**

### **3\. Login**

O cliente informa:

* e-mail;

* senha.

### **4\. Tela inicial**

O cliente visualiza os tratamentos disponíveis.

### **5\. Tratamento**

Ao selecionar um tratamento, poderá visualizar:

* nome;

* descrição;

* preço;

* duração aproximada;

* informações importantes.

### **6\. Agendamento**

O cliente seleciona:

* tratamento;

* data;

* horário disponível.

### **7\. Confirmação**

O aplicativo apresenta:

> Tratamento: Limpeza de pele  
>  Data: 20/11/2026  
>  Horário: 14:00

O cliente confirma o agendamento.

### **8\. Pré-atendimento**

Depois do agendamento, o cliente poderá acessar o questionário e os termos necessários.

### **9\. Finalização**

Após responder ao pré-atendimento, o sistema registra as informações e mostra:

> **Pré-atendimento concluído.**

---

# **📝 Bloco 7 — Pré-atendimento**

O pré-atendimento será integrado ao cadastro e ao agendamento.

## **Questionário**

O cliente poderá responder perguntas relacionadas ao atendimento.

Exemplos:

* Possui alguma alergia?

* Já realizou esse procedimento anteriormente?

* Utiliza algum medicamento?

* Possui alguma condição que deve ser informada ao profissional?

* Existe alguma informação importante que a clínica deve saber antes do atendimento?

As perguntas poderão variar de acordo com o tratamento.

## **Termos**

O aplicativo poderá apresentar termos de responsabilidade e consentimento.

O cliente deverá:

* visualizar o termo;

* marcar que leu;

* confirmar sua concordância.

O sistema deverá registrar que o termo foi aceito.

> O MVP não terá assinatura digital juridicamente avançada. Será utilizado apenas um registro de confirmação para fins acadêmicos e de demonstração.

---

# **📅 Bloco 8 — Agendamento**

O sistema deverá permitir que o cliente:

* escolha um tratamento;

* visualize datas disponíveis;

* escolha um horário;

* confirme o agendamento;

* consulte seus agendamentos futuros;

* visualize agendamentos anteriores.

## **Informações do agendamento**

Cada agendamento deverá possuir:

* cliente;

* tratamento;

* data;

* horário;

* status;

* observações, quando necessário.

## **Status**

O agendamento poderá possuir:

* **Agendado**

* **Concluído**

* **Cancelado**

No MVP, o cancelamento poderá ser realizado pelo profissional ou pelo cliente, conforme a regra definida pelo grupo.

---

# **🧑‍💼 Bloco 9 — Área do profissional**

O profissional terá acesso a uma área diferente da área do cliente.

## **O profissional poderá:**

* visualizar a agenda;

* visualizar os clientes cadastrados;

* consultar dados básicos dos clientes;

* consultar agendamentos;

* visualizar respostas do pré-atendimento;

* cadastrar tratamentos;

* editar tratamentos;

* alterar preços;

* organizar horários disponíveis.

## **Tela de agenda**

A agenda deverá mostrar:

**Data → Horários → Cliente → Tratamento → Status**

Exemplo:

| Horário | Cliente | Tratamento | Status |
| ----- | ----- | ----- | ----- |
| 09:00 | Ana Silva | Limpeza de pele | Agendado |
| 10:30 | Maria Souza | Peeling | Agendado |
| 14:00 | João Santos | Avaliação | Concluído |

---

# **✅ Bloco 10 — Funcionalidades do MVP**

O MVP continuará limitado a **4 funcionalidades principais**, agrupando recursos relacionados.

| \# | Funcionalidade | O que inclui | Essencial? | Quem utiliza |
| ----- | ----- | ----- | ----- | ----- |
| **F1** | **Cadastro e perfil** | Criar conta, login, validação dos dados, visualizar e editar perfil | **Sim** | Cliente e profissional |
| **F2** | **Tratamentos e agendamento** | Visualizar tratamentos/preços, escolher horário, realizar e consultar agendamentos | **Sim** | Cliente |
| **F3** | **Pré-atendimento** | Questionário, termos, confirmação e consulta das respostas | **Sim** | Cliente e profissional |
| **F4** | **Gestão da clínica** | Agenda, clientes, tratamentos, horários e informações dos atendimentos | **Sim** | Profissional |

---

# **🗃️ Bloco 11 — Estrutura inicial dos dados**

O aplicativo utilizará o **Room** para armazenar os dados localmente.

## **Entidade Cliente**

Principais campos:

* `id`

* `nome`

* `cpf`

* `dataNascimento`

* `telefone`

* `email`

* `senha`

## **Entidade Profissional**

* `id`

* `nome`

* `email`

* `telefone`

* `especialidade`

## **Entidade Tratamento**

* `id`

* `nome`

* `descricao`

* `preco`

* `duracao`

* `ativo`

## **Entidade Agendamento**

* `id`

* `clienteId`

* `tratamentoId`

* `data`

* `horario`

* `status`

## **Entidade PreAtendimento**

* `id`

* `agendamentoId`

* `respostas`

* `termoAceito`

* `dataPreenchimento`

> A estrutura poderá ser simplificada durante o desenvolvimento caso alguma relação fique complexa demais para o prazo.

---

# **🔐 Bloco 12 — Validação e segurança do cadastro**

Mesmo sendo um projeto acadêmico, o aplicativo deverá evitar exposição desnecessária dos dados.

## **Regras**

* Senha não deverá ser exibida na tela.

* CPF deverá aparecer parcialmente oculto quando mostrado no perfil.

* Campos obrigatórios deverão ser validados.

* O usuário não poderá acessar dados de outro cliente pela interface normal.

* O aplicativo deverá evitar armazenar informações desnecessárias.

* Dados de exemplo utilizados durante o desenvolvimento deverão ser fictícios.

> Como o MVP utiliza Room localmente, o sistema não terá autenticação real em servidor. O objetivo será demonstrar o funcionamento do fluxo de cadastro e login dentro do aplicativo.

---

# **🚫 Bloco 13 — Fora do escopo**

Para manter o projeto viável até dezembro, o Clientta **não fará nesta primeira versão**:

❌ Pagamentos pelo aplicativo.

❌ Chat ou atendimento por mensagem dentro do aplicativo.

❌ Integração automática com WhatsApp.

❌ Notificações push.

❌ Integração com Google Calendar.

❌ Sistema de recuperação de senha por e-mail.

❌ Autenticação por Google ou redes sociais.

❌ Assinatura digital juridicamente certificada.

❌ Sistema completo de múltiplas clínicas.

❌ Sincronização com servidor ou nuvem.

❌ Integração com sistemas externos de clínicas.

---

# **⚙️ Bloco 14 — Caminho técnico**

* **Opção A — Room:** dados armazenados localmente no dispositivo.

* Opção B — Retrofit.

* Opção C — API \+ favoritos locais.

## **Tecnologias**

* Kotlin;

* Android Studio;

* Jetpack Compose ou componentes Android escolhidos pelo grupo;

* Room;

* Android Jetpack;

* Material 3\.

## **Organização esperada**

**Interface → ViewModel → Repository → DAO → Room**

Essa separação deverá facilitar a manutenção e permitir que cada integrante compreenda a responsabilidade de cada camada.

---

# **⚠️ Bloco 15 — Tratamento de erros**

Operações que podem apresentar erro:

* cadastro;

* login;

* leitura dos dados;

* gravação no banco;

* edição do perfil;

* criação de agendamento;

* preenchimento do pré-atendimento.

O aplicativo deverá utilizar `try/catch` quando necessário para tratar exceções.

## **Mensagens para o usuário**

Em vez de mostrar erros técnicos, o aplicativo deverá apresentar mensagens simples.

Exemplos:

> "Não foi possível salvar seu cadastro. Verifique os dados e tente novamente."

> "Não foi possível realizar o agendamento."

> "Esse horário não está mais disponível."

> "Ocorreu um erro ao carregar suas informações."

> "Preencha todos os campos obrigatórios."

---

# **🎨 Bloco 16 — Identidade visual**

| Item | Definição |
| ----- | ----- |
| **Nome exibido** | Clientta |
| **Cor principal** | `#8F7AAE` |
| **Cor de fundo** | `#F7F3F8` |
| **Cor complementar** | `#F5EEDB` |
| **Cor complementar 2** | `#E8DFF0` |
| **Estilo** | Minimalista, acolhedor, profissional e moderno |
| **Ícone** | Símbolo relacionado a conexão, cuidado e organização |
| **applicationId** | `br.edu.ifpe.clientta` |
| **Versão inicial** | `1.0` |
| **versionCode** | `1` |

## **Princípios da interface**

A interface deverá:

* possuir poucos elementos por tela;

* utilizar textos objetivos;

* apresentar botões claramente identificados;

* manter padrão visual entre as telas;

* utilizar cores suaves;

* evitar excesso de informações;

* priorizar acessibilidade e legibilidade.

---

# **👥 Bloco 17 — Equipe e responsabilidades**

| Integrante | Papel principal | Responsável por |
| ----- | ----- | ----- |
| **Ana Clara** | Dev / telas | Interfaces, navegação, componentes e fluxo do usuário |
| **Sofia** | Dev / dados | Room, entidades, DAO, Repository e organização dos dados |
| **Letícia** | Design / identidade | Cores, ícones, componentes visuais e experiência do usuário |
| **Maria Eduarda** | Documentação / build | README, documentação, testes, organização e geração do APK/AAB |

Todas as integrantes participarão da programação.

A divisão define a principal responsabilidade de cada integrante, mas nenhuma parte deverá ser conhecida exclusivamente por uma pessoa.

---

# **⚠️ Bloco 18 — Riscos**

## **Risco 1 — Complexidade do cadastro**

**Problema:** adicionar muitos campos e regras pode deixar o desenvolvimento demorado.

**Plano B:** manter apenas os dados essenciais e deixar informações secundárias como opcionais.

## **Risco 2 — Banco de dados complexo**

**Problema:** relacionamentos entre clientes, tratamentos, agendamentos e pré-atendimentos podem gerar erros.

**Plano B:** simplificar as entidades e implementar uma funcionalidade por vez.

## **Risco 3 — Interface muito complexa**

**Problema:** muitas telas podem dificultar a navegação.

**Plano B:** utilizar um fluxo simples com poucas telas principais.

## **Risco 4 — Problemas com login**

**Problema:** autenticação local pode apresentar inconsistências.

**Plano B:** utilizar e-mail \+ senha armazenados localmente apenas para demonstração do MVP.

## **Risco 5 — Falta de tempo**

**Plano B:** priorizar, nesta ordem:

1. Cadastro;

2. Tratamentos;

3. Agendamento;

4. Pré-atendimento;

5. Área do profissional;

6. Melhorias visuais.

---

# **🤖 Bloco 19 — Acordo de trabalho com IA**

A implementação poderá utilizar o Gemini no Android Studio.

A IA deverá ser utilizada como ferramenta de apoio, e não como substituta do conhecimento das integrantes.

## **Regras**

1. A IA deverá seguir a proposta do Clientta e manter a interface simples, intuitiva, acessível e com cores neutras e pastéis.

2. Nenhum código gerado pela IA será aceito sem que uma integrante leia, teste e consiga explicar o funcionamento da alteração.

3. A IA deverá priorizar soluções simples e compatíveis com o MVP.

4. A IA não deverá criar funcionalidades que não estejam previstas no Canvas ou PRD sem autorização do grupo.

5. Nenhuma senha, chave de API ou informação sensível deverá ser enviada para a IA.

## **Combinados**

* Ninguém clica em **Accept** no Agent Mode sem revisar a alteração.

* Quem aceitar uma alteração deverá conseguir explicar o código.

* Antes de cada marco, o grupo fará uma revisão conjunta.

* Todas as integrantes deverão testar as principais funcionalidades.

* Erros encontrados deverão ser comunicados ao grupo.

* As integrantes deverão revezar tarefas sempre que possível.

---

# **🗓️ Bloco 20 — Marcos até 10/12**

| Marco | Prazo | Entrega |
| ----- | ----- | ----- |
| **M1 — Canvas** | 16/09 | `CANVAS.md` |
| **M2 — PRD \+ protótipo** | 30/09 | `PRD.md` \+ telas |
| **M3 — Cadastro** | 14/10 | Cadastro, validação e salvamento no Room |
| **M4 — Agendamento** | 21/10 | Tratamentos \+ criação/consulta de agendamentos |
| **M5 — Pré-atendimento** | 04/11 | Questionário \+ termos |
| **M6 — Área profissional** | 11/11 | Agenda \+ clientes \+ tratamentos |
| **M7 — Identidade visual** | 25/11 | Interface final \+ APK |
| **M8 — Entrega final** | 02/12 | AAB \+ README \+ documentação |
| **Apresentação** | **10/12** | `v1.0` |

---

# **🏁 Bloco 21 — Definição de pronto**

O grupo considerará o Clientta pronto quando:

* O aplicativo abre sem fechar inesperadamente.

* O usuário consegue criar uma conta.

* O cadastro valida campos obrigatórios.

* O sistema impede cadastro duplicado por CPF/e-mail.

* O usuário consegue realizar login.

* O usuário consegue visualizar seu perfil.

* O usuário consegue visualizar tratamentos e preços.

* O usuário consegue realizar um agendamento.

* O usuário consegue consultar seus agendamentos.

* O profissional consegue visualizar a agenda.

* O profissional consegue visualizar clientes cadastrados.

* O cliente consegue preencher o pré-atendimento.

* O profissional consegue consultar o pré-atendimento.

* Os dados são armazenados no Room.

* Erros não fazem o aplicativo fechar.

* Mensagens de erro são claras para o usuário.

* A interface possui identidade visual própria.

* O aplicativo possui nome e ícone próprios.

* Duas pessoas de fora do grupo conseguiram utilizar o APK sem explicação.

* O `README.md` explica o projeto.

* O `docs/USO_DE_IA.md` está preenchido.

* O `AGENTS.md` está preenchido.

* Todas as integrantes conseguem explicar as principais funcionalidades.

* Todas as integrantes conseguem realizar uma pequena alteração no projeto.

* Todos os arquivos possuem o comentário de fronteira exigido pelo projeto.

---

# **✍️ Validação do professor**

|  |  |
| ----- | ----- |
| **Data** |  |
| **Situação** | ( ) Aprovado    ( ) Aprovado com ajustes    ( ) Refazer |
| **Observações** |  |

### **O que foi fortalecido principalmente**

