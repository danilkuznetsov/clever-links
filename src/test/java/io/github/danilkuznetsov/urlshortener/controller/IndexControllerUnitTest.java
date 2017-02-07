package io.github.danilkuznetsov.urlshortener.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by danil.kuznetsov on 07/02/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerUnitTest {
    @Inject
    MockMvc mockMvc;

    @Test
    public void shouldReturnHello() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world! I'm url shortener service"));
    }
}
