package pl.witomir.webcrawler.renderer;

import pl.witomir.webcrawler.domain.SiteMap;

public class ConsoleRenderer {
    public void renderResults (SiteMap siteMap) {
        siteMap.getSites().forEach(site -> System.out.println(site.getUrl()));
    }
}
