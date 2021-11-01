package com.medolia.spring.demo.uniResult;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UniResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/demo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=utf-8"))
                .andExpect(jsonPath("$.data").value("hello, this is demo data"))
                .andExpect(jsonPath("$.message").value("操作成功"));
    }

    @Test
    public void testGet1() throws Exception {
        mockMvc.perform(get("/demo/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("114")));
    }

    @Test
    public void testError() throws Exception {
        mockMvc.perform(get("/error"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
