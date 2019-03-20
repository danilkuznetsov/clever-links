package io.github.danilkuznetsov.cleverlinks.services;

import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDescription;
import java.util.List;

/**
 * Created by danil.kuznetsov on 18/01/17.
 */
public interface UrlService {

    FullUrlDescription createUrl(String url);

    List<FullUrlDescription> loadUrls();
}
