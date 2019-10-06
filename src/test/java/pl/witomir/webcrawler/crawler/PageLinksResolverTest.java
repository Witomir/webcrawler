package pl.witomir.webcrawler.crawler;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import pl.witomir.webcrawler.domain.Site;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class PageLinksResolverTest {

    @InjectMocks
    PageLinksResolver pageLinksResolver;

    @Mock
    private DocumentLinkResolver documentLinkResolver;

    @Test
    void testSiteCreation() {
        Document document = new Document(Fixtures.PAGE_URL);
        when(documentLinkResolver.getAllExternalLinks(document, Fixtures.DOMAIN)).thenReturn(Set.of("external"));
        when(documentLinkResolver.getAllLinksToSameDomain(document, Fixtures.DOMAIN)).thenReturn(Set.of("same-domain"));
        when(documentLinkResolver.getStaticContentLinks(document)).thenReturn(Set.of("static"));

        Site site = pageLinksResolver.resolvePageLinks(Fixtures.PAGE_URL, document, Fixtures.DOMAIN);

        assertEquals(Fixtures.PAGE_URL, site.getUrl());
    }

    private static class Fixtures {
        static final String PAGE_URL = "page-url";
        static final String DOMAIN = "domain";
    }
}