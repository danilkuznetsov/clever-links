package io.github.danilkuznetsov.cleverlinks.service;

import io.github.danilkuznetsov.cleverlinks.strategies.GeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.strategies.GeneratorMD5ShortUrl;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
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
public class UrlShorterServiceUnitTest {

    @Mock
    private GeneratorFactory mockGeneratorFactory;

    private UrlShorterService urlShorterService;

    @Before
    public void setup(){
        when(this.mockGeneratorFactory.createGenerator("")).thenReturn(new GeneratorMD5ShortUrl());

        this.urlShorterService = new DefaultUrlShorterService(this.mockGeneratorFactory);
    }

    @Test
    public void shouldCreateNewShortUrl() {
        //given
        String fullUrl = "http://google.com";

        //when
        String actualHash = this.urlShorterService.createNewShortUrl(fullUrl);

        //then
        assertThat(actualHash, notNullValue());
    }

    @Test
    public void shouldFindLongUrlByShortUrl() {
        //given
        String expectedLongUrl = "http://google.com";
        String shortUrl = this.urlShorterService.createNewShortUrl(expectedLongUrl);

        //when
        String actualLongUrl = this.urlShorterService.findLongUrlByShortUrl(shortUrl);

        //then
        assertThat(actualLongUrl, equalTo(expectedLongUrl));

    }

    @Test
    public void shouldCreateDifferentShortUrlForDifferentLongUrl() {
        //given
        String longUrl1 = "http://google.com";
        String longUrl2 = "http://gmail.com";

        //when
        String actualShortUrl1 = this.urlShorterService.createNewShortUrl(longUrl1);
        String actualShortUrl2 = this.urlShorterService.createNewShortUrl(longUrl2);

        //then
        assertThat(actualShortUrl1, not(equalTo(actualShortUrl2)));

    }

    @Test
    public void shouldFindDifferentLongUrlByDifferentShotUrl() {
        //give
        String expectedLongUrl1 = "http://google.com";
        String expectedLongUrl2 = "http://gmail.com";

        //when
        String shortUrl1 = this.urlShorterService.createNewShortUrl(expectedLongUrl1);
        String shortUrl2 = this.urlShorterService.createNewShortUrl(expectedLongUrl2);

        String actualLongUrl1 = this.urlShorterService.findLongUrlByShortUrl(shortUrl1);
        String actualLongUrl2 = this.urlShorterService.findLongUrlByShortUrl(shortUrl2);

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
        String shortUrl = this.urlShorterService.createNewShortUrl(expectedBeforeUpdateLongUrl);

        //then
        String actualBeforeUpdateLongUrl = this.urlShorterService.findLongUrlByShortUrl(shortUrl);
        assertThat(actualBeforeUpdateLongUrl, equalTo(expectedBeforeUpdateLongUrl));

        this.urlShorterService.updateLongUrlByShortUrl(shortUrl, expectedAfterUpdateLongUrl);

        String actualAfterUpdateLongUrl = this.urlShorterService.findLongUrlByShortUrl(shortUrl);
        assertThat(actualAfterUpdateLongUrl, equalTo(expectedAfterUpdateLongUrl));

    }

    @Test
    public void shouldReturnExistingShortUrlWhenCallCreateNewShortUrlBySameLongUrl(){
        //give
        String expectedLongUrl = "http://google.com";

        //when
        String expectedShortUrl = this.urlShorterService.createNewShortUrl(expectedLongUrl);

        //then
        String actualShortUrl = this.urlShorterService.createNewShortUrl(expectedLongUrl);
        assertThat(actualShortUrl,equalTo(expectedShortUrl));

    }

}
