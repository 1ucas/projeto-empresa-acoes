# Projeto Empresa de Ações - Aluno: Lucas Maciel

## Descrição

Projeto de uma empresa de Ações - Compra e Venda

**Características do Projeto:**
- Linguagem: JAVA
- Backend: Java Spring MVC/Rest
- Banco de Dados: MongoDB
- Fila de Comunicação Assíncrona: RabbitMQ

### Componentes do sistema

O modelo MACRO do sistema pode ser visto abaixo:

![alt text](https://github.com/1ucas/projeto-empresa-acoes/blob/master/prj-resources/DiagramaComponentes_v2.png)

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
Api para criar e buscar os dados das empresas. Foi usado o conceito que uma empresa, apesar de não ser um cliente, também é um participante do processo de compra e venda. Então para que um cliente realize a compra da ação, a empresa deve disponibilizar esta ação para venda.
- Porta: 8081
- Principais Operações:
   * GET: lista todas as empresas
   * POST: cria uma nova empresa

##### API de Clientes

- Descrição:
Api para criar e buscar os dados dos clientes.
- Porta: 8082
- Principais Operações:
   * GET: lista todos os clientes
   * POST: cria um novo cliente

##### API de Transações

- Descrição:
Api para realizar as transações de compra e venda. Seu funcionamento é o **core** do sistema. Aqui se concentram as regras de compra e venda de ação, bem como os serviços e listeners que vão gravar e escutar a fila de mensagens de ordens. Esta API também realiza conexões com a coleção de clientes para buscar seus dados de contatos e a coleção de ações para atualizar seus dados. Um Comprador pode possuir várias ações. Portanto um mesmo comprador pode chamar várias vezes a rota de compras.
- Porta: 8082
- Principais Operações:
   * POST (/compras): cria uma nova ordem de compra de ação
   * POST (/vendas): cria uma nova ordem de venda de ação (somente o dono da ação pode executar a operação)

##### API de Ações

- Descrição:
Api para criar e buscar os dados das ações. Uma empresa pode emitir quantas ações forem desejadas.
- Porta: 8085
- Principais Operações:
   * GET: lista todas as ações
   * POST: cria uma nova ação

##### API de Email

- Descrição:
Api para envio de emails. Esta API funciona de forma ativa quando é chamada pela API de transações para enviar o email de sucesso nas ordens de compra e venda das ações. Além disso, esta API funciona também de forma passiva através de um Listener do RabbitMQ. Quando uma ordem cai para a fila de mensagens mortas (ou seja, o Listener responsável por essa mensagem não estiver funcionando corretamente), o usuário responsável pela ordem é notificado que houve um problema em sua ordem e ela não foi processada. Para o correto funcionamento da API de email é necessário configurar a classe EmailConfig com o devido usuário e senha do EMAIL que disparará as mensagens.
- Porta: 8086
- Principais Operações:
   * POST: envia o email com os dados recebidos


### Exemplo de Funcionamento do Fluxo Principal

O fluxo principal de operação sistema é o fluxo de compra e venda de ações. 

Segue abaixo um exemplo de como construir o cenário para uma empresa e dois clientes:

1. Criar uma Empresa com os dados desejados.
1. Criar um Cliente X com os dados desejados 
1. Criar um Cliente Y com os dados desejados
1. Emitir ações da Empresa criada
1. Postar uma ordem de venda de todas ações pela empresa que emitiu as ações, uma vez que para liberar a ação para comércio, inicialmente a empresa deve disponibilizar essa ação como um vendedor normal
1. Postar uma ordem de compra de ações da Empresa criada com o Cliente X
1. Postar uma ordem de compra de ações da Empresa criada com o Cliente Y
1. Postar uma ordem de compra do Cliente X para comprar uma ação do Cliente Y
   1. A ordem de compra será transmitida na fila
   1. A ordem não encontrará uma ordem de venda compatível e será armazenada no banco de dados
1. Postar uma ordem de venda do Cliente Y da ação que o Cliente X deseja
   1. A ordem de venda será transmitida na fila
   1. A ordem encontrará a ordem de compra compatível
      1. A ação terá seu *valor_atual* atualizado com o valor pedido na ordem de venda
	  1. A ordem de compra salva no banco de dados será excluída
      1. Ambos os clientes receberão o email notificando do fluxo alternativo

	  
#### Premissas e convenções 

As seguintes premissas foram seguidas:
* Não há validação de "saldo" ou dinheiro do cliente ao comprar uma ação de valor X.
* Não há validação de horário para realizar as transações.
* Qualquer cliente pode postar ordens de compra solicitando ações de outra pessoa.
* Todas referências entre as negociações e atualização de dados estão sendo feitas diretamente buscando a chave única identificadora no banco de dados para as devidas entidades.

