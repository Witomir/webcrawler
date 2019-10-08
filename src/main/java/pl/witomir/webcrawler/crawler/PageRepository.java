package pl.witomir.webcrawler.crawler;

import com.google.inject.Inject;
import org.jsoup.nodes.Document;
import org.springframework.web.client.RestTemplate;
import pl.witomir.webcrawler.domain.Page;

public class PageRepository {
    private HtmlParser htmlParser;
    private RestTemplate restTemplate;
    private PageMapper pageMapper;

    @Inject
    public PageRepository(HtmlParser htmlParser, RestTemplate restTemplate, PageMapper pageMapper) {
        this.htmlParser = htmlParser;
        this.restTemplate = restTemplate;
        this.pageMapper = pageMapper;
    }

    public Page fetchPage(String pageUrl) {
        String pageHtml = restTemplate.getForObject(pageUrl, String.class);
        Document document = htmlParser.parse(pageHtml);
        document.setBaseUri(pageUrl);

        return pageMapper.mapFromDocument(pageUrl, document);
    }
}
