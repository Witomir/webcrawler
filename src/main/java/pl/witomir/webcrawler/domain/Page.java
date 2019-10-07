package pl.witomir.webcrawler.domain;

import lombok.Data;

import java.util.Set;

@Data
public class Page {
    private String url;
    private Set<String> internalLinks;
    private Set<String> externalLinks;
    private Set<String> staticContentLinks;
}
