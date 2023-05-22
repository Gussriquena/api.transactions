# api.transactions

API para validação do conhecimento em spring e micro-serviços

## Escopo

----

"Um comerciante precisa controlar o seu fluxo de caixa diário com os lançamentos (débitos e  créditos), também precisa de um relatório que disponibilize o saldo diário consolidado".

## Projeto

----

Para criação do projeto foi estruturado o seguinte modelo de banco de dados

![img.png](img.png)

A tabela **product** foi adicionada para criar um 
contexto de negócio para a aplicação e mostrar a possibilidade 
de escala e extensão do sistema existente, apenas acoplando 
novas tabelas à base principal (transaction e transaction_type)

## Ferramentas e setup

----

- JDK 17
- Intellij IDEA
- Docker desktop
- Postman

Para execução do projeto é necessária a criação do banco de dados, 
que pode ser feita utilizando o arquivo docker-compose na pasta
"docker" na raiz do projeto.

Abrir um prompt de comando na pasta 
"docker" e executar o seguinte comando:

`docker-compose up -d`

As tabelas serão criadas automaticamente com registros básicos
utilizando **flyway migrations** quando o projeto java for
iniciado.

## Documentação

----

Todos os arquivos de documentação estão na pasta "doc" na raiz
do projeto, são eles:

- Modelo de banco de dados editável ([draw.io](https://app.diagrams.net/))
- Collection postman para testes da API


## Dependências

----

- Java 17
- Spring 3.0.7
- Lombok
- PostgreSQL
- Flyway Migrations
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- spring-boot-starter-test