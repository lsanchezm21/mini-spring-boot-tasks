package com.example.minitasks.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskControllerV1.class)
class TaskControllerV1GetByIdTest {

    @Autowired
    MockMvc mvc;

    @Test
    void getById_existente_devuelve200() throws Exception {
        mvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Estudiar\"}"))
           .andExpect(status().isCreated());

        mvc.perform(get("/v1/tasks/1"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id").value(1))
           .andExpect(jsonPath("$.title").value("Estudiar"))
           .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void getById_inexistente_devuelve404() throws Exception {
        mvc.perform(get("/v1/tasks/999"))
           .andExpect(status().isNotFound());
    }
}
