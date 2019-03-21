package io.github.danilkuznetsov.cleverlinks.repositories;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public interface FullUrlRepository extends JpaRepository<FullUrl, Long> {

    boolean existsByUrl(String url);

    @Query("select url from FullUrl url left join fetch url.shortUrls ")
    Optional<FullUrl> findDetailsById(Long urlId);
}
