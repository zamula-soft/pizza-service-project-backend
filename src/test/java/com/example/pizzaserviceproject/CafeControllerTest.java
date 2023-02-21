package com.example.pizzaserviceproject;

import com.example.pizzaserviceproject.controller.CafeController;
import com.example.pizzaserviceproject.repository.CafeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class CafeControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired CafeController cafeController;
    @MockBean CafeRepository cafeRepository;

    @Test
    public void normalCafeCreation() throws Exception{

//        mockMvc.perform(
//                        MockMvcRequestBuilders.post("/cafe/add")
//                        .content("{\"name\":\"Test cafe 1\", " +
//                                "\"email\":\"test1@email.com\"}")
//                        .contentType("application/json")
//        )
//                .andExpect(status().isCreated());
    }

}
