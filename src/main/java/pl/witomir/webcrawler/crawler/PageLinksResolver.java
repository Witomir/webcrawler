package pl.witomir.webcrawler.crawler;

import com.google.inject.Inject;
import org.jsoup.nodes.Document;
import pl.witomir.webcrawler.domain.Site;

public class PageLinksResolver {
    private DocumentLinkResolver documentLinkResolver;

    @Inject
    public PageLinksResolver(DocumentLinkResolver documentLinkResolver) {
        this.documentLinkResolver = documentLinkResolver;
    }

    public Site resolvePageLinks(String pageUrl, Document document, String domain) {
        Site site = new Site();
        site.setUrl(pageUrl);
        site.setSameDomainLinks(documentLinkResolver.getAllLinksToSameDomain(document, domain));
        site.setExternalLinks(documentLinkResolver.getAllExternalLinks(document, domain));
        site.setStaticContentLinks(documentLinkResolver.getStaticContentLinks(document));

        return site;
    }
}
