package pl.witomir.webcrawler.crawler;

import com.google.inject.Inject;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.witomir.webcrawler.domain.WrongDomainException;

import java.util.HashSet;
import java.util.Set;

public class DocumentLinkResolver {

    private static final String LINK_TAG_NAME = "a";

    private static final String LINK_HREF_ATTRIBUTE = "href";
    private static final String LINK_SRC_ATTRIBUTE = "src";

    private static final String SELECTOR_FOR_IMAGES_AND_SCRIPTS = "[src]";
    private static final String SELECTOR_FOR_CSS = "link[href]";

    private UrlUtil urlUtil;

    @Inject
    public DocumentLinkResolver(UrlUtil urlUtil) {
        this.urlUtil = urlUtil;
    }

    public Set<String> getAllInternalLinks(Document document) {
        var linksList = new HashSet<String>();
        Elements links = document.getElementsByTag(LINK_TAG_NAME);

        for (var link : links) {
            try {
                String src = link.absUrl(LINK_HREF_ATTRIBUTE);
                String rootPageDomain = urlUtil.getDomainName(document.baseUri());
                String srcDomainName = urlUtil.getDomainName(src);

                if (rootPageDomain.equalsIgnoreCase(srcDomainName)) {
                    linksList.add(urlUtil.removeAnchorFromLink(src));
                }
            } catch (WrongDomainException e) {
                // broken link, ignoring
            }
        }

        return linksList;
    }

    public Set<String> getAllExternalLinks(Document document) {
        Elements links = document.getElementsByTag(LINK_TAG_NAME);
        var linksList = new HashSet<String>();

        for (var link : links) {
            try {
                String linkSrc = link.absUrl(LINK_HREF_ATTRIBUTE);
                String linkDomain = urlUtil.getDomainName(linkSrc);
                String rootPageDomain = urlUtil.getDomainName(document.baseUri());

                if (!rootPageDomain.equalsIgnoreCase(linkDomain)) {
                    linksList.add(linkSrc);
                }
            } catch (WrongDomainException e) {
                // broken link, ignoring
            }
        }

        return linksList;
    }

    public Set<String> getStaticContentLinks(Document document) {
        var linksList = new HashSet<String>();

        Elements media = document.select(SELECTOR_FOR_IMAGES_AND_SCRIPTS);
        Elements imports = document.select(SELECTOR_FOR_CSS);

        media.forEach(link -> linksList.add(link.absUrl(LINK_SRC_ATTRIBUTE)));
        imports.forEach(link -> linksList.add(link.absUrl(LINK_HREF_ATTRIBUTE)));

        return linksList;
    }
}
