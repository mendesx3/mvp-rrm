# Guia de integração do Front-end com o serviço MVP RRM

Este documento foi criado para acelerar a integração do front-end com a API do serviço **MVP RRM**.

## 1) Base URL e ambientes

- Base local (default): `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

> Dica para front: configurar `API_BASE_URL` por ambiente (`dev`, `homolog`, `prod`) e montar rotas relativas a partir dela.

---

## 2) Formato de dados

- `Content-Type`: `application/json`
- `UUID`: campos de identificação (`id`, `contactId`, `productId`) são UUID.
- Valores monetários (`BigDecimal`) vêm em JSON como número (ex.: `49.90`).
- Datas:
  - `LocalDate`: `YYYY-MM-DD`
  - `LocalDateTime`: `YYYY-MM-DDTHH:mm:ss`

---

## 3) Endpoints disponíveis para o front

## 3.1 Produtos

### `POST /products`
Cria um novo produto.

**Request**
```json
{
  "name": "Jaqueta",
  "description": "Jaqueta jeans masculina",
  "costPrice": 120.00,
  "salePrice": 199.90,
  "stockQuantity": 20
}
```

**Response 200**
```json
{
  "id": "8f35ee3a-2f8e-4f95-968a-08c7c5e2decb",
  "name": "Jaqueta",
  "description": "Jaqueta jeans masculina",
  "costPrice": 120.00,
  "salePrice": 199.90,
  "stockQuantity": 20
}
```

### `GET /products?q={texto}`
Busca produtos por nome ou descrição.

**Exemplo**
- `GET /products?q=cam`

**Response 200**
```json
[
  {
    "id": "58c183d3-b2d8-4f8a-93d5-df93dd59b89f",
    "name": "Camiseta",
    "description": "Camiseta básica algodão",
    "costPrice": 25.00,
    "salePrice": 49.90,
    "stockQuantity": 80
  }
]
```

---

## 3.2 Vendas

### `POST /sales`
Processa uma venda completa (estoque + financeiro).

**Request**
```json
{
  "contactId": "a5f10f0e-bf21-4729-bf98-c7b4f3e0708e",
  "paymentMethod": "PIX",
  "items": [
    {
      "productId": "58c183d3-b2d8-4f8a-93d5-df93dd59b89f",
      "quantity": 2
    }
  ]
}
```

**Response 200**
```json
{
  "id": "133a947b-52cb-4f90-9f2e-31ca69850784",
  "contactId": "a5f10f0e-bf21-4729-bf98-c7b4f3e0708e",
  "items": [
    {
      "productId": "58c183d3-b2d8-4f8a-93d5-df93dd59b89f",
      "quantity": 2,
      "unitPrice": 49.90,
      "totalItemValue": 99.80
    }
  ],
  "totalValue": 99.80,
  "paymentMethod": "PIX",
  "createdAt": "2026-01-15T14:32:10"
}
```

**Valores aceitos para `paymentMethod`**
- `DINHEIRO`
- `PIX`
- `CARTAO`
- `PRAZO`

---

## 3.3 Financeiro

### `POST /financial`
Cria um lançamento financeiro manual.

**Request**
```json
{
  "description": "Conta de energia",
  "value": 420.50,
  "dueDate": "2026-02-28",
  "type": "SAIDA",
  "contactId": "b6fb10de-f532-4be4-8b0b-c22a7109fe4c"
}
```

**Response 200**
```json
{
  "id": "32090d67-8b20-43c0-b885-70d65f26ff53",
  "description": "Conta de energia",
  "value": 420.50,
  "dueDate": "2026-02-28",
  "paymentDate": null,
  "type": "SAIDA",
  "status": "PENDENTE",
  "contactId": "b6fb10de-f532-4be4-8b0b-c22a7109fe4c"
}
```

**Valores aceitos para `type`**
- `ENTRADA`
- `SAIDA`

---

## 3.4 Dashboard

### `GET /dashboard/daily-summary?date=YYYY-MM-DD`
Retorna o resumo diário do negócio.

- O parâmetro `date` é opcional.
- Quando não enviado, a API usa a data atual.

**Response 200**
```json
{
  "totalSales": 4290.50,
  "totalToReceive": 1200.00,
  "totalToPay": 3500.00,
  "netCashFlow": 790.50
}
```

---

## 4) Contrato de erro (padrão)

Em falhas de validação/regra/negócio, a API retorna payload padronizado:

```json
{
  "mensagem": "Erro de validação nos dados enviados",
  "codigo": "VALIDACAO",
  "timestamp": "2026-01-15T17:01:20.123456-03:00",
  "detalhes": [
    "items[0].quantity: Quantidade do item deve ser no mínimo 1"
  ]
}
```

### Códigos de erro comuns

- `RECURSO_NAO_ENCONTRADO` (HTTP `404`)
- `REGRA_DE_NEGOCIO` (HTTP `400`)
- `VALIDACAO` (HTTP `400`)
- `ERRO_INTERNO` (HTTP `500`)

---

## 5) Fluxo recomendado para o front

1. **Carregar catálogo**: usar `GET /products?q=` (por texto digitado).
2. **Montar carrinho local** no front com os produtos retornados.
3. **Finalizar venda**: enviar `POST /sales` com `contactId`, `paymentMethod` e `items`.
4. **Consultar painel**: atualizar indicadores via `GET /dashboard/daily-summary`.
5. **Lançamentos manuais**: usar `POST /financial` quando necessário.

---

## 6) Observações importantes para integração

- Atualmente **não há endpoints de CRUD de contatos** na API pública. Para obter `contactId` em ambiente local de demonstração, consulte os contatos seeded no H2 (`/h2-console`) ou capture os IDs diretamente do banco.
- Os produtos seeded podem ser obtidos via busca (`GET /products?q=`).
- Não existe autenticação/autorização ativa neste serviço no estado atual.
- Não há configuração explícita de CORS no projeto. Se front e API estiverem em origens diferentes no navegador, pode ser necessário adicionar configuração de CORS no back-end.

---

## 7) Checklist rápido para o time de front

- [ ] Definir `API_BASE_URL` por ambiente.
- [ ] Implementar cliente HTTP com timeout e interceptação de erro.
- [ ] Tratar erros pelo campo `codigo` e exibir `mensagem`/`detalhes`.
- [ ] Padronizar envio de enums em maiúsculo (`PIX`, `SAIDA`, etc.).
- [ ] Garantir envio de UUIDs válidos nos campos de referência.
