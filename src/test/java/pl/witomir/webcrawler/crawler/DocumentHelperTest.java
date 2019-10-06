package pl.witomir.webcrawler.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DocumentHelperTest {

    @InjectMocks
    DocumentHelper documentHelper;

    @Test
    void getAllLinksToSameDomain() {
        Document document = getSampleDocument();

        List<String> allLinksToSameDomain = documentHelper.getAllLinksToSameDomain(document, "example.pl");

        assertEquals(List.of(Fixtures.SEARCH_PAGE, Fixtures.ABOUT_PAGE), allLinksToSameDomain);
    }

    private Document getSampleDocument() {
        Document document = new Document("http://www.example.pl");

        addElementToDocument(document, Fixtures.SEARCH_PAGE);
        addElementToDocument(document, Fixtures.ABOUT_PAGE);
        addElementToDocument(document, Fixtures.EXTERNAL_CONTACT_PAGE);

        return document;
    }

    private void addElementToDocument(Document document, String url) {
        Element element = new Element("a");
        element.attr("href", url);
        document.appendChild(element);
    }

    private static class Fixtures {
        private static final String ABOUT_PAGE = "http://www.example.pl/about";
        private static final String SEARCH_PAGE = "http://www.example.pl/search";
        private static final String EXTERNAL_CONTACT_PAGE = "http://www.example.com/contact";
    }
}