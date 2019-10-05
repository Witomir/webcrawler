package pl.witomir.webcrawler;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args){
        Injector injector = Guice.createInjector(new DiModule());
        Crawler crawler = injector.getInstance(Crawler.class);
        crawler.generateSiteMap(args);
    }
}
