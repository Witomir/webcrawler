package pl.witomir.webcrawler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.witomir.webcrawler.console.Arguments;
import pl.witomir.webcrawler.console.ArgumentsParser;
import pl.witomir.webcrawler.crawler.Crawler;
import pl.witomir.webcrawler.domain.SiteMap;
import pl.witomir.webcrawler.renderer.ConsoleRenderer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SiteMapGeneratorTest {

    @Mock
    private ArgumentsParser argumentsParser;

    @Mock
    private ConsoleRenderer consoleRenderer;

    @Mock
    private Crawler crawler;

    @Mock
    private Arguments arguments;

    @InjectMocks
    private SiteMapGenerator siteMapGenerator;

    @Test
    void generateSiteMap() {
        String startingUrl = "test args";
        SiteMap siteMap = new SiteMap();
        var args = new String[]{startingUrl};

        when(argumentsParser.parseArguments(args)).thenReturn(arguments);
        when(arguments.getStartingUrl()).thenReturn(startingUrl);
        when(crawler.generateMapStartingOn(startingUrl)).thenReturn(siteMap);

        siteMapGenerator.generateSiteMap(args);

        verify(consoleRenderer).renderResults(siteMap);
    }
}