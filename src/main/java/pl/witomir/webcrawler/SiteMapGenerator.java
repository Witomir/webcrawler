package pl.witomir.webcrawler;

import com.google.inject.Inject;
import pl.witomir.webcrawler.console.ArgumentsParser;
import pl.witomir.webcrawler.crawler.Crawler;
import pl.witomir.webcrawler.domain.SiteMap;
import pl.witomir.webcrawler.renderer.ConsoleRenderer;

public class SiteMapGenerator {

    private ArgumentsParser argumentsParser;
    private Crawler crawler;
    private ConsoleRenderer consoleRenderer;

    @Inject
    public SiteMapGenerator(ArgumentsParser argumentsParser,
                            Crawler crawler,
                            ConsoleRenderer consoleRenderer) {
        this.argumentsParser = argumentsParser;
        this.crawler = crawler;
        this.consoleRenderer = consoleRenderer;
    }

    public void generateSiteMap(String[] args) {
        String startingUrl = argumentsParser.parseArguments(args).getStartingUrl();
        SiteMap siteMap = crawler.generateSiteMapStartingOn(startingUrl);
        consoleRenderer.renderResults(siteMap);
    }
}
