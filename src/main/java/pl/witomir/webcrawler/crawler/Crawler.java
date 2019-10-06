package pl.witomir.webcrawler.crawler;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import pl.witomir.webcrawler.domain.Site;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class Crawler {
    private PageRepository pageRepository;
    private UrlDomainExtractor urlDomainExtractor;
    private PageLinksResolver pageLinksResolver;

    @Inject
    public Crawler(PageRepository pageRepository, UrlDomainExtractor urlDomainExtractor, PageLinksResolver pageLinksResolver) {
        this.pageRepository = pageRepository;
        this.urlDomainExtractor = urlDomainExtractor;
        this.pageLinksResolver = pageLinksResolver;
    }

    public Site generateSiteMapStartingOn(String startUrl) {
        var visitedPages = new HashSet<String>();
        String domain = urlDomainExtractor.getDomainName(startUrl);

        log.debug("Using for URL: {}, the domain: {}", startUrl, domain);
        Set<Site> sites = recursivelyFetchAllLinksOnPages(Set.of(startUrl), visitedPages, domain);

        return sites.stream().findFirst().get();
    }

    private Set<Site> recursivelyFetchAllLinksOnPages(Set<String> linksToTraverse, HashSet<String> visitedPages, String domain) {
        var sites = new HashSet<Site>();

        for (String link : linksToTraverse) {
            if (!visitedPages.contains(link)) {
                visitedPages.add(link);

                log.debug("Fetching page content: {}", link);
                Document document = pageRepository.fetchPage(link);
                var site = pageLinksResolver.resolvePageLinks(link, document, domain);

                Set<String> linksToSameDomain = site.getSameDomainLinks();
                Set<String> linksToVisit = urlDomainExtractor.removeVisitedLinks(linksToSameDomain, visitedPages);

                if (!linksToVisit.isEmpty()) {
                    log.debug("Page has {} children. Fetching them now...", linksToVisit.size());
                    site.setChildPages(recursivelyFetchAllLinksOnPages(linksToVisit, visitedPages, domain));
                }

                sites.add(site);
            }
        }

        return sites;
    }
}
