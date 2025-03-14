# Consulta Credito - Backend

Este repositório contêm o backend do sistema de [Consulta de Credito](https://github.com/Trajy/Desafio-Consulta-Credito). Deselvolvido utilizando Spring Framework.

## Sumário
  - [Endpoints](#endpoints)
  - [Modulo DRY, Low Code, Code Reuse](#modulo-dry-low-code-code-reuse)
  - [Tratamento de Erros](#tratamento-de-erros)
  - [Documentação Automática com Swagger](#documentação-automática-com-swagger)

## Endpoints

| Metodo | Rota | Descrição |
|--------|------|-----------|
| GET | /api/creditos/{numeroNfse} | Retorna lista de dados de credito referentes ao numero da nfse |
| GET | /api/creditos/credito/{numeroCredito} | Retorna dados de credito a partir do numero de crédito |

> [!IMPORTANT]
> Documentação na integra sobre os end-points pode ser acessada ao executar o sistema em [http://localhost:8090/api/swagger-ui/index.html](http://localhost:8090/api/swagger-ui/index.html).
>

## Modulo DRY, Low Code, Code Reuse
A aplicação possui o módulo para reutilização de codigo e implementação dos padrões de projeto [Strategy](https://refactoring.guru/design-patterns/strategy) e [Template Method](https://refactoring.guru/design-patterns/template-method).

#### O Módulo oferece suporte para:
- Desenvolvimento de Arquitetura Hexagonal Limpa (Clean Code, KISS).
- Implementação de CRUD com reutilização de código (fóco no desenvolvimento das regras de negócio).
- Geração de documentação automatica utilizando Swagger.
- Configuração automatica para gerenciamento de exceptions e formatação para [RFC7807](https://datatracker.ietf.org/doc/html/rfc7807) (Norma para padronização de retorno de erros em Rest Apis).

> [!IMPORTANT]
> Uma sugestão de documentação desenvolvida utilizando Quarkus, porêm, os exemplos são aplicados de forma similar no Spring, pode ser encontrada em [Trajy/Quarkus Base Architecture](https://github.com/Trajy/Quarkus-Base-Architecture?tab=readme-ov-file#quarkus-base-architecture).

O módulo está no repositório [Trajy/Spring-Base-Architecture](https://github.com/Trajy/Spring-Api-Architecture), o módulo foi adicionado ao projeto utilizando Maven como gerenciador de dependencias.

```xml
    <repositories>
            <repository>
                <id>jitpack.io</id>
                <url>https://jitpack.io</url>
            </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>com.github.Trajy</groupId>
            <artifactId>Spring-Api-Architecture</artifactId>
            <version>main-SNAPSHOT</version>
        </dependency>
    </dependencies>
```


## Tratamento de Erros
A configuração do gerenciador de exceptions pode ser feita de forma simples.

```Java

import br.com.trajy.architecture.restful.exception.RestGlobalExecptionHandler;

@Import({
        RestGlobalExecptionHandler.class //Configura o gerenciador de exceptions
})
@Configuration
public class AutoConfigurations {

}
```

Exemplo de resposta de erro.

```json
{
    "status": "404",
    "title": null,
    "type": "/api/creditos/credito/12345",
    "detail": "Credito not found for numeroCredito: 12345"
}
```

## Documentação Automática com Swagger

Para gerar a documentação automaticamente basta importar a classe de configuração e os endpoints serão documentados.

O nome da aplicação sera obtido a partir da propriedade `spring.application.name` no arquivo `application.yml` ou `application.properties`

```java
package br.com.trajy.consultacreditoapi.config;

import br.com.trajy.architecture.openapi.OpenApiCoreConfig;

@Import({
        OpenApiCoreConfig.class
})
@Configuration
public class AutoConfigurations {

}
```
basta acessar [http://localhost:8090/api/swagger-ui/index.html](http://localhost:8090/api/swagger-ui/index.html) com a applicação em execução e a ducumetação será carregada..

