package pl.witomir.webcrawler;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import pl.witomir.webcrawler.console.ArgumentsParser;
import pl.witomir.webcrawler.crawler.Crawler;
import pl.witomir.webcrawler.domain.Site;
import pl.witomir.webcrawler.renderer.ConsoleRenderer;

@Slf4j
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
        log.debug("Parsing program arguments");
        String startingUrl = argumentsParser.parseArguments(args).getStartingUrl();

        log.debug("Starting generation of site map");
        Site siteMap = crawler.generateSiteMapStartingOn(startingUrl);

        log.debug("Rendering resutls");
        consoleRenderer.renderResults(siteMap);
    }
}
