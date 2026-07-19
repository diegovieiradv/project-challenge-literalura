# LiterAlura - Challenge

API REST para catalogo de livros consumindo a Gutendex API - projeto Oracle Next Education.

## Descricao

Projeto desenvolvido como desafio do Oracle Next Education (ONE). Cataloga livros consumindo a Gutendex API, permitindo busca por idioma, ranking e autor.

## Funcionalidades

- Cadastro de livros via API Gutendex
- Listagem de livros por idioma (PT, EN, ES, FR)
- Listagem de livros mais baixados
- Busca de livros por titulo
- Cadastro de autores
- Listagem de autores vivos em determinado ano
- Persistencia com JPA/Hibernate

## Tecnologias

- **Linguagem:** Java 17
- **Framework:** Spring Boot
- **ORM:** Spring Data JPA
- **Banco de Dados:** PostgreSQL (H2 para testes)
- **API:** Gutendex (gutendex.com)
- **Build:** Maven

## Como Rodar

```bash
# Crie o banco PostgreSQL (opcional, pode usar H2)
CREATE DATABASE literalura;

# Execute
./mvnw spring-boot:run
```

## Endpoints

| Metodo | Rota | Descricao |
|--------|------|-----------|
| GET | /livros | Listar livros |
| GET | /livros/idioma/{idioma} | Filtrar por idioma |
| GET | /livros/top10 | Top 10 mais baixados |
| GET | /autores | Listar autores |

## Licenca

MIT License - Diego Vieira
