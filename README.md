#  Sistema de Gestão de Despesas Pessoais (Finance Manager)

Um backend em Spring Boot para controle financeiro pessoal, com autenticação via JWT, cadastro de usuários e despesas, e geração de relatórios mensais.

---

## 🔍 Sobre

O Finance Manager permite que usuários registrem e acompanhem suas despesas de forma segura e organizada, com acesso autenticado via token JWT. Inclui recursos de CRUD completo, categorização e estatísticas mensais.

---

## 🚀 Features

- ✅ Autenticação e Autorização com JWT
- 👥 CRUD completo para Usuários
- 💸 CRUD completo para Despesas com categorias
- 📈 Relatórios mensais com agregações de gastos
- 📄 Integração com Swagger para documentação da API
- ⚠️ Tratamento centralizado de exceções
- 🧪 Testes unitários com JUnit e Mockito

---

## 🛠️ Tecnologias

- Java 17
- Spring Boot 3.4.3
- Spring Security + JWT
- Spring Data JPA
- MySQL (produção) / H2 (testes)
- Swagger (SpringDoc OpenAPI)
- JUnit 5 + Mockito

---

## 🏛️ Arquitetura

### 📁 **Camadas do Projeto**

#### 1. **Modelos (Entidades)**
- `Category`: categoria de despesa
- `User`: credenciais e informações básicas
- `Role`: permissões e perfis de usuário
- `Expense`: registros financeiros por usuário

#### 2. **DTOs (Transferência de Dados)**
- `AuthRequest` / `AuthResponse`: login e resposta com token
- `MonthlyStatsDTO`: agregações mensais
- `ReportDTO`: filtros de relatório

#### 3. **Repositórios (Repositories)**
- `UserRepository`
- `ExpenseRepository`
- `CategoryRepository`

#### 4. **Serviços (Services)**
- `UserService`
- `ExpenseService`
- `CategoryService`
- `ReportService`

#### 5. **Controladores (Controllers)**
- `AuthenticationController`: login e registro
- `UserController`: CRUD de usuários
- `ExpenseController`: CRUD de despesas
- `ReportController`: estatísticas mensais
- `CategoryController`: categorias de despesas

#### 6. **Segurança (Security)**
- `AppSecurityConfig`: configurações de segurança
- `JwtUtil`: geração e validação de tokens
- `JwtAuthenticationFilter`: intercepta requisições
- `CustomUserDetailsService`: autenticação customizada

#### 7. **Tratamento de Exceções (Exception Handling)**
- `UserNotFoundException`: exemplo de exceção customizada
- `GlobalExceptionHandler`: handler com `@ControllerAdvice`

#### 8. **Configurações (Config)**
- `CorsConfig`: configuração de CORS
- `SwaggerConfig`: documentação da API

---

## ⚙️ Instalação

```bash
git clone https://github.com/seu-usuario/finance-manager.git
cd finance-manager
./mvnw clean install
./mvnw spring-boot:run
```

---

## 🔧 Configuração

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finance_manager_db
spring.datasource.username=root
spring.datasource.password=senha_do_mysql
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
jwt.secret=umaChaveSuperSecretaComPeloMenos32Chars
jwt.expiration=86400000

server.port=8080
```

> Em modo de testes, o H2 é usado automaticamente.

---

## ▶️ Uso

### Registro
```http
POST /auth/register
```

### Login
```http
POST /auth/login
```

Depois do login, use o token JWT em “Authorization → Bearer Token” para acessar as demais rotas.

---

## 📡 Endpoints da API

### 🔐 Autenticação
| Método | Rota              | Descrição                |
|--------|-------------------|--------------------------|
| POST   | `/auth/register` | Registra novo usuário    |
| POST   | `/auth/login`    | Autentica e retorna JWT  |

### 👤 Usuários
| Método | Rota      | Descrição                 |
|--------|-----------|---------------------------|
| GET    | `/users`  | Lista usuários            |
| GET    | `/users/{id}` | Busca usuário por ID      |
| POST   | `/users`  | Cria novo usuário         |
| PUT    | `/users/{id}` | Atualiza usuário          |
| DELETE | `/users/{id}` | Remove usuário            |

### 💸 Despesas
| Método | Rota            | Descrição                |
|--------|------------------|--------------------------|
| GET    | `/expenses`     | Lista todas as despesas  |
| GET    | `/expenses/{id}`| Busca despesa por ID     |
| POST   | `/expenses`     | Cria nova despesa        |
| PUT    | `/expenses/{id}`| Atualiza despesa         |
| DELETE | `/expenses/{id}`| Remove despesa           |

### 📂 Categorias
| Método | Rota            | Descrição                      |
|--------|------------------|--------------------------------|
| GET    | `/categories`   | Lista todas as categorias      |
| POST   | `/categories`   | Cria nova categoria            |

### 📊 Relatórios
| Método | Rota                      | Descrição                      |
|--------|---------------------------|--------------------------------|
| GET    | `/reports/monthly-stats` | Estatísticas agregadas mensais |

---

## ✅ Testes

```bash
./mvnw test
```

- Testes para autenticação, filtros JWT e CRUD
- Cobertura com foco em serviços e controllers
- Uso de JUnit 5 + Mockito
- Banco H2 para ambiente de testes

---

## 🔒 Segurança

- Tokens JWT assinados com chave secreta
- Stateless: sem sessões de servidor
- Rotas públicas: `/auth/**`, `/swagger-ui/**`, `/v3/api-docs/**`
- Demais rotas protegidas via `Bearer Token`
- Filtro JWT intercepta e valida tokens em cada requisição

---

## 🤝 Contribuindo

1. Forke o projeto
2. Crie uma branch com sua feature
3. Commit suas alterações
4. Push para seu repositório remoto
5. Crie um Pull Request

---

## 📄 Licença

Distribuído sob a licença **MIT**. Veja o arquivo `LICENSE.md`.

---

## 👤 Autor

**Letícia Araujo**  
GitHub: [@Leticia0587](https://github.com/Leticia0587)  
Linkedin: [leticiadearaujo0305](https://www.linkedin.com/in/leticiadearaujo0305/)

---
