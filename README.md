REST API description

#CAFE
- LIST ALL CAFE GET "/cafe" 
- ADD NEW CAFE POST "/cafe/add"
- GET CAFE BY ID WITH PIZZAS GET "/cafe/full/{id}"
- UPDATE CAFE DETAILS (identified by id) PUT "/cafe/{id}"
- DELETE CAFE BY ID DELETE AND ALL PIZZAS "/cafe/{id}"
- SEARCH CAFE BY NAME GET "/cafe/search?name={value}"
- SEARCH CAFE BY ADDRESS GET "/cafe/search?address={value}"

#PIZZA
- LIST ALL PIZZAS GET "/pizza"
- LIST ALL PIZZAS FOR THE SPECIFIC CAFE BY ID GET "/pizza?cafe_id={id}
- ADD NEW PIZZA TO THE SPECIFIC CAFE POST "/pizza/add/"
- GET PIZZA DETAILS GET "/pizza/{id}"
- UPDATE PIZZA BY ID PUT "/pizza/{id}"
- DELETE PIZZA BY ID DELETE "/pizza/{id}"
- SEARCH PIZZA BY NAME "/pizza/search?name={value}"