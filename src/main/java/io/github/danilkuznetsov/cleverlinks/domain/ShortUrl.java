package io.github.danilkuznetsov.cleverlinks.domain;

import javax.persistence.Column;
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
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(exclude = {"id", "fullUrl"})
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Column(name = "url", unique = true, updatable = false, nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "full_url_id", nullable = false, updatable = false)
    private FullUrl fullUrl;

    @Getter
    @Column(name = "enabled")
    private Boolean enabled;

    @Getter
    @Column(name = "deleted")
    private Boolean deleted;

    @Builder
    private ShortUrl(
        final Long id,
        final String shortUrl,
        final FullUrl fullUrl,
        final boolean enabled,
        final boolean deleted
    ) {
        this.id = id;
        this.url = shortUrl;
        this.fullUrl = fullUrl;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public String fullUrl() {
        return this.fullUrl.getUrl();
    }

    void disable() {
        this.enabled = false;
    }

    // TODO: after mark as deleted url is disabled
    // but instead of simply disabled status, the deleted url cannot be enabled
    void markDeleted() {
        this.disable();
        this.deleted = true;
    }
}
