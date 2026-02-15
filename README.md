# MVP RRM

Aplicação exemplo desenvolvida com Spring Boot para demonstrar um fluxo profissional de **vendas**, **financeiro (contas a pagar/receber)** e **resumo diário de negócio**.

## Tecnologias

- Java 21
- Spring Boot 3
- Spring Data JPA
- H2 (em memória)
- Springdoc OpenAPI 2
- Gradle

## Executando o projeto

1. Certifique-se de possuir o Java 21 instalado.
2. Execute os testes automatizados:

```bash
./gradlew test
```

3. Inicie a aplicação:

```bash
./gradlew bootRun
```

A API ficará disponível em `http://localhost:8080`.

## Documentação Swagger

Com a aplicação em execução, acesse a interface gráfica do Swagger para explorar os endpoints e modelos de dados:

```text
http://localhost:8080/swagger-ui.html
```

## Endpoints principais

- `POST /products` – cria um novo produto.
- `GET /products?q=term` – busca produtos pelo nome ou descrição.
- `POST /financial` – cria lançamento financeiro manual.
- `POST /sales` – processa uma venda completa (estoque + financeiro).
- `GET /dashboard/daily-summary?date=YYYY-MM-DD` – retorna indicadores do dia.

## Seed inicial (dados de demonstração)

Ao subir a aplicação, um seed automático é executado **somente se o banco estiver vazio**, criando:

- 3 produtos: `Camiseta`, `Calça`, `Tênis`.
- 1 cliente: `Consumidor Padrão`.
- 1 fornecedor: `Fornecedor Central`.
- 1 conta a pagar: `Aluguel` com vencimento em 5 dias.

> O seed está em `infrastructure.configuration.DataSeedConfiguration`.

Consulte a documentação Swagger para descrições detalhadas de parâmetros e modelos utilizados.
