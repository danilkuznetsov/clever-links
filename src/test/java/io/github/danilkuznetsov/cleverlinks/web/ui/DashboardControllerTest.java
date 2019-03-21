package io.github.danilkuznetsov.cleverlinks.web.ui;

import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDescription;
import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDetails;
import io.github.danilkuznetsov.cleverlinks.factories.FullUrlFactory;
import io.github.danilkuznetsov.cleverlinks.factories.dto.FullUrlDescriptionFactory;
import io.github.danilkuznetsov.cleverlinks.services.UrlService;
import java.util.Collections;
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
@WebMvcTest(DashBoardController.class)
public class DashboardControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UrlService urlService;

    @Test
    public void shouldDisplayDashboardPage() throws Exception {

        when(this.urlService.loadUrls())
            .thenReturn(Collections.singletonList(FullUrlDescription.of(FullUrlFactory.fullUrl())));

        this.mvc.perform(
            get("/dashboard")
        )
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("urls"))
            .andExpect(view().name("dashboard/dashboard"));
    }

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

}
