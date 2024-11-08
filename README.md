# API de Pagamentos

Esta API permite realizar operações de pagamento, estorno e consulta para transações de cartões de crédito.

## Endpoints

### 1. Realizar Pagamento
- **URL**: `/api/pagamentos/pagamento`
- **Método**: `POST`
- **Corpo da Requisição**:
  ```json
  {
  "transacao":{
    "cartao": "111111111111111",
    "descricao": {
      "valor": 50.00,
      "dataHora": "01/05/2024 23:00:00",
      "estabelecimento":"Petshop mundo cão"
    },
    "formaPagamento":{
      "tipo": "AVISTA",
      "parcelas": 1
    }
  }
}
``

### 2. Consultar Pagamento
- **URL**: `/api/pagamentos/consulta/{id}`
- **Método**: `GET`

### 3. Estornar Pagamento
- **URL**: `/api/pagamentos/estorno/{id}`
- **Método**: `PATCH`



## Rodando o Projeto Localmente

Este projeto pode ser rodado utilizando o Docker e Docker Compose para criar um ambiente de banco de dados MySQL.

### Requisitos

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Passo 1: Clonar o Repositório

Clone este repositório para sua máquina local:

```bash
git clone https://github.com/seu-usuario/pagamento-api.git
cd pagamento-api
```

### Passo 2: Rodar o Docker Compose
Execute o comando abaixo para iniciar o ambiente de banco de dados com o Docker Compose. O docker-compose.yml irá configurar o banco de dados MySQL para você.
- docker-compose up -d

### Passo 3: Rodar a aplicação
Build do Projeto: Se você não tiver as dependências do Maven configuradas, basta executar:

bash
Copy code
./mvnw clean install
Executando a Aplicação: Após compilar o projeto, execute a aplicação com o seguinte comando:

bash
Copy code
./mvnw spring-boot:run

Ou outra opção é simplesmente startar o projeto pelo botão de Start do Intellij ou alguma outra ide que esteja  utilizando


### Observação
  - Criei uma classe de teste para processar pagamentos
  - Foi criada também um ControllerAdvice para exceções personalizada
  - O banco que usei foi o postgresql
