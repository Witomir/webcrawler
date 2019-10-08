package pl.witomir.webcrawler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.witomir.webcrawler.console.ArgumentsParser;
import pl.witomir.webcrawler.console.Options;
import pl.witomir.webcrawler.crawler.Crawler;
import pl.witomir.webcrawler.domain.Page;
import pl.witomir.webcrawler.view.ViewModel;
import pl.witomir.webcrawler.view.ViewModelBuilder;
import pl.witomir.webcrawler.view.renderer.ConsoleRenderer;

import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PageMapGeneratorUnitTest {

    @Mock
    private ArgumentsParser argumentsParser;

    @Mock
    private ConsoleRenderer consoleRenderer;

    @Mock
    private ViewModelBuilder viewModelBuilder;

    @Mock
    private Crawler crawler;

    @Mock
    private Options options;

    @InjectMocks
    private SiteMapGenerator siteMapGenerator;

    @Test
    void generateSiteMap() {
        String startingUrl = "test args";
        Set<Page> pageMap = Set.of(new Page());
        var args = new String[]{startingUrl};
        ViewModel viewModel = new ViewModel();

        when(argumentsParser.parseArguments(args)).thenReturn(options);
        when(options.getStartingUrl()).thenReturn(startingUrl);
        when(crawler.getAllPagesOnTheSameDomainStartingOn(startingUrl)).thenReturn(pageMap);
        when(viewModelBuilder.build(pageMap)).thenReturn(viewModel);

        siteMapGenerator.generateSiteMap(args);

        verify(consoleRenderer).renderResults(viewModel);
    }
}