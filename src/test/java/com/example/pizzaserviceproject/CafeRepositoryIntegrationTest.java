package com.example.pizzaserviceproject;

import com.example.pizzaserviceproject.controller.CafeController;
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

import java.util.ArrayList;
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
public class CafeRepositoryIntegrationTest {
    //    """test controllers methods"""

    @Autowired CafeController cafeController;

    @Autowired CafeRepository cafeRepository;
    @Autowired PizzaRepository pizzaRepository;

    @Autowired MockMvc mockMvc;

    //- ADD NEW CAFE POST "/cafe/add"
    @Test
    public void testA_AddTwoCafe() throws Exception{

        cafeRepository.deleteAll();
        long count = cafeRepository.count();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cafe/add")
                                .content("{\"name\":\"Test cafe 1\", " +
                                        "\"email\":\"test1@email.com\"}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cafe/add")
                                .content("{\"name\":\"Test cafe 2\", " +
                                        "\"email\":\"test2@email.com\"}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

        assertThat(cafeRepository.count(), is(count + 2L));

        assertThat(cafeRepository.findAll(), containsInAnyOrder(
                hasProperty("name", is("Test cafe 1")),
                hasProperty("name", is("Test cafe 2"))
        ));

        assertThat(cafeRepository.findAll(), containsInAnyOrder(
                hasProperty("email", is("test1@email.com")),
                hasProperty("email", is("test2@email.com"))
        ));

    }

    //- LIST ALL CAFE GET "/cafe"
    @Test
    public void testB_GetListAllCafe() throws Exception{

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe")
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"rating\":null,\"name\":\"Test cafe 1\"," +
                        "\"city\":null,\"country\":null,\"address\":null,\"email\":\"test1@email.com\",\"site\":null," +
                        "\"facebook\":null,\"phone\":null,\"delivery\":null,\"description\":null,\"open_at\":null,\"close_at\":null}," +
                        "{\"id\":2,\"rating\":null,\"name\":\"Test cafe 2\",\"city\":null,\"country\":null,\"address\":null," +
                        "\"email\":\"test2@email.com\",\"site\":null,\"facebook\":null,\"phone\":null,\"delivery\":null," +
                        "\"description\":null,\"open_at\":null,\"close_at\":null}]"));


    }

    //- GET CAFE BY ID WITH PIZZAS GET "/cafe/full/{id}"
    @Test
    public void testC_GetFullCafeDetailsById() throws Exception{
        Long cafeId = 1L;

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe/full/{id}", cafeId)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"rating\":null,\"name\":\"Test cafe 1\"," +
                        "\"city\":null,\"country\":null,\"address\":null,\"email\":\"test1@email.com\",\"site\":null," +
                        "\"facebook\":null,\"phone\":null,\"delivery\":null,\"description\":null,\"open_at\":null,\"close_at\":null}"));


    }


    //- UPDATE CAFE DETAILS (identified by id) PUT "/cafe/{id}"
    @Test
    public void testD_UpdateCafeDetailsById() throws Exception{
        Long cafeId = 1L;

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/cafe/{id}", cafeId)
                                .content("{\"id\":1,\"rating\":null,\"name\":\"Pizza Hut cafe\"," +
                                        "\"city\":\"Berlin\",\"country\":null,\"address\":\"Potsdamer Platz 1\"," +
                                        "\"email\":\"test1@email.com\",\"site\":null," +
                                        "\"facebook\":null,\"phone\":null,\"delivery\":null," +
                                        "\"description\":null,\"open_at\":null,\"close_at\":null}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        assertEquals(cafeRepository.findById(1L).get().getName(), "Pizza Hut cafe");
        assertEquals(cafeRepository.findById(1L).get().getCity(), "Berlin");
        assertEquals(cafeRepository.findById(1L).get().getAddress(), "Potsdamer Platz 1");

    }


    //- SEARCH CAFE BY NAME GET "/cafe/search?name={value}"
    @Test
    public void testE_GetListCafeSearchByName() throws Exception{
        String value = "cafe";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe/search?name={value}", value)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"rating\":null,\"name\":\"Pizza Hut cafe\"," +
                        "\"city\":\"Berlin\",\"country\":null,\"address\":\"Potsdamer Platz 1\",\"email\":\"test1@email.com\",\"site\":null," +
                        "\"facebook\":null,\"phone\":null,\"delivery\":null,\"description\":null,\"open_at\":null,\"close_at\":null}," +
                        "{\"id\":2,\"rating\":null,\"name\":\"Test cafe 2\",\"city\":null,\"country\":null,\"address\":null," +
                        "\"email\":\"test2@email.com\",\"site\":null,\"facebook\":null,\"phone\":null,\"delivery\":null," +
                        "\"description\":null,\"open_at\":null,\"close_at\":null}]"));

        value = "Pizza Hut";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe/search?name={value}", value)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"rating\":null,\"name\":\"Pizza Hut cafe\"," +
                        "\"city\":\"Berlin\",\"country\":null,\"address\":\"Potsdamer Platz 1\",\"email\":\"test1@email.com\",\"site\":null," +
                        "\"facebook\":null,\"phone\":null,\"delivery\":null,\"description\":null,\"open_at\":null,\"close_at\":null}]"));
    }


    //- SEARCH CAFE BY ADDRESS GET "/cafe/search?address={value}"
//    @Test
    public void testF_GetListCafeSearchByAddress() throws Exception{
        String value = "Platz";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe/search?address={value}", value)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"rating\":null,\"name\":\"Pizza Hut cafe\"," +
                        "\"city\":\"Berlin\",\"country\":null,\"address\":\"Potsdamer Platz 1\",\"email\":\"test1@email.com\",\"site\":null," +
                        "\"facebook\":null,\"phone\":null,\"delivery\":null,\"description\":null,\"open_at\":null,\"close_at\":null}]"));

    }

    //- DELETE CAFE BY ID DELETE AND ALL PIZZAS "/cafe/{id}"
    @Test
    public void testG_DeleteCafeAndPizzasById() throws Exception{

        int cafeCount = cafeRepository.findAll().size();
        int pizzaCount = pizzaRepository.findAll().size();
        int pizzaCountById = pizzaRepository.findByCafeId(1L).size();
        Long cafeId = 1L;

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/cafe/{cafe_id}", cafeId)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        assertEquals(cafeRepository.findById(1L), Optional.empty());
        assertEquals(cafeRepository.findAll().size(), cafeCount - 1);
        assertEquals(pizzaRepository.findByCafeId(1L), new ArrayList<>());
        assertEquals(pizzaRepository.findAll().size(), pizzaCount - pizzaCountById);


    }

}
