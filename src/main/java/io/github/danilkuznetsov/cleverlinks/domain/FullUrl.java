package io.github.danilkuznetsov.cleverlinks.domain;

import io.github.danilkuznetsov.cleverlinks.domain.exceptions.ShortUrlNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "shortUrls")
@Table(name = "full_url")
public class FullUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @Getter
    @Setter(AccessLevel.PACKAGE)
    private String url;

    @OneToMany(mappedBy = "fullUrl", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 10)
    private final Set<ShortUrl> shortUrls = new HashSet<>();

    @Builder
    private FullUrl(
        final Long id,
        final String url
    ) {
        this.id = id;
        this.url = url;
    }

    public void addShortUrl(final String url) {
        final ShortUrl shortUrl = ShortUrl.builder()
            .shortUrl(url)
            .fullUrl(this)
            .enabled(true)
            .build();

        this.shortUrls.add(shortUrl);
    }

    public void updateShortUrl(final Long shortUrlId, final String newShortUrl) {
        final ShortUrl shortUrl = this.findShortUrlById(shortUrlId);
        shortUrl.disable();
        this.addShortUrl(newShortUrl);
    }

    private ShortUrl findShortUrlById(Long shortId) {
        return this.shortUrls
            .stream()
            .filter(shortUrl -> shortId.equals(shortUrl.getId()))
            .findAny()
            .orElseThrow(ShortUrlNotFoundException::new);
    }

    public Set<ShortUrl> shortUrls() {
        return Collections.unmodifiableSet(this.shortUrls);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        final FullUrl fullUrl = (FullUrl) obj;
        return Objects.equals(this.getUrl(), fullUrl.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUrl());
    }
}
