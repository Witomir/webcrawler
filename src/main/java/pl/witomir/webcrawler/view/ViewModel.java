package pl.witomir.webcrawler.view;

import lombok.Data;

import java.util.Set;

@Data
public class ViewModel {
    private Set<String> internalPages;
    private Set<String> externalPages;
    private Set<String> staticContent;
}
