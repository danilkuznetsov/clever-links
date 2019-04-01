package io.github.danilkuznetsov.cleverlinks.domain;

import io.github.danilkuznetsov.cleverlinks.domain.exceptions.ShortUrlNotFoundException;
import io.github.danilkuznetsov.cleverlinks.factories.FullUrlFactory;
import io.github.danilkuznetsov.cleverlinks.factories.ShortUrlFactory;
import org.hamcrest.CoreMatchers;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FullUrlTest {

    @Test
    public void shouldUpdateExistingShortUrl() {

        FullUrl url = FullUrlFactory.simpleFullUrl();

        final ShortUrl mockExistingUrl = Mockito.mock(ShortUrl.class);
        FullUrlFactory.safeAddShortUrl(url, mockExistingUrl);

        when(mockExistingUrl.getId()).thenReturn(ShortUrlFactory.SHORT_URL_ID);

        url.updateShortUrl(ShortUrlFactory.SHORT_URL_ID, ShortUrlFactory.CUSTOM_SHORT_URL);

        assertThat(url.shortUrls().size(), CoreMatchers.is(2));

        // disable and create new short url we want update existing short url
        verify(mockExistingUrl).disable();
    }

    @Test(expected = ShortUrlNotFoundException.class)
    public void shouldThrowExceptionIfUpdatedShortUrlNotFound() {
        FullUrl url = FullUrlFactory.fullUrl();

        long invalidShortUrlId = 10L;
        url.updateShortUrl(invalidShortUrlId, "newUrl");
    }

}
