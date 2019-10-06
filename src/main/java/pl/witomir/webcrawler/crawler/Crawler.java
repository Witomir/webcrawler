package pl.witomir.webcrawler.crawler;

import com.google.inject.Inject;
import org.jsoup.nodes.Document;
import pl.witomir.webcrawler.domain.Site;
import pl.witomir.webcrawler.domain.SiteMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Crawler {
    private PageRepository pageRepository;
    private UrlDomainExtractor urlDomainExtractor;
    private DocumentHelper documentHelper;

    @Inject
    public Crawler(PageRepository pageRepository, UrlDomainExtractor urlDomainExtractor, DocumentHelper documentHelper) {
        this.pageRepository = pageRepository;
        this.urlDomainExtractor = urlDomainExtractor;
        this.documentHelper = documentHelper;
    }

    public SiteMap generateSiteMapStartingOn(String startUrl) {
        var visitedPages = new HashSet<String>();
        var siteMap = new SiteMap();
        String domain = urlDomainExtractor.getDomainName(startUrl);
        Document startPage = pageRepository.fetchPage(startUrl);
        List<String> allLinksToSameDomain = documentHelper.getAllLinksToSameDomain(startPage, domain);
        siteMap.addSite(new Site(startUrl));

        return recursivelyFetchAllLinksOnPages(allLinksToSameDomain, siteMap, visitedPages, domain);
    }

    private SiteMap recursivelyFetchAllLinksOnPages(List<String> linksToTraverse, SiteMap siteMap, HashSet<String> visitedPages, String domain) {
        for (String link : linksToTraverse) {
            if(!visitedPages.contains(link)){
                visitedPages.add(link);
                Document document = pageRepository.fetchPage(link);
                List<String> linksToSameDomain = documentHelper.getAllLinksToSameDomain(document, domain);

            }
        }

        return new SiteMap();
    }
}
