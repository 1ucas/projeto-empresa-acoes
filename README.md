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

### Configuração dos Componentes

#### Banco de Dados

Neste projeto está sendo utilizado o banco não relacional MongoDB.

A recomendação é utilizar a imagem do Docker do Mongo através do comando:

``` docker run -p 27017:27017 --name mongodb -d mongo ```

#### Fila de Comunicação Assíncrona

A fila escolhida para o projeto foi o RabbitMQ.

A recomendação é utilizar a imagem do Docker do RabbitMQ através do comando:

``` docker run -d --hostname rabbitmq --name rabbitmq-management -p 15672:15672 -p 5671:5671 -p 5672:5672 rabbitmq:management ```


#### Componentes de Backend (APIs)

Todas APIs contam com a documentação e possível teste via Swagger, necessitando adicionar apenas o sufixo ```/swagger-ui.html``` à rota de cada uma delas. Portanto, serão detalhadas apenas as características e os comportamentos de cada uma delas. 
A configuração do Build e Deploy das APIs está sendo feita através do Maven, lendo as dependências do arquivo POM.

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


### Exemplo de Funcionamento do Fluxo Principal

O fluxo principal de operação sistema é o fluxo de compra e venda de ações. 

Segue abaixo um exemplo de como construir o cenário para uma empresa e dois clientes:

1. Criar uma Empresa com os dados desejados.
1. Criar um Cliente X com os dados desejados 
1. Criar um Cliente Y com os dados desejados
1. Emitir ações da Empresa criada
1. Colocar uma ordem de compra de ações da Empresa criada com o Cliente X
1. Colocar uma ordem de compra de ações da Empresa criada com o Cliente Y
1. Colocar uma ordem de compra do Cliente X para comprar uma ação do Cliente Y
   1. A ordem de compra será transmitida na fila
   1. A ordem não encontrará uma ordem de venda compatível e será armazenada no banco de dados
1. Colocar uma ordem de venda do Cliente Y da ação que o Cliente X deseja
   1. A ordem de venda será transmitida na fila
   1. A ordem encontrará a ordem de compra compatível
      1. A ação terá seu *valor_atual* atualizado com o valor pedido na ordem de venda
	  1. A ordem de compra salva no banco de dados será excluída
      1. Ambos os clientes receberão o email notificando do fluxo alternativo


