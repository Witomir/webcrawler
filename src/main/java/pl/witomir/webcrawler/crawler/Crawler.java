package pl.witomir.webcrawler.crawler;

import pl.witomir.webcrawler.domain.Site;
import pl.witomir.webcrawler.domain.SiteMap;

import java.util.List;

public class Crawler {
    public SiteMap generateMapStartingOn(String startingUrl) {
        List<Site> sites = List.of(new Site());
        SiteMap siteMap = new SiteMap();
        siteMap.setSites(sites);

        return siteMap;
    }
}
