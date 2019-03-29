# Projeto Empresa de Ações

## Descrição

Projeto de uma empresa de Ações - Compra e Venda

**Características do Projeto:**
- Linguagem: JAVA
- Backend: Java Spring MVC/Rest
- Banco de Dados: MongoDB
- Fila de Comunicação Assíncrona: RabbitMQ

### Componentes do sistema

O modelo MACRO do sistema pode ser visto abaixo:

![alt text](https://github.com/1ucas/projeto-empresa-acoes/blob/master/prj-resources/DiagramaComponentes.png)

### Funcionamento dos Componentes

#### Banco de Dados

Neste projeto está sendo utilizado o banco não relacional MongoDB.

A recomendação é utilizar a imagem do Docker do Mongo através do comando:

```docker run -p 27017:27017 --name mongodb -d mongo```

#### Fila de Comunicação Assíncrona

A fila escolhida para o projeto foi o RabbitMQ.

A recomendação é utilizar a imagem do Docker do RabbitMQ através do comando:

```docker run -d --hostname rabbitmq --name rabbitmq-management -p 15672:15672 -p
5671:5671 -p 5672:5672 rabbitmq:management```

#### Componentes de Backend (APIs)

Todas APIs contam com a documentação e possível teste via Swagger, necessitando adicionar apenas o sufixo ```/swagger-ui.html``` à rota de cada uma delas. Portanto, serão detalhadas apenas as características e os comportamentos de cada uma delas.

##### API de Empresas

- Descrição:
Api para criar e buscar os dados das empresas.
- Porta: 8081

##### API de Clientes

- Descrição:
Api para criar e buscar os dados dos clientes.
- Porta: 8082

##### API de Transações

- Descrição:
Api para realizar as transações de compra e venda.
- Porta: 8082

##### API de Ações

- Descrição:
Api para criar e buscar os dados das ações.
- Porta: 8085

##### API de Email

- Descrição:
Api para envio de emails.
- Porta: 8086

