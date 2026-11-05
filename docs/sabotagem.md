# Relatório de Testes de Sabotagem (UiState)

Este documento registra os testes de sabotagem realizados no aplicativo Clientta para validar o tratamento robusto dos quatro estados da tela (`Carregando`, `Vazio`, `Erro` e `Sucesso`), conforme exigido na Atividade.

## Tabela de Resultados dos Testes de Sabotagem

| Sabotagem | Como provocar | O que apareceu na tela | Status |
| :--- | :--- | :--- | :--- |
| **1. Lista vazia** | Inicializar o aplicativo com banco sem tratamentos cadastrados ou limpar dados locais. | Tela exibe mensagem centralizada: *"Você ainda não cadastrou nenhum tratamento. Toque no botão abaixo para começar."* e o botão *"Adicionar o primeiro"*. | Aprovado |
| **2. Sem internet** | Ativar o modo avião no dispositivo e abrir a tela de tratamentos. | Tela exibe mensagem de erro: *"Sem conexão. Verifique a internet e tente novamente."* e o botão *"Tentar novamente"* funcionando corretamente. | Aprovado |
| **3. Carregando visível** | Iniciar requisição de carregamento/sincronização de dados. | Indicador de progresso circular (`CircularProgressIndicator`) centralizado na tela enquanto a busca está em andamento. | Aprovado |
| **4. Campo obrigatório vazio** | Tentar salvar um formulário sem preencher os campos obrigatórios. | Exibição da mensagem de validação de campos obrigatórios (`error_required_fields`), impedindo o envio inválido. | Aprovado |
| **5. Erro do servidor** | Simular falha de resposta HTTP do servidor ou URL inválida na API. | Tela exibe mensagem de erro: *"Não foi possível carregar os dados do servidor."* acompanhada do botão *"Tentar novamente"*. | Aprovado |

## Conclusão
Em todos os cinco cenários de sabotagem, o aplicativo tratou a situação de forma controlada e amigável para o usuário, sem travamentos, sem telas em branco e cumprindo integralmente os requisitos da arquitetura de estados (`UiState`).
