# Projeto Testando conhecimentos - Java | Spring | Thymeleaf

## Páginas do projeto
<div style="display: inline_block"><br>
  <img align="center" alt="Java" height="60" width="70" src="https://raw.githubusercontent.com/ViniciusPelizzari/pessoa/main/projectbf/src/main/resources/static/img/page_formulario.png">
  <img align="center" alt="spring" height="60" width="70" src="https://raw.githubusercontent.com/ViniciusPelizzari/pessoa/main/projectbf/src/main/resources/static/img/page_resultado.png">
  <img align="center" alt="thymeleaf" height="60" width="70" src="https://raw.githubusercontent.com/ViniciusPelizzari/pessoa/main/projectbf/src/main/resources/static/img/page_sucesso.png">
</div>

## Tecnologias utilizadas
<div style="display: inline_block"><br>
  <img align="center" alt="Java" height="60" width="70" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-plain.svg">
  <img align="center" alt="spring" height="60" width="70" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg">
  <img align="center" alt="thymeleaf" height="60" width="70" src="https://seeklogo.com/images/T/thymeleaf-logo-6E4D42A713-seeklogo.com.png">
  <img align="center" alt="postgresql" height="60" width="70" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original.svg">
  <img align="center" alt="git" height="60" width="70" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/git/git-original.svg">
  <img align="center" alt="github" height="60" width="70" src="https://icon.icepanel.io/Technology/png-shadow-512/GitHub.png">
  <img align="center" alt="intellij" height="60" width="70" src="https://github.com/devicons/devicon/blob/master/icons/intellij/intellij-original.svg">
  <img align="center" alt="postman" height="60" width="70" src="https://icon.icepanel.io/Technology/svg/Postman.svg">
</div>

## Dependências do projeto
- Spring WEB
- Thymeleaf
- Spring Data JPA
- H2 DataBase
- PostgreSQL Driver

## Trechos de código

### Plug-in Maven
Garantir a execução do Maven.

```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-resources-plugin</artifactId>
	<version>3.1.0</version> <!--$NO-MVN-MAN-VER$ -->
</plugin>
```
(localizar arquivo pom.xml - build - plugins - inseri-lo).

### application.properties

```
spring.profiles.active=${APP_PROFILE:test}
spring.jpa.open-in-view=false

cors.origins=${CORS_ORIGINS:http://localhost:5173,http://localhost:3000}
```
(localizar pasta "resources" - criar arquivo "application.properties").

### application-test.properties
Configuração específico do ambiente de testes.

```
# H2 Connection
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# H2 Client
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Show SQL
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

(localizar pasta "resources" - criar arquivo "application-test.properties").
Banco de dados H2 (no navegador): http://localhost:8080/h2-console/

### application-dev.properties
Configuração específico do ambiente de desenvolvimento.

```
#spring.jpa.properties.jakarta.persistence.schema-generation.create-source=metadata
#spring.jpa.properties.jakarta.persistence.schema-generation.scripts.action=create
#spring.jpa.properties.jakarta.persistence.schema-generation.scripts.create-target=create.sql
#spring.jpa.properties.hibernate.hbm2ddl.delimiter=;

spring.datasource.url=jdbc:postgresql://localhost:5432/dscatalog
spring.datasource.username=postgres
spring.datasource.password=1234567

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=none
```

(localizar pasta "resources" - criar arquivo "application-dev.properties").
As linhas comentadas são responsáveis pela autocriação das tabelas do BD - Spring Boot e JPA.

### application-prod.properties
Configuração específico do ambiente de produção.

```
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=none
```

(localizar pasta "resources" - criar arquivo "application-prod.properties").

### system.properties
Especificando versão correspondente do Java.

```
java.runtime.version=17
```
(direto na pasta do projeto - criar arquivo "system.properties").

### WebConfig
Configuração de controle de acesso de recursos de origem cruzada (Cross-Origin Resource Sharing - CORS).

```java
@Configuration
public class WebConfig {

	@Value("${cors.origins}")
	private String corsOrigins;
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedOrigins(corsOrigins);
			}
		};
	}
	
}
```

(dentro da pasta grupo do projeto, criar classe "WebConfig", dentro da pasta "config").

##Script SQL 
Comandos SQL - criação tabelas - inserção de dados -> usado no ambiente de desenvolvimento.

```
create table projectbf (altura float(53) not null, idade integer not null, salario float(53) not null, id bigserial not null, nome varchar(255), primary key (id));
```

(colar no Query Tool - PostgreSQL ou Docker Compose - o que estiver usando).
