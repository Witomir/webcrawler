package pl.witomir.webcrawler.crawler;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.witomir.webcrawler.domain.Page;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PageMapperTest {

    @InjectMocks
    PageMapper pageMapper;

    @Mock
    private DocumentLinkResolver documentLinkResolver;

    @Test
    void testSiteCreation() {
        Document document = new Document(Fixtures.PAGE_URL);
        when(documentLinkResolver.getAllExternalLinks(document)).thenReturn(Set.of("external"));
        when(documentLinkResolver.getAllInternalLinks(document)).thenReturn(Set.of("same-domain"));
        when(documentLinkResolver.getStaticContentLinks(document)).thenReturn(Set.of("static"));

        Page page = pageMapper.mapFromDocument(Fixtures.PAGE_URL, document);

        assertEquals(Fixtures.PAGE_URL, page.getUrl());
    }

    private static class Fixtures {
        static final String PAGE_URL = "page-url";
    }
}