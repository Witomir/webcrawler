package pl.witomir.webcrawler.crawler;

import static org.junit.jupiter.api.Assertions.*;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.mock;

import org.mockito.InjectMocks;

@ExtendWith(MockitoExtension.class)
class HtmlParserTest {
    private final static String TEST_ID = "test-id";

    @InjectMocks
    HtmlParser htmlParser;

    @Test
    public void parseTest() {
        String html = String.format("<html><body><p id=\"%s\">Parsed HTML</p></body></html>", TEST_ID);

        Document doc = htmlParser.parse(html);

        assertEquals("Parsed HTML", doc.getElementById(TEST_ID).text());
    }
}