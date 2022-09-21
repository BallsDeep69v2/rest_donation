package com.example.rest_donation;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonRepositoryTests {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class Saving {

        @Test
        @DirtiesContext
            // nur beim Verändern von Daten anzuwenden
        void works() throws Exception {
            var json = """
                    {
                       "amount": 5,
                       "date": "2022-01-01"
                    }
                    """;
            var resource = "/api/donations/17";

            mockMvc.perform(post("/api/persons/12/donations")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(json)).andReturn();
            mockMvc.perform(get(resource))
                    .andExpect(status().isOk())
                    .andExpect(content().json(json));
        }
    }


}
