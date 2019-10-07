package pl.witomir.webcrawler.crawler;

import pl.witomir.webcrawler.domain.WrongDomainException;

import java.net.URI;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UrlUtil {

    private static final String WWW_PREFIX = "www.";
    private static final char ANCHOR_CHAR = '#';

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

    public String removeAnchorFromLink(String src) {
        if(Objects.nonNull(src) && src.indexOf(ANCHOR_CHAR) != -1){
            return src.substring(0, src.indexOf('#'));
        }

        return src;
    }
}
