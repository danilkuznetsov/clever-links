package io.github.danilkuznetsov.cleverlinks.web.ui;

import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDetails;
import io.github.danilkuznetsov.cleverlinks.factories.FullUrlFactory;
import io.github.danilkuznetsov.cleverlinks.factories.ShortUrlFactory;
import io.github.danilkuznetsov.cleverlinks.factories.dto.FullUrlDescriptionFactory;
import io.github.danilkuznetsov.cleverlinks.services.UrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UrlController.class)
public class UrlControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UrlService urlService;

    @Test
    public void shouldDisplayFullUrlDetailsPage() throws Exception {

        when(this.urlService.loadDetails(FullUrlFactory.FIRST_URL_ID))
            .thenReturn(FullUrlDetails.of(FullUrlFactory.fullUrl()));

        this.mvc.perform(
            get("/dashboard/urls/{id}", FullUrlFactory.FIRST_URL_ID)
        )
            .andExpect(status().isOk())
            .andExpect(view().name("dashboard/fullUrlDetails"))
            .andExpect(model().attributeExists("fullUrlDetails"));
    }

    @Test
    public void shouldRedirectToDetailsPageAfterSuccessfulCreationUrl() throws Exception {

        when(this.urlService.createUrl(FullUrlFactory.FIRST_URL))
            .thenReturn(FullUrlDescriptionFactory.urlDescription());

        this.mvc.perform(
            post("/dashboard/urls")
                .param("url", FullUrlFactory.FIRST_URL)
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/dashboard/urls/1"));
    }

    @Test
    public void shouldRedirectToDetailsPageAfterSuccessfulAddNewCustomUrl() throws Exception {

        when(this.urlService.addCustomShortUrl(FullUrlFactory.FIRST_URL_ID, ShortUrlFactory.SHORT_URL))
            .thenReturn(FullUrlDescriptionFactory.urlDescription());

        this.mvc.perform(
            post("/dashboard/urls/{urlId}/short-urls", FullUrlFactory.FIRST_URL_ID)
                .param("short-url", ShortUrlFactory.SHORT_URL)
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/dashboard/urls/1"));
    }
}
