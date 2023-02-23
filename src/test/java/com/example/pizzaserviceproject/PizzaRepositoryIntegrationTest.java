package com.example.pizzaserviceproject;

import com.example.pizzaserviceproject.controller.CafeController;
import com.example.pizzaserviceproject.controller.PizzaController;
import com.example.pizzaserviceproject.entity.Cafe;
import com.example.pizzaserviceproject.entity.Pizza;
import com.example.pizzaserviceproject.repository.CafeRepository;
import com.example.pizzaserviceproject.repository.PizzaRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PizzaRepositoryIntegrationTest {
//    """test controllers methods"""

    @Autowired PizzaController pizzaControllerController;
    @Autowired CafeController cafeController;

    @Autowired PizzaRepository pizzaRepository;
    @Autowired
    CafeRepository cafeRepository;
//    cafeRepository.deleteAll();

    @Autowired MockMvc mockMvc;

    private static final String CAFE_NAME = "Test cafe";
    private static final String CAFE_EMAIL = "test@email.com";

    private static Cafe cafe = new Cafe(null, CAFE_NAME, null, null, null, CAFE_EMAIL,
            null, null, null, null, null, null, null);


    private static Long CAFE_ID;

    private static final Long FIRST_PIZZA_ID = 1L;
    private static final String FIRST_PIZZA_NAME = "Test pizza 1";
    private static final String FIRST_PIZZA_SIZE = "L";
    private static final BigDecimal FIRST_PIZZA_PRICE = new BigDecimal("10.90");



    private static final Long SECOND_PIZZA_ID = 2L;
    private static final String SECOND_PIZZA_NAME = "Test pizza 2";
    private static final String SECOND_PIZZA_SIZE = "XL";
    private static final BigDecimal SECOND_PIZZA_PRICE = new BigDecimal("15.90");

    private static Pizza pizza_one = new Pizza(cafe, FIRST_PIZZA_NAME, FIRST_PIZZA_SIZE, null, null,
            FIRST_PIZZA_PRICE, null);
    private static Pizza pizza_two = new Pizza(cafe, SECOND_PIZZA_NAME, SECOND_PIZZA_SIZE, null, null,
            SECOND_PIZZA_PRICE, null);

    private static Pizza pizza_three = new Pizza(cafe, SECOND_PIZZA_NAME, SECOND_PIZZA_SIZE, null, null,
            SECOND_PIZZA_PRICE, null);




    //- ADD NEW PIZZA TO THE SPECIFIC CAFE POST "/pizza/add/"
//    @Before
//    public void beforeEachTestAddCafe() throws Exception {
//        cafeRepository.save(cafe);
//        CAFE_ID = cafe.getId();
//
//    }

    @Test
    public void whenAddTwoPizzas_thenReturnSavedPizzas() throws Exception{
        cafeRepository.save(cafe);
        CAFE_ID = cafe.getId();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/pizza?cafe_id=1")
                                .content("{\"id\":\"" + FIRST_PIZZA_ID + "\", " +
                                        "\"name\":\"" + FIRST_PIZZA_NAME + "\", " +
                                        "\"cafe_id\":\"" + CAFE_ID.intValue() + "\", " +
                                        "\"size\":\"" + FIRST_PIZZA_SIZE + "\", " +
                                        "\"price\":\"" + FIRST_PIZZA_PRICE.doubleValue() + "\"}")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/pizza?cafe_id=1")
                                .content("{\"id\":\"" + SECOND_PIZZA_ID + "\", " +
                                        "\"name\":\"" + SECOND_PIZZA_NAME + "\", " +
                                        "\"cafe_id\":\"" + CAFE_ID.intValue() + "\", " +
                                        "\"size\":\"" + SECOND_PIZZA_SIZE + "\", " +
                                        "\"price\":\"" + SECOND_PIZZA_PRICE.doubleValue() + "\"}")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        List<Pizza> pizzaList = pizzaRepository.findAll();

        assertEquals(pizzaList.size(), 2);

        assertThat(pizzaList, containsInAnyOrder(
                hasProperty("name", is(FIRST_PIZZA_NAME)),
                hasProperty("name", is(SECOND_PIZZA_NAME))
        ));

        assertThat(pizzaList, containsInAnyOrder(
                hasProperty("size", is(FIRST_PIZZA_SIZE)),
                hasProperty("size", is(SECOND_PIZZA_SIZE))
        ));

        assertThat(pizzaList, containsInAnyOrder(
                hasProperty("price", Matchers.comparesEqualTo(FIRST_PIZZA_PRICE)),
                hasProperty("price", Matchers.comparesEqualTo(SECOND_PIZZA_PRICE))
        ));

    }

    //- LIST ALL PIZZAS GET "/pizza"
    @Test
    public void givenListOfPizzas_whenGetAllPizzas_thenReturnPizzasList() throws Exception{

        cafeRepository.save(cafe);
        CAFE_ID = cafe.getId();

        List<Pizza> listOfPizzas = new ArrayList<>();
        listOfPizzas.add(pizza_one);
        listOfPizzas.add(pizza_two);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pizza")
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", equalTo(FIRST_PIZZA_ID.intValue())))
                .andExpect(jsonPath("$[0].name", equalTo(FIRST_PIZZA_NAME)))
                .andExpect(jsonPath("$[0].size", equalTo(FIRST_PIZZA_SIZE)))
                .andExpect(jsonPath("$[0].price", equalTo(FIRST_PIZZA_PRICE.doubleValue())))

                .andExpect(jsonPath("$[1].id", equalTo(SECOND_PIZZA_ID.intValue())))
                .andExpect(jsonPath("$[1].name", equalTo(SECOND_PIZZA_NAME)))
                .andExpect(jsonPath("$[1].size", equalTo(SECOND_PIZZA_SIZE)))
                .andExpect(jsonPath("$[1].price", equalTo(SECOND_PIZZA_PRICE.doubleValue())))

        ;
    }

    //- LIST ALL PIZZAS FOR THE SPECIFIC CAFE BY ID GET "/pizza?cafe_id={cafeId}
    @Test
    public void givenListOfPizzas_whenGetPizzaByCafeId_thenReturnListPizzas() throws Exception{

        cafeRepository.save(cafe);
        CAFE_ID = cafe.getId();

        List<Pizza> listOfPizzas = new ArrayList<>();
        listOfPizzas.add(pizza_one);
        listOfPizzas.add(pizza_two);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pizza?cafe_id={cafeId}", CAFE_ID)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", equalTo(FIRST_PIZZA_ID.intValue())))
                .andExpect(jsonPath("$[0].name", equalTo(FIRST_PIZZA_NAME)))
                .andExpect(jsonPath("$[0].size", equalTo(FIRST_PIZZA_SIZE)))
                .andExpect(jsonPath("$[0].price", equalTo(FIRST_PIZZA_PRICE.doubleValue())))

                .andExpect(jsonPath("$[1].id", equalTo(SECOND_PIZZA_ID.intValue())))
                .andExpect(jsonPath("$[1].name", equalTo(SECOND_PIZZA_NAME)))
                .andExpect(jsonPath("$[1].size", equalTo(SECOND_PIZZA_SIZE)))
                .andExpect(jsonPath("$[1].price", equalTo(SECOND_PIZZA_PRICE.doubleValue())))

        ;

    }

    //- GET PIZZA DETAILS GET "/pizza/{id}"
    @Test
    public void givenListOfPizzas_whenGetPizzaDetailsById_thenReturnPizza() throws Exception{

        cafeRepository.save(cafe);
        CAFE_ID = cafe.getId();

        List<Pizza> listOfPizzas = new ArrayList<>();
        listOfPizzas.add(pizza_one);
        listOfPizzas.add(pizza_two);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pizza/{id}", FIRST_PIZZA_ID)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id", equalTo(FIRST_PIZZA_ID.intValue())))
                .andExpect(jsonPath("$.name", equalTo(FIRST_PIZZA_NAME)))
                .andExpect(jsonPath("$.size", equalTo(FIRST_PIZZA_SIZE)))
                .andExpect(jsonPath("$.price", equalTo(FIRST_PIZZA_PRICE.doubleValue())))
        ;


    }

   //- UPDATE PIZZA BY ID PUT "/pizza/{id}"
//    @Test
    public void givenListOfPizzas_whenUpdatePizza_thenReturnUpdatePizza() throws Exception{

        cafeRepository.save(cafe);
        CAFE_ID = cafe.getId();

        List<Pizza> listOfPizzas = new ArrayList<>();
        listOfPizzas.add(pizza_one);
        listOfPizzas.add(pizza_two);

//        pizzaRepository.deleteAll();
//        pizzaRepository.saveAll(listOfPizzas);

        String newPizzaName = "Pan pizza Margherita";

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/pizza/{id}", FIRST_PIZZA_ID)
                                .content("{\"id\":\"" + FIRST_PIZZA_ID + "\", " +
                                        "\"name\":\"" + newPizzaName + "\", " +
//                                        "\"cafe_id\":\"" + CAFE_ID.intValue() + "\", " +
                                        "\"size\":\"" + FIRST_PIZZA_SIZE + "\", " +
                                        "\"price\":\"" + FIRST_PIZZA_PRICE.doubleValue() + "\"}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        assertEquals(pizzaRepository.findById(1L).get().getName(), newPizzaName);

    }


    //- SEARCH PIZZA BY NAME "/pizza/search?name={value}"
    @Test
    public void givenListOfPizzas_whenSearchPizzaByName_thenReturnListPizzas() throws Exception{

        cafeRepository.save(cafe);
        CAFE_ID = cafe.getId();

        List<Pizza> listOfPizzas = new ArrayList<>();
        listOfPizzas.add(pizza_one);
        listOfPizzas.add(pizza_two);

//        pizzaRepository.deleteAll();
//        pizzaRepository.saveAll(listOfPizzas);

        String searchValue;

        searchValue = "pizza";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pizza/search?name={value}", searchValue)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].id", equalTo(FIRST_PIZZA_ID.intValue())))
                .andExpect(jsonPath("$[0].name", equalTo(FIRST_PIZZA_NAME)))
                .andExpect(jsonPath("$[0].size", equalTo(FIRST_PIZZA_SIZE)))
                .andExpect(jsonPath("$[0].price", equalTo(FIRST_PIZZA_PRICE.doubleValue())))

                .andExpect(jsonPath("$[1].id", equalTo(SECOND_PIZZA_ID.intValue())))
                .andExpect(jsonPath("$[1].name", equalTo(SECOND_PIZZA_NAME)))
                .andExpect(jsonPath("$[1].size", equalTo(SECOND_PIZZA_SIZE)))
                .andExpect(jsonPath("$[1].price", equalTo(SECOND_PIZZA_PRICE.doubleValue())))
        ;

        searchValue = "Test pizza 2";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pizza/search?name={value}", searchValue)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", equalTo(SECOND_PIZZA_ID.intValue())))
                .andExpect(jsonPath("$[0].name", equalTo(SECOND_PIZZA_NAME)))
                .andExpect(jsonPath("$[0].size", equalTo(SECOND_PIZZA_SIZE)))
                .andExpect(jsonPath("$[0].price", equalTo(SECOND_PIZZA_PRICE.doubleValue())))
        ;

    }


    //- DELETE PIZZA BY ID DELETE "/pizza/{id}"
    @Test
    public void givenListOfPizzas_whenDeletePizzaById_thenReturnListPizzasOrEmpty() throws Exception{

        cafeRepository.save(cafe);
        CAFE_ID = cafe.getId();

        List<Pizza> listOfPizzas = new ArrayList<>();
        listOfPizzas.add(pizza_one);
        listOfPizzas.add(pizza_two);
        listOfPizzas.add(pizza_three);

        pizzaRepository.save(pizza_three);

        int pizzaCount = pizzaRepository.findAll().size();
        long pizzaId = pizza_three.getId().intValue();

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/pizza/{id}", pizzaId)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        assertEquals(pizzaRepository.findById(pizzaId), Optional.empty());
        assertEquals(pizzaRepository.findAll().size(), pizzaCount - 1);

    }

}
