package com.guilherme.SpringBootWithDataBase.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.SpringBootWithDataBase.model.Country;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {
    @Autowired
    private MockMvc controller;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testAddCountry() throws Exception {
        controller.perform(post("/countries")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new Country("Brazil", "Brasilia", "+55"))))
                .andExpect(status().isOk());
    }

    @Test
    void testAddCountryBadRequest() throws JsonProcessingException, Exception {
        controller.perform(post("/countries")
                .contentType("application/json")
                .content("Brazil"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteCountry() throws Exception {
        controller.perform(delete("/countries/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCountryBadRequest() throws Exception {
        controller.perform(delete("/countries/"))
                .andExpect(status().isNotFound());
    }

    @Test void testDeleteCountryBadRequestWithText() throws Exception {
        controller.perform(delete("/countries/abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetCountries() throws Exception {
        controller.perform(get("/countries")).andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith("application/json"));
    }

    @Test
    void testGetCountry() throws Exception {
        controller.perform(get("/countries/1")).andExpect(status().isOk());
    }

    @Test
    void testPopulate() throws Exception {
        controller.perform(get("/")).andExpect(status().isOk())
        .andExpect(response -> response.equals("Países populados com sucesso!"));
    }

    @Test
    void testUpdateCountry() throws JsonProcessingException, Exception {
        controller.perform(post("/countries")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new Country("Brazil", "Brasilia", "+55"))))
                .andExpect(status().isOk());
    }
}
