package io.github.danilkuznetsov.cleverlinks.controller;

import io.github.danilkuznetsov.cleverlinks.service.UrlShorterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by danil.kuznetsov on 07/02/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlShorterService shorterService;

    @Test
    public void shouldReturnHomePage() throws Exception {

        this.mockMvc.perform(
            get("/")
        )
            .andExpect(status().isOk());
    }

    @Test
    public void shouldRedirectToLongUrlByShortId() throws Exception {
        //give
        String shortUrls = "shortUrls";
        String longUrl = "http://gmail.com";

        given(this.shorterService.findLongUrlByShortUrl(shortUrls)).willReturn(longUrl);
        //when and then
        this.mockMvc
            .perform(get("/{id}", shortUrls))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl(longUrl));

        verify(this.shorterService).findLongUrlByShortUrl(shortUrls);
    }

    @Test
    public void shouldReturnNotFoundByShortIdIsNotFound() throws Exception {
        //give
        String shortUrls = "shortUrls";

        given(this.shorterService.findLongUrlByShortUrl(shortUrls)).willReturn(null);

        //when and then
        this.mockMvc.perform(
            get("/{id}", shortUrls)
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        verify(this.shorterService).findLongUrlByShortUrl(shortUrls);
    }
}
