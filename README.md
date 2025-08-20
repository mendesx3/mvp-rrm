# MVP RRM

Aplicação exemplo desenvolvida com Spring Boot para demonstrar um fluxo simples de **produtos** e **carrinho de compras**. A API agora possui documentação completa utilizando o Swagger/OpenAPI, totalmente escrita em português.

## Tecnologias

- Java 21
- Spring Boot 3
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

```
http://localhost:8080/swagger-ui.html
```

## Endpoints principais

- `POST /products` – cria um novo produto.
- `GET /products?q=term` – busca produtos pelo nome ou descrição.
- `POST /carts` – cria um novo carrinho de compras.
- `GET /carts/{id}` – consulta um carrinho existente.
- `POST /carts/{id}/items` – adiciona itens ao carrinho.
- `POST /carts/{id}/checkout` – finaliza o carrinho e gera um pedido.

Consulte a documentação Swagger para descrições detalhadas de parâmetros e modelos utilizados.

