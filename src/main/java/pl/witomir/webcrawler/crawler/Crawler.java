package pl.witomir.webcrawler.crawler;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import pl.witomir.webcrawler.domain.Page;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Slf4j
public class Crawler {
    private PageRepository pageRepository;
    private UrlUtil urlUtil;
    private PageMapper pageMapper;

    @Inject
    public Crawler(PageRepository pageRepository, UrlUtil urlUtil, PageMapper pageMapper) {
        this.pageRepository = pageRepository;
        this.urlUtil = urlUtil;
        this.pageMapper = pageMapper;
    }

    public Set<Page> getAllPagesOnTheSameDomainStartingOn(String startUrl) {
        log.info("Starting from URL: {}", startUrl);
        var visitedSites = new HashSet<Page>();
        var discoveredPages = new HashSet<String>();
        var pageQueue = new LinkedList<String>();
        pageQueue.add(startUrl);
        discoveredPages.add(startUrl);

        while (!pageQueue.isEmpty()) {
            String currentLink = pageQueue.remove();

            log.info("Fetching page: {}", currentLink);
            Document document = pageRepository.fetchPage(currentLink);
            var page = pageMapper.mapFromDocument(currentLink, document);
            Set<String> linksToSameDomain = page.getInternalLinks();
            Set<String> linksToVisit = urlUtil.removeVisitedLinks(linksToSameDomain, discoveredPages);

            if (!linksToVisit.isEmpty()) {
                log.info("Page has {} previously unseen children. Adding them to queue...", linksToVisit.size());
                discoveredPages.addAll(linksToVisit);
                pageQueue.addAll(linksToVisit);
            }

            visitedSites.add(page);
        }

        return visitedSites;
    }
}
