package pl.witomir.webcrawler.crawler;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import pl.witomir.webcrawler.domain.Page;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Slf4j
public class Crawler {
    private PageRepository pageRepository;
    private UrlUtil urlUtil;

    @Inject
    public Crawler(PageRepository pageRepository, UrlUtil urlUtil) {
        this.pageRepository = pageRepository;
        this.urlUtil = urlUtil;
    }

    public Set<Page> getAllPagesOnTheSameDomainStartingOn(String startUrl) {
        var visitedPages = new HashSet<Page>();
        var allDiscoveredPages = new HashSet<String>();
        var pageQueue = new LinkedList<String>();

        pageQueue.add(startUrl);
        allDiscoveredPages.add(startUrl);

        while (!pageQueue.isEmpty()) {
            String currentLink = pageQueue.remove();
            log.info("Fetching page: {}", currentLink);

            Page page = pageRepository.fetchPage(currentLink);
            Set<String> linksToVisit = urlUtil.removeVisitedLinks(page.getInternalLinks(), allDiscoveredPages);

            if (!linksToVisit.isEmpty()) {
                log.debug("Page has {} previously unseen children. Adding them to queue...", linksToVisit.size());
                allDiscoveredPages.addAll(linksToVisit);
                pageQueue.addAll(linksToVisit);
            }

            visitedPages.add(page);
        }

        return visitedPages;
    }
}
