# T2 - ES2

### Para subir o banco de dados MySQL
```
docker run --name mysql -e MYSQL_ROOT_PASSWORD=test -p 3306:3306 -d mysql
```

### Para subir a aplicação
```
mvn spring-boot:run
```

Junto ao código, está o arquivo __"T2.postman_collection.json"__ com todas as requisições para teste da API.
