# **AGENTS.md — Regras do Projeto Clientta**

## **1\. Sobre o projeto**

O Clientta é um aplicativo Android desenvolvido para gerenciamento de atendimentos em um espaço de beleza.

O aplicativo possui dois perfis principais:

* Cliente  
* Profissional

O projeto deve seguir os requisitos definidos no `PRD.md` e no `CANVAS.md`.

---

## **2\. Tecnologias**

O projeto deve utilizar:

* Kotlin  
* Android Studio  
* Jetpack Compose  
* Navigation Compose  
* Room  
* Coroutines  
* Flow  
* ViewModel  
* Repository

Não adicionar tecnologias ou bibliotecas desnecessárias sem justificativa.

---

## **3\. Arquitetura**

O projeto deve manter uma organização separando:

* interface do usuário;  
* modelos;  
* banco de dados;  
* DAO;  
* Repository;  
* ViewModel;  
* navegação.

Sempre que possível, evitar colocar regras de negócio diretamente nas telas.

---

## **4\. Interface**

A interface deve seguir a identidade visual definida no `PRD.md`.

Cores principais:

* Principal: \#8F7AAE  
* Fundo: \#F7F3F8  
* Complementar: \#F5EEDB  
* Complementar 2: \#E8DFF0

A interface deve ser simples, organizada e adequada para um aplicativo Android.

---

## **5\. Strings**

Textos exibidos para o usuário devem ficar no arquivo `strings.xml`.

Evitar deixar textos fixos diretamente no código das telas quando houver possibilidade de utilizar recursos de string.

---

## **6\. Banco de dados**

O armazenamento local deve utilizar Room.

As entidades, DAOs e operações do banco devem ser organizadas de forma separada.

Não armazenar senhas de forma insegura.

Os dados devem permanecer disponíveis mesmo depois que o aplicativo for fechado e aberto novamente.

---

## **7\. Tratamento de erros**

Operações que possam gerar erros devem possuir tratamento adequado.

Utilizar `try/catch` quando necessário.

As mensagens apresentadas ao usuário devem ser claras e simples.

Não mostrar mensagens técnicas ou stack traces diretamente para o usuário.

---

## **8\. Privacidade**

Não colocar no código:

* senhas;  
* chaves de API;  
* tokens;  
* informações pessoais desnecessárias;  
* credenciais.

Arquivos que contenham informações que não devem ser analisadas pela IA devem ser incluídos no `.aiexclude`.

---

## **9\. Alterações no código**

Antes de alterar um arquivo:

1. Verificar o código existente.  
2. Evitar modificar arquivos que não sejam necessários.  
3. Manter a estrutura existente quando ela estiver de acordo com o projeto.  
4. Não apagar funcionalidades existentes sem autorização.  
5. Testar as alterações realizadas.

Alterações grandes devem ser divididas em partes menores.

---

## **10\. Testes**

Toda funcionalidade nova deve ser testada antes de ser considerada concluída.

Verificar principalmente:

* funcionamento normal;  
* campos vazios;  
* dados inválidos;  
* erros de banco;  
* navegação;  
* persistência dos dados;  
* mensagens apresentadas ao usuário.

---

## **11\. Git e GitHub**

Não desenvolver diretamente na branch `main`.

Utilizar branches de acordo com o tipo de alteração:

* `feat/` para novas funcionalidades;  
* `fix/` para correções;  
* `docs/` para documentação;  
* `chore/` para ajustes gerais.

Os commits devem ser pequenos e descrever claramente o que foi feito.

Exemplos:

* `feat: criar tela de cadastro`  
* `feat: salvar cliente no Room`  
* `fix: validar email no cadastro`  
* `docs: atualizar PRD`

---

## **12\. Uso de IA**

A IA pode auxiliar no desenvolvimento, mas todo código gerado deve ser revisado pelos integrantes do grupo.

Antes de aceitar uma alteração:

1. Verificar o diff.  
2. Entender o que foi alterado.  
3. Testar o aplicativo.  
4. Confirmar que a alteração atende ao PRD.

O uso de IA deve ser registrado em `docs/USO_DE_IA.md`.

---

## **13\. Regra principal**

Não implementar funcionalidades que não estejam relacionadas ao projeto sem autorização.

Sempre priorizar:

1. requisitos do `PRD.md`;  
2. requisitos do `CANVAS.md`;  
3. funcionamento do aplicativo;  
4. simplicidade e organização do código;  
5. testes antes da entrega.

