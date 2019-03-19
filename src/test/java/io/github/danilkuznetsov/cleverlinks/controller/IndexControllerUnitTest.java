package io.github.danilkuznetsov.cleverlinks.controller;

import io.github.danilkuznetsov.cleverlinks.service.UrlShorterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by danil.kuznetsov on 07/02/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerUnitTest {
    @Inject
    MockMvc mockMvc;

    @MockBean
    UrlShorterService shorterService;

    @Test
    public void shouldReturnHello() throws Exception {
        //give
        String expectedContent = "Hello world! I'm url shortener service";
        //when and then
        mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent));
    }

    @Test
    public void shouldRedirectToLongUrlByShortId() throws Exception {
        //give
        String shortUrls = "shortUrls";
        String longUrl = "http://gmail.com";

        given(shorterService.findLongUrlByShortUrl(shortUrls)).willReturn(longUrl);
        //when and then
        mockMvc
                .perform(get("/{id}", shortUrls))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(longUrl));

        verify(shorterService).findLongUrlByShortUrl(shortUrls);
    }

    @Test
    public void shouldReturnNotFoundByShortIdIsNotFound() throws Exception {
        //give
        String shortUrls = "shortUrls";

        given(shorterService.findLongUrlByShortUrl(shortUrls)).willReturn(null);
        //when and then
        mockMvc
                .perform(get("/{id}", shortUrls))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(shorterService).findLongUrlByShortUrl(shortUrls);
    }
}
