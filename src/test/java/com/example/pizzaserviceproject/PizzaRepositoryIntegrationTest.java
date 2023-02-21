package com.example.pizzaserviceproject;

import com.example.pizzaserviceproject.controller.CafeController;
import com.example.pizzaserviceproject.controller.PizzaController;
import com.example.pizzaserviceproject.repository.CafeRepository;
import com.example.pizzaserviceproject.repository.PizzaRepository;
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

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PizzaRepositoryIntegrationTest {
//    """test controllers methods"""

    @Autowired PizzaController pizzaControllerController;
    @Autowired CafeController cafeController;

    @Autowired PizzaRepository pizzaRepository;
    @Autowired CafeRepository cafeRepository;

    @Autowired MockMvc mockMvc;

    //- ADD NEW PIZZA TO THE SPECIFIC CAFE POST "/pizza/add/"
    @Test
    public void testA_AddPizza() throws Exception{

        pizzaRepository.deleteAll();
        cafeRepository.deleteAll();

        long count = pizzaRepository.count();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cafe/add")
                                .content("{\"name\":\"Test cafe 1\", " +
                                        "\"email\":\"test1@email.com\"}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/pizza?cafe_id=1")
                        .content("{\"name\":\"Test pizza 1\", " +
                                "\"cafe_id\":\"1\", " +
                                "\"size\":\"L\", " +
                                "\"price\":\"10.90\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/pizza?cafe_id=2")
                                .content("{\"name\":\"Test pizza 2\", " +
                                        "\"cafe_id\":\"2\", " +
                                        "\"size\":\"XL\", " +
                                        "\"price\":\"15.90\"}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

        assertThat(pizzaRepository.count(), is(count + 2L));

        assertThat(pizzaRepository.findAll(), containsInAnyOrder(
                hasProperty("name", is("Test pizza 1")),
                hasProperty("name", is("Test pizza 2"))
        ));

        assertThat(pizzaRepository.findAll(), containsInAnyOrder(
                hasProperty("size", is("L")),
                hasProperty("size", is("XL"))
        ));

//        assertThat(pizzaRepository.findAll(), containsInAnyOrder(
//                hasProperty("price", is(10.90D)),
//                hasProperty("price", is(15.90D))
//        ));

    }

    //- LIST ALL PIZZAS GET "/pizza"
    @Test
    public void testB_GetListAllPizza() throws Exception{

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pizza")
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("[" +
                        "{\"id\":1,\"name\":\"Test pizza 1\",\"size\":\"L\"," +
                        "\"description\":null,\"key_ingredients\":null,\"price\":10.90,\"isSpicy\":null}," +
                        "{\"id\":2,\"name\":\"Test pizza 2\",\"size\":\"XL\"," +
                        "\"description\":null,\"key_ingredients\":null,\"price\":15.90,\"isSpicy\":null}]"));

    }

    //- LIST ALL PIZZAS FOR THE SPECIFIC CAFE BY ID GET "/pizza?cafe_id={cafeId}
    @Test
    public void testC_GetListAllPizzaByCafeId() throws Exception{

        Long cafeId = 1L;

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pizza?cafe_id={cafeId}", cafeId)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("[" +
                        "{\"id\":1,\"name\":\"Test pizza 1\",\"size\":\"L\"," +
                        "\"description\":null,\"key_ingredients\":null,\"price\":10.90,\"isSpicy\":null}," +
                        "{\"id\":2,\"name\":\"Test pizza 2\",\"size\":\"XL\"," +
                        "\"description\":null,\"key_ingredients\":null,\"price\":15.90,\"isSpicy\":null}]"));

    }

    //- GET PIZZA DETAILS GET "/pizza/{id}"
    @Test
    public void testD_GetPizzaDetailsById() throws Exception{
        Long pizzaId = 1L;

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pizza/{id}", pizzaId)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"Test pizza 1\",\"size\":\"L\"," +
                        "\"description\":null,\"key_ingredients\":null,\"price\":10.90,\"isSpicy\":null}"));


    }

    //- UPDATE PIZZA BY ID PUT "/pizza/{id}"
//    @Test
    public void testE_UpdatePizzaDetailsById() throws Exception{
        Long pizzaId = 1L;

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/pizza/{id}", pizzaId)
                                .content("{\"name\":\"Pan Pizza Margherita\", " +
                                        "\"cafe_id\":\"1\", " +
                                        "\"size\":\"L\", " +
                                        "\"price\":\"10.90\"}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        assertEquals(pizzaRepository.findById(1L).get().getName(), "Pan Pizza Margherita");

    }


    //- SEARCH PIZZA BY NAME "/pizza/search?name={value}"
    @Test
    public void testF_GetListPizzaSearchByName() throws Exception{
        String value = "pizza";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pizza/search?name={value}", value)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("[" +
                        "{\"id\":1,\"name\":\"Test pizza 1\",\"size\":\"L\"," +
                        "\"description\":null,\"key_ingredients\":null,\"price\":10.90,\"isSpicy\":null}," +
                        "{\"id\":2,\"name\":\"Test pizza 2\",\"size\":\"XL\"," +
                        "\"description\":null,\"key_ingredients\":null,\"price\":15.90,\"isSpicy\":null}]"));

        value = "Test pizza 2";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/pizza/search?name={value}", value)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("[" +
                        "{\"id\":2,\"name\":\"Test pizza 2\",\"size\":\"XL\"," +
                        "\"description\":null,\"key_ingredients\":null,\"price\":15.90,\"isSpicy\":null}]"));

    }


    //- DELETE PIZZA BY ID DELETE "/pizza/{id}"
    @Test
    public void testG_DeletePizzasById() throws Exception{

        int pizzaCount = pizzaRepository.findAll().size();
        Long pizzaId = 1L;

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/pizza/{id}", pizzaId)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        assertEquals(pizzaRepository.findById(1L), Optional.empty());
        assertEquals(pizzaRepository.findAll().size(), pizzaCount - 1);

    }

}
