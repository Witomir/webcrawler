package pl.witomir.webcrawler.domain;

import lombok.Data;

import java.util.List;

@Data
public class SiteMap {
    private List<Site> sites;
}
