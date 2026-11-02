package com.example.minitasks.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskControllerV1.class)
class TaskControllerV1Test {

    @Autowired
    MockMvc mvc;

    @Test
    void ping_devuelvePong() throws Exception {
        mvc.perform(get("/v1/ping"))
           .andExpect(status().isOk())
           .andExpect(content().string("pong"));
    }

    @Test
    void crearTarea_devuelve201ConDoneFalse() throws Exception {
        mvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Estudiar\"}"))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.title").value("Estudiar"))
           .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void listarTareas_devuelve200() throws Exception {
        mvc.perform(get("/v1/tasks"))
           .andExpect(status().isOk());
    }
}
