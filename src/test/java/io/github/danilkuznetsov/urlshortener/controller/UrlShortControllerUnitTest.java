package io.github.danilkuznetsov.urlshortener.controller;

import io.github.danilkuznetsov.urlshortener.service.UrlShorterService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by danil.kuznetsov on 07/02/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UrlShortController.class)
public class UrlShortControllerUnitTest {
    @Inject
    MockMvc mockMvc;

    @MockBean
    UrlShorterService shorterService;

    @Test
    public void shouldReturnShortUrlByLongUrl() throws Exception {
        //given
        String fullUrl = "http://gmail.com";
        String testShortUrl = "TestShortUrl";

        given(shorterService.createNewShortUrl(fullUrl)).willReturn(testShortUrl);
        // when and then
        mockMvc.perform(get("/urls/new?url={url}", fullUrl))
                .andExpect(status().isOk())
                .andExpect(content().string(testShortUrl));

        verify(shorterService).createNewShortUrl(fullUrl);
    }

}
