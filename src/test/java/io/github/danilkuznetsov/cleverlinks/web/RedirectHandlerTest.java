package io.github.danilkuznetsov.cleverlinks.web;

import io.github.danilkuznetsov.cleverlinks.services.UrlCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@RunWith(SpringRunner.class)
@WebMvcTest(RedirectHandler.class)
public class RedirectHandlerTest {

    @MockBean
    private UrlCache urlCache;

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldRedirectToLongUrlByShortUrl() throws Exception {

        String shortUrl = "shortUrls";
        String longUrl = "http://gmail.com";

        given(this.urlCache.resolveUrl(shortUrl))
            .willReturn(longUrl);

        this.mvc
            .perform(
                get("/{id}", shortUrl)
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl(longUrl));

    }

    @Test
    public void shouldRedirectToHomePageWhenShortUrlNotFound() throws Exception {

        String shortUrl = "shortUrls";

        given(this.urlCache.resolveUrl(shortUrl))
            .willReturn(null);

        this.mvc.perform(
            get("/{id}", shortUrl)
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));
    }
}
