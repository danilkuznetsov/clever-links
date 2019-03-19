package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDetails;
import io.github.danilkuznetsov.cleverlinks.factories.FullUrlFactory;
import io.github.danilkuznetsov.cleverlinks.services.strategies.GeneratorFactory;
import io.github.danilkuznetsov.cleverlinks.services.strategies.GeneratorMD5ShortUrl;
import java.util.List;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
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
public class UrlServiceTest {

    @Mock
    private GeneratorFactory mockGeneratorFactory;

    private UrlService urlService;

    @Before
    public void setup(){
        when(this.mockGeneratorFactory.createGenerator("")).thenReturn(new GeneratorMD5ShortUrl());

        this.urlService = new UrlServiceImpl(this.mockGeneratorFactory);
    }

    @Test
    public void shouldCreateNewShortUrl() {
        //given
        String fullUrl = "http://google.com";

        //when
        String actualHash = this.urlService.createNewShortUrl(fullUrl);

        //then
        assertThat(actualHash, notNullValue());
    }

    @Test
    public void shouldFindLongUrlByShortUrl() {
        //given
        String expectedLongUrl = "http://google.com";
        String shortUrl = this.urlService.createNewShortUrl(expectedLongUrl);

        //when
        String actualLongUrl = this.urlService.findLongUrlByShortUrl(shortUrl);

        //then
        assertThat(actualLongUrl, equalTo(expectedLongUrl));

    }

    @Test
    public void shouldCreateDifferentShortUrlForDifferentLongUrl() {
        //given
        String longUrl1 = "http://google.com";
        String longUrl2 = "http://gmail.com";

        //when
        String actualShortUrl1 = this.urlService.createNewShortUrl(longUrl1);
        String actualShortUrl2 = this.urlService.createNewShortUrl(longUrl2);

        //then
        assertThat(actualShortUrl1, not(equalTo(actualShortUrl2)));

    }

    @Test
    public void shouldFindDifferentLongUrlByDifferentShotUrl() {
        //give
        String expectedLongUrl1 = "http://google.com";
        String expectedLongUrl2 = "http://gmail.com";

        //when
        String shortUrl1 = this.urlService.createNewShortUrl(expectedLongUrl1);
        String shortUrl2 = this.urlService.createNewShortUrl(expectedLongUrl2);

        String actualLongUrl1 = this.urlService.findLongUrlByShortUrl(shortUrl1);
        String actualLongUrl2 = this.urlService.findLongUrlByShortUrl(shortUrl2);

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
        String shortUrl = this.urlService.createNewShortUrl(expectedBeforeUpdateLongUrl);

        //then
        String actualBeforeUpdateLongUrl = this.urlService.findLongUrlByShortUrl(shortUrl);
        assertThat(actualBeforeUpdateLongUrl, equalTo(expectedBeforeUpdateLongUrl));

        this.urlService.updateLongUrlByShortUrl(shortUrl, expectedAfterUpdateLongUrl);

        String actualAfterUpdateLongUrl = this.urlService.findLongUrlByShortUrl(shortUrl);
        assertThat(actualAfterUpdateLongUrl, equalTo(expectedAfterUpdateLongUrl));

    }

    @Test
    public void shouldReturnExistingShortUrlWhenCallCreateNewShortUrlBySameLongUrl(){
        //give
        String expectedLongUrl = "http://google.com";

        //when
        String expectedShortUrl = this.urlService.createNewShortUrl(expectedLongUrl);

        //then
        String actualShortUrl = this.urlService.createNewShortUrl(expectedLongUrl);
        assertThat(actualShortUrl,equalTo(expectedShortUrl));
    }

    @Test
    public void shouldReturnUrls(){

        List<FullUrlDetails> urls = this.urlService.loadUrls();

        assertThat(urls, hasItem(FullUrlDetails.of(FullUrlFactory.fullUrl())));
    }

}
