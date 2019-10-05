package pl.witomir.webcrawler;

import com.google.inject.Guice;

public class Main {
    public static void main(String[] args) {
        Guice
                .createInjector(new DiModule())
                .getInstance(SiteMapGenerator.class)
                .generateSiteMap(args);
    }
}
