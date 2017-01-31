package io.github.danilkuznetsov;

import io.github.danilkuznetsov.service.DefaultUrlShorterService;
import io.github.danilkuznetsov.service.UrlShorterService;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


/**
 * Created by danil.kuznetsov on 18/01/17.
 */

public class UrlShorterServiceUnitTest {

    @Test
    public void shouldCreateNewShortUrl() {
        //given
        String fullUrl = "http://google.com";
        UrlShorterService urlShorterService = new DefaultUrlShorterService();

        //when
        String actualHash = urlShorterService.createNewShortUrl(fullUrl);

        //then
        assertThat(actualHash, notNullValue());
    }

    @Test
    public void shouldFindLongUrlByShortUrl() {
        //given
        String expectedLongUrl = "http://google.com";
        UrlShorterService urlShorterService = new DefaultUrlShorterService();
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

        UrlShorterService urlShorterService = new DefaultUrlShorterService();

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

        UrlShorterService urlShorterService = new DefaultUrlShorterService();

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

        UrlShorterService urlService = new DefaultUrlShorterService();

        //when
        String shortUrl = urlService.createNewShortUrl(expectedBeforeUpdateLongUrl);

        //then
        String actualBeforeUpdateLongUrl = urlService.findLongUrlByShortUrl(shortUrl);
        assertThat(actualBeforeUpdateLongUrl, equalTo(expectedBeforeUpdateLongUrl));

        urlService.updateLongUrlByShortUrl(shortUrl, expectedAfterUpdateLongUrl);

        String actualAfterUpdateLongUrl = urlService.findLongUrlByShortUrl(shortUrl);

        assertThat(actualAfterUpdateLongUrl, equalTo(expectedAfterUpdateLongUrl));

    }

}
