package pl.witomir.webcrawler.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlParser {

    public Document parse(String html) {
        return Jsoup.parse(html);
    }
}
