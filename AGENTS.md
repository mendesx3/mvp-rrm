# AGENTS.md

## Escopo
Estas instruções valem para todo o repositório `mvp-rrm`.

## Visão geral do projeto
- Stack principal: **Java 21**, **Spring Boot 3**, **Gradle**.
- Arquitetura em camadas com separação de responsabilidades:
  - `com.mvp.core.domain`: entidades e regras de domínio puras.
  - `com.mvp.core.usecase`: casos de uso da aplicação.
  - `com.mvp.core.ports`: contratos/portas para infraestrutura.
  - `com.mvp.infrastructure`: implementações e configuração de aplicação.
  - `com.mvp.adapters.api`: controllers REST, tratamento de erro e DTOs.

## Convenções de desenvolvimento
- Preserve a separação entre domínio, casos de uso, adapters e infraestrutura.
- Evite dependências do Spring dentro de `core.domain` e `core.usecase`.
- Ao adicionar funcionalidade:
  1. Crie/ajuste entidades e regras no domínio.
  2. Exponha o contrato em `ports` se necessário.
  3. Implemente o caso de uso em `core.usecase`.
  4. Faça wiring no `UseCaseConfig`.
  5. Exponha via controller/DTO em `adapters.api`.
- Mantenha mensagens, descrições e documentação da API em **português**.

## API, validação e erros
- Sempre que alterar endpoints, atualize anotações OpenAPI/Swagger.
- Prefira `@Valid` + constraints nos DTOs de entrada.
- Padronize erros amigáveis via `GlobalExceptionHandler`.
- Em regras de negócio, prefira exceções de domínio (ex.: falta de estoque, recurso não encontrado).

## Seed de dados
- O seed inicial oficial está em `com.mvp.infrastructure.configuration.DataSeedConfiguration`.
- O seed deve rodar apenas quando o banco estiver vazio.
- Para demonstrações, mantenha ao menos produtos, cliente padrão, fornecedor e uma conta a pagar de exemplo.

## Build e testes
- Comando padrão de validação:
  - `./gradlew test`
- Para execução local:
  - `./gradlew bootRun`

## Diretrizes para mudanças
- Faça mudanças pequenas, focadas e coerentes com o estilo atual.
- Não introduza frameworks adicionais sem necessidade clara.
- Em novas regras de negócio, prefira validar no domínio/caso de uso, não no controller.
