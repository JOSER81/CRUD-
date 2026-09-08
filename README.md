# Filmes API — CRUD Completo (Java + Spring Boot)

API REST para gerenciamento de filmes, com CRUD completo e os status codes conforme especificado.

## Tecnologias
- Java 17
- Spring Boot 3.3.2 (Web, Data JPA, Validation)
- H2 Database (em memória, para facilitar testes — troque por MySQL/Postgres se quiser persistência real)
- Maven

## Como rodar

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.
Console do H2 (opcional): `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:filmesdb`, usuário `sa`, senha em branco).

## Endpoints

| Método | Rota           | Status  | Descrição                  |
|--------|----------------|---------|-----------------------------|
| POST   | `/filmes`      | 201     | Cria um filme               |
| GET    | `/filmes`      | 200     | Lista todos os filmes       |
| GET    | `/filmes/{id}` | 200     | Busca um filme por id       |
| PUT    | `/filmes/{id}` | 201     | Atualiza um filme existente |
| DELETE | `/filmes/{id}` | 204     | Deleta um filme             |

## Exemplo de corpo (POST/PUT)

```json
{
  "titulo": "Interestelar",
  "diretor": "Christopher Nolan",
  "genero": "Ficção Científica",
  "anoLancamento": 2014,
  "duracaoMinutos": 169
}
```

## Exemplos com curl

```bash
# Criar
curl -X POST http://localhost:8080/filmes \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Interestelar","diretor":"Christopher Nolan","genero":"Ficção Científica","anoLancamento":2014,"duracaoMinutos":169}'

# Listar todos
curl http://localhost:8080/filmes

# Buscar por id
curl http://localhost:8080/filmes/1

# Atualizar
curl -X PUT http://localhost:8080/filmes/1 \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Interestelar (Dublado)","diretor":"Christopher Nolan","genero":"Ficção Científica","anoLancamento":2014,"duracaoMinutos":169}'

# Deletar
curl -X DELETE http://localhost:8080/filmes/1
```

## Estrutura do projeto

```
src/main/java/com/example/filmesapi/
├── FilmesApiApplication.java      # classe principal
├── model/Filme.java               # entidade JPA
├── repository/FilmeRepository.java
├── service/FilmeService.java      # regras de negócio
├── controller/FilmeController.java
└── exception/
    ├── ResourceNotFoundException.java
    └── GlobalExceptionHandler.java  # 404 e 400 tratados
```

Validações (Bean Validation) garantem 400 com mensagens claras se `titulo`, `diretor`, `genero` ou `anoLancamento` estiverem inválidos/ausentes. Buscar/atualizar/deletar um id inexistente retorna 404.
