package io.github.danilkuznetsov.cleverlinks.init;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import io.github.danilkuznetsov.cleverlinks.repositories.FullUrlRepository;
import io.github.danilkuznetsov.cleverlinks.services.UrlCache;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Component
@Profile("dev")
public class DefaultCacheInitializer implements ApplicationRunner {

    private final FullUrlRepository urlRepository;
    private final UrlCache urlCache;

    @Autowired
    public DefaultCacheInitializer(
        final FullUrlRepository urlRepository,
        final UrlCache urlCache
    ) {
        this.urlRepository = urlRepository;
        this.urlCache = urlCache;
    }

    @Override
    @Transactional
    public void run(final ApplicationArguments args) {
        // add all url to cache
        // this is used only for demo version
        List<FullUrl> urls = this.urlRepository.findAll();
        this.urlCache.putAll(urls);
    }
}
