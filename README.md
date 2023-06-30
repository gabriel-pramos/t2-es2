# T2 - ES2

## Rodar com docker-compose
```
docker-compose up
```

O build dos serviços é feito automaticamente pelo docker-compose, mas pode demorar um pouco.

Quando subir pela primeira vez, os serviços podem crashar por causa do banco de dados, que pode demorar para iniciar. Nesse caso, basta aguardar que os serviços reinitiarão automaticamente.

## Collection do Postman

Junto ao código, está o arquivo __"T2.postman_collection.json"__ com todas as requisições para teste da API.


## Testes

Para rodar os testes, é necessário ter o container do banco de dados rodando. Para isso, basta rodar o comando:

```
docker-compose up -d mysql
```

Após isso, basta rodar o comando:

```
DATABASE_STRING=jdbc:mysql://localhost:3306/mysql mvn -f microservico1/ test
DATABASE_STRING=jdbc:mysql://localhost:3306/mysql mvn -f microservico2/ test
```