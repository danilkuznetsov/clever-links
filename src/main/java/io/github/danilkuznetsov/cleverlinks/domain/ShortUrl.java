package io.github.danilkuznetsov.cleverlinks.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "short_url")
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "full_url_id", nullable = false, updatable = false)
    private FullUrl fullUrl;

    @Builder
    private ShortUrl(
        final Long id,
        final String shortUrl,
        final FullUrl fullUrl
    ){

        this.id = id;
        this.url = shortUrl;
        this.fullUrl = fullUrl;
    }
}
