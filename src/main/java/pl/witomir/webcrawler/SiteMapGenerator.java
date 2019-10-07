package pl.witomir.webcrawler;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import pl.witomir.webcrawler.console.ArgumentsParser;
import pl.witomir.webcrawler.crawler.Crawler;
import pl.witomir.webcrawler.domain.Page;
import pl.witomir.webcrawler.view.ViewModel;
import pl.witomir.webcrawler.view.ViewModelBuilder;
import pl.witomir.webcrawler.view.renderer.ConsoleRenderer;

import java.util.Set;

@Slf4j
public class SiteMapGenerator {

    private ArgumentsParser argumentsParser;
    private Crawler crawler;
    private ConsoleRenderer consoleRenderer;
    private ViewModelBuilder viewModelBuilder;

    @Inject
    public SiteMapGenerator(ArgumentsParser argumentsParser,
                            Crawler crawler,
                            ConsoleRenderer consoleRenderer,
                            ViewModelBuilder viewModelBuilder) {
        this.argumentsParser = argumentsParser;
        this.crawler = crawler;
        this.consoleRenderer = consoleRenderer;
        this.viewModelBuilder = viewModelBuilder;
    }

    public void generateSiteMap(String[] args) {
        String startingUrl = argumentsParser.parseArguments(args).getStartingUrl();

        log.info("Starting generation of site map");
        Set<Page> pageMap = crawler.getAllPagesOnTheSameDomainStartingOn(startingUrl);

        log.info("Crawling completed");
        ViewModel viewModel = viewModelBuilder.build(pageMap);

        log.info("Rendering resutls");
        consoleRenderer.renderResults(viewModel);
    }
}
