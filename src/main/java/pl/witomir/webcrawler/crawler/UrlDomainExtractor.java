package pl.witomir.webcrawler.crawler;

import pl.witomir.webcrawler.domain.WrongDomainException;

import java.net.URI;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UrlDomainExtractor {

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

    public Set<String> removeVisitedLinks(Set<String> linksToCheck, Set<String> visitedPages) {
        return linksToCheck
                .stream()
                .filter(link -> !visitedPages.contains(link))
                .collect(Collectors.toSet());
    }
}
