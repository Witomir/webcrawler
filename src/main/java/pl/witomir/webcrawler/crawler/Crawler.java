package pl.witomir.webcrawler.crawler;

import com.google.inject.Inject;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.witomir.webcrawler.domain.Site;
import pl.witomir.webcrawler.domain.SiteMap;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Crawler {
    private PageRepository pageRepository;
    private DomainExtractor domainExtractor;

    @Inject
    public Crawler(PageRepository pageRepository, DomainExtractor domainExtractor) {
        this.pageRepository = pageRepository;
        this.domainExtractor = domainExtractor;
    }

    public SiteMap generateMapStartingOn(String startUrl) {
        String domain = domainExtractor.getDomainName(startUrl);
        Document startPage = pageRepository.fetchPage(startUrl);

        return new SiteMap();
    }

    private ArrayList<String> getAllLinksToSameDomain(Document startPage, String domain) {
        var linksList = new ArrayList<String>();
        Elements links = startPage.getElementsByTag("a");

        for (var link : links) {
            String src = link.attr("src");
            if(src.contains(domain)) {
                linksList.add(src);
            }
        }

        return linksList;
    }

}
