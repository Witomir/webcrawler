package pl.witomir.webcrawler.domain;

import lombok.Data;

import java.util.Set;

@Data
public class Site {
    private String url;
    private Set<Site> childPages;
    private Set<String> sameDomainLinks;
    private Set<String> externalLinks;
    private Set<String> staticContentLinks;
}
