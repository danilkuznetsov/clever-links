package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDescription;
import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDetails;
import io.github.danilkuznetsov.cleverlinks.factories.FullUrlFactory;
import io.github.danilkuznetsov.cleverlinks.factories.ShortUrlFactory;
import io.github.danilkuznetsov.cleverlinks.factories.dto.FullUrlDescriptionFactory;
import io.github.danilkuznetsov.cleverlinks.factories.dto.UpdatedShortUrlFactory;
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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
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

    @Mock
    private UrlCache urlCache;

    private UrlService urlService;

    @Before
    public void setup() {
        when(this.mockGeneratorFactory.defaultGenerator()).thenReturn(new GeneratorMD5ShortUrl());

        this.urlService = new UrlServiceImpl(
            this.urlRepository,
            this.urlCache,
            this.mockGeneratorFactory
        );
    }

    @Test
    public void shouldReturnNewUrl() {
        doAnswer(invocation -> {
            FullUrl url = (FullUrl) invocation.getArguments()[0];
            FullUrlFactory.safeSetId(url, FullUrlFactory.FIRST_URL_ID);
            return url;
        })
            .when(this.urlRepository).save(ArgumentMatchers.any());

        final String newUrl = "http://google.com";
        final FullUrlDescription url = this.urlService.createUrl(newUrl);

        assertThat(url, is(FullUrlDescriptionFactory.urlDescription()));
    }

    @Test
    public void shouldPutCreatedFullUrlIntoCache() {
        final FullUrl url = FullUrlFactory.fullUrl();

        when(this.urlRepository.save(ArgumentMatchers.any(FullUrl.class)))
            .thenReturn(url);

        final String newUrl = "http://google.com";
        this.urlService.createUrl(newUrl);

        verify(this.urlCache).put(url);
    }

    @Test
    public void shouldAddNewCustomUrl() {
        when(this.urlRepository.findDetailsById(FullUrlFactory.FIRST_URL_ID))
            .thenReturn(Optional.of(FullUrlFactory.fullUrl()));

        final FullUrlDescription url = this.urlService
            .addCustomShortUrl(FullUrlFactory.FIRST_URL_ID, ShortUrlFactory.CUSTOM_SHORT_URL);

        assertThat(url, notNullValue());
        assertThat(url.countShortUrls(), is(2));
    }

    @Test(expected = FullUrlNotFoundException.class)
    public void shouldThrowExceptionIfExpandedUrlNotFound() {
        when(this.urlRepository.findDetailsById(FullUrlFactory.FIRST_URL_ID))
            .thenReturn(Optional.empty());

        this.urlService.addCustomShortUrl(FullUrlFactory.FIRST_URL_ID, ShortUrlFactory.CUSTOM_SHORT_URL);
    }

    @Test
    public void shouldPutExpandedFullUrlIntoCache() {
        final FullUrl url = FullUrlFactory.fullUrl();
        when(this.urlRepository.findDetailsById(FullUrlFactory.FIRST_URL_ID))
            .thenReturn(Optional.of(url));

        this.urlService.addCustomShortUrl(FullUrlFactory.FIRST_URL_ID, ShortUrlFactory.CUSTOM_SHORT_URL);

        verify(this.urlCache).put(url);
    }

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

    @Test
    public void shouldReturnFullUrlDetailsAfterUpdatingShortUrl() {
        when(this.urlRepository.findDetailsById(FullUrlFactory.FIRST_URL_ID))
            .thenReturn(Optional.of(FullUrlFactory.fullUrl()));

        FullUrlDescription urlDescription = this.urlService.updateCustomShortUrl(
            FullUrlFactory.FIRST_URL_ID,
            UpdatedShortUrlFactory.updatedUrl()
        );

        assertThat(urlDescription, is(FullUrlDescriptionFactory.urlDescription()));
    }

    @Test(expected = FullUrlNotFoundException.class)
    public void shouldThrowExceptionIfNotFoundUpdatedUrl() {
        when(this.urlRepository.findDetailsById(FullUrlFactory.FIRST_URL_ID))
            .thenReturn(Optional.empty());

        this.urlService.updateCustomShortUrl(
            FullUrlFactory.FIRST_URL_ID,
            UpdatedShortUrlFactory.updatedUrl()
        );
    }

    @Test
    public void shouldUpdateCacheIfUpdateCustomUrl() {
        final FullUrl url = FullUrlFactory.fullUrl();
        when(this.urlRepository.findDetailsById(FullUrlFactory.FIRST_URL_ID))
            .thenReturn(Optional.of(url));

        this.urlService.updateCustomShortUrl(
            FullUrlFactory.FIRST_URL_ID,
            UpdatedShortUrlFactory.updatedUrl()
        );

        verify(this.urlCache).put(url);
    }
}
