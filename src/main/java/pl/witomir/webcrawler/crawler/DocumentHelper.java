package pl.witomir.webcrawler.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DocumentHelper {
    public List<String> getAllLinksToSameDomain(Document document, String domain) {
        var linksList = new ArrayList<String>();
        Elements links = document.getElementsByTag("a");

        for (var link : links) {
            String src = link.absUrl("href");
            if (src.contains(domain)) {
                linksList.add(src);
            }
        }

        return linksList;
    }
}
