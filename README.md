Pizza Service Project consist of two parts:
1. Backend - Java Spring Boot application. Local address: http://localhost:8080/ All functions are REST API endpoints. The description of REST API endpoints is in the text bellow.
2. Frontend - React application. Local address: http://localhost:3000/
3. Swagger - REST API endpoints documentation. Local address: http://localhost:8080/swagger-ui/index.html
4. doc - Project documentation

REST API description

#CAFE
- LIST ALL CAFE GET "/cafe"  Local address: http://localhost:8080/cafe
- ADD NEW CAFE POST "/cafe/add" Local address: http://localhost:8080/cafe/add
- GET CAFE BY ID WITH PIZZAS GET "/cafe/full/{id}" Local address: http://localhost:8080/cafe/full/1
- UPDATE CAFE DETAILS (identified by id) PUT "/cafe/{id}" Local address: http://localhost:8080/cafe/1
- DELETE CAFE BY ID DELETE AND ALL PIZZAS "/cafe/{id}" Local address: http://localhost:8080/cafe/1
- SEARCH CAFE BY NAME GET "/cafe/search?name={value}" Local address: http://localhost:8080/cafe/search?name=cafe
- SEARCH CAFE BY ADDRESS GET "/cafe/search?address={value}" Local address: http://localhost:8080/cafe/search?address=Platz

#PIZZA
- LIST ALL PIZZAS GET "/pizza" Local address: http://localhost:8080/pizza
- LIST ALL PIZZAS FOR THE SPECIFIC CAFE BY ID GET "/pizza?cafe_id={id} Local address: http://localhost:8080/pizza?cafe_id=1
- ADD NEW PIZZA TO THE SPECIFIC CAFE POST "/pizza/add/" Local address: http://localhost:8080/pizza/add
- GET PIZZA DETAILS GET "/pizza/{id}" Local address: http://localhost:8080/pizza/1
- UPDATE PIZZA BY ID PUT "/pizza/{id}" Local address: http://localhost:8080/pizza/1
- DELETE PIZZA BY ID DELETE "/pizza/{id}" Local address: http://localhost:8080/pizza/1
- SEARCH PIZZA BY NAME "/pizza/search?name={value}" Local address: http://localhost:8080/pizza/search?name=pizza