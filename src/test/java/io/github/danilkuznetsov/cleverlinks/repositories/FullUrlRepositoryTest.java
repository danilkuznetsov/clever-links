package io.github.danilkuznetsov.cleverlinks.repositories;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import io.github.danilkuznetsov.cleverlinks.factories.FullUrlFactory;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class FullUrlRepositoryTest {

    @Autowired
    private FullUrlRepository urlRepository;

    @Test
    @Sql("classpath:datasets/fullUrls/oneFullUrl.sql")
    public void shouldReturnUrlById() {

        final FullUrl url = this.urlRepository
            .findById(FullUrlFactory.FIRST_URL_ID)
            .orElseThrow(RuntimeException::new);

        assertThat(url).isEqualTo(FullUrlFactory.fullUrl());
        assertThat(url.shortUrls()).isNotEmpty();
    }
}
