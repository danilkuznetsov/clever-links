package io.github.danilkuznetsov.cleverlinks.service;

import io.github.danilkuznetsov.cleverlinks.strategies.GeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.strategies.GeneratorMD5ShortUrl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by danil.kuznetsov on 18/01/17.
 */

public class UrlShorterServiceUnitTest {

    GeneratorFactory mockGeneratorFactory = mock(GeneratorFactory.class);

    private UrlShorterService urlShorterService;

    @Before
    public void setup(){
        when(mockGeneratorFactory.createGenerator("")).thenReturn(new GeneratorMD5ShortUrl());
        urlShorterService = new DefaultUrlShorterService(mockGeneratorFactory);
    }

    @Test
    public void shouldCreateNewShortUrl() {
        //given
        String fullUrl = "http://google.com";

        //when
        String actualHash = urlShorterService.createNewShortUrl(fullUrl);

        //then
        assertThat(actualHash, notNullValue());
    }

    @Test
    public void shouldFindLongUrlByShortUrl() {
        //given
        String expectedLongUrl = "http://google.com";
        String shortUrl = urlShorterService.createNewShortUrl(expectedLongUrl);

        //when
        String actualLongUrl = urlShorterService.findLongUrlByShortUrl(shortUrl);

        //then
        assertThat(actualLongUrl, equalTo(expectedLongUrl));

    }

    @Test
    public void shouldCreateDifferentShortUrlForDifferentLongUrl() {
        //given
        String longUrl1 = "http://google.com";
        String longUrl2 = "http://gmail.com";

        //when
        String actualShortUrl1 = urlShorterService.createNewShortUrl(longUrl1);
        String actualShortUrl2 = urlShorterService.createNewShortUrl(longUrl2);

        //then
        assertThat(actualShortUrl1, not(equalTo(actualShortUrl2)));

    }

    @Test
    public void shouldFindDifferentLongUrlByDifferentShotUrl() {
        //give
        String expectedLongUrl1 = "http://google.com";
        String expectedLongUrl2 = "http://gmail.com";

        //when
        String shortUrl1 = urlShorterService.createNewShortUrl(expectedLongUrl1);
        String shortUrl2 = urlShorterService.createNewShortUrl(expectedLongUrl2);

        String actualLongUrl1 = urlShorterService.findLongUrlByShortUrl(shortUrl1);
        String actualLongUrl2 = urlShorterService.findLongUrlByShortUrl(shortUrl2);

        //then
        assertThat(actualLongUrl1, equalTo(expectedLongUrl1));
        assertThat(actualLongUrl2, equalTo(expectedLongUrl2));
    }

    @Test
    public void shouldUpdateLongUrlBySameShortUrl() {
        //give
        String expectedBeforeUpdateLongUrl = "http://google.com";
        String expectedAfterUpdateLongUrl = "http://gmail.com";

        //when
        String shortUrl = urlShorterService.createNewShortUrl(expectedBeforeUpdateLongUrl);

        //then
        String actualBeforeUpdateLongUrl = urlShorterService.findLongUrlByShortUrl(shortUrl);
        assertThat(actualBeforeUpdateLongUrl, equalTo(expectedBeforeUpdateLongUrl));

        urlShorterService.updateLongUrlByShortUrl(shortUrl, expectedAfterUpdateLongUrl);

        String actualAfterUpdateLongUrl = urlShorterService.findLongUrlByShortUrl(shortUrl);
        assertThat(actualAfterUpdateLongUrl, equalTo(expectedAfterUpdateLongUrl));

    }

    @Test
    public void shouldReturnExistingShortUrlWhenCallCreateNewShortUrlBySameLongUrl(){
        //give
        String expectedLongUrl = "http://google.com";

        //when
        String expectedShortUrl = urlShorterService.createNewShortUrl(expectedLongUrl);

        //then
        String actualShortUrl = urlShorterService.createNewShortUrl(expectedLongUrl);
        assertThat(actualShortUrl,equalTo(expectedShortUrl));

    }

}
