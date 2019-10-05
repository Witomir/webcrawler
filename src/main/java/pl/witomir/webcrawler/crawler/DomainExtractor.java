package pl.witomir.webcrawler.crawler;

import pl.witomir.webcrawler.domain.WrongDomainException;

import java.net.URI;
import java.util.Objects;

public class DomainExtractor {

    private static final String WWW_PREFIX = "www.";

    public String getDomainName(String url) {
        try {
            var uri = new URI(url);
            String domain = uri.getHost();
            Objects.requireNonNull(domain);

            return domain.startsWith(WWW_PREFIX)
                    ? domain.substring(WWW_PREFIX.length())
                    : domain;
        } catch (Exception e) {
            throw new WrongDomainException(e);
        }
    }
}
