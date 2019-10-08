package pl.witomir.webcrawler.crawler;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import pl.witomir.webcrawler.domain.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PageRepositoryTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    HtmlParser htmlParser;

    @Mock
    PageMapper pageMapper;

    @Mock
    Document document;

    @InjectMocks
    PageRepository pageRepository;

    @Test
    void fetchPage() {
        when(restTemplate.getForObject(Fixtures.URL, String.class)).thenReturn(Fixtures.PAGE_HTML);
        when(htmlParser.parse(Fixtures.PAGE_HTML)).thenReturn(document);
        when(pageMapper.mapFromDocument(Fixtures.URL, document)).thenReturn(Fixtures.PAGE);

        assertEquals(Fixtures.PAGE, pageRepository.fetchPage(Fixtures.URL));

        verify(document).setBaseUri(Fixtures.URL);
    }

    private static class Fixtures {
        private static final String URL = "url";
        private static final Page PAGE = new Page();
        private static final String PAGE_HTML = "page html";
    }
}