package pl.witomir.webcrawler.crawler;

import com.google.inject.Inject;
import org.jsoup.nodes.Document;
import pl.witomir.webcrawler.domain.Page;

public class PageMapper {
    private DocumentLinkResolver documentLinkResolver;

    @Inject
    public PageMapper(DocumentLinkResolver documentLinkResolver) {
        this.documentLinkResolver = documentLinkResolver;
    }

    public Page mapFromDocument(String pageUrl, Document document) {
        Page page = new Page();
        page.setUrl(pageUrl);
        page.setInternalLinks(documentLinkResolver.getAllInternalLinks(document));
        page.setExternalLinks(documentLinkResolver.getAllExternalLinks(document));
        page.setStaticContentLinks(documentLinkResolver.getStaticContentLinks(document));

        return page;
    }
}
