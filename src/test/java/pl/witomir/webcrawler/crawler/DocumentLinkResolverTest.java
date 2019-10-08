package pl.witomir.webcrawler.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentLinkResolverTest {


    @InjectMocks
    DocumentLinkResolver documentLinkResolver;

    @Mock
    private UrlUtil urlUtil;

    @Test
    void getAllLinksToSameDomain() {
        Document document = getSampleDocument();
        Set<String> expectedLinks = Set.of(Fixtures.SEARCH_PAGE, Fixtures.ABOUT_PAGE);
        when(urlUtil.getDomainName(Fixtures.SEARCH_PAGE)).thenReturn(Fixtures.DOMAIN);
        when(urlUtil.getDomainName(Fixtures.ABOUT_PAGE)).thenReturn(Fixtures.DOMAIN);
        when(urlUtil.getDomainName(Fixtures.FULL_DOMAIN_URL)).thenReturn(Fixtures.DOMAIN);
        when(urlUtil.removeAnchorFromLink(any())).thenCallRealMethod();

        Set<String> allLinksToSameDomain = documentLinkResolver.getAllInternalLinks(document);

        assertEquals(expectedLinks, allLinksToSameDomain);
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void getAllExternalLinks() {
        Document document = getSampleDocument();
        Set<String> expectedLinks = Set.of(Fixtures.EXTERNAL_CONTACT_PAGE);
        when(urlUtil.getDomainName(anyString())).thenReturn(Fixtures.DOMAIN);
        when(urlUtil.getDomainName(Fixtures.EXTERNAL_CONTACT_PAGE)).thenReturn("external");
        when(urlUtil.getDomainName(Fixtures.FULL_DOMAIN_URL)).thenReturn(Fixtures.DOMAIN);

        Set<String> allLinksToSameDomain = documentLinkResolver.getAllExternalLinks(document);

        assertEquals(expectedLinks, allLinksToSameDomain);
    }

    @Test
    void getAllStaticContentLinks() {
        Document document = getSampleDocument();
        Set<String> expectedLinks = Set.of(Fixtures.STATIC_JS, Fixtures.STATIC_CSS, Fixtures.STATIC_IMAGE);

        Set<String> allLinksToSameDomain = documentLinkResolver.getStaticContentLinks(document);

        assertEquals(expectedLinks, allLinksToSameDomain);
    }

    private Document getSampleDocument() {
        Document document = new Document(Fixtures.FULL_DOMAIN_URL);

        document.appendChild((new Element("a")).attr("href", Fixtures.SEARCH_PAGE));
        document.appendChild((new Element("a")).attr("href", Fixtures.ABOUT_PAGE));
        document.appendChild((new Element("a")).attr("href", Fixtures.EXTERNAL_CONTACT_PAGE));
        document.appendChild((new Element("script")).attr("src", Fixtures.STATIC_JS));
        document.appendChild((new Element("link")).attr("href", Fixtures.STATIC_CSS));
        document.appendChild((new Element("img")).attr("src", Fixtures.STATIC_IMAGE));

        return document;
    }

    private static class Fixtures {
        private static final String DOMAIN = "example.pl";
        private static final String FULL_DOMAIN_URL = "http://" + DOMAIN;
        private static final String ABOUT_PAGE = "http://www.example.pl/about";
        private static final String SEARCH_PAGE = "http://www.example.pl/search";
        private static final String EXTERNAL_CONTACT_PAGE = "http://www.external.tv/contact";
        private static final String STATIC_JS = "http://jquery.com/jquery.js";
        private static final String STATIC_CSS = "http://bootstrap.com/bootstrap.min.css";
        private static final String STATIC_IMAGE = "http://www.imgur/a/image.jpg";
    }
}