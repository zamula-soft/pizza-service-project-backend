package com.example.pizzaserviceproject;

import com.example.pizzaserviceproject.controller.CafeController;
import com.example.pizzaserviceproject.entity.Cafe;
import com.example.pizzaserviceproject.entity.Pizza;
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
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

    private static final String FIRST_CAFE_NAME = "Test cafe 1";
    private static final String FIRST_CAFE_EMAIL = "test1@email.com";

    private static final String SECOND_CAFE_NAME = "Test cafe 2";
    private static final String SECOND_CAFE_EMAIL = "test2@email.com";


    private static Cafe cafe_one = new Cafe(null, FIRST_CAFE_NAME, null, null, "Address 1",
            FIRST_CAFE_EMAIL,null, null, null, null, null, null,
            null);

    private static Cafe cafe_two = new Cafe(null, SECOND_CAFE_NAME, null, null, null,
            SECOND_CAFE_EMAIL, null, null, null, null, null, null,
            null);

    //- ADD NEW CAFE POST "/cafe/add"
    @Test
    public void whenAddTwoCafes_thenReturnSavedCafes() throws Exception{

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
    public void givenListOfCafes_whenGetAllCafe_thenReturnCafesList() throws Exception{

        cafeRepository.deleteAll();
        List<Cafe> listOfCafes = new ArrayList<>();
        listOfCafes.add(cafe_one);
        listOfCafes.add(cafe_two);
        cafeRepository.saveAll(listOfCafes);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe")
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", equalTo(cafe_one.getId().intValue())))
                .andExpect(jsonPath("$[0].name", equalTo(FIRST_CAFE_NAME)))
                .andExpect(jsonPath("$[0].email", equalTo(FIRST_CAFE_EMAIL)))

                .andExpect(jsonPath("$[1].id", equalTo(cafe_two.getId().intValue())))
                .andExpect(jsonPath("$[1].name", equalTo(SECOND_CAFE_NAME)))
                .andExpect(jsonPath("$[1].email", equalTo(SECOND_CAFE_EMAIL))
                );

    }

    //- GET CAFE BY ID WITH PIZZAS GET "/cafe/full/{id}"
    @Test
    public void givenListOfCafes_whenGetCafeFullDetailsById_thenReturnCafe() throws Exception{

        cafeRepository.deleteAll();
        List<Cafe> listOfCafes = new ArrayList<>();
        listOfCafes.add(cafe_one);
        listOfCafes.add(cafe_two);
        cafeRepository.saveAll(listOfCafes);

        long cafeId = cafeRepository.findByNameContaining(FIRST_CAFE_NAME).get(0).getId().longValue();

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe/full/{id}", cafeId)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(FIRST_CAFE_NAME)))
                .andExpect(jsonPath("$.email", equalTo(FIRST_CAFE_EMAIL)))
        ;
    }


    //- UPDATE CAFE DETAILS (identified by id) PUT "/cafe/{id}"
    @Test
    public void givenListOfCafes_whenUpdateCafeDetailsById_thenReturnCafe() throws Exception{
        cafeRepository.deleteAll();
        List<Cafe> listOfCafes = new ArrayList<>();
        listOfCafes.add(cafe_one);
        listOfCafes.add(cafe_two);
        cafeRepository.saveAll(listOfCafes);

        long cafeId = cafeRepository.findByNameContaining(FIRST_CAFE_NAME).get(0).getId().longValue();
        String updatedName = "Pizza Hut cafe";

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/cafe/{id}", cafeId)
                                .content("{\"id\":1,\"rating\":null,\"name\":\""+  updatedName +"\"," +
                                        "\"city\":null,\"country\":null,\"address\":null," +
                                        "\"email\":\"test1@email.com\",\"site\":null," +
                                        "\"facebook\":null,\"phone\":null,\"delivery\":null," +
                                        "\"description\":null,\"open_at\":null,\"close_at\":null}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(updatedName)))
        ;

    }


    //- SEARCH CAFE BY NAME GET "/cafe/search?name={value}"
    @Test
    public void givenListOfCafes_whenSearchCafeByName_thenReturnListCafes() throws Exception{

        cafeRepository.deleteAll();
        List<Cafe> listOfCafes = new ArrayList<>();
        listOfCafes.add(cafe_one);
        listOfCafes.add(cafe_two);
        cafeRepository.saveAll(listOfCafes);

        String value = "cafe";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe/search?name={value}", value)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo(FIRST_CAFE_NAME)))
                .andExpect(jsonPath("$[0].email", equalTo(FIRST_CAFE_EMAIL)))
                .andExpect(jsonPath("$[1].name", equalTo(SECOND_CAFE_NAME)))
                .andExpect(jsonPath("$[1].email", equalTo(SECOND_CAFE_EMAIL)))

        ;
        value = "cafe 1";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe/search?name={value}", value)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo(FIRST_CAFE_NAME)))
                .andExpect(jsonPath("$[0].email", equalTo(FIRST_CAFE_EMAIL)))
        ;
    }


    //- SEARCH CAFE BY ADDRESS GET "/cafe/search?address={value}"
//    @Test
    public void givenListOfCafes_whenSearchCafeByAddress_thenReturnListCafes() throws Exception{
        String value = "Address 1";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cafe/search?address={value}", value)
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo(FIRST_CAFE_NAME)))
                .andExpect(jsonPath("$[0].email", equalTo(FIRST_CAFE_EMAIL)))
                .andExpect(jsonPath("$[0].address", equalTo(value)))
        ;

    }

    //- DELETE CAFE BY ID DELETE AND ALL PIZZAS "/cafe/{id}"
//    @Test
    public void givenCafe_whenDeleteCafeById_thenReturnListCafeOrEmpty() throws Exception{

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
