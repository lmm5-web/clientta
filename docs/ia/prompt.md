# Instruções para o Gemini (Android Studio) - Projeto Clientta

Você está atuando como assistente de desenvolvimento do projeto **Clientta** (um app Android em Kotlin, Jetpack Compose, Material 3 e Room Database para clínicas de estética).

## OBJETIVO PRINCIPAL:
Sua tarefa é ajudar a construir as telas, navegações, banco de dados (Room) e lógicas do MVP conforme definido no PRD e no Canvas do projeto.

## REGRA OBRIGATÓRIA DE DOCUMENTAÇÃO (OUTPUT.MD):
Sempre que você criar, alterar ou implementar qualquer arquivo, código, estrutura de banco de dados ou tela neste projeto, **você DEVE documentar detalhadamente o que foi feito no arquivo `output.md`**.

O arquivo `output.md` deve conter:
1. **Data e Hora** da alteração.
2. **Resumo da Tarefa Executada** (ex: criação das entidades do Room, implementação da Tela de Login, configuração do NavGraph, etc.).
3. **Lista de Arquivos Criados ou Modificados** (caminho completo de cada arquivo).
4. **Instruções de Uso ou Teste** (como o grupo deve testar o código gerado no Android Studio).

---

## CONTEXTO TÉCNICO E VISUAL DO CLIENTTA:
- **Cores Oficiais**: Cor principal `#8F7AAE` (Roxo suave), fundo `#F7F3F8`, complementares `#F5EEDB` e `#E8DFF0`.
- **Arquitetura**: MVVM (Model-View-ViewModel-Repository-DAO).
- **Entidades do Room**: Cliente, Profissional, Tratamento, Agendamento e PreAtendimento.

## PRÓXIMO PASSO IMEDIATO:
Por favor, leia estas instruções, comece estruturando a base de dados do Room (Entidades e DAOs) e **registre o início do trabalho e o código gerado dentro do arquivo `output.md`**.