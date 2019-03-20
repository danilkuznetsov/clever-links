package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDetails;
import io.github.danilkuznetsov.cleverlinks.factories.FullUrlFactory;
import io.github.danilkuznetsov.cleverlinks.repositories.FullUrlRepository;
import io.github.danilkuznetsov.cleverlinks.services.exceptions.FullUrlAlreadyExist;
import io.github.danilkuznetsov.cleverlinks.services.strategies.GeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.services.strategies.generators.GeneratorMD5ShortUrl;
import java.util.Collections;
import java.util.List;
import org.hamcrest.CoreMatchers;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
    public void shouldCreateNewShortUrl() {
        //given
        String fullUrl = "http://google.com";

        //when
        String actualHash = this.urlService.createShortUrl(fullUrl);

        //then
        assertThat(actualHash, CoreMatchers.notNullValue());
    }

    @Test
    public void shouldFindLongUrlByShortUrl() {
        //given
        String expectedLongUrl = "http://google.com";
        String shortUrl = this.urlService.createShortUrl(expectedLongUrl);

        //when
        String actualLongUrl = this.urlService.findLongUrlByShortUrl(shortUrl);

        //then
        assertThat(actualLongUrl, CoreMatchers.equalTo(expectedLongUrl));

    }

    @Test
    public void shouldCreateDifferentShortUrlForDifferentLongUrl() {
        //given
        String longUrl1 = "http://google.com";
        String longUrl2 = "http://gmail.com";

        //when
        String actualShortUrl1 = this.urlService.createShortUrl(longUrl1);
        String actualShortUrl2 = this.urlService.createShortUrl(longUrl2);

        //then
        assertThat(actualShortUrl1, CoreMatchers.not(CoreMatchers.equalTo(actualShortUrl2)));

    }

    @Test
    public void shouldFindDifferentLongUrlByDifferentShotUrl() {
        //give
        String expectedLongUrl1 = "http://google.com";
        String expectedLongUrl2 = "http://gmail.com";

        //when
        String shortUrl1 = this.urlService.createShortUrl(expectedLongUrl1);
        String shortUrl2 = this.urlService.createShortUrl(expectedLongUrl2);

        String actualLongUrl1 = this.urlService.findLongUrlByShortUrl(shortUrl1);
        String actualLongUrl2 = this.urlService.findLongUrlByShortUrl(shortUrl2);

        //then
        assertThat(actualLongUrl1, CoreMatchers.equalTo(expectedLongUrl1));
        assertThat(actualLongUrl2, CoreMatchers.equalTo(expectedLongUrl2));
    }

    @Test
    public void shouldUpdateLongUrlBySameShortUrl() {
        //give
        String expectedBeforeUpdateLongUrl = "http://google.com";
        String expectedAfterUpdateLongUrl = "http://gmail.com";

        //when
        String shortUrl = this.urlService.createShortUrl(expectedBeforeUpdateLongUrl);

        //then
        String actualBeforeUpdateLongUrl = this.urlService.findLongUrlByShortUrl(shortUrl);
        assertThat(actualBeforeUpdateLongUrl, CoreMatchers.equalTo(expectedBeforeUpdateLongUrl));

        this.urlService.updateLongUrlByShortUrl(shortUrl, expectedAfterUpdateLongUrl);

        String actualAfterUpdateLongUrl = this.urlService.findLongUrlByShortUrl(shortUrl);
        assertThat(actualAfterUpdateLongUrl, CoreMatchers.equalTo(expectedAfterUpdateLongUrl));

    }

    @Test(expected = FullUrlAlreadyExist.class )
    public void shouldThrowExceptionIfCreateExistingFullUrl() {
        String fullUrl = "http://google.com";

        when(this.urlRepository.existsByUrl(fullUrl)).thenReturn(true);

        this.urlService.createShortUrl(fullUrl);
    }

    @Test
    public void shouldReturnUrls() {

        FullUrl url = FullUrlFactory.fullUrl();

        when(this.urlRepository.findAll()).thenReturn(Collections.singletonList(url));

        List<FullUrlDetails> urls = this.urlService.loadUrls();

        assertThat(urls, CoreMatchers.hasItem(FullUrlDetails.of(url)));
    }

}
