package pl.witomir.webcrawler.crawler;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HtmlParserTest {

    private final static String TEST_ID = "test-id";
    private static final String HTML_TEMPLATE = "<html><body><p id=\"%s\">%s</p></body></html>";
    private static final String TEXT_CONTENT = "Parsed HTML";

    private static final String HTML = String.format(HTML_TEMPLATE, TEST_ID, TEXT_CONTENT);

    @InjectMocks
    HtmlParser htmlParser;

    @Test
    void parseTest() {
        Document doc = htmlParser.parse(HTML);

        assertEquals(TEXT_CONTENT, doc.getElementById(TEST_ID).text());
    }
}