# Consulta Credito - Backend

Este repositório contém o backend do sistema de [Consulta de Crédito](https://github.com/Trajy/Desafio-Consulta-Credito), desenvolvido utilizando Spring Framework.

## Sumário
- [Consulta Credito - Backend](#consulta-credito---backend)
  - [Sumário](#sumário)
  - [Como Executar o Projeto](#como-executar-o-projeto)
  - [Endpoints](#endpoints)
  - [Modulo DRY, Low Code, Code Reuse](#modulo-dry-low-code-code-reuse)
      - [O Módulo oferece suporte para:](#o-módulo-oferece-suporte-para)
  - [Tratamento de Erros](#tratamento-de-erros)
  - [Documentação Automática com Swagger](#documentação-automática-com-swagger)
  
## Como Executar o Projeto
O ambiente de desenvolvimento foi constituído utilizando containers Docker e Docker Compose. Para detalhes sobre como executar o projeto, vide a documentação no repositório principal [Desafio Consulta Crédito](https://github.com/Trajy/Desafio-Consulta-Credito).

## Endpoints

| Metodo | Rota | Descrição |
|--------|------|-----------|
| GET | /api/creditos/{numeroNfse} | Retorna lista de dados de crédito referentes ao número da nfse |
| GET | /api/creditos/credito/{numeroCredito} | Retorna dados de crédito a partir do número de crédito |

> [!IMPORTANT]
> Documentação na íntegra sobre os end-points pode ser acessada ao executar o sistema em [http://localhost:8090/api/swagger-ui/index.html](http://localhost:8090/api/swagger-ui/index.html).
>

## Modulo DRY, Low Code, Code Reuse
A aplicação possui o módulo para reutilização de código e implementação dos padrões de projeto [Strategy](https://refactoring.guru/design-patterns/strategy) e [Template Method](https://refactoring.guru/design-patterns/template-method).

#### O Módulo oferece suporte para:
- Desenvolvimento de Arquitetura Hexagonal Limpa (Clean Code, KISS).
- Implementação de CRUD com reutilização de código (foco no desenvolvimento das regras de negócio).
- Geração de documentação automática utilizando Swagger.
- Configuração automática para gerenciamento de exceptions e formatação para [RFC7807](https://datatracker.ietf.org/doc/html/rfc7807) (Norma para padronização de retorno de erros em Rest Apis).

> [!IMPORTANT]
> A documentação do módulo foi desenvolvida utilizando Quarkus, porém, os exemplos são aplicados de forma similar no Spring. Esta pode ser encontrada em [Trajy/Quarkus Base Architecture](https://github.com/Trajy/Quarkus-Base-Architecture?tab=readme-ov-file#quarkus-base-architecture).

O módulo está no repositório [Trajy/Spring-Base-Architecture](https://github.com/Trajy/Spring-Api-Architecture) e foi adicionado ao projeto utilizando Maven como gerenciador de dependências.

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
A configuração do gerenciador de exceptions pode ser feita de forma simples:

```Java

import br.com.trajy.architecture.restful.exception.RestGlobalExecptionHandler;

@Import({
        RestGlobalExecptionHandler.class //Configura o gerenciador de exceptions
})
@Configuration
public class AutoConfigurations {

}
```

Exemplo de resposta de erro:

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
Basta acessar [http://localhost:8090/api/swagger-ui/index.html](http://localhost:8090/api/swagger-ui/index.html) com a aplicação em execução e a documentação será carregada.

