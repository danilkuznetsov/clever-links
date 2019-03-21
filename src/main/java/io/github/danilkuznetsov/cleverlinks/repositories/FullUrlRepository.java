package io.github.danilkuznetsov.cleverlinks.repositories;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public interface FullUrlRepository extends JpaRepository<FullUrl, Long> {

    boolean existsByUrl(String url);

    Optional<FullUrl> findDetailsById(Long urlId);
}
