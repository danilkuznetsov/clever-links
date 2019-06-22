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
        final FullUrl url = FullUrlFactory.simpleFullUrl();

        final ShortUrl mockExistingUrl = Mockito.mock(ShortUrl.class);
        FullUrlFactory.safeAddShortUrl(url, mockExistingUrl);

        when(mockExistingUrl.getId()).thenReturn(ShortUrlFactory.SHORT_URL_ID);

        url.updateShortUrl(ShortUrlFactory.SHORT_URL_ID, ShortUrlFactory.CUSTOM_SHORT_URL);

        assertThat(url.shortUrls().size(), CoreMatchers.is(2));

        // mark as deleted and create new short url we want update existing short url
        verify(mockExistingUrl).markDeleted();
    }

    @Test(expected = ShortUrlNotFoundException.class)
    public void shouldThrowExceptionIfUpdatedShortUrlNotFound() {
        final FullUrl url = FullUrlFactory.fullUrl();

        final long invalidShortUrlId = 10L;
        url.updateShortUrl(invalidShortUrlId, "newUrl");
    }

    @Test
    public void shouldMarkDeleteShortUrlById(){
        final FullUrl url = FullUrlFactory.simpleFullUrl();

        final ShortUrl mockExistingUrl = Mockito.mock(ShortUrl.class);
        when(mockExistingUrl.getId()).thenReturn(ShortUrlFactory.SHORT_URL_ID);

        FullUrlFactory.safeAddShortUrl(url, mockExistingUrl);

        url.deleteShortUrl(ShortUrlFactory.SHORT_URL_ID);

        assertThat(url.shortUrls().size(), CoreMatchers.is(1));

        verify(mockExistingUrl).markDeleted();
    }

    @Test(expected = ShortUrlNotFoundException.class)
    public void shouldThrowExceptionIfShortUrlNotFoundWhenDeletingShortUrl() {
        final FullUrl url = FullUrlFactory.fullUrl();

        final long invalidShortUrlId = 10L;
        url.deleteShortUrl(invalidShortUrlId);
    }
}
