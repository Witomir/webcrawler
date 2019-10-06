package pl.witomir.webcrawler.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SiteMap {
    private List<Site> sites = new ArrayList<>();

    public void addSite(Site site) {
        sites.add(site);
    }

    public boolean contains(String link) {
        return sites.contains(link);
    }
}
