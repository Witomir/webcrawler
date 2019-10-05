package pl.witomir.webcrawler.crawler;

import static org.junit.jupiter.api.Assertions.*;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class PageRepositoryTest {

    private static final String URL = "url";
    private static final String PAGE_HTML = "page html";

    @Mock
    RestTemplate restTemplate;

    @Mock
    HtmlParser htmlParser;

    @Mock
    Document document;

    @InjectMocks
    PageRepository pageRepository;

    @Test
    void fetchPage() {
        when(restTemplate.getForObject(URL, String.class)).thenReturn(PAGE_HTML);
        when(htmlParser.parse(PAGE_HTML)).thenReturn(document);

        assertEquals(document, pageRepository.fetchPage(URL));
    }
}