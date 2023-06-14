# TESTE PRÁTICO FULLSTACK / JavaScript

Aplicação consiste no desenvolvimento de um CRUD simples e desenvolvimento de um frontEnd todo em HTML/JavaScript para cadastro e visualização de usuários e suas hierarquias.

## Tabela
* Usuarios ( criada através do script flyway )

### 📚 Recursos Utilizados

- 🌴 GitHub
- ☕ Java 8
- 📦 Maven
- 🐘 MySql
- ![image](https://github.com/williancunhamoraes81/SchedulePayementDemo/assets/72080283/6aa9af65-9334-48a7-b3f2-fb4e5cf2bff2)
  API REST
- ⚙️ Docker

<br/><br/>
#### 📋 Clonando repositório

```
git clone https://github.com/williancunhamoraes81/Usuarios.git
```
<br/><br/>
#### 🚢 Iniciando serviços necessários
1- Inicia Docker.
2- Na pasta devops da aplicação executar comando "docker-compose up" para iniciar banco MySql.
3- Execute comando "mvn clean install" para baixar as dependências necessárias.
4- Inicie a aplicação Java
5- Abrir através do browser o arquivo "index.html" encontrado na pasta "resources/html" da aplicação.

<br/><br/>

#### 🚢 Utilizando API
<b>CURL para realização de outras necessidades</b>
<br/>

<b>DELETE</b>
curl --location --request DELETE 'localhost:8080/usuario/1'
<br/>
<br/>

<b>GET ALL</b>
curl --location 'localhost:8080/usuario'
<br/>
<br/>

<b>GET BY ID</b>
curl --location 'localhost:8080/usuario/1'
<br/>
<br/>

<b>PUT</b>
curl --location --request PUT 'localhost:8080/usuario/1' \
--header 'Content-Type: application/json' \
--data '{
"nome": "Willian Moraes",
"senha": "",
"hierarquia": null
}'
