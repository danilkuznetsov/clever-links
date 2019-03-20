package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.factories.FullUrlFactory;
import io.github.danilkuznetsov.cleverlinks.factories.ShortUrlFactory;
import java.util.Collections;
import org.hamcrest.CoreMatchers;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public class UrlCacheTest {

    private UrlCache urlCache;

    @Before
    public void setUp() {
        this.urlCache = new UrlCacheImpl();
    }

    @Test
    public void shouldResolveShortUrl() {

        this.urlCache.put(FullUrlFactory.fullUrl());

        String actualUrl = this.urlCache.resolveUrl(ShortUrlFactory.SHORT_URL);

        assertThat(actualUrl, CoreMatchers.equalTo(FullUrlFactory.FIRST_URL));
    }

    @Test
    public void shouldResolveShortUrlAfterBatchPutting() {

        this.urlCache.putAll(Collections.singletonList(FullUrlFactory.fullUrl()));

        String actualUrl = this.urlCache.resolveUrl(ShortUrlFactory.SHORT_URL);

        assertThat(actualUrl, CoreMatchers.equalTo(FullUrlFactory.FIRST_URL));
    }
}
