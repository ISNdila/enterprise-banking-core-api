# README.md
```markdown
# Enterprise Banking Core API
A high-performance, secure, decoupled multiaccount banking engine and digital ledger backend built with **Java Spring Boot 3.x**, **Spring Security**, and **PostgreSQL**.
This system acts as a headless REST API designed to power modern digital banking applications. It handles complex relational domain structures allowi
time financial statements, manage tokenized digital cards, process utility transactions (airtime, electricity, vouchers), and receive localized notifications
---
## 🏗️ Architectural Overview
This system follows a modern **Decoupled API Architecture**, enforcing a strict separation of concerns between the presentation layer and data persis
```text
+--------------------------------------------+
| Client Presentation (Tailwind/JS SPA) |
+--------------------------------------------+ 
                    │
                    ▼ (Secure REST JSON via Global CORS)
+--------------------------------------------+
| Spring Boot 3 Core Engine Layer |
| [Security] -> [Controller] -> [Service] |
+--------------------------------------------+
                    │
                    ▼ (Spring Data JPA / Hibernate)
+--------------------------------------------+
| Persistent Storage (PostgreSQL) |
+--------------------------------------------+
```
### Key Technical Implementations:
* **Proxy Pattern Data Access:** Utilizes Spring Data JPA interfaces to dynamically construct optimized SQL operations at runtime, removing database
* **ACID-Compliant Transactions:** Uses declarative transaction management (@Transactional) to enforce absolute data integrity during multiaccount fund transfers and utility purchases.
* **Global Security Filters:** Implements Spring Security Basic Authentication protocols alongside global CORS mapping configurations to handle deco
* **OnDemand Statement Synthesis:** Computes ledger balances and account statement profiles dynamically across historic transaction tables without main
## 🚀 Local Setup & Installation Instructions
Follow these steps to clone, configure, and execute the core banking API server on your local environment.
### Prerequisites
Ensure you have the following tools installed on your development machine:
* **Java Development Kit (JDK) 17** or higher
* **Apache Maven 3.8+**
* **PostgreSQL 15+** (Local service or Cloud instance like Supabase/Neon)
* An API Client (like **Postman** or **Insomnia**)
### Step 1: Clone the Repository
Open your terminal or command prompt, navigate to your working directory, and clone the code:
```bash
git clone [https://github.com/your-username/enterprise-banking-core-api.git](https://github.com/your-username/enterprise-banking-core-api.git)
cd enterprise-banking-core-api
```
### Step 2: Configure the Database Connection
Navigate to src/main/resources/ and open the application.properties file. Update the datasource properties to match your database environment.
```properties
spring.application.name=banking-api
# PostgreSQL Connection Pipeline Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/nmu_bank_db
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
spring.datasource.driver-class-name=org.postgresql.Driver
# Hibernate ORM Table Lifecycle Automation
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
> ⚠️ **Note:** Create an empty database named nmu_bank_db inside your PostgreSQL instance before starting up the server. Hibernate will automatic
>
### Step 3: Build and Run the Server
Compile the dependencies and execute the Spring Boot application profile using the included Maven wrapper script:
**On Linux/macOS:**
```bash
./mvnw clean package -DskipTests
./mvnw spring-boot:run
```
**On Windows (Command Prompt):**
```cmd mvnw.cmd clean package -DskipTests mvnw.cmd spring-boot:run
```
Look for the confirmation block in your terminal output log:
Started BankingApiApplication in X seconds (JVM running for Y)
The backend is now live and listening for secure incoming traffic at: **http://localhost:8080**
## 📋 Core API Endpoint Mapping Reference
| HTTP Method | API Route Extension | Access Level | Description |
|---|---|---|---|
| **POST** | /api/auth/register | Public | Registers a user profile with preferred South African language strings. |
| **POST** | /api/auth/login | Public | Evaluates credential authenticity and registers user session states. |
| **GET** | /api/accounts | Authenticated | Fetches all portfolio bank account details (Savings, Credit, Check) for the active user. |
| **POST** | /api/accounts/create | Authenticated | Provisions a new account variant assigned with a unique 10-digit SA standard token string. |
| **POST** | /api/wallets/transfer | Authenticated | Executes atomic ACID remittance balancing entries between two accounts. |
| **GET** | /api/statements/{accountId}/download | Authenticated | Generates a dynamic comprehensive ledger statement breakdown object. |
| **GET** | /api/cards/account/{accountId} | Authenticated | Retrieves current digital and virtual tokenized credit cards for an account. |
| **GET** | /api/utilities/products | Authenticated | Streams active prepaid utility product catalog configurations. |
| **GET** | /api/notifications | Authenticated | Gathers unified promotional alerts and personalized transaction status entries. |
## 👥 Portfolio Author
* **Developer:** Your Name
* **Role:** Backend Software Engineer / Computer Hardware Technician
* **Connect:** [![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/isndila)
```

