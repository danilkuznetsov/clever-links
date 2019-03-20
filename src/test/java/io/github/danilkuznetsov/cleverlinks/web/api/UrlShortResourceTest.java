package io.github.danilkuznetsov.cleverlinks.web.api;

import io.github.danilkuznetsov.cleverlinks.services.UrlService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by danil.kuznetsov on 07/02/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UrlShortResource.class)
public class UrlShortResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService shorterService;

    @Test
    public void shouldReturnShortUrlByLongUrl() throws Exception {
        //given
        String fullUrl = "http://gmail.com";
        String testShortUrl = "TestShortUrl";

        given(shorterService.createShortUrl(fullUrl)).willReturn(testShortUrl);
        // when and then
        mockMvc.perform(get("/api/urls/short/new?url={url}", fullUrl))
            .andExpect(status().isOk())
            .andExpect(content().string(testShortUrl));

        verify(shorterService).createShortUrl(fullUrl);
    }

    @Test
    public void shouldReturnLongUrlByShortUrl() throws Exception {
        //given
        String testShortUrl = "TestShortUrl";
        String expectedLongUrl = "http://gmail.com";
        given(shorterService.findLongUrlByShortUrl(testShortUrl)).willReturn(expectedLongUrl);
        //when and then
        mockMvc.perform(get("/api/urls/short/{id}", testShortUrl))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedLongUrl));

        verify(shorterService).findLongUrlByShortUrl(testShortUrl);

    }

}
