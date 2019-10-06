package pl.witomir.webcrawler.crawler;

import com.google.inject.Inject;
import org.jsoup.nodes.Document;
import org.springframework.web.client.RestTemplate;

public class PageRepository {
    private HtmlParser htmlParser;
    private RestTemplate restTemplate;

    @Inject
    public PageRepository(HtmlParser htmlParser, RestTemplate restTemplate) {
        this.htmlParser = htmlParser;
        this.restTemplate = restTemplate;
    }

    public Document fetchPage(String pageUrl) {
        String pageHtml = restTemplate.getForObject(pageUrl, String.class);
        Document document = htmlParser.parse(pageHtml);
        document.setBaseUri(pageUrl);

        return document;
    }
}
