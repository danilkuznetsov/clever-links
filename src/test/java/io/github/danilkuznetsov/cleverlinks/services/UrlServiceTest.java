package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDescription;
import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDetails;
import io.github.danilkuznetsov.cleverlinks.factories.FullUrlFactory;
import io.github.danilkuznetsov.cleverlinks.factories.dto.FullUrlDescriptionFactory;
import io.github.danilkuznetsov.cleverlinks.repositories.FullUrlRepository;
import io.github.danilkuznetsov.cleverlinks.services.exceptions.FullUrlAlreadyExistException;
import io.github.danilkuznetsov.cleverlinks.services.exceptions.FullUrlNotFoundException;
import io.github.danilkuznetsov.cleverlinks.services.strategies.GeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.services.strategies.generators.GeneratorMD5ShortUrl;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    @Mock
    private GeneratorFactory mockGeneratorFactory;

    @Mock
    private FullUrlRepository urlRepository;

    private UrlService urlService;

    @Before
    public void setup() {

        this.urlService = new UrlServiceImpl(
            this.urlRepository,
            this.mockGeneratorFactory
        );

        when(this.mockGeneratorFactory.createGenerator("")).thenReturn(new GeneratorMD5ShortUrl());
    }

    @Test
    public void shouldReturnNewUrl() {

        doAnswer(invocation -> {
            FullUrl url = (FullUrl) invocation.getArguments()[0];
            FullUrlFactory.safeSetId(url, FullUrlFactory.FIRST_URL_ID);
            return url;
        })
            .when(this.urlRepository).save(ArgumentMatchers.any());

        String newUrl = "http://google.com";
        FullUrlDescription url = this.urlService.createUrl(newUrl);

        assertThat(url, is(FullUrlDescriptionFactory.urlDescription()));
    }

//    @Test
//    public void shouldCreateDifferentShortUrlForDifferentLongUrl() {
//        //given
//        String longUrl1 = "http://google.com";
//        String longUrl2 = "http://gmail.com";
//
//        //when
//        String actualShortUrl1 = this.urlService.createUrl(longUrl1);
//        String actualShortUrl2 = this.urlService.createUrl(longUrl2);
//
//        //then
//        assertThat(actualShortUrl1, CoreMatchers.not(CoreMatchers.equalTo(actualShortUrl2)));
//
//    }

//    @Test
//    public void shouldFindDifferentLongUrlByDifferentShotUrl() {
//        //give
//        String expectedLongUrl1 = "http://google.com";
//        String expectedLongUrl2 = "http://gmail.com";
//
//        //when
//        String shortUrl1 = this.urlService.createUrl(expectedLongUrl1);
//        String shortUrl2 = this.urlService.createUrl(expectedLongUrl2);
//
//        String actualLongUrl1 = this.urlService.resolveUrl(shortUrl1);
//        String actualLongUrl2 = this.urlService.resolveUrl(shortUrl2);
//
//        //then
//        assertThat(actualLongUrl1, CoreMatchers.equalTo(expectedLongUrl1));
//        assertThat(actualLongUrl2, CoreMatchers.equalTo(expectedLongUrl2));
//    }

    @Test(expected = FullUrlAlreadyExistException.class)
    public void shouldThrowExceptionIfCreateExistingFullUrl() {
        String fullUrl = "http://google.com";

        when(this.urlRepository.existsByUrl(fullUrl)).thenReturn(true);

        this.urlService.createUrl(fullUrl);
    }

    @Test
    public void shouldReturnUrls() {

        FullUrl url = FullUrlFactory.fullUrl();

        when(this.urlRepository.findAll()).thenReturn(Collections.singletonList(url));

        List<FullUrlDescription> urls = this.urlService.loadUrls();

        assertThat(urls, CoreMatchers.hasItem(FullUrlDescription.of(url)));
    }

    @Test
    public void shouldReturnFullUrlDetails() {

        FullUrl fullUrl = FullUrlFactory.fullUrl();

        when(this.urlRepository.findDetailsById(FullUrlFactory.FIRST_URL_ID))
            .thenReturn(Optional.of(fullUrl));

        FullUrlDetails urlDetails = this.urlService.loadDetails(FullUrlFactory.FIRST_URL_ID);

        assertThat(urlDetails, is(FullUrlDetails.of(fullUrl)));
    }

    @Test(expected = FullUrlNotFoundException.class)
    public void shouldThrowExceptionIfFullUrlNotFound() {

        when(this.urlRepository.findDetailsById(FullUrlFactory.FIRST_URL_ID))
            .thenReturn(Optional.empty());

        this.urlService.loadDetails(FullUrlFactory.FIRST_URL_ID);
    }

}
