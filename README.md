# Hexagonal Product API

API REST para gerenciamento de produtos desenvolvida com Spring Boot 3 e Java 17, seguindo a arquitetura hexagonal (ports and adapters) e princípios SOLID.

## Tecnologias

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database (para desenvolvimento e testes)
- Lombok
- Maven
- Springdoc OpenAPI (Swagger)

## Arquitetura Hexagonal

Este projeto implementa a arquitetura hexagonal (também conhecida como ports and adapters), que separa claramente o domínio da aplicação de suas interfaces externas.

### Princípios

- **Independência do domínio**: O domínio não depende de nenhuma tecnologia específica de interface ou infraestrutura
- **Adaptadores plugáveis**: As interfaces externas podem ser trocadas sem afetar o domínio
- **Testabilidade**: O domínio pode ser testado isoladamente

### Estrutura do Projeto

- **Application**: Contém o domínio e as portas
  - `domain/model`: Entidades e objetos de valor do domínio
  - `domain/service`: Implementações dos serviços de domínio
  - `domain/exception`: Exceções de negócio
  - `port/in`: Portas de entrada (interfaces que o domínio expõe)
  - `port/out`: Portas de saída (interfaces que o domínio consome)

- **Adapter**: Implementa os adaptadores que conectam o mundo externo ao domínio
  - `in/web/controller`: Controladores REST
  - `in/web/dto`: DTOs de entrada e saída
  - `in/web/mapper`: Conversores entre DTOs e objetos de domínio
  - `in/web/exception`: Tratamento de exceções da API REST
  - `out/persistence`: Implementações de persistência
  - `out/persistence/entity`: Entidades JPA
  - `out/persistence/repository`: Repositórios Spring Data
  - `out/persistence/mapper`: Conversores entre entidades JPA e objetos de domínio

## Executando o projeto

### Pré-requisitos

- Java 17
- Maven

### Configuração do banco de dados

O projeto utiliza H2 Database em memória para facilitar testes e desenvolvimento.

### Executando a aplicação

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: http://localhost:8080

### Swagger UI

A documentação da API está disponível através do Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

## API REST Endpoints

| Método | URL                  | Descrição                      |
|--------|----------------------|--------------------------------|
| GET    | /api/products        | Lista todos os produtos        |
| GET    | /api/products/{id}   | Busca um produto pelo ID       |
| POST   | /api/products        | Cria um novo produto           |
| PUT    | /api/products/{id}   | Atualiza um produto existente  |
| DELETE | /api/products/{id}   | Remove um produto              |

## Exemplo de Requisição

### Criar Produto

```bash
curl -X POST 'http://localhost:8080/api/products' \
-H 'Content-Type: application/json' \
-d '{
    "name": "Smartphone XYZ",
    "description": "Smartphone com 8GB RAM, 128GB armazenamento",
    "price": 1299.99,
    "quantity": 50,
    "sku": "PROD-12345"
}'
```

### Resposta

```json
{
    "id": 1,
    "name": "Smartphone XYZ",
    "description": "Smartphone com 8GB RAM, 128GB armazenamento",
    "price": 1299.99,
    "quantity": 50,
    "sku": "PROD-12345"
}
``` 