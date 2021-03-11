#### PUC Minas - Arquitetura de Sistemas Distribuídos

### TCC - POC da api do módulo Sistema de Logística

Sistema de Logística: permite cadastrar e gerir os recursos logísticos empregados tanto para as
operações de compra como de venda. Todos os insumos utilizados pela atividade têxtil são
cadastrados e geridos neste sistema, a fim de manter o controle de sua utilização e evitar
desperdícios.
Este subsistema está totalmente integrado aos sistemas de Compras e de Vendas, que não estão
contemplados no escopo deste trabalho.

#### Stack utilizada

- Java 11
- Spring Boot
- MapStruct
- Lombok
- Junit 5 + Mockito
- Postgres
- Docker

### Executando o projeto localmente

###Pré requisitos
- Gradle instalado
  https://gradle.org/install/

- Docker instalado
  https://docs.docker.com/engine/install/ubuntu/

- Docker compose instalado
  https://docs.docker.com/compose/install/

#####Rodando o banco de dados
`docker-compose up -d`

#####Gerando o build do projeto
`./gradlew build`

#####Gerando a imagem do docker
`docker build  -f Dockerfile-local -t logistics .`

#####Rodando a aplicação
`docker run -d -p 8081:8081 -t logistics`

###Acessando a aplicação

###Swagger

#####Local
http://localhost:8081/swagger-ui.html

#####Produção
https://logistics-supply-api.herokuapp.com//swagger-ui.html
